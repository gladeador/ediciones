<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Usuarios ::.</title>
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
        </script>

        <script>
            function Confirmar(rut){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Borrar(rut);
            }
        </script>
        
        <script>
            function Borrar(rut){
                document.formBorrar.rut.value = rut;
                document.formBorrar.submit();
                document.formBorrar.reset();
            }
        </script>
        
    </head>

    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarUsuarios.ed">
            <input name="rut" type="hidden">
        </form>
            
        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminarUsuarios.ed">
            <input name="rut" type="hidden">
        </form>
            
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Usuarios</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <legend class="txtResaltadoV">Listado de Usuarios</legend>
                <table width="100%">
                    <tr class="txtResSRa">
                        <th>Rut</th>
                        <th>Nombre</th>
                        <th>E-Mail</th>
                    </tr>
                    <%
                        ArrayList lista_usuarios = (ArrayList) request.getAttribute("lista_usuarios");
                        Iterator i = lista_usuarios.iterator();
                        while (i.hasNext()) {
                            Usuarios usuarios = (Usuarios) i.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><%=usuarios.getRut()%>-<%=usuarios.getDv()%></td>
                        <td><%=usuarios.getNombres()%></td>
                        <td><%=usuarios.getEmail()%></td>
                        <th width="1" class="nobg"><a href="javascript:modificar(<%=usuarios.getRut()%>);"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Modificar"></a></th>
                        <th width="1" class="nobg"><a href="javascript:Confirmar(<%=usuarios.getRut()%>);"><img src="<%=request.getContextPath()%>/images/iconos/eliminar.png" border="0" width="20" height="20" title="Eliminar"></a></th>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </fieldset>
        </form>
    </body>
</html>