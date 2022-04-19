package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyReservationDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.Reservation;
import ru.rsreu.restaurantAutomation.datalayer.dto.Table;
import ru.rsreu.restaurantAutomation.datalayer.dto.User;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddReservationCommand implements Command {

    private static final String SPLIT_SYMBOL = ",";
    private static final String MILLISECONDS = ":00.000";
    private static final String SPLIT_ELEMENT = "T";
    private static final String SPACE_ELEMENT = " ";
    private static final String EMPTY_VALUE = "";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME);
        MyReservationDAO myReservationDAO = (MyReservationDAO) req.getServletContext()
                .getAttribute(ContextAttributeName.RESERVATION_DAO_ATTRIBUTE_NAME);
        Timestamp timestamp = Timestamp.valueOf(session.getAttribute(ContextAttributeName.DATE_ATTRIBUTE_NAME)
                .toString().concat(MILLISECONDS).replace(SPLIT_ELEMENT, SPACE_ELEMENT));
        int personNumber = Integer.parseInt((String) session.getAttribute(ContextAttributeName.PERSON_NUMBER_ATTRIBUTE_NAME));
        List<Integer> tableNumber = new ArrayList<>();
        String tablesString = req.getParameter(ContextAttributeName.TABLES_ATTRIBUTE_NAME);
        String[] tableArr = tablesString.split(SPLIT_SYMBOL);
        for (String s : tableArr) {
            if (!Objects.equals(s, EMPTY_VALUE)) {
                tableNumber.add(Integer.valueOf(s));
            }
        }
        List<Table> tables = myReservationDAO.findTables(tableNumber);
        myReservationDAO.makeReservation(new Reservation(user, timestamp, personNumber, tables));
        session.removeAttribute(ContextAttributeName.DATE_ATTRIBUTE_NAME);
        session.removeAttribute(ContextAttributeName.PERSON_NUMBER_ATTRIBUTE_NAME);
        session.removeAttribute(ContextAttributeName.TABLES_ATTRIBUTE_NAME);
    }
}
