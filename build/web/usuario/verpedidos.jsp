<%-- 
    Document   : Verpares
    Created on : 05/10/2017, 02:01:15 PM
    Author     : mich
--%>
<%@page import="Modelo.Producto"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<% HttpSession objSesion = request.getSession(false);
    try {

        String usuario = (String) objSesion.getAttribute("usuario");
        String tipos = (String) objSesion.getAttribute("tipo");
        String ids = String.valueOf(objSesion.getAttribute("i_d"));
        if (usuario != null && tipos != null && (tipos.equals("ADMIN") || tipos.equals("VENTAS") || tipos.equals("USUARIO") || tipos.equals("ALTAS"))) {
            if (tipos.equals("ALTAS")) {
                response.sendRedirect("productos.jsp");
            }
        } else {
            response.sendRedirect("../index.jsp");
        }

        ArrayList<Producto> cor = (ArrayList<Producto>) objSesion.getAttribute("producto");
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
            fechaca = fechaca + "-0" + (mes) + "-" + año;
        } else {
            fechac = fechac + "-" + mes + "-" + año;
            fechaca = fechaca + "-" + (mes) + "-" + año;
        }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="1200">
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Pedidos</title>
        <link rel="icon" sizes="32x32" href="../images/aff.png" >
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel='stylesheet' type="text/css" href="../css/opcional.css">
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery-3.1.1.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/userscrip.js"></script>
        <script type="text/javascript" src="../js/dhtmlgoodies_calendar.js?random=20171118"></script>
        <link type="text/css" rel="stylesheet" href="../css/dhtmlgoodies_calendar.css?random=20171118" media="screen">
    </head>
    <body class="body1">
        <script type="text/javascript">
            $(document).ready(function () {
                document.getElementById('peds').focus();
            });
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+
            function valida_envia() {
                var valor = $("#f1").val();
                //(/^\d{4}\-\d{2}|\d{1}\-\d{2}$/i.test(valor))
                if (!(/^\d{2}|\d{1}\-\d{2}\-\d{4}\$/i.test(valor))) {
                    alert("fecha invalida invalido");
                    document.forma.f1.focus();
                    document.getElementById("f1").value = "<%=fechac%>";
                    return 0;
                }
                var valor1 = $("f2").val();
                if (!(/^([A-Za-z\d]*)$/i.test(valor1))) {
                    alert("Contraseña invalida! ");
                    document.forma.contrasenalog.focus();
                    document.getElementById("f2").value = "";
                    return 0;
                }
            }
        </script>

        <div class="container-fluid">
            <nav class="navbar navbar-default navbar-inverse">
                <div class="navbar-header" >
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"></a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <%if (tipos.equals("ADMIN") || tipos.equals("VENTAS")) {%>
                        <li class=""><a  class="" href="index.jsp">Captura Pedidos</a> </li>
                            <%}%>
                            <%if (tipos.equals("ADMIN") || tipos.equals("USUARIO") || tipos.equals("ALTAS")) {%>
                        <li class=""><a  class="" href="productos.jsp">Productos</a> </li>
                            <%}%>
                            <%if (tipos.equals("ADMIN") || tipos.equals("USUARIO") || tipos.equals("VENTAS")) {%>
                        <li class="dropdown active">
                            <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                                Pedidos<span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" id="#90" role="menu">
                                <li class="active"><a>Visualizar Pedidos</a></li>
                            </ul>
                        </li>
                        <%}%>
                        <%if (tipos.equals("ADMIN") || tipos.equals("USUARIO")) {%>
                        <li class=""><a  class="" href="consultas.jsp">Consultas</a></li>
                            <%}%>
                        <li><a href="../Cierresesion">Salir</a></li>
                    </ul>
                    <div id="" class="nav navbar-nav" style="float:right" >
                        <%if (tipos.equals("ADMIN") || tipos.equals("VENTAS")) {
                                if (!cor.isEmpty()) {
                                    out.print("<li  id=\"carrosid\" s><a style='color:white' href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"> " + " (" + cor.size() + ")</a></li>");

                                } else {
                                    out.print("<li  id=\"carrosid\"><a href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"></a></li>");
                                }
                            }
                        %>
                    </div>
                </div>
            </nav>
            <div class="container">
                <div class="row">
                    <div class="col-md-12 " >
                        <div class="col-sm-offset-3" >
                            <form name="forma" >
                                <div class="col-xs-3" align="center">
                                    <label class="ln">Inicial</label><input class="form-control input-sm chat-input ln" type="text" name="f1" id="f1" value="<%=fechaca%>" onchange="verp()" maxlength="10"  required/>
                                </div>
                                <div class="col-xs-2">
                                    <br>
                                    <input type="button" class="btn btn-toolbar alert-success ln" value="Cal" onclick="displayCalendar(document.forms[0].f1, 'dd-mm-yyyy', this)"/>
                                </div>
                                <div class="col-xs-3" align="center">
                                    <label class="ln" >Final</label><input class="form-control input-sm chat-input ln" type="text" name="f2" id="f2" value="<%=fechac%>" onchange="verp()" maxlength="10" required/>
                                </div>
                                <div class="col-xs-2">
                                    <br>
                                    <input type="button" value="Cal" class="btn btn-toolbar alert-success ln" onclick="displayCalendar(document.forms[0].f2, 'dd-mm-yyyy', this)"/>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
               <div class="container-fluid">
                        <div class="row" >
                        <div class=" col-md-6 col-sm-6">
                        <div class="col-md-5">
                            <br><input type="text" id="peds" placeholder="Busqueda de productos" class="form-control" onkeyup="verp()"> 
                        </div>
                        <div class="col-md-3">
                            <br><a target="_blank"><button onclick="getreport()" class="btn btn-success">Generar Reporte</button></a> 
                        </div>
                    </div>
                    <div class="  col-md-6 col-xs-9" align="center">
                        <div class="col-xs-5" align = center>
                            <br>
                        <label class="">Activo</label>
                        <input type="radio" name="status" id="staus" value="a" checked="checked" onclick="verp()"/>
                        </div>
                        <div class="col-xs-5" align = center>
                            <br>
                        <label class="">Cancelada</label>
                        <input type="radio" name="status" id="status" value="c" onclick="verp()"/>
                        </div>
                    </div>
                    
                    </div>
                </div>

                <!--<div class="row">
                        <div class="container">
                            <div class="col-sm-3">
                                Buscar Combinacion
                            <input type="text" name="buscaprodd" id="buscaprodd" class=" form-control input-sm chat-input" Onkeypress="buscacom()">
                            </div>
                            </div>
                    </div> -->    
                <div class="container-fluid" style="overflow: auto;">
                    <div class="  "  style="overflow: auto" >
                        <div class="" style="overflow: auto;">
                            <br><table  id="tablesorter-demo" class="table table-hover table-responsive" style="overflow: auto;">
                                <thead class="enctabla " style="overflow: auto;" align="center">
                                <th class="ln" align="center">Pedido</th>
                                <th class="ln" align="center">Fecha Pedido</th>
                                <th class="ln" align="center">Fecha Entrega</th>
                                <th class="ln">Cliente</th>
                                <th class="ln">Cantidad</th>
                                <th class="ln">Importe</th>
                                <th class="ln">Serie</th>
                                <th class="ln">folio</th>
                                </thead>
                                <tbody id="llenar" class="" style="overflow: auto;">
                                </tbody> 
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function verp() {
                var valor = $('#f1').val();
                var valor1 = $('#f2').val();
                if (!(/^\d{2}|\d{2}\-\d{2}\-\d{4}\$/i.test(valor)) && !(/^\d{2}|\d{2}\-\d{2}\-\d{4}\$/i.test(valor1))) {
                    alert("fecha invalida invalido");
                    //document.forma.f1.focus();
                    document.getElementById("f1").value = "<%=fechac%>";
                    document.getElementById("f2").value = "<%=fechac%>";
                    return 0;
                } else {
                    var pro = $('#f1').val();
                    var pro1 = $('#f2').val();
                    var peds = $('#peds').val();
                    var report=$('input:radio[name=status]:checked').val();
                    var uso = "fechas";
                    $.ajax({
                        type: 'post',
                        data: {f1: pro, f2: pro1, uso: uso, busqueda: peds,status:report},
                        url: '../Carrito',
                        success: function (result) {
                            $('#llenar').html(result);
                        }
                    });
                }
            }
            function getreport() {
                /*var f1 = $('#f1').val(); 
                var f2 = $('#f2').val();
                var peds = $('#peds').val();
                var report=$('input:radio[name=status]:checked').val();
                location.href = "reportevta.jsp?f1=" + f1 + "&f2=" + f2 + "&peds=" + peds+"&report="+report;*/
               location = "reportevta_pedido.jsp?peds=11 V";
               //location="pedido_1.jsp";
            }
        </script>
    </body>
</html>
<%
    } catch (Exception e) {
        out.print("location = ../index.");
    }
%>