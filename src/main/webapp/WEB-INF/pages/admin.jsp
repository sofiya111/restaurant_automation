<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/static/restaurantAutomation.css" />">
    <link rel="stylesheet" href="<c:url value="/static/color.css" />">
    <script type="text/javascript" src="<c:url value="/static/restaurantAutomation.js" />"></script>
    <title>Администратор</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <form action="/PersonalAccount" method="POST">
            <input type="hidden" name="cmd" value="PersonalAccount"/>
            <button class="btn btn-lightred"><span>Личный кабинет</span></button>
          </form>
        </li>
      </ul>
      <ul class="nav">
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
    <div>
      <div>
        <form class="searchForm" action="/users" method="GET">
          <input type="hidden" name="userType" value="admin"/>
          <input class="searchinput" name="search" type="text" placeholder="Искать...">
          <button class="btnsearch" type="submit"></button>
        </form>
      </div>
      <div class="add">
        <button type="button" class="btn btn-lightred"
        onclick="clickAdd(this)" data-toggle="modal" data-target="#myModal">Добавить пользователя</button><br/><br/>
      </div>
    </div>

    <table class="table table-hover">
    <thead class="table-active">
    <tr>
      <th>Пользователь</th>
      <th>Роль</th>
      <th>Телефон</th>
      <th>Email</th>
      <th>Статус блокировки</th>
      <th/>
    </tr>
    </thead>
      <tbody>
        <c:forEach items="${users}" var="user">
         <tr >
           <td onclick="clickEdit(this)" data-whatever="Сохранить" data-toggle="modal" data-target="#myModal"
               data-idUser=${user.id} data-name=${user.name} data-role=${user.role} data-phoneNumber=${user.phoneNumber}
               data-email=${user.email} data-password=${user.password} class="align-middle">${user.name}</td>
           <td onclick="clickEdit(this)" data-whatever="Сохранить" data-toggle="modal" data-target="#myModal"
               data-idUser=${user.id} data-name=${user.name} data-role=${user.role} data-phoneNumber=${user.phoneNumber}
               data-email=${user.email} data-password=${user.password} class="align-middle">${user.role.getName()}</td>
           <td onclick="clickEdit(this)" data-whatever="Сохранить" data-toggle="modal" data-target="#myModal"
               data-idUser=${user.id} data-name=${user.name} data-role=${user.role} data-phoneNumber=${user.phoneNumber}
               data-email=${user.email} data-password=${user.password} class="align-middle">${user.phoneNumber}</td>
           <td onclick="clickEdit(this)" data-whatever="Сохранить" data-toggle="modal" data-target="#myModal"
               data-idUser=${user.id} data-name=${user.name} data-role=${user.role} data-phoneNumber=${user.phoneNumber}
               data-email=${user.email} data-password=${user.password} class="align-middle">${user.email}</td>
           <td onclick="clickEdit(this)" data-whatever="Сохранить" data-toggle="modal" data-target="#myModal"
               data-idUser=${user.id} data-name=${user.name} data-role=${user.role} data-phoneNumber=${user.phoneNumber}
               data-email=${user.email} data-password=${user.password} class="align-middle">${user.getLockStatus().getName()}</td>
           <td class="align-right" style="text-align: center;">
           <button data-whatever="Удалить" onclick="clickDelete(this)" data-toggle="modal" data-target="#myModal" data-name=${user.name}
             data-role=${user.role} data-phoneNumber=${user.phoneNumber} data-email=${user.email} data-idUser=${user.id}
             data-password=${user.password} class="btn btn-danger">Удалить</button></td>
         </tr>
        </c:forEach>
     </tbody>
    </table>
  </div>
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Редактировать</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <form action="/users" method="POST">
        <div class="modal-body">
            <input id="userId" type="hidden" name="id" value=""/>
            Введите имя<br/>
            <input id="name" type="text" required class="form-control" name="name" value=""/><br/>
            Введите телефон<br/>
            <input id="phoneNumber" onkeypress="return (event.charCode >= 48 && event.charCode <= 57 && /^\d{0,11}$/.test(this.value));" type="text" required class="form-control" name="phoneNumber" minlength="11" maxlength="11" value=""/><br/>
            Введите логин<br/>
            <input id="login" type="text" required class="form-control" name="login" value=""/><br/>
            Введите пароль<br/>
            <input id="password" type="text" required class="form-control" name="password" minlength="4" maxlength="20" value=""/><br/>
            Выберите роль<br/>
            <select id="role" name="role" required class="browser-default custom-select">
              <option value="USER" selected="selected">Клиент</option>
              <option value="EMPLOYEE">Работник</option>
              <option value="MODER">Модератор</option>
              <option value="ADMIN">Администратор</option>
            </select><br/><br/>
            <div class="modal-footer">
              <input id="changeType" type="hidden" name="type" value=""/>
              <input type="hidden" name="cmd" value="GetUsers"/>
              <button id="btn_modal" class="btn btn-primary"></button>
              </form>
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
             </div>
          </div>
        </div>
      </div>
    </div>
  </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>