package by.pisl.lab3.controller.command;

import by.pisl.lab3.controller.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0.0
 */
public interface Command {
    Page execute(HttpServletRequest req, HttpServletResponse res);
}
