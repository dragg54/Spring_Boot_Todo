package todo.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todo.entities.AuthorizationResponse;
import todo.repositories.UserRepo;
import todo.entities.UserDtl;

@Data
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthorizationResponse register(UserDtl user){
        System.out.println("user = " + user);
        var newUser = UserDtl
                .builder()
                .userName(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();
        userRepo.save(newUser);
        String token = jwtService.generateToken(newUser);
        return AuthorizationResponse.builder().token(token).build();
    }

    @Override
    public AuthorizationResponse login(UserDtl user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDtl userDtl = userRepo.findByUserName(user.getUsername());
        String token = jwtService.generateToken(user);
        return AuthorizationResponse.builder().token(token).build();
    }
}
