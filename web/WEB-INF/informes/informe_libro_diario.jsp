<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Comprobante.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Comprobante ::.</title>
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
        <form name="formData">
            <table width="100%" border="0">
                <tr class="cuadroTexto">
                    <td class="nobg" width="45%" style="vertical-align: bottom">EDICIONES R Y V  SOCIEDAD ANONIMA<br />
                        EDICION,IMPORTACION,DISTRIBUCION Y/O VTA.LIBROS,REVISTAS,FOL<br />
                        LIBERTAD 66<br />
                        SANTIAGO CENTRO<br />
                        SANTIAGO</td>
                    <td class="nobg" width="10%" style="vertical-align: bottom" align="center">Libro Diario</td>
                    <td class="nobg" width="45%" style="vertical-align: bottom" align="right"><%=fecha.getFecha_actualStr()%></td>
                </tr>
                <tr class="cuadroTexto">
                    <td class="nobg">76.008.969-9</td>
                    <td class="nobg"></td>
                    <td class="nobg"></td>
                </tr>
            </table>
            <br />
            <table width="100%">
                <tr class="cuadroTexto">
                    <th class="nobg" width="10%">Num Comprobante</th>
                    <th class="nobg" width="100">Fecha</th>
                    <th class="nobg">Descripción</th>
                    <th class="nobg" width="60">Debe</th>
                    <th class="nobg" width="60">Haber</th>
                    <th class="nobg">Descripción</th>
                </tr>
                <tr class="cuadroTexto">
                    <th class="nobg">Código Cuenta</th>
                    <th class="nobg">Descripción</th>
                </tr>
                <%
                    ArrayList lista_comprobantes = (ArrayList) request.getAttribute("lista_comprobantes");
                    Iterator lc = lista_comprobantes.iterator();
                    while (lc.hasNext()) {
                        Comprobante comprobante = (Comprobante) lc.next();
                %>
                <tr class="cuadroTexto">
                    <td><%=comprobante.getNum_comprobante()%></td>
                    <td><%=comprobante.getFecha_comprobanteStr()%></td>
                    <td><%=comprobante.getDescripcion()%></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <%
                    ComprobanteDAO comDAO = new ComprobanteDAO();
                    java.sql.Date fecha_comprobante = new java.sql.Date(comprobante.getFecha_comprobante().getTime());
                    ArrayList lista = comDAO.traerTodos_Comprobante_Libro_Diario(comprobante.getNum_comprobante(), fecha_comprobante);
                    Iterator lc2 = lista.iterator();
                    float total_debe = 0;
                    float total_haber = 0;
                    while (lc2.hasNext()) {
                        Comprobante compro = (Comprobante) lc2.next();
                        if (compro.getFactura() != null) {
                %>
                <tr class="cuadroTexto">
                    <td><%=compro.getFactura().getCuenta_base_total().getNum_cuenta()%></td>
                    <td><%=compro.getFactura().getCuenta_base_total().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getFactura().getCuenta_base_total().getCuentas().equals("Activo") || compro.getFactura().getCuenta_base_total().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getFactura().getTotal()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getFactura().getTotal();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getFactura().getTotal()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getFactura().getTotal();
                        }
                    %>
                    <td>Fact/<%=compro.getFactura().getNum_factura()%> <%=compro.getFactura().getClientes().getNombre()%></td>
                </tr>
                <tr class="cuadroTexto">
                    <td><%=compro.getFactura().getCuenta_base_neto().getNum_cuenta()%></td>
                    <td><%=compro.getFactura().getCuenta_base_neto().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getFactura().getCuenta_base_neto().getCuentas().equals("Activo") || compro.getFactura().getCuenta_base_neto().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compro.getFactura().getNeto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getFactura().getNeto();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getFactura().getNeto()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getFactura().getNeto();
                        }
                    %>
                    <td>Fact/<%=compro.getFactura().getNum_factura()%> <%=compro.getFactura().getClientes().getNombre()%></td>
                </tr>
                <tr class="cuadroTexto">
                    <td><%=compro.getFactura().getCuenta_base_iva().getNum_cuenta()%></td>
                    <td><%=compro.getFactura().getCuenta_base_iva().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getFactura().getCuenta_base_iva().getCuentas().equals("Activo") || compro.getFactura().getCuenta_base_iva().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getFactura().getIva()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getFactura().getIva();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getFactura().getIva()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getFactura().getIva();
                        }
                    %>
                    <td>Fact/<%=compro.getFactura().getNum_factura()%> <%=compro.getFactura().getClientes().getNombre()%></td>
                </tr>
                <%
                    }
                    if (compro.getGastos() != null) {
                        if (compro.getGastos().getNeto_exento() != 0) {
                %>
                <tr class="cuadroTexto">
                    <td><%=compro.getGastos().getCuenta_base_neto().getNum_cuenta()%></td>
                    <td><%=compro.getGastos().getCuenta_base_neto().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getGastos().getCuenta_base_neto().getCuentas().equals("Activo") || compro.getGastos().getCuenta_base_neto().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compro.getGastos().getNeto_afecto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getGastos().getNeto_afecto();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getGastos().getNeto_afecto()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getGastos().getNeto_afecto();
                        }
                    %>
                    <td><%=compro.getGastos().getTipo_documento().getId_tipo_documento()%>/<%=compro.getGastos().getNum_boleta()%> <%=compro.getGastos().getProveedores().getNombre()%></td>
                </tr>
                <%
                } else {
                %>
                <tr class="cuadroTexto">
                    <td><%=compro.getGastos().getCuenta_base_neto().getNum_cuenta()%></td>
                    <td><%=compro.getGastos().getCuenta_base_neto().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getGastos().getCuenta_base_neto().getCuentas().equals("Activo") || compro.getGastos().getCuenta_base_neto().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compro.getGastos().getNeto_afecto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getGastos().getNeto_afecto();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getGastos().getNeto_afecto()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getGastos().getNeto_afecto();
                        }
                    %>
                    <td><%=compro.getGastos().getTipo_documento().getId_tipo_documento()%>/<%=compro.getGastos().getNum_boleta()%> <%=compro.getGastos().getProveedores().getNombre()%></td>
                </tr>
                <tr class="cuadroTexto">
                    <td><%=compro.getGastos().getCuenta_base_iva().getNum_cuenta()%></td>
                    <td><%=compro.getGastos().getCuenta_base_iva().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getGastos().getCuenta_base_iva().getCuentas().equals("Activo") || compro.getGastos().getCuenta_base_iva().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getGastos().getIva_afecto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getGastos().getIva_afecto();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getGastos().getIva_afecto()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getGastos().getIva_afecto();
                        }
                    %>
                    <td><%=compro.getGastos().getTipo_documento().getId_tipo_documento()%>/<%=compro.getGastos().getNum_boleta()%> <%=compro.getGastos().getProveedores().getNombre()%></td>
                </tr>
                <tr class="cuadroTexto">
                    <td><%=compro.getGastos().getCuenta_base_total().getNum_cuenta()%></td>
                    <td><%=compro.getGastos().getCuenta_base_total().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getGastos().getCuenta_base_total().getCuentas().equals("Activo") || compro.getGastos().getCuenta_base_total().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getGastos().getTotal_afecto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getGastos().getTotal_afecto();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getGastos().getTotal_afecto()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getGastos().getTotal_afecto();
                        }
                    %>
                    <td><%=compro.getGastos().getTipo_documento().getId_tipo_documento()%>/<%=compro.getGastos().getNum_boleta()%> <%=compro.getGastos().getProveedores().getNombre()%></td>
                </tr>
                <%
                        }
                    }
                    if (compro.getCheque() != null) {
                %>
                <tr class="cuadroTexto">
                    <td><%=compro.getCheque().getCuentas_base().getNum_cuenta()%></td>
                    <td><%=compro.getCheque().getCuentas_base().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getCheque().getCuentas_base().getCuentas().equals("Activo") || compro.getCheque().getCuentas_base().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compro.getCheque().getMonto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getCheque().getMonto();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getCheque().getMonto()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getCheque().getMonto();
                        }
                    %>
                    <td><%=compro.getCheque().getFactura().getClientes().getNombre()%></td>
                </tr>
                <%
                    }
                    if (compro.getCuota() != null) {
                %>
                <tr class="cuadroTexto">
                    <td><%=compro.getCuota().getCuentas_base().getNum_cuenta()%></td>
                    <td><%=compro.getCuota().getCuentas_base().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getCuota().getCuentas_base().getCuentas().equals("Activo") || compro.getCuota().getCuentas_base().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compro.getCuota().getMonto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getCuota().getMonto();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getCuota().getMonto()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getCuota().getMonto();
                        }
                    %>
                    <td><%=compro.getCuota().getFactura().getClientes().getNombre()%></td>
                </tr>
                <%
                    }
                    if (compro.getLetra() != null) {
                %>
                <tr class="cuadroTexto">
                    <td><%=compro.getLetra().getCuentas_base().getNum_cuenta()%></td>
                    <td><%=compro.getLetra().getCuentas_base().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getLetra().getCuentas_base().getCuentas().equals("Activo") || compro.getLetra().getCuentas_base().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compro.getLetra().getMonto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getLetra().getMonto();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getLetra().getMonto()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getLetra().getMonto();
                        }
                    %>
                    <td><%=compro.getLetra().getFactura().getClientes().getNombre()%></td>
                </tr>
                <%
                    }
                    if (compro.getCheque_pago() != null) {
                %>
                <tr class="cuadroTexto">
                    <td><%=compro.getCheque_pago().getCuentas_base().getNum_cuenta()%></td>
                    <td><%=compro.getCheque_pago().getCuentas_base().getDescripcion()%></td>
                    <td></td>
                    <%
                        if ((compro.getCheque_pago().getCuentas_base().getCuentas().equals("Activo") || compro.getCheque_pago().getCuentas_base().getCuentas().equals("Perdida")) && compro.getDebe_haber().equals("")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compro.getCheque_pago().getMonto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getCheque_pago().getMonto();
                    } else if (compro.getDebe_haber().equals("Debe")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compro.getCheque_pago().getMonto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getCheque_pago().getMonto();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getCheque_pago().getMonto()%></fmt:formatNumber></td>
                    <%
                        total_haber = total_haber + compro.getCheque_pago().getMonto();
                    }
                    %>
                    <td><%=compro.getCheque_pago().getProveedores().getNombre()%></td>
                </tr>
                <%
                    }
                    if (compro.getProveedor() != null) {
                        Stock_ProductoDAO spDAO = new Stock_ProductoDAO();
                        ArrayList lista_stock = spDAO.traerTodos_Factura_Compras_para_Libro_Diario(compro.getProveedor().getRut(), compro.getNum_factura());
                        Iterator s = lista_stock.iterator();
                        while (s.hasNext()) {
                            Stock_Producto stock = (Stock_Producto) s.next();
                %>
                <tr class="cuadroTexto">
                    <td><%=stock.getCuentas_base().getNum_cuenta()%></td>
                    <td><%=stock.getCuentas_base().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (stock.getCuentas_base().getCuentas().equals("Activo") || stock.getCuentas_base().getCuentas().equals("Perdida")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=stock.getTotal()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + stock.getTotal();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=stock.getTotal()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + stock.getTotal();
                        }
                    %>
                    <td><%=stock.getProveedores().getNombre()%></td>
                </tr>
                <%
                        }
                    }
                    if (compro.getCuenta_base() != null) {
                %>
                <tr class="cuadroTexto">
                    <td><%=compro.getCuenta_base().getNum_cuenta()%></td>
                    <td><%=compro.getCuenta_base().getDescripcion()%></td>
                    <td></td>
                    <%
                        if (compro.getDebe_haber().equals("Debe")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compro.getMonto()%></fmt:formatNumber></td>
                    <td></td>
                    <%
                        total_debe = total_debe + compro.getMonto();
                    } else {
                    %>
                    <td></td>
                    <td align="right">$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=compro.getMonto()%></fmt:formatNumber></td>
                    <%
                            total_haber = total_haber + compro.getMonto();
                        }
                    %>
                    <td><%=compro.getDescripcion_2()%></td>
                </tr>
                <%
                        }
                    }
                %>
                <tr class="cuadroTexto">
                    <td></td>
                    <td></td>
                    <td align="right">Total Comprobante</td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=total_debe%></fmt:formatNumber></td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=total_haber%></fmt:formatNumber></td>
                    <td></td>
                </tr>
                <tr class="cuadroTexto">
                    <th class="nobg"></th>
                    <th class="nobg"></th>
                    <th class="nobg"></th>
                    <th class="nobg"></th>
                    <th class="nobg"></th>
                    <th class="nobg"></th>
                </tr>
                <%
                    }
                %>
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