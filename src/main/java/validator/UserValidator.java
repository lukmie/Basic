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

    private UserValidator(){

    }

    public static UserValidator getInstance(){
        if(instance == null){
            instance = new UserValidator();
        }
        return instance;
    }

    public boolean isValidate(User user) throws UserShortLengthLoginException, UserShortLengthPasswordException, UserLoginAlreadyExistException {
        if(isLoginLengthNoEnough(user.getLogin())){
            throw new UserShortLengthLoginException("Login is too short.");
        }

        if(isPasswordLengthNoEnough(user.getPassword())){
            throw new UserShortLengthPasswordException("Password is too short.");
        }

        return true;
    }

    private boolean isLoginLengthNoEnough(String login){
        return login.length() < MIN_LENGTH_LOGIN;
    }

    private boolean isPasswordLengthNoEnough(String password){
        return password.length() < MIN_LENGTH_PASSWORD;
    }
}
