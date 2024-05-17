package todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.entities.UserDtl;

@Repository
public interface UserRepo extends JpaRepository<UserDtl, Long> {
    public UserDtl findByUserName(String userName);
}
