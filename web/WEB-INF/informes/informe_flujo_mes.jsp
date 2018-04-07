<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Informe Flujo Cierre de Mes ::.</title>
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
                    <th class="menuSuperior">Informe Flujo Cierre de Mes</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <table width="100%">
                    <tr>
                        <th>RAZÓN SOCIAL</th>
                        <%
                            String mes_f = request.getParameter("mes");
                            String mes = "";
                            String ano = request.getParameter("ano");
                            int cont = 0;
                            int total = 0;
                            ArrayList lista_meses = (ArrayList) request.getAttribute("lista_meses");
                            Iterator m = lista_meses.iterator();
                            while (m.hasNext()) {
                                Fecha_Actual fecha = (Fecha_Actual) m.next();

                                cont++;
                        %>
                        <th width="50"><%=fecha.getMes()%> <%=fecha.getAno()%></th>
                        <%
                            }
                        %>
                        <th width="100">MONTO C/ IVA</th>
                    </tr>
                    <%
                        ArrayList lista_formas_pago = (ArrayList) request.getAttribute("lista_formas_pago");
                        Iterator fp = lista_formas_pago.iterator();
                        while (fp.hasNext()) {
                            Forma_Pago forma = (Forma_Pago) fp.next();
                            ArrayList lista_factura = (ArrayList) request.getAttribute("lista_factura");
                            Iterator f = lista_factura.iterator();
                            while (f.hasNext()) {
                                Factura factura = (Factura) f.next();
                                if (forma.getId_forma_pago() == factura.getForma_pago().getId_forma_pago()) {
                    %>
                    <tr class="cuadroTexto">
                        <td><%=factura.getClientes().getNombre()%></td>
                        <%
                            int sub_total = 0;
                            for (int i = factura.getMes_factura(); i <= (cont + 2); i++) {
                                int mes_2 = 0;
                                int ano_2 = 0;
                                int valor = 0;
                                if (i > 0 && i <= 12) {
                                    mes_2 = i;
                                    ano_2 = Integer.parseInt(ano);
                                }
                                if (i > 12 && i <= 24) {
                                    mes_2 = i - 12;
                                    ano_2 = Integer.parseInt(ano) + 1;
                                }
                                if (forma.getId_forma_pago() == 1) {
                                    if (factura.getMes_factura() == mes_2 && factura.getAno_factura() == ano_2) {
                                        valor = factura.getTotal();
                                    }
                                }
                                if (forma.getId_forma_pago() == 2) {
                                    ChequeDAO cheDAO = new ChequeDAO();
                                    ArrayList lista_cheques = cheDAO.traerTodos_Cheque_por_Factura(factura.getId_factura());
                                    Iterator c = lista_cheques.iterator();
                                    while (c.hasNext()) {
                                        Cheque cheque = (Cheque) c.next();
                                        if (cheque.getMes_cheque_ven() == mes_2 && cheque.getAno_cheque_ven() == ano_2) {
                                            valor = cheque.getMonto();
                                        }
                                    }
                                }
                                if (forma.getId_forma_pago() == 3) {
                                    CuotaDAO cuoDAO = new CuotaDAO();
                                    ArrayList lista_cuotas = cuoDAO.traerTodos_Cuota_por_Factura(factura.getId_factura());
                                    Iterator c = lista_cuotas.iterator();
                                    while (c.hasNext()) {
                                        Cuota cuota = (Cuota) c.next();
                                        if (cuota.getMes_cuota_ven() == mes_2 && cuota.getAno_cuota_ven() == ano_2) {
                                            valor = cuota.getMonto();
                                        }
                                    }
                                }
                                if (forma.getId_forma_pago() == 4) {
                                    LetraDAO letDAO = new LetraDAO();
                                    ArrayList lista_letras = letDAO.traerTodos_Letra_por_Factura(factura.getId_factura());
                                    Iterator c = lista_letras.iterator();
                                    while (c.hasNext()) {
                                        Letra letra = (Letra) c.next();
                                        if (letra.getMes_letra_ven() == mes_2 && letra.getAno_letra_ven() == ano_2) {
                                            valor = letra.getMonto();
                                        }
                                    }
                                }
                                sub_total = sub_total + valor;
                        %>
                        <td align="right"><fmt:formatNumber type="number" pattern="##,###,###"><%=valor%></fmt:formatNumber></td>
                        <%
                            }
                            total = total + sub_total;
                        %>
                        <td align="right"><fmt:formatNumber type="number" pattern="##,###,###"><%=sub_total%></fmt:formatNumber></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    <tr class="titulos">
                        <th><%=forma.getDescripcion()%></th>
                    </tr>
                    <%
                        }
                    %>
                    <tr class="cuadroTexto">
                        <td colspan="<%=cont%>"></td>
                        <th>Total</th>
                        <td align="right"><fmt:formatNumber type="number" pattern="##,###,###"><%=total%></fmt:formatNumber></td>
                    </tr>
                </table>
            </fieldset>
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