<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Clientes ::.</title>
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
            function modificar(rut){
                document.formModificar.rut.value = rut;
                document.formModificar.submit();
            }
            
            function Confirmar(rut){
                if (!confirm("�Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Borrar(rut);
            }
            
            function Borrar(rut){
                document.formBorrar.rut.value = rut;
                document.formBorrar.submit();
                document.formBorrar.reset();
            }
        </script>

    </head>

    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarClientes.ed">
            <input name="rut" type="hidden">
        </form>

        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminarClientes.ed">
            <input name="rut" type="hidden">
        </form>

        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Clientes</th>
                </tr>
            </table>
            <br>
            <table align="center" width="100%">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Listado de Clientes</legend>
                            <table width="100%">
                                <tr>
                                    <th>Rut</th>
                                    <th>Nombre</th>
                                    <th>Giro</th>
                                    <th>E-Mail</th>
                                </tr>
                                <%
                                    ArrayList lista_clientes = (ArrayList) request.getAttribute("lista_clientes");
                                    Iterator i = lista_clientes.iterator();
                                    while (i.hasNext()) {
                                        Clientes clientes = (Clientes) i.next();
                                %>
                                <tr class="cuadroTexto">
                                    <td><%=clientes.getRut()%>-<%=clientes.getDv()%></td>
                                    <td><%=clientes.getNombre()%></td>
                                    <td><%=clientes.getGiro()%></td>
                                    <td><%=clientes.getEmail()%></td>
                                    <th width="1" class="nobg"><a href="javascript:modificar(<%=clientes.getRut()%>);"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Modificar"></a></th>
                                    <th width="1" class="nobg"><a href="javascript:Confirmar(<%=clientes.getRut()%>);"><img src="<%=request.getContextPath()%>/images/iconos/eliminar.png" border="0" width="20" height="20" title="Eliminar"></a></th>
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