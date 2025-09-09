package ru.bogachev.node_service.service;

import ru.bogachev.node_service.state.BotState;

import java.util.Deque;

public interface StateCachingService {

    void save(Long userId, Deque<BotState> state);

    Deque<BotState> get(Long userId);

    void delete(Long userId);

}
