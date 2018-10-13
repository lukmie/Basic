package validator;

import api.UserDao;
import dao.UserDaoImpl;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;

import java.io.IOException;

public class UserValidator {

    private static UserValidator instance = null;

    private final int MIN_LENGTH_LOGIN = 4;
    private final int MIN_LENGTH_PASSWORD = 6;

    private UserDao userDao = UserDaoImpl.getInstance();

    private UserValidator(){

    }

    public static UserValidator getInstance(){
        if(instance == null){
            instance = new UserValidator();
        }
        return instance;
    }

    public boolean isValidate(User user) throws UserShortLengthLoginException, UserShortLengthPasswordException, UserLoginAlreadyExistException {
        if(isValidLogin(user)){
            throw new UserShortLengthLoginException("Login is too short.");
        }
        if(isValidPassword(user)){
            throw new UserShortLengthPasswordException("Password is too short.");
        }
        if(isUserByLoginExist(user.getLogin())){
            throw new UserLoginAlreadyExistException("User with this login already exists.");
        }

        return true;
    }

    private boolean isValidLogin(User user){
        return user.getLogin().length() >= MIN_LENGTH_LOGIN;
    }

    private boolean isValidPassword(User user){
        return user.getPassword().length() >= MIN_LENGTH_PASSWORD;
    }

    private boolean isUserByLoginExist(String login){
        User user = null;

        try {
            user = userDao.getUserByLogin(login);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(user == null) return false;

        return true;

    }


}
