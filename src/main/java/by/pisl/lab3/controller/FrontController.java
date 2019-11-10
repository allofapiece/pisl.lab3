package by.pisl.lab3.controller;

import by.pisl.lab3.controller.command.CommandStrategy;
import by.pisl.lab3.controller.command.SimpleCommandStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private CommandStrategy commandStrategy;

    public FrontController() {
        this.commandStrategy = SimpleCommandStrategy.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");

        Page page = commandStrategy.get(command).execute(req, resp);

        if (page.isRedirect()) {
            resp.sendRedirect(this.toUrl(page, req));
        } else {
            req.getRequestDispatcher(this.toUrl(page, req, false)).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected String toUrl(Page page, HttpServletRequest req) {
        return toUrl(page, req, true);
    }

    protected String toUrl(Page page, HttpServletRequest req, boolean useContext) {
        String url = page.getPage();

        if (url.charAt(0) != '/') {
            url = '/' + url;
        }

        if (useContext) {
            url = req.getContextPath() + url;
        }

        return url;
    }
}
