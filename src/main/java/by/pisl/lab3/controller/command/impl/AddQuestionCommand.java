package by.pisl.lab3.controller.command.impl;

import by.pisl.lab3.controller.Page;
import by.pisl.lab3.controller.command.Command;
import by.pisl.lab3.entity.Question;
import by.pisl.lab3.repository.SimpleRepositoryStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0.0
 */
@by.pisl.lab3.controller.command.annotation.Command(command = "add-question")
public class AddQuestionCommand implements Command {
    @Override
    @SuppressWarnings("all")
    public Page execute(HttpServletRequest req, HttpServletResponse res) {
        Question question = Question.builder()
                .question(req.getParameter("question"))
                .option1(req.getParameter("option1"))
                .option2(req.getParameter("option2"))
                .option3(req.getParameter("option3"))
                .build();

        SimpleRepositoryStore.getInstance().get(Question.class).save(question);

        return Page.builder()
                .page("/fc?command=show-questions")
                .redirect(true)
                .build();
    }
}
