<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/static/restaurantAutomation.css" />">
    <link rel="stylesheet" href="<c:url value="/static/color.css" />">
    <script type="text/javascript" src="<c:url value="/static/restaurantAutomation.js" />"></script>
    <title>Бронирование столика с выбором меню</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <form action="/main" method="POST">
            <input type="hidden" name="cmd" value="Establishment"/>
            <button class="btn btn-lightred"><span>Главная</span></button>
          </form>
        </li>
        <li class="nav-item">
          <form action="/map" method="POST">
            <input type="hidden" name="cmd" value="StartPage"/>
            <button class="btn btn-lightred"><span>К заведениям</span></button>
          </form>
        </li>
        <li class="nav-item">
          <form action="/menu" method="POST">
            <input type="hidden" name="cmd" value="GetMenu"/>
            <button class="btn btn-lightred"><span>Меню</span></button>
          </form>
        </li>
      </ul>
      <ul class="nav">
        <li class="nav-item">
          <form action="/login" method="POST">
            <input type="hidden" name="cmd" value="PersonalAccount"/>
            <button class="btn btn-lightred"><span>Личный кабинет</span></button>
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
  <form action="/reservationFoodOrder" method="POST" id="formTable">
    <div class="form-row">
      <div class="form-group col-md-6">
        <label for="inputEmail4">Количество персон</label>
        <input id="personNumber" type="number" class="form-control" name="personNumber" onchange="findFreeTables()"  min=1 value="${personNumber}"/>
      </div>
      <div class="form-group col-md-6">
        <label for="inputPassword4">Время бронирования</label>
        <input id="dateReservation" type="datetime-local" min="2022-03-09T08:30" onclick="setMinTime()" class="form-control" name="date" onchange="findFreeTables()" value="${date}"/>
      </div>
    </div>
      <input type="hidden" name="cmd" value="GetFreeTable"/>
      <div class="card-group">
      <div class="row row-cols-1 row-cols-md-2">
       <c:forEach items="${tables}" var="table">
         <div class="col mb-2" id="table" data-table="${table.tableNumber}">
           <div class="card" style="width: 14rem;">
             <div class="card-body">
               <form action="/reservationFoodOrder" method="POST">
               <input id="tableValue" type="hidden" value="${table.tableNumber}">
               <h5 class="card-title">${table.tableNumber}</h5>
               <h6 class="card-text">${table.seatsNumber} мест</h6>
               <div class="btn-group-toggle" data-toggle="buttons">
                 <label class="btn btn-outline-danger" for="btn-check-outlined">
                   <input id="check" type="checkbox" unchecked autocomplete="off"> Выбрать
                 </label>
               </div>
             </div>
           </div>
         </div>
       </c:forEach>
      </div>
      </div>
    </form>
    <div class="row">
      <div class="col-md-12 order-md-1">
        <h4 class="d-flex justify-content-between mb-3">
          <span class="text-muted">Меню</span>
        </h4>
        <ul class="list-group">
          <c:forEach items="${selectedDishes}" var="dish">
            <div onchange="changeReservFoodOrder(this)" data-value="${dish}">
              <form class="formTable" action="/reservationFoodOrder" method="POST">
                <li class="list-group-item d-flex lh-condensed align-items-center">
                  <div class="p-2 col">
                    <h5 class="my-0">${dish.getKey().name}</h6>
                  </div>
                  <div class="p-2 col">
                    <p class="">Цена: </br><span class="price">${dish.getKey().price*dish.getValue()}</span></p>
                  </div>
                  <div class="p-2 col">
                    Количество:
                    <input id="count" type="number" class="form-control" min=1 name="dishesCount" value="${dish.getValue()}"/>
                    <input type="hidden" name="id" value="${dish.getKey().id}"/>
                    <input type="hidden" name="name" value="${dish.getKey().name}"/>
                    <input type="hidden" name="price" value="${dish.getKey().price}"/>
                    <input type="hidden" name="cmd" value="DeleteDish"/><br/>
                    <input class="page" type="hidden" name="page" value="reservationFoodOrder"/>
                    <input class="changeType" type="hidden" name="changeType" value="delete"/>
                  </div>
                  <div class="p-2">
                    <button onclick="changeReservFoodOrder(this)" class="btn btn-danger btnDish">X</button>
                  </div>
                </li>
              </form>
            </div>
          </c:forEach>
          <li class="list-group-item d-flex justify-content-between">
            <h4>Итого (RUB)</h4>
            <strong id="total">${total}</strong>
          </li>
        </ul>
      </div>
    </div></br>
  <form id="confirmForm" class="float-sm-right" action="/success" method="POST">
    <input type="hidden" name="cmd" value="ConfirmOrder"/>
    <input type="hidden" name="orderType" value="reservationFoodOrder"/>
    <input type="hidden" id="tables" name="tables" type="text" />
    <input type="hidden" id="personNumberRes" name="personNumber" type="text" />
    <input type="hidden" id="dateRes" name="date" type="text" />
  </form>
  <button onclick="confirmResFood()" class="btn btn-lightred ">Оформить заказ</button><br/><br/>
  </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>

