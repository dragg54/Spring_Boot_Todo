package todo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import todo.entities.Todo;
import todo.exceptions.TodoNotFoundException;
import todo.repositories.ITodoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoServiceTest {
    @InjectMocks
    private TodoService todoService;
    @Mock
    private ITodoRepository todoRepository;
    private Todo todo;

    @BeforeEach
    void setUp()  {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void When_Get_Todo_Is_Invoked_Then_Should_Return_A_Todo() throws TodoNotFoundException {
        Todo expectedTodo =  Todo.builder()
                .todoId(1L)
                .todo("I want to wash my cloths")
                .build();
        expectedTodo.setTodoId(1L);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(expectedTodo));

        // Call the service method
        Optional<Todo> result = todoService.getTodo(1L);

        // Verify that the returned Todo matches the expected Todo
        assertEquals(expectedTodo, result.get());
    }
}