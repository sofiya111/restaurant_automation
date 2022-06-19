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
    <title>Меню</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <form action="/login" method="POST">
            <input type="hidden" name="cmd" value="PersonalAccount"/>
            <button class="btn btn-lightred"><span>Личный кабинет</span></button>
          </form>
        </li>
      </ul>
      <ul class="nav">
        <li class="nav-item">
          <form action="/orders" method="POST">
            <input type="hidden" name="cmd" value="Orders"/>
            <button class="btn btn-lightred"><span>Заказы</span></button>
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
    <div class="addDish">
        <button type="button" class="btn btn-lightred"
          onclick="clickAddDish(this)" data-toggle="modal" data-target="#dishCard">Добавить блюдо</button>
    </div>
    <div class="container col-md-8 mb-3">
      <div class="row">
        <c:forEach items="${menu}" var="dish">
          <div class="col-sm-3 mb-4">
            <div class="card border-secondary">
              <div class="card-body">
                <h5 class="card-title">${dish.name}</h5>
                <p class="card-text">Стоимость</p>
                <h5 class="card-text" style="text-align:right;">${dish.price}</p></h5>
                <button class="btn btn-lightred btnDish" onclick="clickEditDish(this)"
                  data-name=${dish.name} data-price=${dish.price} data-dishId=${dish.id}
                  data-toggle="modal" data-target="#dishCard" >Редактировать</button><br/><br/>
                <button class="btn btn-lightred btnDish" onclick="clickDeleteDish(this)"
                  data-name=${dish.name} data-price=${dish.price} data-dishId=${dish.id}
                  data-toggle="modal" data-target="#dishCard" >Удалить</button><br/><br/>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </div>
  <div class="modal fade" id="dishCard" tabindex="-1" role="dialog" aria-labelledby="dishCardLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Редактировать блюдо</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <form action="/menu" method="POST">
        <div class="modal-body">
            <input id="changeType" type="hidden" name="type" value=""/>
            <input id="id" type="hidden" name="id" value=""/>
            Введите название блюда<br/>
            <input id="name" required type="text" class="form-control" name="name"/><br/>
            Введите стоимость<br/>
            <input id="price" required type="number" name="price" class="form-control" min="0.00" max="100000.00" step="0.01" />
        </div>
        <div class="modal-footer">
          <input type="hidden" name="cmd" value="GetMenuEmployee"/>
          <button id="btn_modal" class="btn btn-primary"></button>
          </form>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
          </form>
        </div>
      </div>
    </div>
  </div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>