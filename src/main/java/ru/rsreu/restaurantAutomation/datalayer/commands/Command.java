package ru.rsreu.restaurantAutomation.datalayer.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for commands
 */
public interface Command {

    String START_PAGE_PATH = "/WEB-INF/pages/map.jsp";
    String ESTABLISHMENT_PAGE_PATH = "/WEB-INF/pages/main.jsp";
    String EMPLOYEE_PAGE_PATH = "/WEB-INF/pages/employee.jsp";
    String RESERVATION_PATH = "/WEB-INF/pages/reservation.jsp";
    String REGISTRAION_PATH = "/WEB-INF/pages/registration.jsp";
    String FOOD_ORDER_PATH = "/WEB-INF/pages/foodOrder.jsp";
    String RESERVATION_FOOD_ORDER_PATH = "/WEB-INF/pages/reservationFoodOrder.jsp";
    String LOGIN_PATH = "/WEB-INF/pages/login.jsp";
    String ERROR_PATH = "/WEB-INF/pages/error.jsp";
    String ADMIN_PATH = "/WEB-INF/pages/admin.jsp";
    String CONFIRM_ORDER_PATH = "/WEB-INF/pages/success.jsp";
    String MENU_PATH = "/WEB-INF/pages/menu.jsp";
    String PERSONAL_ACCOUNT_USER_PATH = "/WEB-INF/pages/personalAccountUser.jsp";
    String PERSONAL_ACCOUNT_PATH = "/WEB-INF/pages/personalAccount.jsp";
    String UNKNOWN_CHAT_PATH = "/WEB-INF/pages/error.jsp";
    String MODER_PAGE_PATH = "/WEB-INF/pages/moder.jsp";
    String START_PAGE_REDIRECT_PATH = "/";
    String RESERVATION_FOOD_ORDER_PAGE_REDIRECT_PATH = "/reservationFoodOrder";

    void execute(HttpServletRequest req, HttpServletResponse resp);

}
