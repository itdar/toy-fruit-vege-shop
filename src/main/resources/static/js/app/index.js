
var main = {
    init : function () {
        var _this = this;
        $('#btn-check-token').on('click', function () {
            _this.token_check();
        });
        $('#btn-check-fruitprice').on('click', function () {
            _this.fruit_check();
        });
        $('#btn-check-vegeprice').on('click', function () {
            _this.vege_check();
        });
    },
    token_check : function () {
        $.ajax({
            type: 'GET',
            url: '/token',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function(data) {
            console.log(data);
            var json = JSON.parse(JSON.stringify(data));
            $('#accessToken').text(json.accessToken);
            alert('토큰 조회.');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    fruit_check : function () {
        var data = {
            name: $('#fruit_name').val(),
        };

        $.ajax({
            type: 'GET',
            url: '/fruit/product',
            dataType: 'json',
            headers: {
                'Authorization': $('#accessToken').text()
            },
            contentType:'application/json; charset=utf-8',
            data: { name: data.name },
        }).done(function(data) {
            console.log(data);
            var json = JSON.parse(JSON.stringify(data));
            $('#fruit-price').text(json.price);
            // alert('과일 가격 조회.');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    vege_check : function () {
        var data = {
            name: $('#vege_name').val(),
        };

        $.ajax({
            type: 'GET',
            url: '/vegetable/item',
            dataType: 'json',
            headers: {
                'Authorization': $('#accessToken').text()
            },
            contentType:'application/json; charset=utf-8',
            data: { name: data.name },
        }).done(function(data) {
            console.log(data);
            var json = JSON.parse(JSON.stringify(data));
            $('#vege-price').text(json.price);
            // alert('채소 가격 조회.');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();