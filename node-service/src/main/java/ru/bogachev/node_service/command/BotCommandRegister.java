package ru.bogachev.node_service.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import ru.bogachev.node_service.command.handlers.Command;
import ru.bogachev.node_service.command.handlers.StartCommand;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BotCommandRegister {

    @Getter
    @AllArgsConstructor
    public enum FirstMonitorCommands {

        START("/start", StartCommand.class);

        private final String value;
        private final Class<? extends Command> commandHandler;

        public Command getCommandHandler(ApplicationContext context) {
            return context.getBean(commandHandler);
        }
    }

}
