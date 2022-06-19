<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/static/restaurantAutomation.css" />">
    <link rel="stylesheet" href="<c:url value="/static/color.css" />">
    <script type="text/javascript" src="<c:url value="/static/restaurantAutomation.js" />"></script>
    <title>Администратор</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <form action="/PersonalAccount" method="POST">
            <input type="hidden" name="cmd" value="PersonalAccount"/>
            <button class="btn btn-lightred"><span>Личный кабинет</span></button>
          </form>
        </li>
      </ul>
      <ul class="nav">
        <li class="nav-item">
          <form action="/menu" method="POST">
            <input type="hidden" name="cmd" value="GetMenuEmployee"/>
            <button class="btn btn-lightred"><span>Меню</span></button>
          </form>
        </li>
        <li class="nav-item">
          <form action="/login" method="POST">
            <input type="hidden" name="cmd" value="Logout"/>
            <button class="btn btn-lightred"><span>Выйти</span></button>
          </form>
        </li>
      </ul>
    </div>
  </nav>
  <div class="main">
    <select id="orderType"  required class="browser-default custom-select">
        <option value="reservation" selected="selected">Бронирование</option>
        <option value="foodOrder">Предзаказ еды</option>
        <option value="reservationFoodOrder">Бронирование с предзаказом</option>
    </select><br/><br/>
  </div>
    <table class="table table-hover">
    <thead class="table-active">
    <tr>
      <th style="text-align: center;">Номер заказа</th>
      <th style="text-align: center;">Имя клиента</th>
      <th style="text-align: center;">Время заказа</th>
      <th style="text-align: center;">Количество персон</th>
      <th style="text-align: center;">Столики</th>
    </tr>
    </thead>
      <tbody>
        <c:forEach items="${orders}" var="order">
         <tr>
           <td style="text-align: center;">${order.getId()}</td>
           <td style="text-align: center;">${order.client.name}</td>
           <td style="text-align: center;">${order.reservationTime}</td>
           <td style="text-align: center;">${order.personNumber}</td>
           <td style="text-align: center;">
           <button onclick="clickGetTables(this)" data-toggle="modal" data-target="#myModal"
                    data-tables=${order.tables} class="btn btn-danger">Столики</button></td>
         </tr>
        </c:forEach>
     </tbody>
    </table>
  </div>
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Столики</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
            <div class="modal-body">
              <table class="table table-hover">
                  <thead class="table-active">
                  <tr>
                    <th>Номер столика</th>
                    <th>Количество мест</th>
                  </tr>
                  </thead>
                    <tbody id="tables">
                       <tr>
                         <td>10</td>
                         <td>4</td>
                       </tr>
                   </tbody>
                  </table>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>