package todo.services;

import todo.entities.Todo;

import java.util.List;
import java.util.Optional;

public interface ITodoService {
    void createTodo(Todo todo);

    List<Todo> getTodos();

    Optional<Todo> getTodo(Long id);

    List<Todo> queryByTodo(String todoQuery);
}
