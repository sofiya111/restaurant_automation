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
    <div class="main">
    <form action="/personalAccount" method="POST">
      Введите имя<br/>
      <input type="text" name="name" value=""/><br/><br/>
      Введите телефон<br/>
      <input type="text" name="phoneNumber" value=""/><br/><br/>
      Введите email<br/>
      <input type="text" name="login" value=""/><br/><br/>
      <input type="hidden" name="cmd" value="PersonalAccount"/>
      <button class="btn btn-secondary">Зарегистрироваться</button><br/><br/>
    </form>
    </div>
</body>
</html>
