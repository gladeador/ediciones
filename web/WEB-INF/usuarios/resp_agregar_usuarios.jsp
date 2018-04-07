<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Usuarios ::.</title>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/ediciones.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>

        <script language="JavaScript">
            noF5();
        </script>

    </head>

    <%
        Usuarios usuario = (Usuarios) request.getAttribute("Usuario");
    %>

    <body>
        <form name="formData">
            <table align="center">
                <tr>
                    <td>
                        <fieldset>
                            <table>
                                <tr>
                                    <th class="subTitulos">El Nuevo usuario, ha sido Creado con Exito!</th>
                                </tr>
                                <tr>
                                    <th class="specalt">Nombre </th>
                                    <th class="specalt"><%=usuario.getNombres()%></td>
                                </tr>
                                <tr>
                                    <th class="specalt" id="emailt">E-Mail </th>
                                    <td class="subTitulos"><%=usuario.getEmail()%></td>
                                </tr>                                <tr>
                                    <th class="specalt">Clave de Acceso </th>
                                    <td class="subTitulos">Cuatro primeros digitos del Rut</td>
                                </tr>
                            </table>
                            <br/>
                            <table align="center" id="button_imprimir">
                                <tr>
                                    <th><input name="b_imprimir" type="button" id="b_imprimir" class="botones" onclick="javascript:imprimir(this.id)" value="Imprimir"></th>
                                </tr>
                            </table>
                            <script>
                                function imprimir(b_imprimir){
                                    showhide(b_imprimir);
                                    window.print();
                                    showhide(b_imprimir);
                                }
                            </script>
                        </fieldset>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>