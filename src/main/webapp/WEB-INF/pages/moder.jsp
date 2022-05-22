<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value="/static/restaurantAutomation.css" />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <script type="text/javascript" src="<c:url value="/static/restaurantAutomation.js" />"></script>
    <title>Модератор</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <form action="/PersonalAccount" method="POST">
            <input type="hidden" name="cmd" value="PersonalAccount"/>
            <button class="btn btn-dark"><span>Личный кабинет</span></button>
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
    <div class="main">
      <form class="searchForm" action="/usersModer" method="GET">
        <input type="hidden" name="userType" value="moder"/>
        <input class="searchinput" name="search" type="text" placeholder="Искать...">
        <button class="btnsearch" type="submit"></button>
      </form><br/>
    <table class="table table-hover">
    <thead class="table-active">
    <tr>
      <th>Пользователь</th>
      <th>Роль</th>
      <th>Телефон</th>
      <th>Email</th>
      <th>Статус блокировки</th>
    </tr>
    </thead>
      <tbody>
        <c:forEach items="${users}" var="user">
         <tr onclick="clickModer(this)" data-idUser=${user.id} data-lockStatus=${user.getLockStatus().getName()} data-toggle="modal" data-target="#myModal">
          <td class="align-middle">${user.name}</td>
          <td class="align-middle">${user.role}</td>
          <td class="align-middle">${user.phoneNumber}</td>
          <td class="align-middle">${user.email}</td>
          <td class="align-middle">${user.getLockStatus()}</td>
         </tr>
        </c:forEach>
     </tbody>
    </table>
  </div>
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Изменить статус блокировки?</h5>
          <input type="hidden" name="cmd" value="PersonalAccount"/>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
          <form action="/usersModer" method="POST">
            <input id="userId" type="hidden" name="id" value=""/>
            <input id="lockStatus" type="hidden" name="lockStatus" value=""/>
            <input type="hidden" name="cmd" value="GetUsersModer"/>
            <button class="btn btn-primary">Да</button>
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