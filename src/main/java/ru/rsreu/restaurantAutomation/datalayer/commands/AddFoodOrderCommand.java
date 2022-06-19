package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyFoodOrderDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.Dish;
import ru.rsreu.restaurantAutomation.datalayer.dto.FoodOrder;
import ru.rsreu.restaurantAutomation.datalayer.dto.User;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Map;

public class AddFoodOrderCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME);
        Map<Dish, Integer> dishes = (Map<Dish, Integer>) session.getAttribute(ContextAttributeName.DISHES_ATTRIBUTE_NAME);
        MyFoodOrderDAO myFoodOrderDAO = (MyFoodOrderDAO) req.getServletContext()
                .getAttribute(ContextAttributeName.FOOD_ORDER_DAO_ATTRIBUTE_NAME);
        float price = (float) session.getAttribute(ContextAttributeName.TOTAL_PRICE_ATTRIBUTE_NAME);
        Timestamp orderTime = new Timestamp(System.currentTimeMillis());
        int establishment = (int) session.getAttribute(ContextAttributeName.ESTABLISHMENT_ATTRIBUTE_NAME);
        myFoodOrderDAO.makeFoodOrder(new FoodOrder(user, price, orderTime, dishes),establishment);
        session.removeAttribute(ContextAttributeName.DISHES_ATTRIBUTE_NAME);
        session.removeAttribute(ContextAttributeName.TOTAL_PRICE_ATTRIBUTE_NAME);
    }
}
