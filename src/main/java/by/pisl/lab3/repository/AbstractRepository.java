package by.pisl.lab3.repository;

import by.pisl.lab3.repository.populator.EntityPopulator;
import by.pisl.lab3.repository.populator.SimpleEntityPopulator;
import by.pisl.lab3.repository.preprocess.QueryPreprocess;
import by.pisl.lab3.repository.preprocess.TableQueryPreprocess;
import by.pisl.lab3.service.util.ReflUtils;
import lombok.Data;

import java.beans.IntrospectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @version 1.0.0
 */
@Data
public abstract class AbstractRepository<T> implements Repository<T> {
    protected String SELECT_ALL = "select * from {tableName}";
    protected String SELECT_BY_ID_QUERY = "select * from {tableName} where id = ?";
    protected String SELECT_RAND = "select * from {tableName} order by rand() limit 1";
    protected String INSERT_INTO = "insert into {tableName}({fields}) values ({values})";

    private List<QueryPreprocess> queryPreprocessors = new LinkedList<>();

    private EntityPopulator<T> populator;

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private String tableName;

    private Class target;

    private AbstractRepository() {
        queryPreprocessors.add(new TableQueryPreprocess(extractTableName(this.getClass().getName())));
    }

    public AbstractRepository(String target) {
        this();

        try {
            this.target = Class.forName(target);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        populator = new SimpleEntityPopulator<>(this.target);
    }

    public AbstractRepository(String target, EntityPopulator<T> populator) {
        this(target);
        this.populator = populator;
    }

    protected String extractTableName(String className) {
        return className.substring(className.lastIndexOf(".") + 1, className.indexOf("Repository")).toLowerCase();
    }

    @Override
    public Optional<T> findById(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connectionPool.takeConnection();
            stmt = getPreparedStatement(conn, SELECT_BY_ID_QUERY);

            stmt.setLong(1, id);

            return Optional.of(retrieve(stmt));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.closeConnection(conn, stmt);
        }

        return Optional.empty();
    }

    @Override
    public Optional<T> findRandom() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connectionPool.takeConnection();
            stmt = getPreparedStatement(conn, SELECT_RAND);

            return Optional.of(retrieve(stmt));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.closeConnection(conn, stmt);
        }

        return Optional.empty();
    }

    @Override
    public T save(T entity) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = connectionPool.takeConnection();
            stmt = populator.prepare(entity, preprocessQuery(INSERT_INTO), conn);

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();

            if (rs != null && rs.next()) {
                var pd = ReflUtils.getPropertyDescriptor(entity.getClass(), "id");
                pd.getWriteMethod().invoke(entity, rs.getLong(1));
            }

            return entity;
        } catch (SQLException | IntrospectionException | ReflectiveOperationException e) {
            e.printStackTrace();
        } catch (Error er) {
            er.printStackTrace();
        } finally {
            connectionPool.closeConnection(conn, stmt);
        }

        return entity;
    }

    public List<T> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connectionPool.takeConnection();
            stmt = getPreparedStatement(conn, SELECT_ALL);

            return this.populator.populateAll(execute(stmt));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.closeConnection(conn, stmt);
        }

        return Collections.emptyList();
    }

    protected PreparedStatement getPreparedStatement(Connection conn, String query) {
        try {
            return conn.prepareStatement(preprocessQuery(query));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected String preprocessQuery(String query) {
        for (QueryPreprocess queryPreprocess : this.queryPreprocessors) {
            query = queryPreprocess.preprocess(query);
        }

        return query;
    }

    protected T retrieve(PreparedStatement stmt) throws SQLException {
        return this.populator.populate(execute(stmt));
    }

    protected ResultSet execute(PreparedStatement stmt) throws SQLException {
        return stmt.executeQuery();
    }
}
