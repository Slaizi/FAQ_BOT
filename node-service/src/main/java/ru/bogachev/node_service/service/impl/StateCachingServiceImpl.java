package ru.bogachev.node_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.bogachev.node_service.service.StateCachingService;
import ru.bogachev.node_service.state.BotState;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

import static ru.bogachev.node_service.state.BotState.FIRST_MONITOR;

@Service
@RequiredArgsConstructor
public class StateCachingServiceImpl implements StateCachingService {

    private static final String REDIS_STATE_KEY_TMP = "StateCachingService::user::%d";

    private final RedisTemplate<String, Object> redisTmp;

    @Override
    public void save(Long userId, Deque<BotState> state) {
        if (state.isEmpty()) state.push(FIRST_MONITOR);

        redisTmp.opsForValue()
                .set(getRedisStateKeyTmp(userId), state);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Deque<BotState> get(Long userId) {
        String redisKey = getRedisStateKeyTmp(userId);

        return Optional.ofNullable(
                (Deque<BotState>) redisTmp.opsForValue().get(redisKey)
        ).orElseGet(
                () -> {
                    Deque<BotState> usrStates = new ArrayDeque<>();
                    usrStates.push(FIRST_MONITOR);
                    save(userId, usrStates);
                    return usrStates;
                }
        );
    }

    @Override
    public void delete(Long userId) {
        redisTmp.delete(getRedisStateKeyTmp(userId));
    }

    private String getRedisStateKeyTmp(Long userId) {
        return String.format(REDIS_STATE_KEY_TMP, userId);
    }
}
