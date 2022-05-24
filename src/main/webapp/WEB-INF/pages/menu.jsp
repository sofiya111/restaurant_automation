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
    <title>Меню</title>
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
          <form action="/foodOrder" method="POST">
            <input type="hidden" name="cmd" value="FoodOrder"/>
            <button class="btn btn-lightred"><span>К заказу</span></button>
          </form>
        </li>
        <li class="nav-item">
          <form action="/login" method="POST">
            <input type="hidden" name="cmd" value="PersonalAccount"/>
            <button class="btn btn-lightred"><span>Личный кабинет</span></button>
          </form>
        </li>
      </ul>
    </div>
  </nav>
    <div class="main">
      <div class="row">
        <div class="col-md-4 order-md-2 mb-4">
          <h4 class="d-flex justify-content-between align-items-center mb-3">
            <span class="text-muted">Заказ</span>
          </h4>
          <ul class="list-group mb-3">
          <c:forEach items="${selectedDishes}" var="dish">
            <div onchange="changePrice(this)">
            <form class="formTable" action="/menu" method="POST">
            <li class="list-group-item d-flex justify-content-between lh-condensed">
              <h6 class="my-0">${dish.getKey().name}</h6>
              <div>
                Цена: <span class="price">${dish.getKey().price*dish.getValue()}</span></br>Количество:
                <input type="number" class="form-control" min=1 name="dishesCount" value="${dish.getValue()}"/>
                <input type="hidden" name="id" value="${dish.getKey().id}"/>
                <input type="hidden" name="name" value="${dish.getKey().name}"/>
                <input type="hidden" name="price" value="${dish.getKey().price}"/>
                <input type="hidden" name="cmd" value="DeleteDish"/><br/>
                <input class="page" type="hidden" name="page" value="menu"/>
                <input class="changeType" type="hidden" name="changeType" value="delete"/>
                <button class="btn btn-lightred btnDish">Убрать</button><br/><br/>
              </div>
            </li>
            </form>
            </div>
           </c:forEach>
            <li class="list-group-item d-flex justify-content-between">
              <span>Итого (RUB)</span>
              <strong id="total">${total}</strong>
            </li>
          </ul>
        </div>
        <div class="container col-md-8 mb-3">
          <div class="row">
           <c:forEach items="${menu}" var="dish">
             <div class="col-sm-4 mb-4">
               <div class="card border-secondary">
                 <div class="card-body">
                   <form action="/menu" method="POST">
                   <h5 class="card-title">${dish.name}</h5>
                   <p class="card-text">Количество</p>
                   <input type="number" class="form-control" min=1 name="dishesCount" value="1"/><br/>
                   <p class="card-text">Стоимость</p>
                   <h5 class="card-text" style="text-align:right;">${dish.price}</p></h5>
                   <input type="hidden" name="id" value="${dish.id}"/>
                   <input type="hidden" name="name" value="${dish.name}"/>
                   <input type="hidden" name="price" value="${dish.price}"/>
                   <input type="hidden" name="cmd" value="AddDish"/>
                   <button class="btn btn-lightred btnDish">В корзину</button><br/><br/>
                   </form>
                 </div>
               </div>
             </div>
           </c:forEach>
          </div>
      </div>
  </div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>