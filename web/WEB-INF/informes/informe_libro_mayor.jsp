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
                    <td class="nobg" width="10%" style="vertical-align: bottom" align="center">Libro Mayor</td>
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
                    <th class="nobg" width="100">Fecha</th>
                    <th class="nobg" width="10%">Num Comprobante</th>
                    <th class="nobg" width="10%">Cuenta</th>
                    <th class="nobg"></th>
                    <th class="nobg" width="60">Debe</th>
                    <th class="nobg" width="60">Haber</th>
                    <th class="nobg" width="60">Saldo</th>
                    <th class="nobg">Descripción</th>
                </tr>
                <%
                    ArrayList lista_cuentas = (ArrayList) request.getAttribute("lista_cuentas");
                    Iterator lc = lista_cuentas.iterator();
                    while (lc.hasNext()) {
                        Cuentas_Base cuentas_base = (Cuentas_Base) lc.next();
                        int saldo_debe = 0;
                        int saldo_haber = 0;

                        ArrayList lista_saldo_anterior = (ArrayList) request.getAttribute("lista_saldo_anterior");
                        Iterator sa = lista_saldo_anterior.iterator();
                        while (sa.hasNext()) {
                            Libro_Mayor libro = (Libro_Mayor) sa.next();
                            if (libro.getCuentas_base().getNum_cuenta().equals(cuentas_base.getNum_cuenta())) {
                                if ((libro.getCuentas_base().getCuentas().equals("Activo") || libro.getCuentas_base().getCuentas().equals("Perdida")) && libro.getDebe_haber().equals("")) {
                                    saldo_debe = saldo_debe + libro.getValor();
                                } else if (libro.getDebe_haber().equals("Debe")) {
                                    saldo_debe = saldo_debe + libro.getValor();
                                } else {
                                    saldo_haber = saldo_haber + libro.getValor();
                                }
                            }
                        }
                %>
                <tr class="cuadroTexto">
                    <th class="nobg">N° Cuenta</th>
                    <td><%=cuentas_base.getNum_cuenta()%></td>
                    <td><%=cuentas_base.getDescripcion()%></td>
                    <td align="right">Saldo Anterior de la Cuenta:</td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=saldo_debe%></fmt:formatNumber></td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=saldo_haber%></fmt:formatNumber></td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=saldo_debe - saldo_haber%></fmt:formatNumber></td>
                    <td></td>
                </tr>
                <%
                    ArrayList lista_comprobantes = (ArrayList) request.getAttribute("lista_comprobantes");
                    Iterator lc2 = lista_comprobantes.iterator();
                    int saldo = saldo_debe - saldo_haber;
                    int total_debe = 0;
                    int total_haber = 0;
                    while (lc2.hasNext()) {
                        Libro_Mayor libro = (Libro_Mayor) lc2.next();
                        if (libro.getCuentas_base().getNum_cuenta().equals(cuentas_base.getNum_cuenta())) {
                %>
                <tr class="cuadroTexto">
                    <td><%=libro.getFechaStr()%></td>
                    <td><%=libro.getComprobante().getNum_comprobante()%></td>
                    <td></td>
                    <td></td>
                    <%
                        if ((libro.getCuentas_base().getCuentas().equals("Activo") || libro.getCuentas_base().getCuentas().equals("Perdida")) && libro.getDebe_haber().equals("")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=libro.getValor()%></fmt:formatNumber></td>
                    <td align = "right">$0</td>
                    <%
                        total_debe = total_debe + libro.getValor();
                        saldo = saldo + libro.getValor();
                    } else if (libro.getDebe_haber().equals("Debe")) {
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=libro.getValor()%></fmt:formatNumber></td>
                    <td align = "right">$0</td>
                    <%
                        total_debe = total_debe + libro.getValor();
                        saldo = saldo + libro.getValor();
                    } else {
                    %>
                    <td align = "right">$0</td>
                    <td align = "right" >$<fmt:formatNumber type = "number" pattern = "##,###,###" ><%=libro.getValor()%></fmt:formatNumber></td>
                    <%
                        total_haber = total_haber + libro.getValor();
                        saldo = saldo - libro.getValor();
                    }
                    %>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=saldo%></fmt:formatNumber></td>
                    <td><%=libro.getComprobante().getDescripcion()%></td>
                </tr>
                <%
                        }
                    }
                %>
                <tr class="cuadroTexto">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="right">Total Cuenta en el período:</td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=total_debe%></fmt:formatNumber></td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=total_haber%></fmt:formatNumber></td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=total_debe - total_haber%></fmt:formatNumber></td>
                    <td></td>
                </tr>
                <tr class="cuadroTexto">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="right">Total Cuenta:</td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=saldo_debe + total_debe%></fmt:formatNumber></td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=saldo_haber + total_haber%></fmt:formatNumber></td>
                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=(saldo_debe - saldo_haber) + (total_debe - total_haber)%></fmt:formatNumber></td>
                    <td></td>
                </tr>
                <tr class="cuadroTexto">
                    <th class="nobg"></th>
                    <th class="nobg"></th>
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