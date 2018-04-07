<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Informe Cobranza ::.</title>
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

    <body>
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Informe Cobranza</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <table width="100%">
                    <tr>
                        <th>RAZÓN SOCIAL</th>
                        <th>FECHA</th>
                        <th width="70">Nº FACT.</th>
                        <th width="100">MONTO C/ IVA</th>
                    </tr>
                    <%
                        ArrayList lista_clientes = (ArrayList) request.getAttribute("lista_clientes");
                        ArrayList Lista_facturas = (ArrayList) request.getAttribute("Lista_facturas");
                        Iterator c = lista_clientes.iterator();
                        while (c.hasNext()) {
                            int sum = 0;
                            Clientes clientes = (Clientes) c.next();
                            Iterator f = Lista_facturas.iterator();
                            while (f.hasNext()) {
                                Factura factura = (Factura) f.next();
                                if (clientes.getRut() == factura.getClientes().getRut()) {
                    %>
                    <tr class="cuadroTexto">
                        <th class="specalt"><%=factura.getClientes().getNombre()%></th>
                        <td><%=factura.getFecha_facturaStr()%></td>
                        <td><%=factura.getNum_factura()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getTotal()%></fmt:formatNumber></td>
                    </tr>
                    <%
                                sum = sum + factura.getTotal();
                            }
                        }
                    %>
                    <tr class="cuadroTexto">
                        <td align="right" colspan="3">Total</td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum%></fmt:formatNumber></td>
                    </tr>
                    <tr class="cuadroTexto">
                        <td colspan="4"></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </fieldset>
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