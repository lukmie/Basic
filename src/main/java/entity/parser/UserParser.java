package entity.parser;

import entity.User;

public class UserParser {

    public static User stringToUser(String userStr){

        String[] userInformations = userStr.split(User.USER_SEPARATOR);

        Integer id = Integer.parseInt(userInformations[0]);
        String login = userInformations[1];
        String password = userInformations[2];

        return new User(id, login, password);
    }
}
