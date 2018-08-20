<%-- 
    Created on : 12/04/2018, 11:17:46 AM
    Author     : mich
--%>
<%@page import="Modelo.Corrida"%>
<%@page import="Modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%   HttpSession objSesion = request.getSession(false);
    try {
        String usuario = (String) objSesion.getAttribute("usuario");
        String tipos = (String) objSesion.getAttribute("tipo");
        ArrayList<String> dis = (ArrayList<String>) objSesion.getAttribute("distribucion");
        ArrayList<Producto> cor = (ArrayList<Producto>) objSesion.getAttribute("producto");
        ArrayList<Corrida> corrida = (ArrayList<Corrida>) objSesion.getAttribute("corrida");
        //System.out.println(usuario + " " + tipos);
            if (usuario != null && tipos != null && (tipos.equals("USUARIO") || tipos.equals("VENTAS") || tipos.equals("ADMIN")|| tipos.equals("ALTAS"))) {
            if(tipos.equals("ALTAS")){
                response.sendRedirect("usuario/productos.jsp");
            }else if(tipos.equals("VENTAS")){
                response.sendRedirect("usuario/index.jsp");
            }
        } else {
            response.sendRedirect("../index.jsp");
        }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogo de Productos</title>
        <link rel="icon" sizes="32x32" href="../images/aff.png" />
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel='stylesheet' type="text/css" href="../css/opcional.css">
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery-3.1.1.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/userscrip.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                document.getElementById('catalogo').focus();
            });
        </script>
    </head>
    <body class="boldtabla_noitalicV2">
        <div class="container-fluid" style="">
            <!--<label class="">Ingrese Codigo de Producto:</label>
                            <input type="password" id="catalogo" placeholder="Busqueda de productos" class="form-control" onchange="to_searchprod()"> --> 
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
                    <li class=""><a  class="" href="index.jsp" >Captura Pedidos</a> </li>
                    <%}%>
                    <%if (tipos.equals("ADMIN") || tipos.equals("USUARIO")||tipos.equals("ALTAS")) {%>
                    <li class=""><a  class="" href="productos.jsp">Productos</a> </li>
                    <%}%>
                    <%if (tipos.equals("ADMIN") || tipos.equals("USUARIO")||tipos.equals("VENTAS")) {%>
                    <li class="dropdown">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Pedidos<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">
                            <li class=""><a href="verpedidos.jsp">Visualizar Pedidos</a></li>
                        </ul>
                    </li>
                    <%}%>
                    <%if (tipos.equals("ADMIN") || tipos.equals("USUARIO")){%>
                    <li class="active"><a  class="">Consultas</a></li>
                    <%}%>
                    <li><a href="../Cierresesion">Salir</a></li>
                </ul>
                <div id="" class="nav navbar-nav" style="float:right" >
             <%if (tipos.equals("ADMIN")||tipos.equals("VENTAS")) {
                        if (!cor.isEmpty()) {
                            out.print("<li  id=\"carrosid\" s><a style='color:white' href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"> " + " (" + cor.size() + ")</a></li>");
                            
                        } else {
                            out.print("<li  id=\"carrosid\"><a href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"></a></li>");
                        }}
                    %>
                </div>
                </div>
            </nav>
                <div class="">
                <div class="row espacioparavta"  style="" align="center">
                <div class="col-md-8 col-lg-offset-2 cuadromenu" id="get_catalogo" align="center" style="">
                    <div class="row espas-search-prods letracuadro_menu">
                        <div class="col-xs-4">
                            <div class="col-xs-8"><label class="">Linea</label></div>
                            <div class="col-xs-4"><input type="radio" name="report" id="report" value="linea" checked="checked" onclick="getfields()"/></div>
                        </div>
                        <div class="col-xs-4">
                            <div class="col-xs-4"><label>Clasificación</label></div>
                            <div class="col-xs-8"><input type="radio" name="report" id="report" value="clasificacion"  onclick="getfields()"/></div>
                        </div>
                        <div class="col-xs-4">
                            <div class="col-xs-4"><label>Cliente</label></div>
                            <div class="col-xs-8"><input type="radio" name="report" id="report" value="cliente" onclick="getfields()"/></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row espacioparaped"  style="" align="center">
                <div class="col-md-2 col-md-offset-5 cuadromenu" id="get_catalogo" align="center"  >
                    <div class="row espas-search-prods">
                        <div class="col-md-6" align="center">
                            <label>Concentrado</label>
                            <input type="radio" name="tipo" id="tipo" value="concentrado" checked="checked" onclick="getfields()"/>
                        </div>
                        <div class="col-md-5"  align="center">
                            <label>Detallado</label>
                            <input type="radio" name="tipo" id="tipo" value="detallado" onclick="getfields()"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" align="center">
                <div class="col-md-2 col-md-offset-5" id="getselect"></div>
            </div>
            <div class="row espacioparaped" align="center">
                <button class="btn btn-success" id="gobusca" onclick="getreport()">Generar reporte</button>
            </div>
            <div id="distribucion"></div>
            </div>    
            
            <div class="row espaciobtn">
            </div>
        </div>


    </body>
</html>
<%
    } catch (Exception e) {
        System.out.println(e);
        out.print("<script>location='../index.jsp';</script>");
    }
%>