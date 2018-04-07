<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Seleccione Año Ranking Ventas por Region::.</title>
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
        <form name="formData" method="post" action="<%=request.getContextPath()%>/controller/RankingVentasRegion.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Seleccione Año Ranking Ventas por Region</th>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <td>
                        <table align="center">
                            <tr>
                                <th class="specalt">Seleccione Año</th>
                            </tr>
                            <tr>
                                <td align="center">
                                    <select name="ano" id="ano" class="cuadroTexto">
                                        <%
                                            for (int i = 2011; i <= Integer.parseInt(fecha.getAno()); i++) {
                                        %>
                                        <option selected=""><%=i%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <table align="center">
                            <tr>
                                <td><a href="javascript:document.formData.submit();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/buscar.png" border="0" width="30" height="30" title="Buscar"></a></td>
                            </tr>
                        </table>  
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>