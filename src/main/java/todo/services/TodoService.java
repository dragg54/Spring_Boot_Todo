package todo.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.entities.Todo;
import todo.exceptions.TodoNotFoundException;
import todo.repositories.ITodoRepository;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class TodoService implements ITodoService{
    @Autowired
    private  ITodoRepository repository;

    public void createTodo(Todo todo){
        repository.save(todo);
    }

    public List<Todo> getTodos(){
        return repository.findAll();
    }

    public Optional<Todo> getTodo(Long id) throws TodoNotFoundException {
        var todo = repository.findById(id);
        if(!todo.isPresent()){
            throw new TodoNotFoundException(String.format("Todo with id %d cannot be found",id));
        }
        return todo;
    }

    public List<Todo> queryByTodo(String todoQuery){
        return repository.findByTodo(todoQuery);
    }
}
