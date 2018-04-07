<%@page import="cl.ediciones.model.Usuarios"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Cambio Clave</title>
        <link rel="icon" href="<%=request.getContextPath()%>/images/escudo.ico" type="image/x-icon" />
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link href="<%=request.getContextPath()%>/estilos/dbs.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/estilos/menuuser.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>

        <script language="JavaScript">
            noF5();
        </script>

    </head>

    <%
        HttpSession sesion = request.getSession();
        Usuarios user = (Usuarios) sesion.getAttribute("usuario");
    %>

    <body oncontextmenu="return false">
        <table align="center" height="100%">
            <tr>
                <td valign="middle">
                    <fieldset>
                        <table align="center">
                            <tr>
                                <th align="center" class="menuSuperior">Cambio de Clave</th>
                            </tr>
                            <tr>
                                <td class="txtNormalNegro"><strong>Don(&ntilde;a): </strong><br>
                                    <%=user.getNombres()%>, <br>
                                    <strong>Su clave fue cambiada exitosamente.</strong></td>
                            </tr>
                            <tr>
                                <th><img src="<%=request.getContextPath()%>/images/llaves.jpg"></th>
                            </tr>
                        </table>
                    </fieldset>
                </td>
            </tr>
        </table>
    </body>
</html>
