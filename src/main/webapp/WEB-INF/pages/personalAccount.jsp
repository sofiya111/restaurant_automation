<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value="/static/restaurantAutomation.css" />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Личный кабинет</title>
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
      </ul>
      <ul class="nav">
        <li class="nav-item">
          <form action="/login" method="POST">
            <input type="hidden" name="cmd" value="Logout"/>
            <button class="btn btn-dark"><span>Выйти</span></button>
          </form>
        </li>
      </ul>
    </div>
  </nav>
<dl class="personalInfo">
  <dt>Имя</dt>
    <dd>${user.name}</dd>
  <dt>Телефон</dt>
    <dd>${user.phoneNumber}</dd>
  <dt>Почта</dt>
    <dd>${user.email}</dd>
</dl>
</body>
</html>
