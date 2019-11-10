package by.pisl.lab3.repository;

import by.pisl.lab3.bundle.DataBaseResourceBundle;
import by.pisl.lab3.repository.exception.DAOException;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0.0
 */
public class SimpleRepositoryStore implements RepositoryStore {
    private Map<Class, Repository> map = new HashMap<>();

    private static volatile RepositoryStore instance;

    private SimpleRepositoryStore() {}

    public static RepositoryStore getInstance() {
        if (instance == null) {
            synchronized (RepositoryStore.class) {
                if (instance == null) {
                    instance = new SimpleRepositoryStore().init();
                }
            }
        }

        return instance;
    }

    @Override
    public Repository get(Class clazz) {
        if (!map.containsKey(clazz)) {
            throw new RuntimeException("Invalid repository type");
        }

        return map.get(clazz);
    }

    protected RepositoryStore init() {
        try {
            Reflections reflections = new Reflections("by.pisl.lab3.repository");
            Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(by.pisl.lab3.repository.annotation.Repository.class);

            for (Class<?> repository : annotated) {
                String clazz = repository.getAnnotation(by.pisl.lab3.repository.annotation.Repository.class).clazz();
                map.put(Class.forName(clazz), (Repository) repository.getConstructor(String.class).newInstance(clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error er) {
            er.printStackTrace();
        }

        return this;
    }
}
