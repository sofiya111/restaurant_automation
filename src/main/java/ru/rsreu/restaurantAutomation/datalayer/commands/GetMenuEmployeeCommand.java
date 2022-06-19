package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyMenuDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.Dish;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;
import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GetMenuEmployeeCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";
    private static final String MENU_ATTRIBUTE_NAME = "menu";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MyMenuDAO myMenuDAO = (MyMenuDAO) req.getServletContext()
                    .getAttribute(ContextAttributeName.MENU_DAO_ATTRIBUTE_NAME);
            String changeType = req.getParameter(ContextAttributeName.TYPE_ATTRIBUTE_NAME);
            if (Objects.equals(changeType, ContextAttributeName.ADD_ATTRIBUTE_NAME)) {
                String name = req.getParameter(ContextAttributeName.NAME_ATTRIBUTE_NAME);
                float price = Float.parseFloat(req.getParameter(ContextAttributeName.PRICE_ATTRIBUTE_NAME));
                HttpSession session = req.getSession(false);
                int establishment = (int) session.getAttribute(ContextAttributeName.ESTABLISHMENT_ATTRIBUTE_NAME);
                myMenuDAO.addDish(new Dish(0, name, price),establishment);
            }
            if (Objects.equals(changeType, ContextAttributeName.EDIT_ATTRIBUTE_NAME)) {
                int id = Integer.parseInt(req.getParameter(ContextAttributeName.ID_ATTRIBUTE_NAME));
                String name = req.getParameter(ContextAttributeName.NAME_ATTRIBUTE_NAME);
                float price = Float.parseFloat(req.getParameter(ContextAttributeName.PRICE_ATTRIBUTE_NAME));
                myMenuDAO.editDish(new Dish(id, name, price));
            }
            if (Objects.equals(changeType, ContextAttributeName.DELETE_ATTRIBUTE_NAME)) {
                String name = req.getParameter(ContextAttributeName.NAME_ATTRIBUTE_NAME);
                myMenuDAO.deleteDish(name);
            }
            List<Dish> dishes = myMenuDAO.getMenu();
            req.setAttribute(MENU_ATTRIBUTE_NAME, dishes);
            req.getRequestDispatcher(EMPLOYEE_PAGE_PATH).forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
