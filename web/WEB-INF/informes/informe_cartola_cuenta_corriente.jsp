<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Cartola Cuenta Corriente ::.</title>
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
                    <th class="menuSuperior">CARTOLA CUENTA CORRIENTE Nº 61-74268-9</th>
                </tr>
                <tr>
                    <th class="menuSuperior">Periodo: <%=request.getAttribute("periodo")%></th>
                </tr>
            </table>
            <br>
            <fieldset>
                <table width="100%">
                    <tr>
                        <th width="100">Fecha</th>
                        <th>Descripcion</th>
                        <th width="100">Documento</th>
                        <th width="60">Cargos</th>
                        <th width="60">Abonos</th>
                        <th width="60">Saldo</th>
                    </tr>
                    <%
                        Cartola saldo_cartola = (Cartola) request.getAttribute("cartola_saldo_anterior");
                        int saldo_ini = saldo_cartola.getSaldos() - saldo_cartola.getCargos() + saldo_cartola.getAbonos();
                    %>
                    <tr class="cuadroTexto">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=saldo_ini%></fmt:formatNumber></td>
                    </tr>
                    <%
                        ArrayList lista_cartolas = (ArrayList) request.getAttribute("lista_cartolas");
                        Iterator c = lista_cartolas.iterator();
                        int saldo = saldo_ini;
                        int total_cargos = 0;
                        int total_abonos = 0;
                        while (c.hasNext()) {
                            Cartola cartola = (Cartola) c.next();
                            saldo = saldo - cartola.getCargos() + cartola.getAbonos();
                    %>
                    <tr class="cuadroTexto">
                        <th class="specalt"><%=cartola.getFechaStr()%></th>
                        <td><%=cartola.getDescripcion() == null ? "" : cartola.getDescripcion()%></td>
                        <td><%=cartola.getDocumento() == null ? "" : cartola.getDocumento()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=cartola.getCargos()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=cartola.getAbonos()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=saldo%></fmt:formatNumber></td>
                    </tr>
                    <%
                            total_cargos = total_cargos + cartola.getCargos();
                            total_abonos = total_abonos + cartola.getAbonos();
                        }
                    %>
                </table>
            </fieldset>
            <br/>
            <table>
                <tr>
                    <th class="menuSuperior" colspan="2">Totales del Periodo</th>
                </tr>
                <tr class="cuadroTexto">
                    <th class="specalt">Cargos</th>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=total_cargos%></fmt:formatNumber></td>
                </tr>
                <tr class="cuadroTexto">
                    <th class="specalt">Abonos</th>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=total_abonos%></fmt:formatNumber></td>
                </tr>
                <tr class="cuadroTexto">
                    <th class="specalt">Saldos</th>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=total_abonos - total_cargos%></fmt:formatNumber></td>
                </tr>
            </table>
            <br/>
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