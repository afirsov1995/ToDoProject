package utils;

import dto.UserDTO;

import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static final String LOGIN_ATTRIBUTE = "login";

    public static Long getUserId(HttpSession session){
        return ((UserDTO) session.getAttribute(LOGIN_ATTRIBUTE)).getId();
    }

}
