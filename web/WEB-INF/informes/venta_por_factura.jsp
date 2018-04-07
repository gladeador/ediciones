<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Venta por Factura ::.</title>
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
        ArrayList lista_facturas = (ArrayList) request.getAttribute("Lista_facturas");
    %>

    <body>
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Venta por Factura</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <table width="100%">
                    <tr>
                        <th>RAZÓN SOCIAL</th>
                        <th>FECHA DESPACHO</th>
                        <th>Nº GUÍA</th>
                        <th>Nº FACT.</th>
                        <th width="100">MONTO NETO</th>
                        <th width="100">MONTO C/ IVA</th>
                    </tr>
                    <%
                        Iterator f = lista_facturas.iterator();
                        while (f.hasNext()) {
                            Factura factura = (Factura) f.next();
                    %>
                    <tr class="cuadroTexto">
                        <th class="specalt"><%=factura.getClientes().getNombre()%></th>
                        <td><%=factura.getFecha_facturaStr()%></td>
                        <td>
                            <%
                                String guias = null;
                                Guia_DespachoDAO guiDAO = new Guia_DespachoDAO();
                                ArrayList lista_guias = guiDAO.traerTodos_Guia_Despacho_por_Factura(factura.getId_factura());
                                Iterator g = lista_guias.iterator();
                                while (g.hasNext()) {
                                    Guia_Despacho guia = (Guia_Despacho) g.next();
                                    if (guias == null) {
                                        guias = guia.getNum_guia() + "";
                                    } else {
                                        guias = " - " + guia.getNum_guia();
                                    }
                            %>
                            <%=guias%>
                            <%
                                }
                            %>
                        </td>
                        <td><%=factura.getNum_factura()%></td>
                        <td><div align="right"><%=factura.getNeto()%></div></td>
                        <td><div align="right"><%=factura.getTotal()%></div></td>
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