<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Guías ::.</title>
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
            function modificar(id_guia_despacho){
                document.formModificar.id_guia_despacho.value = id_guia_despacho;
                document.formModificar.submit();
            }
        </script>

        <script>
            function Confirmar(id_guia_despacho){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Borrar(id_guia_despacho);
            }
        </script>

        <script>
            function Borrar(id_guia_despacho){
                document.formBorrar.id_guia_despacho.value = id_guia_despacho;
                document.formBorrar.submit();
                document.formBorrar.reset();
            }
        </script>

    </head>

    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarGuiaDespacho.ed">
            <input name="id_guia_despacho" type="hidden">
        </form>

        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminarGuiaDespacho.ed">
            <input name="id_guia_despacho" type="hidden">
        </form>

        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Guías</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <legend class="txtResaltadoV">Listado de Guías</legend>
                <table width="100%">
                    <tr>
                        <th>Nº de Guía</th>
                        <th>Rut Cliente</th>
                        <th>Nombre Cliente</th>
                        <th>Total Guía</th>
                    </tr>
                    <%
                        ArrayList lista_guias = (ArrayList) request.getAttribute("lista_guias");
                        Iterator i = lista_guias.iterator();
                        while (i.hasNext()) {
                            Guia_Despacho guia = (Guia_Despacho) i.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><%=guia.getNum_guia()%></td>
                        <%
                            if (guia.getClientes() == null) {
                        %>
                        <td></td>
                        <td></td>
                        <%                        } else {
                        %>
                        <td><%=guia.getClientes().getRut()%>-<%=guia.getClientes().getDv()%></td>
                        <td><%=guia.getClientes().getNombre()%></td>
                        <%
                            }
                        %>
                        <td align="right">$<%=guia.getTotal()%></td>
                        <th width="1" class="nobg"><a href="javascript:modificar(<%=guia.getId_guia_despacho()%>);"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Modificar"></a></th>
                        <th width="1" class="nobg"><a href="javascript:Confirmar(<%=guia.getId_guia_despacho()%>);"><img src="<%=request.getContextPath()%>/images/iconos/eliminar.png" border="0" width="20" height="20" title="Eliminar"></a></th>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </fieldset>
        </form>
    </body>
</html>