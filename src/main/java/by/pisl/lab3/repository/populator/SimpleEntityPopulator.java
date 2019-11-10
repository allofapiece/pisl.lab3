package by.pisl.lab3.repository.populator;

import by.pisl.lab3.service.util.InflectorUtils;
import org.apache.commons.text.StringSubstitutor;

import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @version 1.0.0
 */
public class SimpleEntityPopulator<T> implements EntityPopulator<T> {
    private Class target;

    public SimpleEntityPopulator(Class target) {
        this.target = target;
    }

    @Override
    public T populate(ResultSet resultSet) {
        T instance = null;

        try {
            if (resultSet.next()) {
                instance = this.createInstance();

                var list = getProperties();

                for (PropertyDescriptor pd : list) {
                    populateField(instance, pd, resultSet);
                }
            }
        } catch (IntrospectionException | SQLException | ReflectiveOperationException e) {
            e.printStackTrace();
        }

        return instance;
    }

    @Override
    public List<T> populateAll(ResultSet resultSet) {
        List<T> entities = new ArrayList<>();

        T entity;

        while ((entity = populate(resultSet)) != null) {
            entities.add(entity);
        }

        return entities;
    }

    public PreparedStatement prepare(T entity, String query, Connection conn) throws IntrospectionException, SQLException, InvocationTargetException, IllegalAccessException {
        query = StringSubstitutor.replace(query, Map.of(
                "fields", fields(),
                "values", queryValues()
        ), "{", "}");
        var stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

        var list = getProperties(true);

        for (var i = 0; i < list.size(); i++) {
            stmt.setObject(i + 1, list.get(i).getReadMethod().invoke(entity));
        }

        return stmt;
    }

    public String fields(boolean withoutId) throws IntrospectionException {
        return getPropertiesAsStream(withoutId)
                .map(pd -> "`" + InflectorUtils.camel2Underscore(pd.getName()) + "`")
                .collect(Collectors.joining(","));
    }

    public String fields() throws IntrospectionException {
        return fields(true);
    }

    public String queryValues() throws IntrospectionException {
        return getPropertiesAsStream(true)
                .map(pd -> "?")
                .collect(Collectors.joining(","));
    }

    public List<PropertyDescriptor> getProperties() throws IntrospectionException {
        return getPropertiesAsStream(false).collect(Collectors.toList());
    }

    public List<PropertyDescriptor> getProperties(boolean withoutId) throws IntrospectionException {
        return getPropertiesAsStream(withoutId).collect(Collectors.toList());
    }

    public Stream<PropertyDescriptor> getPropertiesAsStream(boolean withoutId) throws IntrospectionException {
        var stream = Arrays.stream(Introspector.getBeanInfo(this.target).getPropertyDescriptors());

        if (withoutId) {
            stream = stream.filter(pd -> !"id".equals(pd.getName()));
        }

        return stream.filter(pd -> !pd.getName().equals("class"));
    }

    @SuppressWarnings("all")
    protected T createInstance() {
        T instance = null;

        try {
            instance = (T) this.target.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        return instance;
    }

    protected Class getGenericType() {
        return (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected void populateField(T instance, PropertyDescriptor field, ResultSet rs) throws SQLException, InvocationTargetException, IllegalAccessException {
        try {
            field.getWriteMethod()
                    .invoke(
                            instance,
                            rs.getObject(InflectorUtils.camel2Underscore(field.getName()), field.getPropertyType())
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
