package ru.rsreu.restaurantAutomation.filters;

import ru.rsreu.restaurantAutomation.datalayer.dto.Authorization;
import ru.rsreu.restaurantAutomation.datalayer.dto.LockStatus;
import ru.rsreu.restaurantAutomation.datalayer.dto.User;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;


public class AutomationServiceFilter implements Filter {

    private static final String USER_LOGGED_IN_ERROR_MESSAGE = "Пользователь не авторизован";
    private static final String USER_LOCKED_ERROR_MESSAGE = "Пользователь заблокирован";
    private static final String NO_ERROR_MESSAGE = "No error";
    private static final String LOGIN_COMMAND = "Login";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        User user = getUser(request);
        if (user.equals(User.NULL_USER) &&
                !Objects.equals(req.getParameter(ContextAttributeName.COMMAND_ATTRIBUTE_NAME), LOGIN_COMMAND)) {
            setErrorAttributes(request, true, USER_LOGGED_IN_ERROR_MESSAGE);
        } else if (user.getAuthorization().equals(Authorization.LOGOUT)) {
            setErrorAttributes(request, true, USER_LOGGED_IN_ERROR_MESSAGE);
        } else if (user.isLockStatus().equals(LockStatus.LOCK)) {
            setErrorAttributes(request, true, USER_LOCKED_ERROR_MESSAGE);
        } else {
            setErrorAttributes(request, false, NO_ERROR_MESSAGE);
        }
        chain.doFilter(request, response);
    }

    private void setErrorAttributes(ServletRequest request, boolean isError, String errorMessage) {
        request.setAttribute(ContextAttributeName.FILTER_ACCESSED_ATTRIBUTE_NAME, !isError);
        request.setAttribute(ContextAttributeName.ERROR_MESSAGE_ATTRIBUTE_NAME, errorMessage);
    }

    private User getUser(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        User user;
        if (session == null) {
            user = User.NULL_USER;
        } else {
            user = (User) session.getAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME);
            if (user == null) {
                user = User.NULL_USER;
            }
        }
        return user;
    }

    @Override
    public void init(FilterConfig config) {
    }
}
