<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Cartola ::.</title>
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
            function modificar(id_cartola){
                document.formModificar.id_cartola.value = id_cartola;
                document.formModificar.submit();
            }
            
            function Confirmar(id_cartola){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                document.formBorrar.id_cartola.value = id_cartola;
                document.formBorrar.submit();
                document.formBorrar.reset();
            }
        </script>

    </head>

    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarCartola.ed">
            <input name="id_cartola" type="hidden">
        </form>

        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminarCartola.ed">
            <input name="id_cartola" type="hidden">
        </form>

        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Cartola</th>
                </tr>
            </table>
            <br>
            <table align="center" width="100%">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Listado de Cartola</legend>
                            <table width="100%">
                                <tr>
                                    <th>Fecha</th>
                                    <th>Descripcion</th>
                                    <th>Documento</th>
                                </tr>
                                <%
                                    ArrayList lista_cartolas = (ArrayList) request.getAttribute("lista_cartolas");
                                    Iterator i = lista_cartolas.iterator();
                                    while (i.hasNext()) {
                                        Cartola cartola = (Cartola) i.next();
                                %>
                                <tr class="cuadroTexto">
                                    <td><%=cartola.getFechaStr()%></td>
                                    <td><%=cartola.getDescripcion()%></td>
                                    <td><%=cartola.getDocumento()%></td>
                                    <th width="1" class="nobg"><a href="javascript:modificar(<%=cartola.getId_cartola()%>);"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Modificar"></a></th>
                                    <th width="1" class="nobg"><a href="javascript:Confirmar(<%=cartola.getId_cartola()%>);"><img src="<%=request.getContextPath()%>/images/iconos/eliminar.png" border="0" width="20" height="20" title="Eliminar"></a></th>
                                </tr>
                                <%
                                    }
                                %>
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>