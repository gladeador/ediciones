<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Boleta ::.</title>
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
            function modificar(id_boleta){
                document.formModificar.id_boleta.value = id_boleta;
                document.formModificar.submit();
            }
        </script>

        <script>
            function Confirmar(id_boleta){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Borrar(id_boleta);
            }
        </script>

        <script>
            function Borrar(id_boleta){
                document.formBorrar.id_boleta.value = id_boleta;
                document.formBorrar.submit();
                document.formBorrar.reset();
            }
        </script>

    </head>

    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarBoleta.ed">
            <input name="id_boleta" type="hidden">
        </form>

        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminaBoleta.ed">
            <input name="id_boleta" type="hidden">
        </form>

        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Boletas</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <legend class="txtResaltadoV">Listado de Boletas</legend>
                <table width="100%">
                    <tr>
                        <th>N&deg; de Boleta</th>
                        <th>Rut Cliente</th>
                        <th>Nombre Cliente</th>
                        <th>Total Boleta</th>
                    </tr>
                    <%
                        ArrayList lista_boletas = (ArrayList) request.getAttribute("lista_boleta");
                        Iterator i = lista_boletas.iterator();
                        while (i.hasNext()) {
                            Boleta boleta = (Boleta) i.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><%=boleta.getNum_boleta()%></td>
                        <%
                            if (boleta.getClientes() == null) {
                        %>
                        <td></td>
                        <td></td>
                        <%                        } else {
                        %>
                        <td><%=boleta.getClientes().getRut()%>-<%=boleta.getClientes().getDv()%></td>
                        <td><%=boleta.getClientes().getNombre()%></td>
                        <%
                            }
                        %>
                        <td align="right">$<%=boleta.getTotal()%></td>
                        <th width="1" class="nobg"><a href="javascript:modificar(<%=boleta.getId_boleta()%>);"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Modificar"></a></th>
                        <th width="1" class="nobg"><a href="javascript:Confirmar(<%=boleta.getId_boleta()%>);"><img src="<%=request.getContextPath()%>/images/iconos/eliminar.png" border="0" width="20" height="20" title="Eliminar"></a></th>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </fieldset>
        </form>
    </body>
</html>