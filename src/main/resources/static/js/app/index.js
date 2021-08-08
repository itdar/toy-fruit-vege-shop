
var main = {
    init : function () {
        var _this = this;
        $('#btn-check-fruitprice').on('click', function () {
            _this.fruit_check();
        });
        $('#btn-check-vegeprice').on('click', function () {
            _this.vege_check();
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
            Authorization: $('#accessToken').val(),
            contentType:'application/json; charset=utf-8',
            data: { name: data.name },
        }).done(function() {
            alert('과일 가격 조회.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    vege_check : function () {
        var data = {
            name: $('#vege_name').val()
        };

        $.ajax({
            type: 'GET',
            url: '/vegetable/item',
            dataType: 'json',
            Authorization: $('#accessToken').val(),
            contentType:'application/json; charset=utf-8',
            data: { name: data.name },
        }).done(function() {
            alert('채소 가격 조회.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();