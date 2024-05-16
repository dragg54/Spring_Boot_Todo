package todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import todo.repositories.UserRepo;

public class UserDetailsServiceImp implements  UserDetailsService {
    @Autowired
    private final UserRepo repo;
    public UserDetailsServiceImp(UserRepo repo){
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUserName(username);
    }
}
