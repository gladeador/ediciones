<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Ranking Acumulado Clientes ::.</title>
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
        ArrayList lista_clientes = (ArrayList) request.getAttribute("Lista_clientes");
        ArrayList arreglos = (ArrayList) request.getAttribute("Lista_arreglos");
    %>

    <body>
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Ranking Acumulado Clientes</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <table width="100%">
                    <tr>
                        <th>RKN</th>
                        <th>Razón Social</th>
                        <th>ENE</th>
                        <th>FEB</th>
                        <th>MAR</th>
                        <th>ABR</th>
                        <th>MAY</th>
                        <th>JUN</th>
                        <th>JUL</th>
                        <th>AGO</th>
                        <th>SEP</th>
                        <th>OCTU</th>
                        <th>NOV</th>
                        <th>DIC</th>
                        <th>TOTAL</th>
                    </tr>
                    <%
                        int rkn = 1;
                        Iterator c = lista_clientes.iterator();
                        while (c.hasNext()) {
                            Clientes clientes = (Clientes) c.next();
                    %>
                    <tr class="cuadroTexto">
                        <td valign="top"><%=rkn++%></td>
                        <th class="specalt"><%=clientes.getNombre()%></th>
                        <%
                            int total = 0;
                            Iterator a = arreglos.iterator();
                            while (a.hasNext()) {
                                ArrayList lista_facturas = (ArrayList) a.next();
                                if (lista_facturas.isEmpty()) {
                        %>
                        <td></td>
                        <%                            } else {
                            String suma_string = "";
                            int suma_total = 0;
                            Iterator f = lista_facturas.iterator();
                            while (f.hasNext()) {
                                Factura factura = (Factura) f.next();
                                if (clientes.getRut() == factura.getClientes().getRut()) {
                                    suma_total = suma_total + factura.getTotal();
                                }
                            }
                            if (suma_total != 0) {
                                suma_string = "" + suma_total;
                                total = total + suma_total;
                            }
                        %>
                        <td><div align="right"><%=suma_string.equals("") ? "" : "$"%><fmt:formatNumber type="number" pattern="##,###,###"><%=suma_string%></fmt:formatNumber></div></td>
                        <%                            }
                        %>
                        <%
                            }
                        %>
                        <td valign="top"><div align="right"><div align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=total%></fmt:formatNumber></div></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </fieldset>
            <br>
            <table align="center">
                <tr>
                    <td><a href="javascript:window.history.go(-1);" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"></a></td>
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
        </form>
    </body>
</html>