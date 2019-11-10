package by.pisl.lab3.service.listener;

import by.pisl.lab3.controller.command.SimpleCommandStrategy;
import by.pisl.lab3.repository.ConnectionPool;
import by.pisl.lab3.repository.SimpleRepositoryStore;
import by.pisl.lab3.repository.exception.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initializeConnectionPool();
        System.out.println("connection");
        SimpleRepositoryStore.getInstance();
        System.out.println("repo");
        SimpleCommandStrategy.getInstance();
        System.out.println("command");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        closeConnectionPool();
    }

    private void initializeConnectionPool() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    private void closeConnectionPool() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.dispose();
    }
}