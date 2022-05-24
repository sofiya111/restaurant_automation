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
    <title>Бронирование столика</title>
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
  <div class="main">
    <div class="row">
      <div class="col-md-12 order-md-1">
        <h4 class="d-flex justify-content-between mb-3">
          <span class="text-muted">Заказ</span>
        </h4>
        <ul class="list-group">
          <c:forEach items="${selectedDishes}" var="dish">
            <div onchange="changeFoodOrder(this)">
              <form class="formTable" action="/foodOrder" method="POST">
                <li class="list-group-item d-flex lh-condensed align-items-center">
                  <div class="p-2 col">
                    <h5 class="my-0">${dish.getKey().name}</h6>
                  </div>
                  <div class="p-2 col">
                    <p>Цена: </br><span class="price">${dish.getKey().price*dish.getValue()}</span></p>
                  </div>
                  <div class="p-2 col">
                    Количество:
                    <input id="count" type="number" class="form-control" min=1 name="dishesCount" value="${dish.getValue()}"/>
                    <input type="hidden" name="id" value="${dish.getKey().id}"/>
                    <input type="hidden" name="name" value="${dish.getKey().name}"/>
                    <input type="hidden" name="price" value="${dish.getKey().price}"/>
                    <input type="hidden" name="cmd" value="DeleteDish"/><br/>
                    <input class="page" type="hidden" name="page" value="foodOrder"/>
                    <input class="changeType" type="hidden" name="changeType" value="delete"/>
                  </div>
                  <div class="p-2">
                    <button onclick="changeFoodOrder(this)" class="btn btn-danger btnDish">X</button>
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
    <input type="hidden" name="orderType" value="foodOrder"/>
    <input type="hidden" name="cmd" value="ConfirmOrder"/>
  </form>
  <button onclick="confirmFoodOrder()" class="btn btn-lightred">Оформить заказ</button><br/><br/>
  </div>
</body>
</html>
