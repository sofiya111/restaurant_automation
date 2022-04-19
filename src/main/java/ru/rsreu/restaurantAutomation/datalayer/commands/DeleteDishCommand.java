package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.dto.Dish;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class DeleteDishCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter(ContextAttributeName.ID_ATTRIBUTE_NAME));
        String dishName = req.getParameter(ContextAttributeName.NAME_ATTRIBUTE_NAME);
        float price = Float.parseFloat(req.getParameter(ContextAttributeName.PRICE_ATTRIBUTE_NAME));
        int dishCount = Integer.parseInt(req.getParameter(ContextAttributeName.DISHES_COUNT_ATTRIBUTE_NAME));
        HttpSession session = req.getSession(false);
        Map<Dish, Integer> dishes = (Map<Dish, Integer>) session.getAttribute(ContextAttributeName.DISHES_ATTRIBUTE_NAME);
        Dish dish = new Dish(id, dishName, price);
        if (req.getParameter(ContextAttributeName.CHANGE_TYPE_ATTRIBUTE_NAME)
                .equals(ContextAttributeName.CHANGE_ATTRIBUTE_NAME)) {
            dishes.put(dish, dishCount);
        } else {
            dishes.remove(dish);
        }
        float total = 0;
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        session.setAttribute(ContextAttributeName.DISHES_ATTRIBUTE_NAME, dishes);
        session.setAttribute(ContextAttributeName.TOTAL_PRICE_ATTRIBUTE_NAME, total);
        Command command = new UnknownCommand();
        String page = req.getParameter(ContextAttributeName.PAGE_ATTRIBUTE_NAME);
        switch (page) {
            case ContextAttributeName.MENU_ATTRIBUTE_NAME:
                command = new GetMenuCommand();
                break;
            case ContextAttributeName.FOOD_ORDER_ATTRIBUTE_NAME:
                command = new FoodOrderCommand();
                break;
            case ContextAttributeName.RESERVATION_FOOD_ORDER_ATTRIBUTE_NAME:
                command = new ReservationFoodOrderCommand();
                break;
        }
        command.execute(req, resp);
    }
}
