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
            document.getElementById("tabla").focus();
            
        }
    });
}
function to_searchprod_cata() {
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
function averdato() {
    var total = document.getElementsByTagName("td").length;
    var prod=$('#catalogo').val();
    var cadena = "";
    var flag=0;
    var pares=0;
    var uso = "anadir";
    for (var i = 0; i < total; i++) {
        var variable = document.getElementsByTagName("td")[i].innerHTML;
        if(variable == "<br>" || variable==" " || variable== "" || variable=="&nbsp;"){
            cadena = cadena + "0" + ","
        }
        else{
            cadena = cadena + variable + ",";
        }
        
    }
    if(flag==0){
        $.ajax({
            type: 'post',
            data: {p: cadena, uso: uso,prod:prod},
            url: '../Carrito',
            success: function (result) {
                document.location.reload();                                                 
                //$('#carrosid').html("<div class='container-fluid'><ul class=nav navbar-nav><li ><a style='color:white' href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\">("+result+")</a></li></ul></div>");
            }
        });
    }
        
   // $('#distribucion').html("<div class=\"container-fluid\"><div class=\"col-md-offset-5\"><label>Estilo Agregado al pedido exitosamente</label></div></div>");
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
    document.location.reload();
  //  document.getElementById("carrosid").innerHTML = "<div class='container-fluid'><li style='background-color:none'><a href=Utilidades_Donacionest.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"></a></li></div></nav>";
  //  $('#distribucion').html("<div class=\"container-fluid\"><div class=\"col-md-offset-5\"><label>Vaciado de pedido completo</label></div></div>");
  //  $('#desc-ped').html("");
}

function borrar(prod){
    var uso = "deleterow";
    $.ajax({
        type: 'post',
        data: {uso: uso,prod:prod},
        url: '../Carrito',
        success: function (result) {
            document.location.reload();
        }
    });
    
    
}
function dopedido(){
    var fp=$('#fp').val();
    var fe=$('#fe').val();
    var nc=$('#nc').val();
    var dir=$('#dir').val();
    var rfc=$('#rfc').val();
    var tel=$('#tel').val();
    var email=$('#email').val();
    var uso = "nuevopedido";
    $.ajax({
        type: 'post',
        data: {uso: uso,fp:fp,fe:fe,nc:nc,dir:dir,rfc:rfc,tel:tel,email:email},
        url: '../Carrito',
        success: function (result) {
            alert(result);
          document.location.reload();
        }
    });
}
//consultas
function getfields(){
    var report=$('input:radio[name=report]:checked').val();
    var tipo=$('input:radio[name=tipo]:checked').val();
    document.getElementById("selectbuscar").focus();
    var selec= $('#selectbuscar').val();
    $.ajax({
        type: 'post',
        data: {uso: report,tipo:tipo},
        url: '../Consultas',
        success: function (result) {
            $('#getselect').html(result);
        }
    });
    
}
function salto(){
    document.getElementById("gobusca").focus();
    
}

//Combinaciones
function getcombi(){
    document.getElementById("combinacion").value=$('#selectcom').val();
    document.getElementById("selectcor").focus();
}
function getcorri(){
    document.getElementById("corrida").value=$('#selectcor').val();
    document.getElementById("selectlin").focus();
}
function getlinea(){
    document.getElementById("linea").value=$('#selectlin').val();
    document.getElementById("costo").focus();
}


