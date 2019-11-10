package by.pisl.lab3.controller.command;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0.0
 */
public class SimpleCommandStrategy implements CommandStrategy {
    private Map<String, Command> map = new HashMap<>();

    private static volatile CommandStrategy instance;

    private SimpleCommandStrategy() {}

    public static CommandStrategy getInstance() {
        if (instance == null) {
            synchronized (SimpleCommandStrategy.class) {
                if (instance == null) {
                    instance = new SimpleCommandStrategy().init();
                }
            }
        }

        return instance;
    }

    protected CommandStrategy init() {
        try {
            Reflections reflections = new Reflections("by.pisl.lab3.controller.command");
            Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(by.pisl.lab3.controller.command.annotation.Command.class);

            for (Class<?> command : annotated) {
                String commandName = command.getAnnotation(by.pisl.lab3.controller.command.annotation.Command.class).command();
                map.put(commandName, (Command) command.getConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error er) {
            er.printStackTrace();
        }

        return this;
    }

    @Override
    public Command get(String command) {
        if (!map.containsKey(command)) {
            throw new RuntimeException("Incorrect command");
        }

        return map.get(command);
    }
}
