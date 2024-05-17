package todo.services;
import todo.entities.UserDtl;
import todo.entities.AuthorizationResponse;

public interface UserService {
    AuthorizationResponse register(UserDtl user);
    AuthorizationResponse login(UserDtl user);
}
