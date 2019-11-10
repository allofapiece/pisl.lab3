package by.pisl.lab3.controller.command.impl;

import by.pisl.lab3.controller.Page;
import by.pisl.lab3.controller.command.Command;
import by.pisl.lab3.entity.Answer;
import by.pisl.lab3.entity.Question;
import by.pisl.lab3.repository.SimpleRepositoryStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0.0
 */
@by.pisl.lab3.controller.command.annotation.Command(command = "answer")
public class AnswerCommand implements Command {
    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse res) {
        var answer = Answer.builder()
                .option(Integer.parseInt(req.getParameter("option")))
                .questionId(Long.parseLong(req.getParameter("question_id")))
                .build();

        SimpleRepositoryStore.getInstance().get(Answer.class).save(answer);

        return Page.builder()
                .page("/fc?command=show-random")
                .redirect(true)
                .build();
    }
}
