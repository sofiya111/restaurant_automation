package ru.rsreu.restaurantAutomation.servlets;

import ru.rsreu.restaurantAutomation.datalayer.commands.Command;
import ru.rsreu.restaurantAutomation.datalayer.commands.FindUserCommand;
import ru.rsreu.restaurantAutomation.datalayer.commands.UnknownCommand;
import ru.rsreu.restaurantAutomation.datalayer.db.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

/**
 * Front controller implementation
 */
public class FrontController extends HttpServlet {

    private static final String CLASS_NOT_FOUND_EXCEPTION_MESSAGE = "Class not found";
    private static final String INSTANTIATION_EXCEPTION = "The specified class object cannot be instantiated";
    private static final String ILLEGAL_ACCESS_EXCEPTION = "the currently executing method does not have access to" +
            " the definition of the specified class, field, method or constructor";
    private static final String COMMAND_CLASS_TEMPLATE = "ru.rsreu.restaurantAutomation.datalayer.commands.%sCommand";
    private static final long serialVersionUID = 1L;

    /**
     * Processing GET requests
     * @param req
     * @param resp
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String search = req.getParameter(ContextAttributeName.SEARCH_ATTRIBUTE_NAME);
        if (search != null) {
            Command cmd = new FindUserCommand();
            cmd.execute(req, resp);
        }
        String commandName = req.getParameter(ContextAttributeName.COMMAND_ATTRIBUTE_NAME);
        Command command = getCommandCheckingUser(req, commandName);
        command.execute(req, resp);
    }

    /**
     * Processing POST requests
     * @param req
     * @param resp
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String commandName = req.getParameter(ContextAttributeName.COMMAND_ATTRIBUTE_NAME);
        Command command = getCommandCheckingUser(req, commandName);
        command.execute(req, resp);
    }

    /**
     * Define command by her name from request
     * @param commandName
     * @return chosen command
     */
    protected Command defineCommand(String commandName) {
        Command command = new UnknownCommand();
        try {
            Class<?> type = Class.forName(String.format(COMMAND_CLASS_TEMPLATE, commandName));
            command = type.asSubclass(Command.class).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new MyServletException(CLASS_NOT_FOUND_EXCEPTION_MESSAGE, e);
        } catch (InstantiationException e) {
            throw new MyServletException(INSTANTIATION_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            throw new MyServletException(ILLEGAL_ACCESS_EXCEPTION, e);
        } catch (InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return command;
    }

    /**
     * Get command after checking the user
     * @param req
     * @param commandName
     * @return command
     */
    private Command getCommandCheckingUser(HttpServletRequest req, String commandName) {
        Boolean filterAccess = (Boolean) req.getAttribute(ContextAttributeName.FILTER_ACCESSED_ATTRIBUTE_NAME);
        Command command;
        if (!filterAccess) {
            command = new UnknownCommand();
        } else {
            command = defineCommand(commandName);
        }

        return command;
    }

    /**
     * Set servlet context
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = getServletContext();
        DAOFactory daoFactory = DAOFactory.getInstance();
        MyUserDAO myUserDAO = daoFactory.getUserDAO();
        setContext(context, myUserDAO, ContextAttributeName.USER_DAO_ATTRIBUTE_NAME);
        MyMenuDAO myMenuDAO = daoFactory.getMenuDAO();
        setContext(context, myMenuDAO, ContextAttributeName.MENU_DAO_ATTRIBUTE_NAME);
        MyReservationDAO myReservationDAO = daoFactory.getReservationDAO();
        setContext(context, myReservationDAO, ContextAttributeName.RESERVATION_DAO_ATTRIBUTE_NAME);
        MyFoodOrderDAO myFoodOrderDAO = daoFactory.getFoodOrderDAO();
        setContext(context, myFoodOrderDAO, ContextAttributeName.FOOD_ORDER_DAO_ATTRIBUTE_NAME);
    }

    /**
     * Set servlet context attribute
     * @param servletContext
     * @param object
     * @param attributeName
     */
    private void setContext(ServletContext servletContext, Object object, String attributeName) {
        if (servletContext.getAttribute(attributeName) == null) {
            servletContext.setAttribute(attributeName, object);
        }
    }
}
