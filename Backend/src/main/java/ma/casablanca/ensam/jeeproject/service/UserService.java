package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.User;

public interface UserService {
    public User register(User user);
    public User findUserByEmail(String email);
    public User findUserByUsername(String username);
}
