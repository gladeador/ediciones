<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Informe Libro Ventas ::.</title>
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
                    <th class="menuSuperior">Informe Libro Ventas</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <table width="100%">
                    <tr>
                        <th>RAZÓN SOCIAL</th>
                        <th>FECHA</th>
                        <th width="70">Nº FACT.</th>
                        <th width="100">neto</th>
                        <th width="100">IVA</th>
                        <th width="100">MONTO C/ IVA</th>
                    </tr>
                    <tr class="txtResaltadoV">
                        <th colspan="6">Factura</th>
                    </tr>
                    <%
                        int sum_neto = 0;
                        int sum_iva = 0;
                        int sum_total = 0;
                        ArrayList lista_facturas = (ArrayList) request.getAttribute("lista_facturas");
                        Iterator f = lista_facturas.iterator();
                        while (f.hasNext()) {
                            Factura factura = (Factura) f.next();
                    %>
                    <tr class="cuadroTexto">
                        <th class="specalt"><%=factura.getClientes().getNombre()%></th>
                        <td align="center"><%=factura.getFecha_facturaStr()%></td>
                        <td><%=factura.getNum_factura()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getNeto()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getIva()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getTotal()%></fmt:formatNumber></td>
                    </tr>
                    <%
                            sum_neto = sum_neto + factura.getNeto();
                            sum_iva = sum_iva + factura.getIva();
                            sum_total = sum_total + factura.getTotal();
                        }
                    %>
                    <tr class="cuadroTexto">
                        <td align="right" colspan="3">Total</td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_neto%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_iva%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_total%></fmt:formatNumber></td>
                    </tr>
                    <tr class="txtResaltadoV">
                        <th colspan="6">Boleta</th>
                    </tr>
                    <%
                        int sum_neto_boleta = 0;
                        int sum_iva_boleta = 0;
                        int sum_total_boleta = 0;
                        ArrayList lista_boletas = (ArrayList) request.getAttribute("lista_boletas");
                        Iterator b = lista_boletas.iterator();
                        while (b.hasNext()) {
                            Boleta boleta = (Boleta) b.next();
                    %>
                    <tr class="cuadroTexto">
                        <th class="specalt"><%=boleta.getClientes().getNombre()%></th>
                        <td align="center"><%=boleta.getFecha_boletaStr()%></td>
                        <td><%=boleta.getNum_boleta()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=boleta.getNeto()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=boleta.getIva()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=boleta.getTotal()%></fmt:formatNumber></td>
                    </tr>
                    <%
                            sum_neto_boleta = sum_neto_boleta + boleta.getNeto();
                            sum_iva_boleta = sum_iva_boleta + boleta.getIva();
                            sum_total_boleta = sum_total_boleta + boleta.getTotal();
                        }
                    %>
                    <tr class="cuadroTexto">
                        <td align="right" colspan="3">Total</td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_neto_boleta%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_iva_boleta%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_total_boleta%></fmt:formatNumber></td>
                    </tr>
                    <tr class="txtResaltadoV">
                        <th colspan="6">Notas de Credito</th>
                    </tr>
                    <%
                        int sum_neto_nota = 0;
                        int sum_iva_nota = 0;
                        int sum_total_nota = 0;
                        ArrayList lista_notacredito = (ArrayList) request.getAttribute("lista_notacredito");
                        Iterator n = lista_notacredito.iterator();
                        while (n.hasNext()) {
                            NotaCredito notacredito = (NotaCredito) n.next();
                    %>
                    <tr class="cuadroTexto">
                        <th class="specalt"><%=notacredito.getClientes().getNombre()%></th>
                        <td align="center"><%=notacredito.getFecha_notacreditoStr()%></td>
                        <td><%=notacredito.getNum_notacredito()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=notacredito.getNeto()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=notacredito.getIva()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=notacredito.getTotal()%></fmt:formatNumber></td>
                    </tr>
                    <%
                            sum_neto_nota = sum_neto_nota + notacredito.getNeto();
                            sum_iva_nota = sum_iva_nota + notacredito.getIva();
                            sum_total_nota = sum_total_nota + notacredito.getTotal();
                        }
                    %>
                    <tr class="cuadroTexto">
                        <td align="right" colspan="3">Total</td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_neto_nota%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_iva_nota%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_total_nota%></fmt:formatNumber></td>
                    </tr>
                    <tr class="cuadroTexto">
                        <td colspan="6"></td>
                    </tr>
                    <tr class="cuadroTexto">
                        <td align="right" colspan="3">Total Generales</td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_neto + sum_neto_boleta - sum_neto_nota%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_iva + sum_iva_boleta - sum_iva_nota%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_total + sum_total_boleta - sum_total_nota%></fmt:formatNumber></td>
                    </tr>
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