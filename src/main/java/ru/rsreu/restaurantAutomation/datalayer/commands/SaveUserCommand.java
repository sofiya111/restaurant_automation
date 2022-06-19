package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyUserDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.Authorization;
import ru.rsreu.restaurantAutomation.datalayer.dto.LockStatus;
import ru.rsreu.restaurantAutomation.datalayer.dto.Role;
import ru.rsreu.restaurantAutomation.datalayer.dto.User;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;
import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveUserCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        MyUserDAO myUserDAO = (MyUserDAO) req.getServletContext()
                .getAttribute(ContextAttributeName.USER_DAO_ATTRIBUTE_NAME);
        String name = req.getParameter(ContextAttributeName.NAME_ATTRIBUTE_NAME);
        Role role = Role.USER;
        String phoneNumber = req.getParameter(ContextAttributeName.PHONE_NUMBER_ATTRIBUTE_NAME);
        String login = req.getParameter(ContextAttributeName.LOGIN_ATTRIBUTE_NAME);
        String password = req.getParameter(ContextAttributeName.PASSWORD_ATTRIBUTE_NAME);
        myUserDAO.addUser(new User(0, name, role, phoneNumber,
                login, LockStatus.UNLOCK, password, Authorization.LOGOUT));
        Command command = new LoginCommand();
        command.execute(req, resp);
    }
}
