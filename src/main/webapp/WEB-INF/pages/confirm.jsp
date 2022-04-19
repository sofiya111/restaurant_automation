<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value="/static/restaurantAutomation.css" />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Регистрация</title>
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
    <div class="main">
    <form action="/personalAccount" method="POST">
    <h4>Заказ успешно оформлен!</h4></br>
      Номер заказа: 34<br/><br/>
      Имя: Иван<br/><br/>
      Телефон: 89005005050 <br/><br/>
      Email: ivan202@gmail.com<br/><br/>
      Количество персон: 5<br/><br/>
      Дата и время бронирования: 31.03.2022 10:33<br/><br/>
      Номер столика: 10<br/><br/>
    </form>
    </div>
</body>
</html>
