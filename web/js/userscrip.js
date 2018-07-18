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
                var cadena;
                var uso="anadir";
                for (var i = 0; i < total; i++) {
                    var variable = document.getElementsByTagName("td")[i].innerHTML;
                     $.ajax({
                    type: 'post',
                    data: {p: variable, uso: uso},
                    url: '../Carrito',
                    success: function (result) {
                        cadena = cadena +''+result+' -';
                        //
                    }
                });
                }
                document.getElementById("carrosid").innerHTML="<ul class=nav navbar-nav><li style='background-color:red'><a style='color:white' href=Utilidades_Donacionest.jsp>Nueva Compra Interna  (1)</a></li></ul>";
                $('#distribucion').html("<div class=\"row\"><div class=\"col-md-offset-5\"><label>Estilo Agregado al pedido exitosamente</label></div></div>");
            }
            function vaciar() {
                var uso="vaciar";
                     $.ajax({
                    type: 'post',
                    data: {uso: uso},
                    url: '../Carrito',
                    success: function (result) {
                        //
                    }
                });
                
                document.getElementById("carrosid").innerHTML="<ul class=nav navbar-nav><li><a href=Utilidades_Donacionest.jsp>Nueva Compra Interna  (1)</a></li></ul>";
                $('#distribucion').html("<div class=\"row\"><div class=\"col-md-offset-5\"><label>Vaciado de pedido completo</label></div></div>");
            }


