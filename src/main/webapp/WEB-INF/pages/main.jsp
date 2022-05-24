<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script type="text/javascript" src="<c:url value="/static/restaurantAutomation.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/static/restaurantAutomation.css" />">
    <link rel="stylesheet" href="<c:url value="/static/color.css" />">
    <title>Главная страница</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <form action="/main" method="POST">
            <input type="hidden" name="cmd" value="GetEstablishment"/>
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
      </ul>
    </div>
  </nav>
  <img class="d-block w-100" height="44%" src="/static/main.jpg" alt="First slide">
 <div class="row main">
    <div class="col-lg-4" align="center">
       <img class="rounded-circle" src="/static/res.jpg" alt="Generic placeholder image" width="140" height="140">
       <h2>Бронирование столика</h2>
        <form action="/reservation" method="POST">
          <input type="hidden" name="cmd" value="Reservation"/>
          <p><button class="btn btn-lightred btn-lg">Перейти к оформлению »</button></p><br/><br/>
        </form>
    </div>
    <div class="col-lg-4" align="center">
        <img class="rounded-circle" src="/static/resFood.jpg" alt="Generic placeholder image" width="150" height="150">
        <h2>Бронирование столика с выбором меню</h2>
        <form action="/reservationFoodOrder" method="POST">
          <input type="hidden" name="cmd" value="ReservationFoodOrder"/>
          <p><button class="btn btn-lightred btn-lg">Перейти к оформлению »</button></p><br/><br/>
        </form>
    </div>
    <div class="col-lg-4" align="center">
        <img class="rounded-circle" src="/static/foodOrder.jpg" alt="Generic placeholder image" width="140" height="140">
        <h2>Заказ еды на вынос</h2>
        <form action="/foodOrder" method="POST">
          <input type="hidden" name="cmd" value="FoodOrder"/>
          <p><button class="btn btn-lightred btn-lg">Перейти к оформлению »</button></p><br/><br/>
        </form>
    </div>
 </div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
