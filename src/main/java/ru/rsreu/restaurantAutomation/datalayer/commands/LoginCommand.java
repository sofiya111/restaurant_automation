package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyUserDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.Authorization;
import ru.rsreu.restaurantAutomation.datalayer.dto.LockStatus;
import ru.rsreu.restaurantAutomation.datalayer.dto.Role;
import ru.rsreu.restaurantAutomation.datalayer.dto.User;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {

    private static final String USER_LOCKED_ERROR_MESSAGE = "Пользователь заблокирован";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String login = req.getParameter(ContextAttributeName.LOGIN_ATTRIBUTE_NAME);
            MyUserDAO myUserDAO = (MyUserDAO) req.getServletContext()
                    .getAttribute(ContextAttributeName.USER_DAO_ATTRIBUTE_NAME);
            User user;
            if (myUserDAO.findUser(login) != null) {
                user = myUserDAO.findUser(login);
                String password = req.getParameter(ContextAttributeName.PASSWORD_ATTRIBUTE_NAME);
                 if (user.getLockStatus().equals(LockStatus.LOCK)) {
                    req.setAttribute(ContextAttributeName.ERROR_MESSAGE_ATTRIBUTE_NAME, USER_LOCKED_ERROR_MESSAGE);
                    req.getRequestDispatcher(ERROR_PATH).forward(req, resp);
                } else if (isCorrectPassword(password, user.getPassword())) {
                    HttpSession session = req.getSession(true);
                    user.setAuthorization(Authorization.LOGIN);
                    session.setAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME, user);
                    if (user.getRole().equals(Role.USER)) {
                        req.getRequestDispatcher(START_PAGE_PATH).forward(req, resp);
                    } else if (user.getRole().equals(Role.ADMIN)) {
                        Command command = new GetUsersCommand();
                        command.execute(req, resp);
                    } else if (user.getRole().equals(Role.MODER)) {
                        Command command = new GetUsersModerCommand();
                        command.execute(req, resp);
                    } else if (user.getRole().equals(Role.EMPLOYEE)) {
                        Command command = new GetMenuEmployeeCommand();
                        command.execute(req, resp);
                    }
                } else {
                     req.setAttribute("error","Password is incorrect");
                     req.getRequestDispatcher(LOGIN_PATH).forward(req, resp);
                }
            } else {
                req.setAttribute("error","Login is incorrect");
                req.getRequestDispatcher(LOGIN_PATH).forward(req, resp);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isCorrectPassword(String password, String userPassword) {
        return password.equals(userPassword);
    }
}
