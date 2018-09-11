package dao;

import api.UserDao;
import entity.Product;
import entity.User;
import entity.parser.UserParser;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private String fileName;

    public UserDaoImpl(String fileName) throws IOException {
        this.fileName=fileName;
        FileUtils.createNewFile(fileName);
    }

    public void saveUser(User user) throws IOException {
        List<User> users = getAllUsers();
        users.add(user);
        saveUsers(users);
    }

    public void saveUsers(List<User> users) throws FileNotFoundException {
        FileUtils.clearFile(fileName);
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true));
        for(User user:users){
            pw.write(user.toString() + "\n");
        }
        pw.close();
    }

    public List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<User>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String readLine = br.readLine();
        while(readLine != null){
            User user = UserParser.stringToUser(readLine);
            users.add(user);
            readLine = br.readLine();
        }
        br.close();

        return null;
    }

    public User getUserByLogin(String login) throws IOException {
        List<User> users = getAllUsers();

        for(User user:users){
            if(user.getLogin().equals(login)){
                return user;
            }
        }
        return null;
    }

    public User getUserById(Integer id) throws IOException {
        List<User> users = getAllUsers();

        for(User user:users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public void removeUserByLogin(String login) throws IOException {
        List<User> users = getAllUsers();

        for(User user:users){
            if(user.getLogin().equals(login)){
                users.remove(user);
            }
        }
    }

    public void removeUserById(Integer id) throws IOException {
        List<User> users = getAllUsers();

        for(User user:users){
            if(user.getId().equals(id)){
                users.remove(user);
            }
        }
    }
}
