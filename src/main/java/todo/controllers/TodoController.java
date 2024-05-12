package todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.entities.Todo;
import todo.services.ITodoService;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private ITodoService todoService;

    @PostMapping("todo")
    public void createTodo(@RequestBody Todo todo){
        todoService.createTodo(todo);
    }

    @GetMapping("todos")
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }
    @GetMapping("todo/{id}")
    public Optional<Todo> getTodo(@PathVariable ("id") Long todoId){
        return todoService.getTodo(todoId);
    }

    @GetMapping("todo")
    public ResponseEntity<List<Todo>> getTodoByQuery(@RequestParam String todo){
        var result = todoService.queryByTodo(todo);
        return ResponseEntity.ok(result);
    }
}
