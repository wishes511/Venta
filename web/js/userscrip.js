/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function to_searchprod() {
    var catalogo = $('#catalogo').val();
    var uso = "buscar";
    $.ajax({
        type: 'post',
        data: {p: catalogo, uso: uso},
        url: '../Productos',
        success: function (result) {
            $('#distribucion').html(result);
        }
    });
}
function averdato() {
    var total = document.getElementsByTagName("td").length;
    var prod=$('#catalogo').val();
    var cadena = "";
    var uso = "anadir";
    for (var i = 0; i < total; i++) {
        var variable = document.getElementsByTagName("td")[i].innerHTML;
        cadena = cadena + variable + ",";
    }
        $.ajax({
            type: 'post',
            data: {p: cadena, uso: uso,prod:prod},
            url: '../Carrito',
            success: function (result) {
                $('#carrosid').html("<div class='container-fluid'><ul class=nav navbar-nav><li ><a style='color:white' href=><img class=\"imagencesta\" src=\"../images/cesta.png\">("+result+")</a></li></ul></div>");
            }
        });
    
    alert(cadena);
    $('#distribucion').html("<div class=\"container-fluid\"><div class=\"col-md-offset-5\"><label>Estilo Agregado al pedido exitosamente</label></div></div>");
}
function vaciar() {
    var uso = "vaciar";
    $.ajax({
        type: 'post',
        data: {uso: uso},
        url: '../Carrito',
        success: function (result) {
            //
        }
    });

    document.getElementById("carrosid").innerHTML = "<div class='container-fluid'><li style='background-color:none'><a href=Utilidades_Donacionest.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"></a></li></div></nav>";
    $('#distribucion').html("<div class=\"container-fluid\"><div class=\"col-md-offset-5\"><label>Vaciado de pedido completo</label></div></div>");
}


