<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value="/static/restaurantAutomation.css" />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script type="text/javascript" src="<c:url value="/static/restaurantAutomation.js" />"></script>
    <title>Главная страница</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <form action="/main" method="POST">
            <input type="hidden" name="cmd" value="StartPage"/>
            <button class="btn btn-dark"><span>Главная</span></button>
          </form>
        </li>
        <li class="nav-item">
          <form action="/menu" method="POST">
            <input type="hidden" name="cmd" value="GetMenu"/>
            <button class="btn btn-dark"><span>Меню</span></button>
          </form>
        </li>
      </ul>
      <ul class="nav">
        <li class="nav-item">
          <form action="/login" method="POST">
            <input type="hidden" name="cmd" value="PersonalAccount"/>
            <button class="btn btn-dark"><span>Личный кабинет</span></button>
          </form>
        </li>
      </ul>
    </div>
  </nav>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="w-100 opacity-2" height="93%" src="/static/res.jpg" alt="First slide">
      <div class="carousel-caption">
        <form action="/reservation" method="POST">
          <input type="hidden" name="cmd" value="Reservation"/>
          <p><button class="btn btn-dark btn-lg">Бронирование столика</button></p><br/><br/>
        </form>
      </div>
    </div>
    <div class="carousel-item">
      <img class="d-block w-100 opacity-2" height="93%" src="/static/resFood.jpg" alt="Second slide">
      <div class="carousel-caption d-none d-md-block">
        <form action="/reservationFoodOrder" method="POST">
          <input type="hidden" name="cmd" value="ReservationFoodOrder"/>
          <p><button class="btn btn-dark btn-lg">Бронирование столика с выбором меню</button></p><br/><br/>
        </form>
      </div>
    </div>
    <div class="carousel-item">
      <img class="d-block w-100 opacity-2" height="93%" src="/static/foodOrder.jpg" alt="Third slide">
      <div class="carousel-caption d-none d-md-block">
        <form action="/foodOrder" method="POST">
          <input type="hidden" name="cmd" value="FoodOrder"/>
          <p><button class="btn btn-dark btn-lg">Заказ еды на вынос</button></p><br/><br/>
        </form>
      </div>
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
