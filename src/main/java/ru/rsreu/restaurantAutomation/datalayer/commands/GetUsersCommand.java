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
import java.util.List;
import java.util.Objects;

public class GetUsersCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";
    private static final String USER_ATTRIBUTE_NAME = "users";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MyUserDAO myUserDAO = (MyUserDAO) req.getServletContext()
                    .getAttribute(ContextAttributeName.USER_DAO_ATTRIBUTE_NAME);
            String changeType = req.getParameter(ContextAttributeName.TYPE_ATTRIBUTE_NAME);
            if (Objects.equals(changeType, ContextAttributeName.DELETE_ATTRIBUTE_NAME)) {
                int id = Integer.parseInt(req.getParameter(ContextAttributeName.ID_ATTRIBUTE_NAME));
                myUserDAO.deleteUser(id);
            }
            if (Objects.equals(changeType, ContextAttributeName.UPDATE_ATTRIBUTE_NAME)) {
                int id = Integer.parseInt(req.getParameter(ContextAttributeName.ID_ATTRIBUTE_NAME));
                String name = req.getParameter(ContextAttributeName.NAME_ATTRIBUTE_NAME);
                Role role = Role.valueOf(req.getParameter(ContextAttributeName.ROLE_ATTRIBUTE_NAME));
                String phoneNumber = req.getParameter(ContextAttributeName.PHONE_NUMBER_ATTRIBUTE_NAME);
                String email = req.getParameter(ContextAttributeName.LOGIN_ATTRIBUTE_NAME);
                String password = req.getParameter(ContextAttributeName.PASSWORD_ATTRIBUTE_NAME);
                myUserDAO.updateUser(new User(id, name, role, phoneNumber,
                        email, LockStatus.UNLOCK, password, Authorization.LOGOUT));
            }
            if (Objects.equals(changeType, ContextAttributeName.ADD_ATTRIBUTE_NAME)) {
                String name = req.getParameter(ContextAttributeName.NAME_ATTRIBUTE_NAME);
                Role role = Role.valueOf(req.getParameter(ContextAttributeName.ROLE_ATTRIBUTE_NAME));
                String phoneNumber = req.getParameter(ContextAttributeName.PHONE_NUMBER_ATTRIBUTE_NAME);
                String email = req.getParameter(ContextAttributeName.LOGIN_ATTRIBUTE_NAME);
                String password = req.getParameter(ContextAttributeName.PASSWORD_ATTRIBUTE_NAME);
                myUserDAO.addUser(new User(0, name, role, phoneNumber,
                        email, LockStatus.UNLOCK, password, Authorization.LOGOUT));
            }
            List<User> users = myUserDAO.getAllUsers();
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME);
            users.remove(user);
            req.setAttribute(USER_ATTRIBUTE_NAME, users);
            req.getRequestDispatcher(ADMIN_PATH).forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
