package com.naitik.journalapp.repository;

import com.naitik.journalapp.entity.RedisUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private RedisTemplate<String, RedisUser> redisTemplate;

    private static final String KEY = "USER123";

    public RedisUser save(RedisUser user) {
        redisTemplate.opsForHash().put(KEY, user.getUserID(), user);
        return user;
    }

    public RedisUser findById(String userId) {
        return (RedisUser) redisTemplate.opsForHash().get(KEY, userId);
    }

    public void delete(String userId) {
        redisTemplate.opsForHash().delete(KEY, userId);
    }

    public List<RedisUser> findAll() {
        return redisTemplate.opsForHash().values(KEY).stream()
                .map(user -> (RedisUser) user)
                .collect(Collectors.toList());
    }

}
