package controller;

import model.model.user.User;
import model.validation.Validation;

public class UserController extends Controller {


    public User validationLoginInfo(final String username, final String password) throws Exception {
        Validation.usernameValidation(username);
        Validation.isEmpty(password, "Password");
        return database.fetchUserByUsernamePassword(username, password);
    }


}
