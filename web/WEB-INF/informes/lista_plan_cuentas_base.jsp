<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Plan de Cuentas Base ::.</title>
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
                    <th class="menuSuperior">Plan de Cuentas Base</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <table width="100%">
                    <tr>
                        <th width="80">Cuentas</th>
                        <th>Descripcion</th>
                        <th width="100"></th>
                        <th width="100">Cuenta</th>
                    </tr>
                    <%
                        ArrayList listaPadres = (ArrayList) request.getAttribute("Lista_Cuentas");
                        Iterator p = listaPadres.iterator();
                        while (p.hasNext()) {
                            Cuentas_Base cuentas = (Cuentas_Base) p.next();
                    %>
                    <tr class="cuadroTexto">
                        <th class="specalt"><%=cuentas.getNum_cuenta()%></th>
                        <td><%=cuentas.getDescripcion()%></td>
                        <td><%=cuentas.getTipo()%></td>
                        <td><%=cuentas.getCuentas()%></td>
                    </tr>
                    <%
                        Cuentas_BaseDAO cueDAO = new Cuentas_BaseDAO();
                        ArrayList listaHijos = cueDAO.traerTodos_Cuentas_Base_Hijo_por_Padre(cuentas.getNum_cuenta());
                        Iterator h = listaHijos.iterator();
                        while (h.hasNext()) {
                            Cuentas_Base cuentas_h = (Cuentas_Base) h.next();
                    %>
                    <tr class="cuadroTexto">
                        <th class="spec"><div align="center"><%=cuentas_h.getNum_cuenta()%></div></th>
                        <td><%=cuentas_h.getDescripcion()%></td>
                        <td><%=cuentas_h.getTipo()%></td>
                        <td><%=cuentas_h.getCuentas()%></td>
                    </tr>
                    <%
                        ArrayList listaHijos_2 = cueDAO.traerTodos_Cuentas_Base_Hijo_por_Padre(cuentas_h.getNum_cuenta());
                        Iterator h_2 = listaHijos_2.iterator();
                        while (h_2.hasNext()) {
                            Cuentas_Base cuentas_h_2 = (Cuentas_Base) h_2.next();
                    %>
                    <tr class="cuadroTexto">
                        <td align="right"><%=cuentas_h_2.getNum_cuenta()%></td>
                        <td><%=cuentas_h_2.getDescripcion()%></td>
                        <td><%=cuentas_h_2.getTipo()%></td>
                        <td><%=cuentas_h_2.getCuentas()%></td>
                    </tr>
                    <%
                                }
                            }
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