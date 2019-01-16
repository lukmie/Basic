package dao;

import api.UserDao;
import entity.User;
import entity.parser.UserParser;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private String fileName = "users.data";

    private static UserDaoImpl instance = null;

    private UserDaoImpl() {
        try {
            FileUtils.createNewFile(fileName);
        } catch (IOException e) {
            System.out.println("File path error.");
            System.exit(-1);
        }
    }

    public static UserDaoImpl getInstance(){
        if(instance == null){
            instance = new UserDaoImpl();
        }
        return instance;
    }

    public void saveUser(User user) throws IOException {
        List<User> users = getAllUsers();
        users.add(user);
        saveUsers(users);
    }

    public void saveUsers(List<User> users) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileName, true));
        for(User user : users) {
            printWriter.write(user.toString() + "\n");
        }
        printWriter.close();
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

    public List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<User>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String readLine = br.readLine();
        while(readLine != null){
            User user = UserParser.stringToUser(readLine);
            users.add(user);
            readLine = br.readLine();
        }

        return users;
    }

}
