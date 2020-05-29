package service.root;

import com.twmacinta.util.MD5;
import dto.UserDTO;
import entity.User;
import exception.ValidationException;
import repository.UserRepository;
import utils.MessagesUtils;

import javax.naming.NamingException;
import java.sql.SQLException;

public class LoginService {

    private static final String ADDITIONAL_CODING_STRING = "magicWord";
    private final UserRepository userRepository = new UserRepository();

    public UserDTO loginUser(String login, String password) throws SQLException, NamingException {
        if (!isDataValid(login, password)) {
            throw new ValidationException(MessagesUtils.INVALID_DATA_MESSAGE);
        }
        User user = userRepository.getByLogin(login);
        if (!isUserExists(user)) {
            throw new ValidationException(MessagesUtils.INVALID_LOGIN_MESSAGE);
        }
        var encodedPassword = new MD5(password + ADDITIONAL_CODING_STRING);
        if (!isPasswordCorrect(encodedPassword.asHex(), user)) {
            throw new ValidationException(MessagesUtils.INVALID_PASSWORD_MESSAGE);
        }
        return new UserDTO(user.getId(), user.getLogin(), user.getFio());
    }


    private boolean isPasswordCorrect(String password, User user) {
        return user.getPassword().equals(password);
    }

    private boolean isUserExists(User user) {
        return user != null;
    }

    private boolean isDataValid(String login, String password) {
        return login != null || password != null;
    }
}
