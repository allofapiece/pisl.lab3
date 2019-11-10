package by.pisl.lab3.repository;

import by.pisl.lab3.repository.annotation.Repository;

/**
 * @version 1.0.0
 */
@Repository(clazz = "by.pisl.lab3.entity.Question")
public class QuestionRepository<Question> extends AbstractRepository<Question> {
    public QuestionRepository(String target) {
        super(target);
    }
}
