package service.root;

import com.twmacinta.util.MD5;
import entity.User;
import exception.ValidationException;
import repository.UserRepository;
import utils.MessagesUtils;

import javax.naming.NamingException;
import java.sql.SQLException;

public class RegistrationService {

    private static final int MINIMUM_DATA_LENGTH = 5;
    private static final String ADDITIONAL_CODING_STRING = "magicWord";
    private final UserRepository userRepository = new UserRepository();

    public Long registerUser(String login, String password, String fio) throws ValidationException, SQLException, NamingException {
        if (!isValidData(login, password, fio)) {
            throw new ValidationException(MessagesUtils.SHORT_DATA_MESSAGE);
        }
        if (isUserExists(login)) {
            throw new ValidationException(MessagesUtils.USER_EXISTS_MESSAGE);
        }
        User user = new User();
        user.setLogin(login);
        var encodedPassword = new MD5(password + ADDITIONAL_CODING_STRING);
        user.setPassword(encodedPassword.asHex());
        user.setFio(fio);
        return userRepository.save(user);
    }

    private boolean isValidData(String login, String password, String fio) {
        return login != null && login.length() > MINIMUM_DATA_LENGTH && password != null
                && password.length() > MINIMUM_DATA_LENGTH && fio != null && fio.length() > MINIMUM_DATA_LENGTH;
    }

    private boolean isUserExists(String login) throws SQLException, NamingException {
        return userRepository.getByLogin(login) != null;
    }
}
