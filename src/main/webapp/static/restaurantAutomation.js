function clickDelete(el){
    document.getElementById("btn_modal").innerText = "Удалить";
    $('#changeType').val("delete");
    $("h5").html("Удаление пользователя");
    click(el);
}

function clickEdit(el){
    document.getElementById("btn_modal").innerText = "Сохранить";
    $('#changeType').val("update");
    $("h5").html("Редактирование пользователя");
    click(el);
}

function clickAdd(el){
    document.getElementById("btn_modal").innerText = "Добавить";
    $("h5").html("Добавление пользователя");
    $('#changeType').val("add");
    $('#name').val("");
    $('#phoneNumber').val("");
    $('#login').val("");
    $('#password').val("");
    $("#role").val("USER");
    $("#id").val("");
}

function click(el){
    var name = $(el).attr('data-name');
    var phoneNumber = $(el).attr('data-phoneNumber');
    var login = $(el).attr('data-email');
    var password = $(el).attr('data-password');
    var role = $(el).attr('data-role');
    var idUser = $(el).attr('data-idUser');
    $('#name').val(name);
    $('#phoneNumber').val(phoneNumber);
    $('#login').val(login);
    $('#password').val(password);
    $("#role").val(role);
    $('#userId').val(idUser);
}

function clickModer(el){
    var idUser = $(el).attr('data-idUser');
    var lockStatus = $(el).attr('data-lockStatus');
    $('#userId').val(idUser);
    $('#lockStatus').val(lockStatus);
}

function clickAddDish(el){
    $('#id').val("");
    $('#name').val("");
    $('#price').val("");
    $('#changeType').val("add");
    document.getElementById("btn_modal").innerText = "Сохранить";
}

function clickEditDish(el){
    var id = $(el).attr('data-dishId');
    var name = $(el).attr('data-name');
    var price = $(el).attr('data-price');
    $('#id').val(id);
    $('#name').val(name);
    $('#price').val(price);
    $('#changeType').val("edit");
    document.getElementById("btn_modal").innerText = "Сохранить";
}

function clickDeleteDish(el){
    var id = $(el).attr('data-dishId');
    var name = $(el).attr('data-name');
    var price = $(el).attr('data-price');
    $('#id').val(id);
    $('#name').val(name);
    $('#price').val(price);
    $('#changeType').val("delete");
    document.getElementById("btn_modal").innerText = "Удалить";
}

function findFreeTables(){
    if(document.getElementById("personNumber").value>0 && document.getElementById("dateReservation").value>"01/01/2022 00:00:00"){
        document.getElementById('formTable').submit();
    }
}

function setMinTime(){
    var now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    document.getElementById("dateReservation").setAttribute("min", now.toISOString().slice(0,16));
}

function confirmOrder(){
    if (getTables().length==0){
        alert("Вы не выбрали столик!")
    } else {
        document.getElementById('confirmForm').submit();
    }
}

function confirmResFood(){
    var count = document.getElementById("count");
    if (getTables().length==0){
        alert("Вы не выбрали столик!")
    } else if(count==null){
        alert("Вы не выбрали блюда!");
    } else {
        document.getElementById('confirmForm').submit();
    }
}

function getTables(){
    var res = []
    var posts = document.querySelectorAll("#table");
    for (var i = 0; i<posts.length; i++) {
        var checkbox = posts[i].querySelector("#check");
        if (checkbox.checked) {
            var post = posts[i].querySelector("#tableValue").value;
            res[i]=post;
        }
    }
    var date = $('#dateReservation').val();
    $('#personNumberRes').val($('#personNumber').val());
    $('#dateRes').val(date);
    $('#tables').val(res);
    return res
}

function confirmFoodOrder(){
    var count = document.getElementById("count");
    if(count==null){
        alert("Вы не выбрали блюда!");
    } else {
        document.getElementById('confirmForm').submit();
    }
}

function changePrice(el){
    el.querySelectorAll('.changeType')[0].setAttribute("value","change");
    el.querySelectorAll('.formTable')[0].submit();
}

function changeFoodOrder(el){
    el.querySelectorAll('.changeType')[0].setAttribute("value","change");
    el.querySelectorAll('.formTable')[0].submit();
}

function changeReservFoodOrder(el){
    el.querySelectorAll('.changeType')[0].setAttribute("value","change");
    el.querySelectorAll('.formTable')[0].submit();
}