package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.User;
import ma.casablanca.ensam.jeeproject.dao.repositories.UserRepository;
import ma.casablanca.ensam.jeeproject.dto.UserDto;
import ma.casablanca.ensam.jeeproject.dto.UserRegistrationDto;
import ma.casablanca.ensam.jeeproject.mapper.UserMapper;
import ma.casablanca.ensam.jeeproject.mapper.UserRegistrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserManager implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRegistrationMapper userRegistrationMapper ;
    @Autowired
    private UserMapper userMapper ;

    @Override
    public UserDto register(UserRegistrationDto userRegistDto) {
        User user = userRegistrationMapper.toEntity(userRegistDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
