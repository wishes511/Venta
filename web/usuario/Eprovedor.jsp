<%-- 
    Created on : 12/04/2018, 11:17:46 AM
    Author     : mich
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%int id_produc = 0;
    String usuarios = "";
    HttpSession objSesion = request.getSession(false);
    boolean estado;
    try {

        String usuario = (String) objSesion.getAttribute("usuario");
        String tipos = (String) objSesion.getAttribute("tipo");
        ArrayList<String> dis = (ArrayList<String>) objSesion.getAttribute("distribucion");
        ArrayList<String> cor = (ArrayList<String>) objSesion.getAttribute("corrida");
        System.out.println(usuario + " " + tipos);
        if (usuario != null && tipos != null && (tipos.equals("ADMIN"))) {

        } else {
            response.sendRedirect("../index.jsp");
        }
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechac = "";
        String fechaca = "";
        if (dia < 10) {
            fechac = "0" + dia;
            fechaca = "0" + (dia - 1);
        } else {
            fechac = dia + "";
            fechaca = dia + "";
        }
        if (mes < 10) {
            fechac = fechac + "-0" + mes + "-" + año;
            fechaca = fechaca + "-0" + (mes - 1) + "-" + año;
        } else {
            fechac = fechac + "-" + mes + "-" + año;
            fechaca = fechaca + "-" + (mes - 1) + "-" + año;
        }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogo de Productos</title>
        <link rel="shortcut icon" type="image/x-icon" href="../supp.ico" />
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel='stylesheet' type="text/css" href="../css/opcional.css">
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery-3.1.1.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/userscrip.js"></script>
        <script type="text/javascript">
            function valida_nom() {
                var texto = document.form1.names.value;
                if (!(/^([A-Z\a-z]+)$/i.test(texto))) {
                    alert("nombre invalido! ");
                    response.sendRedirect("../index.jsp");
                    document.form1.names.focus();
                    return false;
                } else
                    return true;
            }
            function valida_calle() {
                valor = document.form1.apes.value;
                if (!(/^([A-Z\a-z]+)$/i.test(valor))) {
                    alert("nombresdf invalido! ");
                    document.form1.apes.focus();
                    return false;
                } else
                    return true;
            }
            function validacion() {
                if (valida_nom() == true && valida_calle() == true) {
                    return true;
                } else
                    response.sendRedirect("../index.jsp");
                return false;
            }
        </script>
        <script>

        </script>
    </head>
    <body>
        <div class="container-fluid">
            <!--<button onclick="nuevomostrar()">lolo</button>--> 
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" href="../index.jsp"><img src="../images/home.png" class="" width="25"></a>
                </div>
                <ul class="nav navbar-nav">
                    <%
                        if (tipos.equals("ADMIN") || tipos.equals("AMECANICA")) {
                    %>
                    <li class="active">
                        <a  class="" >
                            Venta
                        </a>
                    </li>
                    <li><a href="../Cierresesion">Salir</a></li>
                </ul>
<div id="" class="nav nav-pills" style="float:right">
                    <%
                        }
                        if (!dis.isEmpty()) {
                            out.print("<li  id=\"carrosid\" style='background-color:red'><a style='color:white' href=Utilidades_Donacionest.jsp>Nueva Compra Interna "+" ("+dis.size()+")</a></li>");
                          for(int i =0;i<dis.size();i++){
                           System.out.println(dis.get(i));
                        }                       
                         } else {
                            out.print("<li  id=\"carrosid\"><a href=Utilidades_Donacionest.jsp>Nueva Compra Interna</a></li>");

                        }
                    %>
                    </div>
            </nav>
            <div class="row" >
                
            <!--    <div class=" col-md-offset-6"  id="get_catalogo" align="center">
                <div style=" overflow: auto"  class="col-md-12" style="background-color: blueviolet" align="center">
                    <table border="1" width="5" class="table table-bordered table-condensed table-hover table-striped" style="overflow: auto" align="center">
                        <thead align="center" >
                        <th width="10">25</th>
                        <th width="10">25.5</th>
                        <th  width="10">26</th>
                        <th  width="10">26.5</th>
                        <th  width="10">27</th>
                        <th  width="10">27.5</th>
                        <th  width="10">28</th>
                        <th  width="10">28.5</th>
                        <th  width="10">29</th>
                        <th  width="10">29.5</th>
                        <th  width="10">30</th>
                        <th width="10">30.5</th>
                        </thead>
                        <tr align="center" contenteditable="true">
                            <td ></td>
                            <td width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td width="10" onclick="averdato()"></td>
                            <td  width="10"><img class="imagentabla" src="../images/ok.png" alt=""></td>
                        </tr>
                    </table>
                </div>
                </div> -->
                </div>
                <div class="row" >
                    <div class="col-lg-8 col-lg-offset-2" id="get_catalogo" align="center" style="padding: 2%">
                <div class="row espas-search-prods">
                    <div class="col-sm-4">
                        <input type="text" id="catalogo" placeholder="Busqueda de productos" class="form-control" onchange="to_searchprod()"> 
                    </div> 
                    <button style="float:right" class="btn btn-danger" onclick="vaciar()">Vaciar</button>
                </div>
                        </div>
                <div class="espacio1"> <!-- Cliente -->
                    <div class="col-lg-8 espas col-lg-offset-2" id="get_catalogo" align="center">
                        <div class="col-md-offset-2">
                            <div class="col-md-3" align="center"><label class="">Cliente:</label><select class="form-control" id="cliente"><option value="1" class="form-control">Mostrador</option></select></div>
                            <div class="col-md-3"><label>Fecha Pedido :</label><div class=""><input class="form-control" type="text" id="fp" value="<%=fechac%>"></div></div>
                            <div class="col-md-3"><label>Fecha Entrega:</label><div class=""><input class="form-control" type="text" id="fe" value="<%=fechac%>"></div></div>
                        </div>
                        <div class="" style="padding-top: 10%">
                            <div class="col-md-4"><label>Nombre Cliente :</label><div class=""><input class="form-control" type="text" id="nc" ></div></div>
                            <div class="col-md-5"><label>Direccion:</label><div class=""><input class="form-control" type="text" id="dir" ></div></div>
                            <div class="col-md-3"><label>RFC:</label><div class=""><input class="form-control" type="text" id="rfc" ></div></div>
                        </div>
                    </div> 
                </div>

            </div>
                <div class="row" id="distribucion">
           <!-- <div class="col-md-4" style="padding-top: 2%" align="center">
                        <div class="row"><label class="prodbusqeuda">Estilo</label><label class="prodbusqeuda">Combinacion</label><label class="prodbusqeuda">Corrida</label></div>
                        <div class="row"><label class="prodbusqeuda">5454</label><label class="prodbusqeuda">HONTING - KOTOTE</label><label class="prodbusqeuda">25/30</label></div>
                    </div>
                <div class=" col-md-offset-4"  id="get_catalogo" align="center" style="padding-top: 2%">
                <div style=" overflow: auto"  class="col-md-12" align="center" >
                    <table border="1" width="5" class="table table-bordered table-condensed table-hover table-striped" style="overflow: auto" align="center">
                        <thead align="center" >
                        <th width="10">25</th>
                        <th width="10">25.5</th>
                        <th  width="10">26</th>
                        <th  width="10">26.5</th>
                        <th  width="10">27</th>
                        <th  width="10">27.5</th>
                        <th  width="10">28</th>
                        <th  width="10">28.5</th>
                        <th  width="10">29</th>
                        <th  width="10">29.5</th>
                        <th  width="10">30</th>
                        <th width="10">30.5</th>
                        </thead>
                        <tr align="center" contenteditable="true">
                            <td ></td>
                            <td width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td  width="10"></td>
                            <td width="10" onclick="averdato()"></td>
                            <td  width="10"><img class="imagentabla" src="../images/ok.png" alt=""></td>
                        </tr>
                    </table>
                </div>
                </div> -->         
                </div>
        </div>
        <script>
            
            function carrito(id)
            {
                window.location.href = "?total=" + id;
            }
            function mostrarVentanas(id)
            {
                document.getElementById("benviar0").value = id;
                document.getElementById("cant1").value = id;
                var ventana = document.getElementById("miVentana");
                ventana.style.marginTop = "100px";
                //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                ventana.style.display = "block";
                ventana.style.left = 40 + "%";
                document.getElementById("cantis").focus();
            }
            function ocultarVentanas()
            {
                var ventana = document.getElementById("miVentana");
                ventana.style.display = "none";
            }
            function nuevomostrar() {
                document.getElementById("valorr").value = "lij";
                var ventana = document.getElementById("ress");
                ventana.style.marginTop = "100px";
                ventana.style.display = "block";
                ventana.style.left = 40 + "%";
                $('#ress').fadeOut(3000);
            }
            function mostrar()
            {
                var ventana = document.getElementById("miVen");
                ventana.style.marginTop = "10%";
                //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                ventana.style.display = "block";
                ventana.style.left = 35 + "%";
                document.getElementById("tipos").focus();
            }
            function ocultars() {
                var vent = document.getElementById("miVen");
                vent.style.display = "none";
            }
            function resultado(total)
            {
                var pago = $("#cobro").val();
                var totaltotal = total - pago;
                if (totaltotal < 0) {
                    totaltotal = totaltotal * (-1);
                }
                document.getElementById("cambio").value = totaltotal;
            }
            function ru() {
                var pro = $('#tipos').val();
                $.ajax({
                    type: 'post',
                    data: {id: pro},
                    url: '../CProveedor',
                    success: function (result) {
                        document.getElementById("idu").value = result;
                    }
                });
            }
            function ru2() {
                var pro = $('#tipos').val();
                document.getElementById("idu").value = pro;
                document.getElementById("idu").focus();
            }
            function saltoref() {
                document.getElementById("ref").focus();
            }
            function tomaralcarro() {
                if (!(/^([1-9]+)([0-9]*)$/.test($('#cantis').val()))) {
                    document.formas.cantis.focus();
                    alert("Porfavor coloca la cantidad de productos que deseas agregar");
                    return false;
                } else {
                    var ids = $("#benviar0").val();
                    var pro = $('#cantis').val();
                    var uso1 = "NUEVO";
                    $.ajax({
                        type: 'post',
                        data: {ids: ids, cant: pro, uso: uso1},
                        url: '../CProveedor',
                        success: function () {
                            location.reload(true);
                        }
                    });
                }
            }
            function erase(id) {
                var uso1 = "BORRAR";
                $.ajax({
                    type: 'post',
                    data: {ids: id, uso: uso1},
                    url: '../CProveedor',
                    success: function () {
                        location.reload(true);
                    }
                });
            }


        </script>
        <!-- modal de cuantos productos al hacer clic -->
        <div id="miVentana" style="position: fixed; width: 25%; height: 30%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal; background-color: #FAFAFA; color: white; display:none;">
            <div class="row " style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
                <a class="btn" onclick="ocultarVentanas()"><img src="../images/right.png" width="50" height="50"></a>
                <form action="../Vent" method="get" name="formas">
                    <div class=" modal fade modal-content"></div>
                    <h5 class="h5" align="center">ID del producto</h5>
                    <input type=text name=ids id="cant1" class="form-control input-sm chat-input" placeholder="Id del producto" disabled="disabled"> <br>
                    <h5 class="h5" align="center">Cantidad a comprar</h5>
                    <input type=text name=cant id="cantis"  class="form-control input-sm chat-input" placeholder=Cantidad onchange="tomaralcarro()" required> <br><br>
                    <div align="center" >
                        <a class="btn" name="envio" onclick="tomaralcarro()" id="benviar0"><img   class="img-responsive"  src="../images/ok.png" width=75 height=75></a>
                    </div> 
                </form>
            </div>
        </div>
        <div id="miVen" class="modal-header" style="position: fixed; width: 35%; height: 30%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal;color:white; display:none;">
            <div class="row " style="background-color: #616185;padding-left: 1%;padding-right: 1%;border-radius: 10px;">
                <a class="btn" onclick="ocultars()"><img src="../images/right.png" width="50" height="50"></a>
                <form action="../Nuevavtat_prov" method="post" name="form">
                    <div class=" modal fade modal-content"></div>
                    <h5 class="h5" align="center">Clave de proveedor</h5>
                    <input type=text name=idu id="idu" class="form-control input-sm chat-input" placeholder="Id del Usuario" required> <br>
                    Proveedor: <select class=" text-capitalize" name="tipos" id="tipos" style="color:black" onchange="ru2()">
                        <%
                            try {

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                    </select><br>
                    <h5 class="h5" align="center">Referencia</h5>
                    <input type=text name=ref id="ref" class="form-control input-sm chat-input" placeholder="Referencia" required> <br>
                    <div align="center" >
                        <br><br><input type="submit" class="btn btn-success" name="" value="Completar"><br><br><h4></h4>
                    </div>
                </form>
            </div>
        </div>
        <div id="ress" class="modal-header" style="position: fixed; width: 25%; height: 30%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal; background-color: #FAFAFA; color: white; display:none;">
            <div style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
                <LABEL id="valorr"><big>completo</big></LABEL>
            </div>
        </div>  
    </div>
</body>
</html>
<%
    } catch (Exception e) {
        System.out.println(e);
        response.sendRedirect("../index.jsp");
    }
%>