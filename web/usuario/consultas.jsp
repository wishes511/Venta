<%-- 
    Created on : 12/04/2018, 11:17:46 AM
    Author     : mich
--%>
<%@page import="Modelo.Corrida"%>
<%@page import="Modelo.Producto"%>
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
        ArrayList<Producto> cor = (ArrayList<Producto>) objSesion.getAttribute("producto");
        ArrayList<Corrida> corrida = (ArrayList<Corrida>) objSesion.getAttribute("corrida");
        System.out.println(usuario + " " + tipos);
        if (usuario != null && tipos != null && (tipos.equals("ADMIN"))) {

        } else {
            response.sendRedirect("../index.jsp");
        }
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        String fechac = dia+"-"+mes+"-"+año;
        String fechaca = "";
       

%>
<!DOCTYPE html>
<html>
    <head>
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
        <div class="container-fluid" style="">
            <!--<label class="">Ingrese Codigo de Producto:</label>
                            <input type="password" id="catalogo" placeholder="Busqueda de productos" class="form-control" onchange="to_searchprod()"> --> 
            <nav class="navbar navbar-default navbar-inverse">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.jsp"><img src="../images/home.png" class="" width="25"></a>
                </div>
                <ul class="nav navbar-nav">
                    <%                        if (tipos.equals("ADMIN") || tipos.equals("AMECANICA")) {
                    %>
                    <li class="">
                        <a  class="" href="index.jsp">Captura Pedidos</a>
                    </li>
                    <li class="">
                        <a  class="" href="productos.jsp">Productos</a>
                    </li>
                    <li class="dropdown">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Pedidos<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">
                            <li class=""><a href="verpedidos.jsp">Visualizar Pedidos</a></li>
                        </ul>
                    </li>
                    <li class="active">
                        <a  class="" >Consultas</a>
                    </li>
                    
                    <li><a href="../Cierresesion">Salir</a></li>
                </ul>
                <div id="" class="nav navbar-nav" style="float:right">
                    <%
                        }
                        if (!cor.isEmpty()) {
                            out.print("<li  id=\"carrosid\" s><a style='color:white' href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"> " + " (" + cor.size() + ")</a></li>");
                            for (int i = 0; i < cor.size(); i++) {
                                System.out.println(dis.size()+" "+cor.size() + " -" + i + " " + cor.get(i).getProducto());
                            }
                        } else {
                            out.print("<li  id=\"carrosid\"><a href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"></a></li>");

                        }
                    %>
                </div>
            </nav>

            <div class="row"  style="" align="center">
                <div class="col-md-8 col-lg-offset-2 " id="get_catalogo" align="center" style="padding: 2%;background-color:graytext;border-radius: 8px">
                    <div class="row espas-search-prods">
                        <div class="col-xs-4">
                            <div class="col-xs-8"><label class="">Clasificación</label></div>
                            <div class="col-xs-4"><input type="radio" name="report" id="report" value="clasificacion" checked="checked" onclick="getfields()"/></div>
                        </div>
                        <div class="col-xs-4">
                            <div class="col-xs-4"><label>Linea</label></div>
                            <div class="col-xs-8"><input type="radio" name="report" id="report" value="linea"  /></div>
                            
                            
                        </div>
                        <div class="col-xs-4">
                            <div class="col-xs-4"><label>Cliente</label></div>
                            <div class="col-xs-8"><input type="radio" name="report" id="report" value="cliente" /></div>
                            
                            
                        </div>
                    </div>
                </div>
            </div>
                <div class="row espacioparaped"  style="" align="center">
                <div class="col-md-2 col-md-offset-5" id="get_catalogo" align="center" style="padding: 2%;background-color:blueviolet;border-radius: 8px" >
                    <div class="row espas-search-prods">
                        <div class="col-md-6" align="center">
                            <label>Concentrado</label>
                            <input type="radio" name="tipo" id="tipo" value="concentrado" checked="checked" />
                        </div>
                        <div class="col-md-5"  align="center">
                            <label>Detallado</label>
                           <input type="radio" name="tipo" id="tipo" value="detallado" />
                        </div>
                    </div>
                </div>
            </div>
                <div class="row" align="center">
                    
                    <div class="col-md-2 col-md-offset-5" id="getselect">
                        <label>Nombre:</label>
                        <select class="form-control"  id="selectbuscar" onchange="salto()">
                        <option class="form-control">pruebas</option>.
                        <option class="form-control">pruebas1</option>
                        </select>
                    </div>
                    
                    
                </div>
                <div class="row espacioparaped" align="center">
                    <button class="btn btn-success" id="gobusca">Buscar</button>
                </div>
                    <div id="distribucion"></div>
            <div class="row espaciobtn">
            </div>
                
            <script>
                function mostrarVentanas()
                {
                    var ventana = document.getElementById("miVentana");
                    ventana.style.marginTop = "150px";
                    //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                    ventana.style.display = "block";
                    ventana.style.left = 15 + "%";
                    document.getElementById("cantis").focus();
                }
                function ocultarVentanas()
                {
                    var ventana = document.getElementById("miVentana");
                    ventana.style.display = "none";
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

            </script>
            <!-- modal de cuantos productos al hacer clic -->

</div>
        
                            
    </body>
</html>
<%
    } catch (Exception e) {
        System.out.println(e);
        out.print("<script>location='../index.jsp';</script>");
    }
%>