<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Clientes ::.</title>
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
                    <th class="menuSuperior">Libro de Compras - Mes: <%=request.getParameter("mes")%> </th>
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
                                    <th>Nº</th>
                                    <th>Tipo</th>
                                    <th>Documento</th>
                                    <th>Fecha</th>
                                    <th>Proveedor</th>
                                    <th>Rut</th>
                                    <th>Exento</th>
                                    <th>Afecto</th>
                                    <th>Iva</th>
                                    <th>Total</th>
                                </tr>
                                <%
                                    int cont = 1;
                                    ArrayList lista_compras = (ArrayList) request.getAttribute("Libro_Compras");
                                    Iterator i = lista_compras.iterator();
                                    while (i.hasNext()) {
                                        Libro_Compras compras = (Libro_Compras) i.next();
                                %>
                                <tr class="cuadroTexto">
                                    <td><%=cont++%></td>
                                    <td align="center"><%=compras.getTipo()%></td>
                                    <td align="right"><%=compras.getDocumento()%></td>
                                    <td><%=compras.getFechaStr()%></td>
                                    <td><%=compras.getProveedores().getNombre()%></td>
                                    <td><%=compras.getProveedores().getRut()%>-<%=compras.getProveedores().getDv()%></td>
                                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compras.getExento()%></fmt:formatNumber></td>
                                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compras.getAfecto()%></fmt:formatNumber></td>
                                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=compras.getIva()%></fmt:formatNumber></td>
                                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=(compras.getExento()+compras.getAfecto()+compras.getIva())%></fmt:formatNumber></td>
                                </tr>
                                <%
                                    }
                                %>
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