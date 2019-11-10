package by.pisl.lab3.repository;

import by.pisl.lab3.repository.annotation.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @version 1.0.0
 */
@Repository(clazz = "by.pisl.lab3.entity.Answer")
public class AnswerRepository<Answer> extends AbstractRepository<Answer> {
    private static final String SELECT_BY_QUESTION_ID = "select * from {tableName} where question_id = ?";

    public AnswerRepository(String target) {
        super(target);
    }

    public List<Answer> findByQuestionId(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.getConnectionPool().takeConnection();
            stmt = getPreparedStatement(conn, SELECT_BY_QUESTION_ID);

            stmt.setLong(1, id);

            return this.getPopulator().populateAll(execute(stmt));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getConnectionPool().closeConnection(conn, stmt);
        }

        return Collections.emptyList();
    }
}
