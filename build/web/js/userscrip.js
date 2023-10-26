/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function domod(prod) {
    location = "modprod.jsp?prod=" + prod;
}
function doupdate() {
    var estilo = $("#estilo").val();
    var combinacion = $("#combinacion").val();
    var corrida = $("#corrida").val();
    var linea = $("#linea").val();
    var marca = $("#marca").val();
    var prod = $("#prod").val();
    var tipo = $("#tipo").val();
    //alert(estilo+"-"+combinacion+"-"+corrida+"-"+linea+"-"+marca+"-"+prod);
    var uso = "update";
    $.ajax({
        type: 'post',
        data: {uso: uso, prod: prod, estilo: estilo, combinacion: combinacion, corrida: corrida, linea: linea, marca: marca, tipo: tipo},
        url: '../Productos',
        success: function (result) {
            //('#distribucion').html(result);
            alert(result);
            location = "productos.jsp";
            // document.location.reload();                                                 
            //$('#carrosid').html("<div class='container-fluid'><ul class=nav navbar-nav><li ><a style='color:white' href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\">("+result+")</a></li></ul></div>");
        }
    });
}
function to_searchprod() {
    //var catalogo = $('#catalogo').val();
    var catalogo = $('#sel').val();
    var stock;
    if (document.getElementById("stock").checked === true) {
        stock = "S";
    } else {
        stock = "P";
    }
    var uso = "buscar";
    $.ajax({
        type: 'post',
        data: {p: catalogo, uso: uso, stock: stock},
        url: '../Productos',
        success: function (result) {
            $('#distribucion').html(result);
            //document.getElementById("tabla").focus();
            document.getElementById("tabla").focus();
        }
    });
}
function to_searchestilo() {
    var catalogo = $('#catalogo').val();
    var uso = "estilo";
    var stock;
    if (document.getElementById("stock").checked === true) {
        stock = "S";
    } else {
        stock = "P";
    }
    $.ajax({
        type: 'post',
        data: {p: catalogo, uso: uso, stock: stock},
        url: '../Productos',
        success: function (result) {
            //$('#distribucion').html("");
            $('#sel').html(result);
            document.getElementById("sel").focus();

        }
    });
}
function nextstock() {
    document.getElementById('stock').focus();
}

//funcion para ver como funciona el obtener el valor de un combo
function combox() {
    var catalogo = "";
    if (document.getElementById("serie").checked === true) {
        catalogo = "1";
    } else {
        catalogo = 0;
    }
    //$('#distribucion').html(catalogo);

}
//Vacia el texto que hay dentro del 
function vaciartexto() {
    $('catalogo').val("");
    document.getElementById("catalogo").value = "";
}


function to_searchprod_cata() {// busqueda de producto
    var catalogo = $('#catalogo').val();
    var uso = "searchprod_cata";
    $.ajax({
        type: 'post',
        data: {p: catalogo, uso: uso},
        url: '../Productos',
        success: function (result) {
            $('#tabla-prods').html(result);
        }
    });
}
function modpedido() {//funcion modificar pedido
    var total = document.getElementsByTagName("td").length;
    var prod = $('#catalogo').val();
    var cadena = "";
    var flag = 0;
    var pares = 0;
    var uso = "modificar";
    for (var i = 0; i < total; i++) {
        var variable = document.getElementsByTagName("td")[i].innerHTML;
        if (variable == "<br>" || variable == " " || variable == "" || variable == "&nbsp;" || variable == "</p>") {
            cadena = cadena + "0" + ",";
        } else {
            cadena = cadena + variable + ",";
        }
    }
    if (flag == 0) {
        $.ajax({
            type: 'post',
            data: {p: cadena, uso: uso, prod: prod},
            url: '../Carrito',
            success: function (result) {
                //('#distribucion').html(result);
                location = "index.jsp";
                // document.location.reload();                                                 
                //$('#carrosid').html("<div class='container-fluid'><ul class=nav navbar-nav><li ><a style='color:white' href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\">("+result+")</a></li></ul></div>");
            }
        });
    }

    // $('#distribucion').html("<div class=\"container-fluid\"><div class=\"col-md-offset-5\"><label>Estilo Agregado al pedido exitosamente</label></div></div>");
}
function averdato() {
    var total = document.getElementsByTagName("td").length;
    //var prod=$('#catalogo').val();
    var prod = $('#sel').val();
    var cadena = "";
    var flag = 0;
    var pares = 0;
    //var serie = "";
    var stock;
    var precio = $('#precio').val();
    /*if (document.getElementById("serie").checked === true) {
     serie = "A";
     } else {
     serie = "B";
     }*/
    if (document.getElementById("stock").checked === true) {
        stock = "S";
    } else {
        stock = "P";
    }
    var uso = "anadir";
    for (var i = 0; i < total; i++) {
        var variable = document.getElementsByTagName("td")[i].innerHTML;
        if (variable == "<br>" || variable == " " || variable == "" || variable == "&nbsp;") {
            cadena = cadena + "0" + ","
        } else {
            cadena = cadena + variable + ",";
        }
    }
    //validar precio
    var flag2 = false;
    if ((/^\d+\.\d+|\d+$/i.test(precio))) {
        flag2 = true;
    }

    if (flag == 0 && flag2) {
        $.ajax({
            type: 'post',
            data: {p: cadena, uso: uso, prod: prod, precio: precio, stock: stock},
            url: '../Carrito',
            success: function (result) {
                //('#distribucion').html(result);
                alert('Producto añadido');
                location = "index.jsp";
                // document.location.reload();                                                 
                //$('#carrosid').html("<div class='container-fluid'><ul class=nav navbar-nav><li ><a style='color:white' href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\">("+result+")</a></li></ul></div>");
            }
        });
    } else {
        alert('El precio no es valido, intentalo de nuevo');
    }
    // $('#distribucion').html("<div class=\"container-fluid\"><div class=\"col-md-offset-5\"><label>Estilo Agregado al pedido exitosamente</label></div></div>");
}

function verif() {
    var precio = $('#precio').val();
    if ((/^\d+\.\d+|\d+$/i.test(precio))) {
        //alert('Ci');
    }

}

function datoprepack() {
    var precio = $('#precio').val();
    if (precio == " " || precio == "") {
        alert('El campo de precio no puede ir vacio');
    } else {
        var total = document.getElementsByTagName("td").length;
        var prod = $('#sel').val();
        //var prod = $('#catalogo').val();
        var cadena = "";
        var flag = 0;
        var pares = $('#nprepack').val();
        var precio = $('#precio').val();
        var stock;
        if (document.getElementById("stock").checked === true) {
            stock = "S";
        } else {
            stock = "P";
        }
        var uso = "anadir";
        if (pares === '') {
            alert("EL campo de numero de pares no puede ir vacio.");
            document.getElementById('nprepack').focus();
        } else {
            for (var i = 0; i < total; i++) {
                var variable = document.getElementsByTagName("td")[i].innerHTML;
//                Verifica cada celda y que no contenga algun otro numero para la carga del prepack
                if (variable == "<br>" || variable == " " || variable == "" || variable == "&nbsp;" || (/^\d+<br>$/i.test(variable))) {
                    cadena = cadena + pares + ",";
                } else {
                    cadena = cadena + variable + ",";
                }
            }
            if (flag == 0) {
                $.ajax({
                    type: 'post',
                    data: {p: cadena, uso: uso, prod: prod, precio: precio,stock:stock},
                    url: '../Carrito',
                    success: function (result) {
                        //('#distribucion').html(result);
                        alert('Producto añadido');
                        location = "index.jsp";
                        // document.location.reload();                                                 
                        //$('#carrosid').html("<div class='container-fluid'><ul class=nav navbar-nav><li ><a style='color:white' href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\">("+result+")</a></li></ul></div>");
                    }
                });
            }
        }
    }

}
function tomod(prod) {
    location = "modped.jsp?prod=" + prod;

}
function vaciar() {
    if (confirm("¿Realmente desea vaciar el pedido?")) {
        var uso = "vaciar";
        $.ajax({
            type: 'post',
            data: {uso: uso},
            url: '../Carrito',
            success: function (result) {
                //
            }
        });
        document.location.reload();
    }

    //  document.getElementById("carrosid").innerHTML = "<div class='container-fluid'><li style='background-color:none'><a href=Utilidades_Donacionest.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"></a></li></div></nav>";
    //  $('#distribucion').html("<div class=\"container-fluid\"><div class=\"col-md-offset-5\"><label>Vaciado de pedido completo</label></div></div>");
    //  $('#desc-ped').html("");
}

function borrar(prod) {
    var uso = "deleterow";
    $.ajax({
        type: 'post',
        data: {uso: uso, prod: prod},
        url: '../Carrito',
        success: function (result) {
            document.location.reload();
        }
    });


}
function dopedido() {
    var obs = $('#obs').val();
    var cliente = $('#clin').val();
    var serie;
    if (document.getElementById("serie").checked === true) {
        serie = "A";
    } else {
        serie = "B";
    }
    var uso = "nuevopedido";
    var a = "";
    $.ajax({
        type: 'post',
        data: {uso: uso, obs: obs, cliente: cliente, serie: serie},
        url: '../Carrito',
        success: function (result) {
            if (result == "") {
                a = '<label id=alabel>Error al completar pedido, intentelo de nuevo</label>';
                //alert(a);
            } else {
                a = '<label id=alabel>Pedido completado con exito el numero de pedido es: ' + result + ' </label>';
                //alert(a);
                //document.location.reload();
                //location = "reportevta_pedido.jsp?peds=" + result;
            }
            $('#resp').html(a);
            doemail(result);
        }
    });
}


function baja(clave) {
    if (confirm("¿Realmente desea cancelar el pedido?")) {
        var pro = $('#f1').val();
        var pro1 = $('#f2').val();
        var peds = $('#peds').val();
        var report = $('input:radio[name=status]:checked').val();
        var uso = "baja";
        $.ajax({
            type: 'post',
            data: {uso: uso, clave: clave},
            url: '../Carrito',
            success: function (result) {
                verped(pro, pro1, peds, report);
            }
        });

    }


}
function alta(clave) {
    var pro = $('#f1').val();
    var pro1 = $('#f2').val();
    var peds = $('#peds').val();
    var report = $('input:radio[name=status]:checked').val();
    var uso = "alta";
    $.ajax({
        type: 'post',
        data: {uso: uso, clave: clave},
        url: '../Carrito',
        success: function (result) {
            verped(pro, pro1, peds, report);
        }
    });

}
function verped(valor, valor1, peds, report) {
    if (!(/^\d{2}|\d{2}\-\d{2}\-\d{4}\$/i.test(valor)) && !(/^\d{2}|\d{2}\-\d{2}\-\d{4}\$/i.test(valor1))) {
        alert("fecha invalida invalido");
        //document.forma.f1.focus();
        document.getElementById("f1").value = "<%=fechac%>";
        document.getElementById("f2").value = "<%=fechac%>";
        return 0;
    } else {
        var uso = "fechas";
        $.ajax({
            type: 'post',
            data: {f1: valor, f2: valor1, uso: uso, busqueda: peds, status: report},
            url: '../Carrito',
            success: function (result) {
                $('#llenar').html(result);
            }
        });
    }
}
//consultas
function getfields() {
    var report = $('input:radio[name=report]:checked').val();
    var tipo = $('input:radio[name=tipo]:checked').val();
    var selec = $('#selectbuscar').val();
    $.ajax({
        type: 'post',
        data: {uso: report, tipo: tipo},
        url: '../Consultas',
        success: function (result) {
            $('#getselect').html(result);
            document.getElementById("selectbuscar").focus();
        }
    });

}
function salto() {
    document.getElementById("gobusca").focus();

}
function getreport() {
    var select = $('#selectbuscar').val();
    var report = $('input:radio[name=report]:checked').val();
    var tipo = $('input:radio[name=tipo]:checked').val();
    location = "reporteconsultas.jsp?report=" + report + "&tipo=" + tipo + "&select=" + select;

}
//Combinaciones
function getcombi() {
    document.getElementById("combinacion").value = $('#selectcom').val();
    document.getElementById("selectcor").focus();
}
function getcorri() {
    document.getElementById("corrida").value = $('#selectcor').val();
    document.getElementById("selectlin").focus();
}
function getlinea() {
    document.getElementById("linea").value = $('#selectlin').val();
    document.getElementById("costo").focus();
}


