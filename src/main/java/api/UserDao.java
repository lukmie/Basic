package api;

import entity.Product;
import entity.User;

import java.io.IOException;
import java.util.List;

public interface UserDao {

    void saveUser(User user) throws IOException;
    void saveUsers(List<User> users) throws IOException;

    void removeUserByLogin(String login) throws IOException;
    void removeUserById(Integer id) throws IOException;

    List<User> getAllUsers() throws IOException;

}
