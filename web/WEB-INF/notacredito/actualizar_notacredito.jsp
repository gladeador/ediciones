<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Nota de Credito ::.</title>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/ediciones.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/tablas.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>

        <script language="JavaScript">
            noF5();
        </script>

        <script>
            function modificar(id_notacredito){
                document.formModificar.id_notacredito.value = id_notacredito;
                document.formModificar.submit();
            }
        </script>

        <script>
            function Confirmar(id_notacredito,estado){
                              if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Borrar(id_notacredito,estado);
            }
        </script>

        <script>
            function Borrar(id_notacredito,estado){
                document.formBorrar.id_notacredito.value = id_notacredito;
                document.formBorrar.estado.value = estado;
                document.formBorrar.submit();
                document.formBorrar.reset();
            }
        </script>

    </head>

    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarNotaCredito.ed">
            <input name="id_notacredito" type="hidden">
            <input name="estado" type="hidden">
        </form>

        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminaNotaCredito.ed">
            <input name="id_notacredito" type="hidden">
            <input name="estado" type="hidden" value='A'>
        </form>

        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Nota de Credito</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <legend class="txtResaltadoV">Listado Nota de Credito</legend>
                <table width="100%">
                    <tr>
                        <th>N&deg; de Nota Crédito</th>
                        <th>Rut Cliente</th>
                        <th>Nombre Cliente</th>
                        <th>Total NotaCredito</th>
                    </tr>
                    <%
                        ArrayList lista_notacredito = (ArrayList) request.getAttribute("lista_notacredito");
                        Iterator i = lista_notacredito.iterator();
                        while (i.hasNext()) {
                            NotaCredito nota_credito = (NotaCredito) i.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><%=nota_credito.getNum_notacredito()%></td>
                        <%
                            if (nota_credito.getClientes() == null) {
                        %>
                        <td></td>
                        <td></td>
                        <%                        } else {
                        %>
                        <td><%=nota_credito.getClientes().getRut()%>-<%=nota_credito.getClientes().getDv()%></td>
                        <td><%=nota_credito.getClientes().getNombre()%></td>
                        <%
                            }
                        %>
                        <td align="right">$<%=nota_credito.getTotal()%></td>
                        <th width="1" class="nobg"><a href="javascript:modificar(<%=nota_credito.getId_notacredito()%>);"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Modificar"></a></th>
                        <th width="1" class="nobg"><a href="javascript:Confirmar(<%=nota_credito.getId_notacredito()%>,'A');"><img src="<%=request.getContextPath()%>/images/iconos/eliminar.png" border="0" width="20" height="20" title="Eliminar"></a></th>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </fieldset>
        </form>
    </body>
</html>