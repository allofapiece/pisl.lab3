package by.pisl.lab3.controller.command.impl;

import by.pisl.lab3.controller.Page;
import by.pisl.lab3.controller.command.Command;
import by.pisl.lab3.entity.Answer;
import by.pisl.lab3.entity.Question;
import by.pisl.lab3.entity.QuestionWrap;
import by.pisl.lab3.repository.SimpleRepositoryStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0.0
 */
@by.pisl.lab3.controller.command.annotation.Command(command = "show-questions")
public class ShowQuestionsCommand implements Command {
    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse res) {
        var questions = SimpleRepositoryStore.getInstance().get(Question.class).findAll();
        var answers = SimpleRepositoryStore.getInstance().get(Answer.class).findAll();

        List<QuestionWrap> wraps = new ArrayList<>();

        questions.stream().forEach(question -> {
            var ansList = ((List<Answer>)answers).stream()
                    .filter(answer -> ((Question) question).getId().equals(((Answer) answer).getQuestionId()))
                    .collect(Collectors.toList());

            wraps.add(QuestionWrap.builder()
                    .question((Question)question)
                    .stat1(calcStat(ansList, 1))
                    .stat2(calcStat(ansList, 2))
                    .stat3(calcStat(ansList, 3)).build());
        });

        req.setAttribute("wraps", wraps);

        return Page.builder()
                .page("jsp/pages/showQuestions.jsp")
                .redirect(false)
                .build();
    }

    public float calcStat(List<Answer> list, int i) {
        if (list.isEmpty()) {
            return 0;
        }

        return Math.round((float)list.stream().filter(answer -> answer.getOption() == i).count() / list.size() * 100);
    }
}
