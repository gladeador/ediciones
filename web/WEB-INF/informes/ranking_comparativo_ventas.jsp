<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="org.apache.taglibs.standard.lang.jpath.expression.RoundFunction"%>
<%@page import="com.sun.image.codec.jpeg.TruncatedFileException"%>
<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Ranking Comparativo de Ventas ::.</title>
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
        Iterator c = lista_clientes.iterator();
        ArrayList primer_ano = (ArrayList) request.getAttribute("Lista_primer_ano");
        ArrayList segundo_ano = (ArrayList) request.getAttribute("Lista_segundo_ano");
        Factura total_ano_1 = (Factura) request.getAttribute("total_ano_1");
        Factura total_ano_2 = (Factura) request.getAttribute("total_ano_2");
    %>
    <body>
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Ranking Comparativo de Ventas</th>
                </tr>
            </table>
            <br>
            <table align="center" width="100%">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Libro de Compras</legend>
                            <table width="100%">
                                <tr>
                                    <th>Razón Social</th>
                                    <th width="10">RKN</th>
                                    <th width="80">Total <%=request.getParameter("ano_1")%></th>
                                    <th width="10">RKN</th>
                                    <th width="80">Total <%=request.getParameter("ano_2")%></th>
                                    <th width="80"><%=request.getParameter("ano_2")%> - <%=request.getParameter("ano_1")%></th>
                                    <th width="10">%</th>
                                </tr>
                                <%
                                    while (c.hasNext()) {
                                        Clientes clientes = (Clientes) c.next();
                                %>
                                <tr class="cuadroTexto">
                                    <th class="specalt"><%=clientes.getNombre()%></th>
                                    <%
                                        int valor_1 = 0;
                                        String rkn_1 = "";
                                        Iterator p = primer_ano.iterator();
                                        while (p.hasNext()) {
                                            Informes informes = (Informes) p.next();
                                            if (informes.getRut() == clientes.getRut()) {
                                                valor_1 = informes.getSuma_total();
                                                rkn_1 = String.valueOf(informes.getRkn());
                                            }
                                        }
                                    %>
                                    <td align="center"><%=rkn_1%></td>
                                    <td align="right"><%=valor_1%></td>
                                    <%
                                        int valor_2 = 0;
                                        String rkn_2 = "";
                                        Iterator s = segundo_ano.iterator();
                                        while (s.hasNext()) {
                                            Informes informes = (Informes) s.next();
                                            if (informes.getRut() == clientes.getRut()) {
                                                valor_2 = informes.getSuma_total();
                                                rkn_2 = String.valueOf(informes.getRkn());
                                            }
                                        }
                                        float mul = valor_2 * 100;
                                        float por = mul / total_ano_2.getTotal();
                                        BigDecimal big = new BigDecimal(String.valueOf(por));
                                        big = big.setScale(2, RoundingMode.HALF_UP);
                                    %>
                                    <td align="center"><%=rkn_2%></td>
                                    <td align="right"><%=valor_2%></td>
                                    <td align="right"><%=(valor_2 - valor_1)%></td>
                                    <td align="right"><%=big%></td>
                                </tr>
                                <%
                                    }
                                %>
                                <tr class="cuadroTexto">
                                    <td colspan="2" align="right">Totales</td>
                                    <td align="right"><%=total_ano_1.getTotal()%></td>
                                    <td></td>
                                    <td align="right"><%=total_ano_2.getTotal()%></td>
                                    <td align="right"><%=total_ano_2.getTotal() - total_ano_1.getTotal()%></td>
                                    <td></td>
                                </tr>
                            </table>
                        </fieldset>
                        <br></br>
                        <table align="center">
                            <tr>
                                <td><a href="javascript:window.history.go(-1);" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"></a></td>
                            </tr>
                        </table>
                    </td>
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