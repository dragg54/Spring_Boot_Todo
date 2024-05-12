package todo.services;

import todo.entities.Todo;
import todo.exceptions.TodoNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ITodoService {
    void createTodo(Todo todo);

    List<Todo> getTodos();

    Optional<Todo> getTodo(Long id) throws TodoNotFoundException;

    List<Todo> queryByTodo(String todoQuery);
}
