package ru.rsreu.restaurantAutomation.listener;

import ru.rsreu.restaurantAutomation.datalayer.db.MyUserDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.User;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    public static final int INTERVAL = 60000;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(INTERVAL);
        User user = (User) event.getSession().getAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME);
        MyUserDAO myUserDAO = (MyUserDAO) event.getSession().getServletContext()
                .getAttribute(ContextAttributeName.USER_DAO_ATTRIBUTE_NAME);
        myUserDAO.login(user);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        User user = (User) event.getSession().getAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME);
        MyUserDAO myUserDAO = (MyUserDAO) event.getSession().getServletContext()
                .getAttribute(ContextAttributeName.USER_DAO_ATTRIBUTE_NAME);
        myUserDAO.logout(user);
    }

}
