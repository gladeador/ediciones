<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Productos.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Modificar Productos ::.</title>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/ediciones.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/tablas.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>

        <script language="JavaScript">
            noF5();
        </script>

        <script>
            function buscarProductos(id_productos){
                Productos.buscar_Productos(id_productos, cargarProductos);
            }
                
            function cargarProductos(data){
                dwr.util.setValues(data);
            }
        </script>       

    </head>

    <body onload="javascript:buscarProductos(<%=request.getParameter("id_productos")%>);" >
        <form name="formData" action="<%=request.getContextPath()%>/controller/ModificarProductos.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Modificar Productos</th>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Descripción del Producto</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Descripción </th>
                                    <td><input name="descripcion" type="text" class="cuadroTexto" id="descripcion" size="30" onchange="javascript:this.value = this.value.toUpperCase();"><input name="id_productos" type="hidden"></td>
                                </tr>
                                <tr>
                                    <th class="specalt">Descripción Corta </th>
                                    <td><input name="desc_corta" type="text" class="cuadroTexto" id="desc_corta" onchange="javascript:this.value = this.value.toUpperCase();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt">Valor Neto </th>
                                    <td><input name="valor_neto" type="text" class="cuadroTexto" id="valor_neto" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="5" maxlength="9"></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <th><input name="b_limpiar" type="reset" id="b_limpiar" class="botones" value="Limpiar" onclick="javascript:buscarProductos(<%=request.getParameter("id_productos")%>);"></th>
                    <th><input name="b_enviar" type="submit" id="b_enviar" class="botones" value="Modificar"></th>
                </tr>
            </table>
        </form>
    </body>
</html>