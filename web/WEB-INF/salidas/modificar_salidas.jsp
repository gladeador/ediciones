<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Salidas.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Modificar Salidas ::.</title>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/ediciones.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/tablas.css">
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/js_calendar/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/js_calendar/dhtmlgoodies_calendar.js?random=20060118"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>

        <script language="JavaScript">
            noF5();
        </script>

        <script>
            function buscar_salida(id_salida){
                Salidas.buscar_salidas(id_salida, cargarSalidas);
            }
            function cargarSalidas(data){
                DWRUtil.setValues(data);
                document.getElementById("fecha_salida").value = data.fecha_salidaStr;
                document.getElementById("id_tipo_salida").value = data.tipo_salida.id_tipo_salida;
                document.getElementById("id_productos").value = data.productos.id_productos;
            }
            
            function Enviar(){
                var cantidad = document.getElementById("cantidad").value;
                var fecha_salida = document.getElementById("fecha_salida").value;
                if(cantidad == 0){
                    alert("Debe ingresar una salida superior a 0");
                    return;
                }
                if(fecha_salida == ""){
                    alert("Debe ingresar una Fecha de Salida");
                    return;
                }
                document.formData.submit();
            }
            
            function Limpiar(){
                var id_salida = document.getElementById("id_salida").value;
                document.formData.reset();
                buscar_salida(id_salida);
            }
        </script>

    </head>

    <body onload="javascript:buscar_salida(<%=request.getParameter("id_salida")%>);">
        <form name="formData" action="<%=request.getContextPath()%>/controller/ModificarSalidas.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Modificar Salidas</th>
                </tr>
            </table>
            <br>
            <table align="center" width="100%">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Detalles de la Salida</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Tipo de Salida </th>
                                    <td valign="top">
                                        <select name="id_tipo_salida" class="cuadroTexto" id="id_tipo_salida" onchange="javascript:tipo_cambio(this.value);">
                                            <%
                                                Tipo_SalidaDAO salDAO = new Tipo_SalidaDAO();
                                                ArrayList lista_tipo_salidas = salDAO.traerTodos_Tipo_Salida();
                                                Iterator t_s = lista_tipo_salidas.iterator();
                                                while (t_s.hasNext()) {
                                                    Tipo_Salida tipo_salida = (Tipo_Salida) t_s.next();
                                            %>
                                            <option value="<%=tipo_salida.getId_tipo_salida()%>"><%=tipo_salida.getDescripcion()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Producto </th>
                                    <td valign="top">
                                        <select name="id_productos" class="cuadroTexto" id="id_productos">
                                            <%
                                                ProductosDAO proDAO = new ProductosDAO();
                                                ArrayList lista_productos = proDAO.traerTodos_Productos();
                                                Iterator p = lista_productos.iterator();
                                                while (p.hasNext()) {
                                                    Productos productos = (Productos) p.next();
                                            %>
                                            <option value="<%=productos.getId_productos()%>"><%=productos.getDescripcion()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt">Cantidad </th>
                                    <td><input name="cantidad" type="text" class="cuadroTexto" id="cantidad" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="5" maxlength="9" value="0"><input name="id_salida" type="hidden" id="id_salida"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Fecha Salida </th>
                                    <td valign="middle">
                                        <input name="fecha_salida" type="text" class="cuadroTexto" id="fecha_salida" align="left" readonly>
                                        <button onclick="javascript:displayCalendar(document.forms[0].fecha_salida,'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Observaciones </th>
                                    <td valign="top"><textarea name="observaciones" class="cuadroTexto" id="observaciones" maxlength="200" cols="50" rows="3"></textarea></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <th><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:Limpiar();" value="Limpiar"></th>
                    <th><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:Enviar();" value="Modificar"></th>
                </tr>
            </table>
        </form>
    </body>
</html>