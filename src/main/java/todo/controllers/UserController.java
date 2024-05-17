package todo.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todo.entities.UserDtl;
import todo.services.UserService;
import todo.services.UserServiceImpl;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody  UserDtl request){
        var token = userService.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  UserDtl request){
        var token = userService.login(request);
        return ResponseEntity.ok(token);
    }
}
