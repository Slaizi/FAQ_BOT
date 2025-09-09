package ru.bogachev.node_service.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import ru.bogachev.node_service.command.handlers.Command;
import ru.bogachev.node_service.command.handlers.StartCommand;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BotCommandRegister {

    public interface BotCmdActions {
        String getValue();
        Command getCommandHandler(ApplicationContext context) throws BeansException;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum FirstMonitorCommands implements BotCmdActions {

        START("/start", StartCommand.class);

        private final String value;
        private final Class<? extends Command> commandHandler;

        public Command getCommandHandler(ApplicationContext context) throws BeansException {
            return context.getBean(commandHandler);
        }
    }

}
