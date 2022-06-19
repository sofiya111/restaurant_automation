<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.2.3/animate.min.css'>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="<c:url value="/static/login.css" />">
  <script type="text/javascript" src="<c:url value="/static/restaurantAutomation.js" />"></script>
  <title>Регистрация</title>
</head>
<body>
<div class='box'>
  <div class='box-form'>
    <div class='box-login-tab'></div>
    <div class='box-login-title' style="text-align: center;">
      <h2>Regisration</h2>
    </div>
    <div class='box-login'>
      <div class='fieldset-body' id='login_form'>
      <form id="form" action="/main" method="POST">
      <input type="hidden" name="cmd" value="SaveUser"/>
        <p class='field'>
          <input type='text' class="error" value='${error}' readonly/>
          <label for='user'>Введите имя</label>
          <input type='text' class="inputField" name="name" id='user' required minlength=2 name='login' title='Логин' />
          <span id='valida' class='i i-warning'></span>
        </p>
      	<p class='field'>
          <label for='pass'>Введите email</label>
          <input name="login" type='text'class="inputField" name="name" id='login' minlength=7 required title='Пароль' />
          <span id='valida' class='i i-close'></span>
        </p>
      	<p class='field'>
          <label for='pass'>Введите номер телефона</label>
          <input name="phoneNumber" type='text' class="inputField" id='text'onkeypress="return (event.charCode >= 48 && event.charCode <= 57 && /^\d{0,11}$/.test(this.value));" type="text" required name="phoneNumber" minlength="11" maxlength="11" name='password' title='Пароль' />
          <span id='valida' class='i i-close'></span>
        </p>
      	<p class='field'>
          <label for='pass'>Введите пароль</label>
          <input type='password' class="inputField" name='password' id='password' minlength=4 required  class='password' title='Пароль' />
          <span id='valida' class='i i-close'></span>
        </p>
        <p class='field'>
          <label for='pass'>Введите повторно пароль</label>
          <input id='passwordCheck' class="inputField" type='password' minlength=4 required name='passwordCheck' title='Пароль' />
          <span id='valida' class='i i-close'></span>
        </p>    </form>
        <input  onclick="ver()" type='submit' id='do_login' value='Зарегистрироваться' title='Зарегистрироваться' />
      </div>
    </div></br>

  </div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js'></script>
<script type="text/javascript" src="<c:url value="/static/restaurantAutomation.js" />"></script>
</body>
</html>
