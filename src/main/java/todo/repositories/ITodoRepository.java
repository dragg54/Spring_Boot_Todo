package todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import todo.entities.Todo;

import java.util.List;

@Repository
public interface ITodoRepository  extends JpaRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t WHERE t.todo LIKE %:todoQuery%")
 public List<Todo> findByTodo(@Param("todoQuery") String todoQuery);
}
