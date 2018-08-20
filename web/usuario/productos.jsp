
<%@page import="DAO.DAO_Linea"%>
<%@page import="Modelo.Linea"%>
<%@page import="DAO.DAO_Corrida"%>
<%@page import="DAO.DAO_Combinacion"%>
<%@page import="Modelo.Combinacion"%>
<%@page import="Modelo.Corrida"%>
<%@page import="Modelo.Producto"%>
<%@page import="DAO.DAO_Producto"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%
    HttpSession objSesion = request.getSession(false);
    try {
        String usuario = (String) objSesion.getAttribute("usuario");
        String tipos = (String) objSesion.getAttribute("tipo");
        ArrayList<String> dis = (ArrayList<String>) objSesion.getAttribute("distribucion");
        ArrayList<Producto> cor = (ArrayList<Producto>) objSesion.getAttribute("producto");
        ArrayList<Corrida> corrida = (ArrayList<Corrida>) objSesion.getAttribute("corrida");
        //System.out.println(usuario + " " + tipos);
        if (usuario != null && tipos != null && (tipos.equals("ADMIN")|| tipos.equals("VENTAS")|| tipos.equals("USUARIO")|| tipos.equals("ALTAS"))) {
            if(tipos.equals("VENTAS")){
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("../index.jsp");
        }
        ArrayList<Combinacion> comb = new ArrayList<Combinacion>();
        ArrayList<Corrida> corlist = new ArrayList<Corrida>();
        ArrayList<Linea> linlist = new ArrayList<Linea>();
        DAO_Combinacion acomb = new DAO_Combinacion();
        DAO_Corrida acor = new DAO_Corrida();
        DAO_Linea alin = new DAO_Linea();
        linlist=alin.getall();
        corlist=acor.getall();
        comb=acomb.getall();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Productos</title>
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" sizes="32x32" href="../images/aff.png" />
        <link rel='stylesheet' type="text/css" href="../css/bootstrap.min.css">
        <link rel='stylesheet' type="text/css" href="../css/responsive.css">
        <link rel='stylesheet' type="text/css" href="../css/opcional.css">
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery-3.1.1.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/userscrip.js"></script>
        <script>
            // /^[a-zAZ0-9_\.\-]+@[a-zA-Z0-9\-]+
            $(document).ready(function () {
                document.getElementById('catalogo').focus();
            });
            $(document).ready(function () {
                $("#benviar").click(function () {
                    var nombres = $("#name").val();
                    var cmenu = $("#cmenu").val();
                    var cmayo = $("#cmayo").val();
                    var cprodu = $("#cprodu").val();
                    var desc = $("#desc").val();
                    var stock = $("#stock").val();

                    if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cmenu))) {
                        document.form2.cmenu.focus();
                        alert("Coloca solo numeros");
                        return false;
                    }
                    if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cmayo))) {
                        document.form2.cmayo.focus();
                        alert("Coloca solo numeros");
                        return false;
                    }
                    if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cprodu))) {
                        document.form2.cprodu.focus();
                        alert("Coloca solo numeros");
                        return false;
                    }

                    if (!(/^([A-Z\a-z\s\ñ]*)$/.test(desc))) {
                        document.form2.desc.focus();
                        alert("Descripción invalida solo puedes utilizar espacios y letras");
                        return false;
                    }
                    if (!(/^([0-9]+)$/.test(stock))) {
                        document.form2.stock.focus();
                        alert("Coloca solo numeros");
                        return false;
                    }
                });
            });
            $("#benviar10").click(function () {
                var nombres = $("#n").val();
                var cmenu = $("#cmin").val();
                var cmayo = $("#cmay").val();
                var cprodu = $("#cprod").val();
                var stock = $("#stockk").val();

                if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cmenu))) {
                    document.form1.cmin.focus();
                    alert("Coloca solo numeros");
                    return false;
                }
                if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cmayo))) {
                    document.form1.cmay.focus();
                    alert("Coloca solo numeros");
                    return false;
                }
                if (!(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cprodu))) {
                    document.form1.cprod.focus();
                    alert("Coloca solo numeros");
                    return false;
                }
                if (!(/^([0-9]+)$/.test(stock))) {
                    document.form1.stockk.focus();
                    alert("Coloca solo numeros");
                    return false;
                }
            });
        </script>
    </head>
    <body class="body1">
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
                    <li class=""><a  class="" href="index.jsp" >Captura Pedidos</a> </li>
                    <%}%>
                    <%if (tipos.equals("ADMIN") || tipos.equals("USUARIO")||tipos.equals("ALTAS")) {%>
                    <li class="dropdown active">
                        <a  class="dropdown-toggle" data-toggle="dropdown" href="#80">
                            Productos<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" id="#90" role="menu">
                            <li class="active"><a>Productos Generales</a></li>
                            <li><a class="" onclick="etiquetas()">Etiquetas</a></li>
                        </ul>
                    </li>
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
                    <li class=""><a  class="" href="consultas.jsp">Consultas</a></li>
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
                    
                    
            <div class="row">
                <div class="col-md-4 ">
                    <%if (!tipos.equals("USUARIO")){%>
                    <h3 class="h3" align="center">Nuevo producto</h3>
                    <form name="form2" action="../Productos" method="post" class="form-login esp1" style="overflow: auto">
                        Estilo<input class="form-control input-sm chat-input" type="text" name="estilo" id="estilo" value="" maxlength="20"  required/><br>
                        <div class="col-xs-12">
                            <div class="col-xs-12">Combinacion</div>
                            <div class="col-xs-12">
                                <div class="col-xs-8"><input class="form-control input-sm chat-input" type="text" name="combinacion" id="combinacion" value="" maxlength="20"  required/></div>
                                <div class="col-xs-4"><select class="form-control" id="selectcom" onchange="getcombi()"><option></option>
                                    <%
                                    if(!comb.isEmpty()){
                                        for(int i=0;i<comb.size();i++){
                                            out.print("<option class=\"form-control\" value=\""+comb.get(i).getDescripcion()+"\">"+comb.get(i).getDescripcion()+"</option>");
                                        }
                                    }else System.out.println("Vacio");
                                    %>
                                    </select></div>
                            </div>
                        </div><br>
                        <div class="col-xs-12">
                            <div class="col-xs-12">Corrida</div>
                            <div class="col-xs-12">
                                <div class="col-xs-8"><input class="form-control input-sm chat-input" type="text" name="corrida" id="corrida" value="" maxlength="20"  required/></div>
                                <div class="col-xs-4"><select class="form-control" id="selectcor" onchange="getcorri()"><option></option>
                                    <%
                                    if(!corlist.isEmpty()){
                                        for(int i=0;i<corlist.size();i++){
                                            out.print("<option class=\"form-control\" value=\""+corlist.get(i).getCorrida()+"\">"+corlist.get(i).getDesc()+"</option>");
                                        }
                                    }else System.out.println("Vacio");
                                    %>
                                    </select></div>
                            </div>
                        </div><br>
                        <div class="col-xs-12">
                            <div class="col-xs-12">Linea</div>
                            <div class="col-xs-12">
                                <div class="col-xs-8"><input class="form-control input-sm chat-input" type="text" name="linea" id="linea" value="" maxlength="20"  required/></div>
                                <div class="col-xs-4"><select class="form-control" id="selectlin" onchange="getlinea()"><option></option>
                                    <%
                                    if(!linlist.isEmpty()){
                                        for(int i=0;i<linlist.size();i++){
                                            out.print("<option class=\"form-control\" value=\""+linlist.get(i).getDescripcion()+"\">"+linlist.get(i).getDescripcion()+"</option>");
                                        }
                                    }else System.out.println("Vacio");
                                    %>
                                    </select></div>
                            </div>
                        </div><br><br>
                         Costo<input class="form-control input-sm chat-input" type="text" name="costo" id="costo" value=""  required/><br>
                        Tipo <input class="form-control input-sm chat-input" type="text" name="tipo" id="tipo" value=""  required/><br>
                        Marca <select onchange="" class="form-control" name="marca" id="marca" required>
                            <option>Traffic</option>
                            <option>Red Traffic</option>
                            <option>OCastell</option>
                            <option>Duty Gear</option>
                            <option>OC Tactical</option>
                            <option>OCastell Seguridad</option>
                        </select><br>
                        <input type="submit" class="btn btn-success" value="Aceptar" name="benviar" id="benviar" />
                        <br><br>
                        <input style="display: none" class="form-control input-sm chat-input" type="text" name="uso" id="uso" value="nuevo"/><br>
                    </form><%}%>
                </div>
                <div class="col-md-8">
                    <h3 class="h3" align="center">Vista general de productos</h3>
                    <div class="row espas-search-prods">
                        <div class="col-sm-4">
                            <input type="text" id="catalogo" placeholder="Busqueda de productos" class="form-control" onkeypress="to_searchprod_cata()"> 
                        </div>                    
                    </div>
                    <div class="col-sm-offset-1z esp1"  style="overflow: auto" >
                        <table class="table table-responsive table-hover mapa" id="tabla-prods">
                            <thead>
                                <th>Estilo</th>
                                <th>Combinacion</th>
                                <th>Corrida</th>
                                <th>Linea</th>
                                <th>Costo</th>
                                <th>Tipo</th>
                                <!--<td>Borrar</td>--> 
                                <th>Submarca</th>
                            </thead>
                        <%       try {
                                    DAO_Producto dp = new DAO_Producto();
                                    ArrayList<Producto> arr = new ArrayList<Producto>();
                                    arr = dp.buscarall();
                                    for(int i =0;i<arr.size();i++){
                                    out.print("<tr>");
                                    out.print("<td>"+arr.get(i).getEstilo()+"</td>");
                                    out.print("<td>" + arr.get(i).getCombinacionchar() + "</td>");
                                    out.print("<td>" + arr.get(i).getCorridachar()+"</td>");
                                    out.print("<td>" + arr.get(i).getLineachar()+ "</td>");
                                    out.print("<td>" + arr.get(i).getCostof()+ "</td>");
                                    out.print("<td>" + arr.get(i).getTipo()+ "</td>");
                                    out.print("<td>" + arr.get(i).getMarca()+ "</td>");
                                    //out.print("<td><a name=mod value=" + arr.get(i).getProducto() +" class=btn onclick=modi(" + arr.get(i).getProducto() + ")><img src=\"../images/modificar.png\" width=30 height=30></a></td>");
                                    out.print("</tr>");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            %>
                        </table>
                    </div>
                </div>
            </div>
            <script>
                function etiquetas() {
                    var estilo = $('#catalogo').val();
                    location="etiquetas.jsp?catalogo="+estilo;
                    
                }
                function to_searchprod() {
                    var catalogo = $('#catalogo').val();
                    var uso = "catalago_general";
                    $.ajax({
                        type: 'post',
                        data: {p: catalogo, uso: uso},
                        url: '../Getregs',
                        success: function (result) {
                            $('#tabla-prods').html(result);
                        }
                    });
                }
                function mostrarVentana1(id, n, cmay, cmin, st, cprod)
                {
                    document.getElementById("cprod").value = cprod;
                    document.getElementById("id").value = id;
                    document.getElementById("benviar10").value = id;
                    document.getElementById("n").value = n;
                    document.getElementById("cmay").value = cmay;
                    document.getElementById("cmin").value = cmin;
                    document.getElementById("stockk").value = st;
                    var ventana = document.getElementById("miVentana1");
                    ventana.style.marginTop = "30px";
                    //ventana.style.left = ((document.body.clientWidth) / 2) +  "px";
                    ventana.style.display = "block";
                    ventana.style.left = 20 + "%";
                }
                function ocultarVentana1()
                {
                    var ventana = document.getElementById("miVentana1");
                    ventana.style.display = "none";
                }
                function agregarmodificacion() {
                    var benviar1 = $("#id").val();
                    var n = $("#n").val();
                    var p = $("#cmay").val();
                    var calle = $("#cmin").val();
                    var tel = $("#stockk").val();
                    var tipos = $("#tipos").val();
                    var cprod = $("#cprod").val();

                    if (n != "" || p != "" || calle != "" || tel != "" || cprod != "") {
                        if (!(/^([A-Z\a-z\_]+)$/.test(n)) || !(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(p)) || !(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(calle)) || !(/^([0-9]+)\.([0-9\s]*)|([0-9]+)$/.test(cprod)) || !(/^([0-9]+)$/.test(tel))) {
                            alert("Verifique los campos");
                            return false;
                        } else {
                            window.location.href = "../Modificarprod?benviar1=" + benviar1 + "&n=" + n + "&cmay=" + p + "&cmen=" + calle + "&cprod=" + cprod + "&tipos=" + tipos + "&stock=" + tel;
                        }
                    } else {
                        alert("No puedes modificar un usuario con campos vacios");
                    }
                }
                function modi(id)
                {
                   // window.location.href = "Editarprod.jsp?modi=" + id;
                }
                function eliminar(id)
                {
                    window.location.href = "../Borrarproductot?borrar=" + id;
                }
            </script>
            <div id="miVentana1" class="modal-scrollbar-measure" style="position: fixed; width: 60%; height: 90%; top: 0; left: 0; font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 5px; font-weight: normal; color: white; display:none;">
                <div class="">      
                    <div class="" style="background-color: #616185;padding-left: 2%;padding-right: 2%;border-radius: 10px;">
                        <a onclick="ocultarVentana1()"><img src="../images/der.png"></a> 
                        <form action="" onsubmit="" name="form1"  style="">
                            <div class=" modal fade modal-content "></div>
                            <h5 class="h5" align="center">ID del producto</h5>
                            <input type=text name="id" id="id" class=form-control input-sm chat-input placeholder=""  disabled="disabled" required> <br>
                            <h5 class="h5" align="center">Nombre</h5>
                            <input type=text name="n" id="n"  class=form-control input-sm chat-input placeholder="" required> <br>
                            <h5 class="h5" align="center">Costo menudeo</h5>
                            <input type=text name=cmay id="cmay" class=form-control input-sm chat-input placeholder="" required> <br>
                            <h5 class="h5" align="center">Costo mayoreo</h5>
                            <input type=text name=cmen id="cmin" class=form-control input-sm chat-input placeholder="" required> <br>
                            <h5 class="h5" align="center">Costo Producción</h5>
                            <input type=text name=cprod id="cprod" class=form-control input-sm chat-input placeholder="" required> <br>
                            <h5 class="h5" align="center">Stock</h5>
                            <input type=text name=stock id="stockk" class=form-control input-sm chat-input placeholder="4772727573"> <br>
                            <h4 class="h4">  Habilitar Producto : <select name="tipos" style="color: gray" id="tipos">
                                    <option>SI</option>
                                    <option>NO</option>
                                </select></h4> 
                            <div align="center">
                                <a class=" btn" name="benviar" id="benviar10" onclick="agregarmodificacion()"><img src="../images/modificar.png" class="img-responsive" width=50 height=50></a>
                                <br><br></div>
                        </form>
                    </div>
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
