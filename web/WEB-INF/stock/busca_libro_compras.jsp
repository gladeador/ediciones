<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Busca Libro de Compras ::.</title>
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
        <form name="formData" method="post" action="<%=request.getContextPath()%>/controller/GeneraLibroCompras.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Busca Libro de Compras</th>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <td>
                        <table align="center">
                            <tr>
                                <th class="specalt">Seleccione Mes</th>
                                <th class="specalt">Seleccione A�o</th>
                            </tr>
                            <tr>
                                <td align="center">
                                    <select name="mes" id="mes" class="cuadroTexto">
                                        <option value="01">Enero</option>
                                        <option value="02">Febrero</option>
                                        <option value="03">Marzo</option>
                                        <option value="04">Abril</option>
                                        <option value="05">Mayo</option>
                                        <option value="06">Junio</option>
                                        <option value="07">Julio</option>
                                        <option value="08">Agosto</option>
                                        <option value="09">Septiembre</option>
                                        <option value="10">Octubre</option>
                                        <option value="11">Noviembre</option>
                                        <option value="12">Diciembre</option>
                                    </select>
                                </td>
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