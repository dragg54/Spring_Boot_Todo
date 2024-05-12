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
import todo.entities.Todo;
import todo.exceptions.TodoNotFoundException;
import todo.repositories.ITodoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServiceTest {
    @InjectMocks
    private TodoService todoService;
    @Mock
    private ITodoRepository todoRepository;
    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = Todo.builder()
                .todoId(3L)
                .todo("I want to wash my cloths")
                .build();
        todoRepository = Mockito.mock(ITodoRepository.class);
        Mockito.when(todoRepository.findById(3L)).thenReturn(Optional.ofNullable(todo));
    }

    @Test
    public void When_Get_Todo_Is_Invoked_Then_Should_Return_A_Todo() throws TodoNotFoundException {
        Optional<Todo> result = todoService.getTodo(3L);
        assertTrue(result.isPresent());
        assertEquals(todo, result.get());    }
}