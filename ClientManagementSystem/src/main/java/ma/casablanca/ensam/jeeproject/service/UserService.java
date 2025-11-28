package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.User;
import ma.casablanca.ensam.jeeproject.dto.UserDto;
import ma.casablanca.ensam.jeeproject.dto.UserRegistrationDto;

import java.util.List;

public interface UserService {
    public UserDto register(UserRegistrationDto userRegistrationDto);
    public List<User> findUserByEmail(String email);
    public User findUserByUsername(String username);
}
