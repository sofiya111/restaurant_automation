<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html >
<head>
  <meta charset="UTF-8">
  <title>Авторизация</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.2.3/animate.min.css'>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="<c:url value="/static/login.css" />">
</head>
<body>
<form action="/main" method="POST">
<input type="hidden" name="cmd" value="Login"/>
<div class='box'>
  <div class='box-form'>
    <div class='box-login-tab'></div>
    <div class='box-login-title'>
      <div class='i i-login'></div><h2>LOGIN</h2>
    </div>
    <div class='box-login'>
      <div class='fieldset-body' id='login_form'>
        <p class='field'>
          <input type='text' class="error" value='${error}' readonly/>
          <label for='user'>ЛОГИН</label>
          <input type='text' class="inputField" id='user' required minlength=4 name='login' title='Логин' />
          <span id='valida' class='i i-warning'></span>
        </p>
      	<p class='field'>
          <label for='pass'>ПАРОЛЬ</label>
          <input type='password'class="inputField" id='password' minlength=4 required name='password' title='Пароль' />
          <span id='valida' class='i i-close'></span>
        </p>
        <input type='submit' id='do_login' value='Войти' title='Войти' />
      </div>
    </div></br>
    </form>
    <div align="center">
      <form action="/main" method="POST">
         <input type="hidden" name="cmd" value="Registration"/>
         <button class="btn btn-lightred">Регистрация</button><br/><br/>
      </form>
    </div>
  </div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js'></script>
</body>
</html>
