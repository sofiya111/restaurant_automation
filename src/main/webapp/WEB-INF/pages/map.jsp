<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<c:url value="/static/map.css" />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_ru&apikey=26687db5-9651-4bc8-8e2a-d4043cacd204" type="text/javascript"></script>
    <script src="https://yandex.st/jquery/2.2.3/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="<c:url value="/static/restaurantAutomation.css" />">
    <link rel="stylesheet" href="<c:url value="/static/color.css" />">
    <script type="text/javascript" src="<c:url value="/static/map.js" />"></script>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <form action="/main" method="POST">
            <input type="hidden" name="cmd" value="StartPage"/>
            <button class="btn btn-lightred"><span>Главная</span></button>
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
            <input type="hidden" name="cmd" value="Establishment"/>
            <button class="btn btn-lightred"><span>К заказу</span></button>
          </form>
        </li>
        <li class="nav-item">
          <form action="/login" method="POST">
            <input type="hidden" name="cmd" value="PersonalAccount" />
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
  <form action="/order" method="POST">
    <input type="hidden" name="cmd" value="GetEstablishment"/>
    <div id="map"></div>
  </form>
</body>
