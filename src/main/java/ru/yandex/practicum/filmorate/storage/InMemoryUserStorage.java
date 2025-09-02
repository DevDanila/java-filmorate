package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class InMemoryUserStorage {
    private final Map<Long, User> userStorage = new HashMap<>();
    private long id = 0L;

    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }

    public void createUser(User user) {
        if (user.getId() != null) {
            throw new ValidationException("При создании пользователя ID должен быть null");
        }
        long newId = ++id;
        user.setId(newId);
        userStorage.put(newId, user);
    }

    public void updateUser(User user) {
        if (user.getId() == null) {
            throw new ValidationException("ID пользователя не может быть null при обновлении");
        }
        if (userStorage.containsKey(user.getId())) {
            userStorage.put(user.getId(), user);
        } else {
            throw new NotFoundException("Обновляемого пользователя не найдено");
        }
    }
}