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
        <title>.:: Ranking Ventas por Region ::.</title>
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
        ArrayList lista_regiones = (ArrayList) request.getAttribute("Lista_regiones");
        ArrayList lista_clientes = (ArrayList) request.getAttribute("Lista_clientes");
        ArrayList primer_ano = (ArrayList) request.getAttribute("Lista_primer_ano");
        Factura total_ano = (Factura) request.getAttribute("total_ano");
        ArrayList ranking_region = (ArrayList) request.getAttribute("ranking_region");
    %>
    <body>
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Ranking Ventas por Region Acumulado <%=request.getParameter("ano")%></th>
                </tr>
            </table>
            <br>
            <fieldset>
                <legend class="txtResaltadoV">Libro de Compras</legend>
                <table width="100%">
                    <tr>
                        <th width="10">RKN</th>
                        <th>Razón Social</th>
                        <th width="80">Venta Neto</th>
                        <th width="80">Venta C/Iva</th>
                        <th width="1%"> %</th>
                    </tr>
                    <%
                        Iterator r = lista_regiones.iterator();
                        while (r.hasNext()) {
                            float suma_total_por = 0;
                            int suma_total_neto = 0;
                            int suma_total_total = 0;
                            BigDecimal total_big = null;
                            Region region = (Region) r.next();
                            Iterator c = lista_clientes.iterator();
                            while (c.hasNext()) {
                                Clientes clientes = (Clientes) c.next();
                                if (region.getId_region() == clientes.getCiudad().getRegion().getId_region()) {
                    %>
                    <tr class="cuadroTexto">
                        <td><%=clientes.getRanking()%></td>
                        <th class="specalt"><%=clientes.getNombre()%></th>
                        <%
                            int valor_neto = 0;
                            int valor_total = 0;
                            Iterator s = primer_ano.iterator();
                            while (s.hasNext()) {
                                Informes informes = (Informes) s.next();
                                if (informes.getRut() == clientes.getRut()) {
                                    valor_neto = informes.getSuma_neto();
                                    valor_total = informes.getSuma_total();
                                    suma_total_neto = suma_total_neto + valor_neto;
                                    suma_total_total = suma_total_total + valor_total;
                                }
                            }
                            float mul = valor_total * 100;
                            float por = mul / total_ano.getTotal();
                            BigDecimal big = new BigDecimal(String.valueOf(por));
                            big = big.setScale(2, RoundingMode.HALF_UP);

                            suma_total_por = suma_total_por + por;
                        %>
                        <td align="right"><%=valor_neto%></td>
                        <td align="right"><%=valor_total%></td>
                        <td align="right"><%=big%></td>
                    </tr>
                    <%
                            }
                        }
                        total_big = new BigDecimal(String.valueOf(suma_total_por));
                        total_big = total_big.setScale(2, RoundingMode.HALF_UP);
                    %>
                    <tr>
                        <th colspan="2">Venta <%=region.getDescripcion()%></th>
                        <th><div align="right"><%=suma_total_neto%></div></th>
                    <th><div align="right"><%=suma_total_total%></div></th>
                    <th><div align="right"><%=total_big%></div></th>
                    </tr>
                    <%

                        }
                    %>
                    <tr>
                        <td colspan="5"></td>
                    </tr>
                    <tr>
                        <th colspan="2"><div align="right">TOTAL ACUMULADO A JUNIO <%=request.getParameter("ano")%></div></th>
                    <th><div align="right"><%=total_ano.getNeto()%></div></th>
                    <th><div align="right"><%=total_ano.getTotal()%></div></th>
                    <th><div align="right">100,00</div></th>
                    </tr>
                </table>
            </fieldset>
            <br>
            <fieldset>
                <table>
                    <tr>
                        <th>RKN</th>
                        <th>Region</th>
                        <th>% Acumulado</th>
                    </tr>
                    <%
                        Iterator rr = ranking_region.iterator();
                        while (rr.hasNext()) {
                            Informes informes = (Informes) rr.next();
                            float mul = informes.getSuma_total() * 100;
                            float por = mul / total_ano.getTotal();
                            BigDecimal big = new BigDecimal(String.valueOf(por));
                            big = big.setScale(2, RoundingMode.HALF_UP);

                    %>
                    <tr class="cuadroTexto">
                        <td><%=informes.getRkn()%></td>
                        <td><%=informes.getDescripcion()%></td>
                        <td><div align="center"><%=big%></div></td>
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