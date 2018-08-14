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
    if (usuario != null && tipos != null && (tipos.equals("ADMIN")|| tipos.equals("VENTAS")|| tipos.equals("USUARIO")|| tipos.equals("ALTAS"))) {
            if(tipos.equals("ALTAS")){
                response.sendRedirect("productos.jsp");
            }
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
        <title>Desc. de pedido</title>
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
        <div class="container-fluid">
            <!--<button onclick="nuevomostrar()">lolo</button>--> 
            <nav class="navbar navbar-default navbar-inverse">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.jsp"><img src="../images/home.png" class="" width="25"></a>
                </div>
                <ul class="nav navbar-nav">
                    <%if (tipos.equals("ADMIN")) {
                    %>
                    <li class="">
                        <a  class="" href="index.jsp">Captura Pedidos</a>
                    </li>
                    
                    <li class=""><a href="productos.jsp">Productos</a></li>
                            <
                    <li class="dropdown">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Pedidos<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">
                            <li class="active"><a href="verpedidos.jsp">Visualizar Pedidos</a></li>
                        </ul>
                    </li>
                    <li class=""><a  class="" href="consultas.jsp">Consultas</a></li>
                    <li><a href="../Cierresesion">Salir</a></li>
                </ul>
                <div id="" class="nav navbar-nav" style="float:right">
                    <%
                       
                        if (!cor.isEmpty()) {
                            out.print("<li  id=\"carrosid\" s><a style='color:white' href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"> " + " (" + cor.size() + ")</a></li>");
                            
                        } else {
                            out.print("<li  id=\"carrosid\"><a href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"></a></li></div>");
                        }}else if (tipos.equals("VENTAS")) {
                    %>
                    
                    <li class="">
                        <a  class="" href="index.jsp">Captura Pedidos</a>
                    </li>
                    <li class="dropdown active">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Pedidos<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">
                            <li class="active"><a href="verpedidos.jsp">Visualizar Pedidos</a></li>
                        </ul>
                    </li>
                    <li><a href="../Cierresesion">Salir</a></li>
                    </ul>
                    <div id="" class="nav navbar-nav" style="float:right">
                    <%if (!cor.isEmpty()) {
                            out.print("<li  id=\"carrosid\" s><a style='color:white' href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"> " + " (" + cor.size() + ")</a></li>");
                        } else {
                            out.print("<li  id=\"carrosid\"><a href=pedido.jsp><img class=\"imagencesta\" src=\"../images/cesta.png\"></a></li>");
                           
                        }%>
                        </div>
                        <% }else if(tipos.equals("USUARIO")) {
                    %>
                    <li class=""><a href="productos.jsp">Productos</a></li>
                    <li class="dropdown">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Pedidos<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">
                            <li class="active"><a href="verpedidos.jsp">Visualizar Pedidos</a></li>
                        </ul>
                    </li>
                    <li class=""><a  class="" href="consultas.jsp">Consultas</a></li>
                    <li><a href="../Cierresesion">Salir</a></li>
                    <%}%>
            </nav>
            <div class="row" >

                <!--   <div class=" col-md-offset-6"  id="get_catalogo" align="center">
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
                                <td width="10">0</td>
                                <td width="10">1</td>
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
                <div class="container-fluid" align="center" id="desplieguepedido">
                <div class="col-md-5" align="center" id="desc-ped">
                    <%
                      if (!dis.isEmpty()) { %>
                    <div class="row" align="center">
                        <div class="col-xs-1"><label >X</label></div>
                        <div class="col-xs-2"><label >Estilo</label></div>
                        <div class="col-xs-5"><label >Combinacion</label></div>
                        <div class="col-xs-2"><label >Corrida</label></div>
                        <div class="col-xs-2"><label >Pares</label></div>
                        
                    </div>
                    <%
                            int cont = 0;
                            int pares = 0;
                            int totalpares=0;
                            for (int i = 0; i < cor.size(); i++) {
                                float pi = corrida.get(i).getPi();
                                float pf = corrida.get(i).getPf()+1;
                                while (pi < pf) {
                                    //System.out.println(dis.get(cont) +"-"+pi+"-"+pf);
                                    pares += Integer.parseInt(dis.get(cont));
                                    totalpares+=Integer.parseInt(dis.get(cont));
                                    pi += 0.5;
                                    cont++;
                                }
                                out.print("<div class=\"row espacioparavta \" align=\"center\">"
                                        +"<div class=\"col-xs-1\" align=center><a onclick=borrar("+cor.get(i).getProducto()+")><img src=\"../images/delete.png\" class=\"imagentabla\"></a></div>"
                                        + "<div class=\"col-xs-2\"><label >" + cor.get(i).getEstilo() + "</label></div>"
                                        + "<div class=\"col-xs-5 letraparavta\"><label >" + cor.get(i).getCombinacionchar() + "</label></div>"
                                        + "<div class=\"col-xs-2\"><label >" + cor.get(i).getCorridachar() + "</label></div>"
                                        + "<div class=\"col-xs-1\" align=\"center\"><label>" + pares + "</label></div>"
                                        +"</div>");
                                pares = 0;
                            }
                        }else out.print("<div class=\"letrapedidofalso\"><label class=letratotal>El Pedido esta vacio!</label></div>");
                    %>
                </div>
                <%if (!dis.isEmpty()) { 
                  out.print("<div class=\" col-md-offset-5\"  id=\"get_catalogo\" align=\"center\" style=\"padding-top: 2%\">\n" +
"                <div style=\" overflow: auto\"  class=\"col-md-12\" align=\"center\" >\n" +
"                    <table border=\"1\" width=\"5\" class=\"table table-bordered table-condensed table-hover\" style=\"overflow: auto\" align=\"center\">");
                            int cont = 0;
                            for (int i = 0; i < cor.size(); i++) {
                                out.println("<tr align=\"center\" >");
                                float pi = corrida.get(i).getPi();
                                float pf = corrida.get(i).getPf()+1;
                                while (pi < pf) {
                                    out.print("<td width=\"10\"><p class=\"boldtabla\">"+pi+"</p>"+dis.get(cont)+"</td>");
                                    pi += 0.5;
                                    cont++;
                                }
                                out.println("</tr>");
                            }
                            out.print("</table></div></div>");
                }
                %>
                </div>
                

            <div class="row" >
                <div class="col-lg-8 col-lg-offset-2" id="get_catalogo" align="center" style="padding: 2%">
                   <!--<div class="row espas-search-prods">
                        <div class="col-sm-4">
                            <input type="text" id="catalogo" placeholder="Busqueda de productos" class="form-control" onchange="to_searchprod()"> 
                        </div>
                        <%//if (!dis.isEmpty()) { %>
                        <div class="container-fluid">
                            <button style="float:right" class="btn btn-danger" onclick="vaciar()">Vaciar</button>
                        </div> 
                        <% //}%>
                    </div>--> 
                </div>


            </div>
            <div class="row espaciobtn">
                <%if (!dis.isEmpty()) { %>
                <div align="center">
                    <button style=" " class="btn btn-warning" onclick="mostrarVentanas()">Guardar Pedido</button>
                </div>
                <% }%>
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
            <div id="miVentana" style="position: fixed; width: 70%; height: 30%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal; background-color: #FAFAFA; color: white; display:none;">
                <div class="row " style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
                    <a class="btn" onclick="ocultarVentanas()"><img src="../images/right.png" width="50" height="50"></a>
                    <div class="espacio1"> <!-- Cliente -->
                        <div class="col-md-10 espas col-md-offset-1" id="get_catalogo" align="center">
                            <div class="col-md-offset-2">
                                <div class="col-md-5"><label>Fecha Pedido :</label><div class=""><input class="form-control" type="text" id="fp" value="<%=fechac%>" disabled="disable"></div></div>
                                <div class="col-md-5"><label>Fecha Entrega:</label><div class=""><input class="form-control" type="text" id="fe" value="<%=fechac%>"></div></div>
                            </div>
                            <div class="" style="padding-top: 10%">
                                <div class="col-md-4"><label>Nombre Cliente :</label><div class=""><input class="form-control" type="text" id="nc" ></div></div>
                                <div class="col-md-8"><label>Direccion:</label><div class=""><input class="form-control" type="text" id="dir" ></div></div>
                                <!--<div class="col-md-3"><label>RFC:</label><div class=""><input class="form-control" type="text" id="rfc" ></div></div>-->
                            </div>
                            <div class="" style="padding-top: 10%">
                                <div class="col-md-4"><label>Telefono</label><div class=""><input class="form-control" type="text" id="tel" ></div></div>
                                <div class="col-md-8"><label>Email</label><div class=""><input class="form-control" type="text" id="email" ></div></div>
                            </div>
                            <div style="padding-top: 10%">
                                <button class="btn btn-danger" onclick="dopedido()">Finalizar Pedido</button>
                            </div>
                        </div> 
                    </div>
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
        out.print("<script>location='../index.jsp';</script>");
    }
%>