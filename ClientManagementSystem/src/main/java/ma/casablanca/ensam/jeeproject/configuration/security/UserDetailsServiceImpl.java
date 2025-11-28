package ma.casablanca.ensam.jeeproject.configuration.security;

import ma.casablanca.ensam.jeeproject.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String login;
        String password;
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<ma.casablanca.ensam.jeeproject.dao.entities.User> users = userRepository.findByEmail(username);
        if(users.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        password = users.get(0).getPassword();
        login = users.get(0).getUsername();
        authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));
        return new User(login , password , authorities);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
