<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Salidas.js'></script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Salidas ::.</title>
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
            function Listar() {
                var id_tipo_salida = document.getElementById("id_tipo_salida").value;
                var id_productos = document.getElementById("id_productos").value;

                Salidas.detalle_salidas(id_tipo_salida, id_productos, cargarDetalle_Salidas);
            }

            function cargarDetalle_Salidas(data) {
             /*   console.log(data);
                alert(data.cantidad);
                return;*/
                dwr.util.useLoadingMessage("Cargando...");
                var id;
                var cont = 1;
                var cellFuncs = [
                    function (data) {id++;
                        return data.tipo_salida.descripcion;},
                    function (data) {
                        return "<div align=\"center\">" + data.fecha_salidaStr + "</div>";},
                    function (data) {
                        return "<div align=\"center\">" + data.cantidad + "</div>";},
                    function (data) {
                        if (data.observaciones == null || data.observaciones == "") {
                            return "Sin Observaciones";
                        } else {
                            return data.observaciones;
                        }
                    },
                    function (data) {
                        return "<th width=\"1\" class=\"nobg\"><a href=\"javascript:modificar(" + data.id_salida + ");\"><img src=\"<%=request.getContextPath()%>/images/iconos/modificar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Modificar\"></a></th>";
                    },
                    function (data) {
                        return "<th width=\"1\" class=\"nobg\"><a href=\"javascript:Confirmar(" + data.id_salida + ");\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a></th>";
                    }
                ]
                id = -1;

                dwr.util.removeAllRows("tabla_detalle_salidas",
                        {filter: function (tr)
                            {
                                return (tr.id != "pattern");
                            }});
                dwr.util.addRows("tabla_detalle_salidas", data, cellFuncs, {escapeHtml: false});
            }

            function modificar(id_salida) {
                document.formModificar.id_salida.value = id_salida;
                document.formModificar.submit();
            }

            function Confirmar(id_salida) {
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")) {
                    return;
                }
                Borrar(id_salida);
            }

            function Borrar(id_salida) {
                document.formBorrar.id_salida.value = id_salida;
                document.formBorrar.submit();
                document.formBorrar.reset();
            }

            function Limpiar() {
                dwr.util.removeAllRows("tabla_detalle_salidas",
                        {filter: function (tr)
                            {
                                return (tr.id != "pattern");
                            }});
                document.formData.reset();
            }
        </script>

    </head>

    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarSalidas.ed">
            <input name="id_salida" type="hidden">
        </form>

        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminarSalidas.ed">
            <input name="id_salida" type="hidden">
        </form>

        <form name="formData" action="<%=request.getContextPath()%>/controller/ActualizarSalidas.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Salidas</th>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Detalles de la Salida</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Tipo de Salida </th>
                                    <td valign="top">
                                        <select name="id_tipo_salida" class="cuadroTexto" id="id_tipo_salida" onchange="javascript:tipo_cambio(this.value);">
                                            <option value="">Todas</option>
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
                                            <option value="">Todos</option>
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
                            </table>
                        </fieldset>
                        <table align="center">
                            <tr>
                                <td><a href="javascript:Limpiar();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/limpiar.png" border="0" width="30" height="30" title="Limpiar"></a></td>
                                <td><a href="javascript:Listar();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/buscar.png" border="0" width="30" height="30" title="Buscar"></a></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <br>
            <fieldset>
                <table align="center" width="100%">
                    <tr>
                        <th width="25%">Tipo de Salida</th>
                        <th width="20%">Fecha Salida</th>
                        <th width="10%">Cantidad</th>
                        <th width="44%">Observaciones</th>
                    </tr>
                    <tbody id="tabla_detalle_salidas" class="subTitulos">
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </fieldset>
        </form>
    </body>
</html>