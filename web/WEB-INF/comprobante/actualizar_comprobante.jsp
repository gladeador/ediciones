<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Comprobante.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Comprobante ::.</title>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/ediciones.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/tablas.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>

        <script language="JavaScript">
            noF5();
        </script>

    </head>
    <%
        HttpSession sesion = request.getSession();
        Fecha_Actual fecha = (Fecha_Actual) sesion.getAttribute("fecha_actual");
    %>
    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarComprobante.ed">
            <input name="id_comprobante" type="hidden">
        </form>

        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminarComprobante.ed">
            <input name="id_comprobante" type="hidden">
        </form>

        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Comprobante</th>
                </tr>
            </table>
            <br />
            <fieldset>
                <legend class="txtResaltadoV">Listado de Comprobante</legend>
                <table width="100%">
                    <tr>
                        <th>Num Comprobante</th>
                        <th>Fecha</th>
                    </tr>
                    <%
                        ArrayList lista_comprobantes = (ArrayList) request.getAttribute("lista_comprobantes");
                        Iterator lc = lista_comprobantes.iterator();
                        while (lc.hasNext()) {
                            Comprobante comprobante = (Comprobante) lc.next();
                    %>
                    <tbody id="tabla_detalle_comprobante" class="cuadroTexto">
                        <tr class="cuadroTexto">
                            <td><%=comprobante.getNum_comprobante()%></td>
                            <td><%=comprobante.getFecha_comprobanteStr()%></td>
                        </tr>
                    </tbody>
                    <%
                        }
                    %>
                </table>
            </fieldset>
        </form>
    </body>
</html>