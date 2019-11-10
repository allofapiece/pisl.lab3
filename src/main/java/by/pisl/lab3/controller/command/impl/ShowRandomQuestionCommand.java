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
@by.pisl.lab3.controller.command.annotation.Command(command = "show-random")
public class ShowRandomQuestionCommand implements Command {
    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse res) {
        req.setAttribute("question", SimpleRepositoryStore.getInstance().get(Question.class).findRandom().get());

        return Page.builder()
                .page("jsp/pages/question.jsp")
                .redirect(false)
                .build();
    }
}
