package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyReservationDAO;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddReservationFoodOrderCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Command command = new AddFoodOrderCommand();
        command.execute(req, resp);
        command = new AddReservationCommand();
        command.execute(req, resp);
        MyReservationDAO myReservationDAO = (MyReservationDAO) req.getServletContext()
                .getAttribute(ContextAttributeName.RESERVATION_DAO_ATTRIBUTE_NAME);
        myReservationDAO.makeOrder();
    }
}
