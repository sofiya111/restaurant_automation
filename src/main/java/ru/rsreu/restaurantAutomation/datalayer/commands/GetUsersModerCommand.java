package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyUserDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.LockStatus;
import ru.rsreu.restaurantAutomation.datalayer.dto.User;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;
import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GetUsersModerCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";
    private static final String USER_ATTRIBUTE_NAME = "users";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MyUserDAO myUserDAO = (MyUserDAO) req.getServletContext()
                    .getAttribute(ContextAttributeName.USER_DAO_ATTRIBUTE_NAME);
            String lockStatus = req.getParameter(ContextAttributeName.LOCK_STATUS_ATTRIBUTE_NAME);
            if (Objects.equals(lockStatus, LockStatus.UNLOCK.getName())) {
                int id = Integer.parseInt(req.getParameter(ContextAttributeName.ID_ATTRIBUTE_NAME));
                myUserDAO.lockUser(id);
            }
            if (Objects.equals(lockStatus, LockStatus.LOCK.getName())) {
                int id = Integer.parseInt(req.getParameter(ContextAttributeName.ID_ATTRIBUTE_NAME));
                myUserDAO.unlockUser(id);
            }
            List<User> users = myUserDAO.getClients();
            req.setAttribute(USER_ATTRIBUTE_NAME, users);
            req.getRequestDispatcher(MODER_PAGE_PATH).forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
