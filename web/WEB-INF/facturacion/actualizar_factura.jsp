<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Guï¿½as ::.</title>
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
            function modificar(id_factura){
                document.formModificar.id_factura.value = id_factura;
                document.formModificar.submit();
            }
            
            function eliminar(id_factura){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                document.formBorrar.id_factura.value = id_factura;
                document.formBorrar.submit();
                document.formBorrar.reset();
            }
            
            function anular(id_factura){
                if (!confirm("¿Esta seguro que desea Anular este Registro?")){
                    return;
                }
                document.formAnular.id_factura.value = id_factura;
                document.formAnular.submit();
                document.formAnular.reset();
            }
            
            function imprimir_factura(num_factura){
                popup = window.open("http://192.168.10.5/facturacion/factura.php?num_factura="+num_factura+"","popup","width=800,height=600,location=no,top=100,left=120,scrollbars=yes");
                popup.focus();
            }
        </script>

    </head>

    <%
        HttpSession sesion = request.getSession();
        Fecha_Actual fecha = (Fecha_Actual) sesion.getAttribute("fecha_actual");
    %>

    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarFactura.ed">
            <input name="id_factura" type="hidden">
        </form>

        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminaFactura.ed">
            <input name="id_factura" type="hidden">
        </form>

        <form name="formAnular" method="post" action="<%=request.getContextPath()%>/controller/AnulaFactura.ed">
            <input name="id_factura" type="hidden">
        </form>

        <form name="formData" method="post" action="<%=request.getContextPath()%>/controller/ActualizaFacturaCriterios.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Facturas</th>
                </tr>
            </table>
            <br/>
            <fieldset id="fieldset_filtro">
                <legend class="menuSuperior"><a href="javascript:showhide('tabla_filtro');">Filtro de Busqueda</a></legend>
                <table id="tabla_filtro" style="display:none;">
                    <tr>
                        <th class="specalt" valign="top">Rut Cliente </th>
                        <td><input name="rut" class="cuadroTexto" id="rut" value="<%=request.getParameter("rut") == null ? "" : request.getParameter("rut")%>" /></td>
                    </tr>
                    <tr>
                        <th class="specalt">Busqueda por Cliente</th>
                        <td>
                            <select name="rut_cliente_b" class="cuadroTexto" id="rut_cliente_b">
                                <option value="">Todos</option>
                                <%
                                    ClientesDAO cliDAO = new ClientesDAO();
                                    ArrayList lista_clientes_busqueda = cliDAO.traerTodos_Clientes();
                                    Iterator cb = lista_clientes_busqueda.iterator();
                                    while (cb.hasNext()) {
                                        Clientes clientes = (Clientes) cb.next();
                                %>
                                <option value="<%=clientes.getRut()%>"><%=clientes.getNombre()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th class="specalt" valign="top">N° Factura </th>
                        <td><input name="num_factura" class="cuadroTexto" id="num_factura" value="<%=request.getParameter("num_factura") == null ? "" : request.getParameter("num_factura")%>" /></td>
                    </tr>
                    <tr>
                        <th class="specalt" valign="top">Mes </th>
                        <td valign="top">
                            <select name="mes" id="mes" class="cuadroTexto">
                                <option value="">Todos</option>
                                <option value="01">Enero</option>
                                <option value="02">Febrero</option>
                                <option value="03">Marzo</option>
                                <option value="04">Abril</option>
                                <option value="05">Mayo</option>
                                <option value="06">Junio</option>
                                <option value="07">Julio</option>
                                <option value="08">Agosto</option>
                                <option value="09">Septiembre</option>
                                <option value="10">Octubre</option>
                                <option value="11">Noviembre</option>
                                <option value="12">Diciembre</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th class="specalt" valign="top">Año </th>
                        <td valign="top">
                            <select name="ano" id="ano" class="cuadroTexto">
                                <option value="" selected="">Todos</option>
                                <%
                                    for (int i = 2011; i <= Integer.parseInt(fecha.getAno()); i++) {
                                %>
                                <option value="<%=i%>"><%=i%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th class="specalt" valign="top">Estado Factura </th>
                        <td valign="top">
                            <select name="estado" id="estado" class="cuadroTexto">
                                <option value="">Todas</option>
                                <option value="N">Nuevas</option>
                                <option value="E">Emitidas</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2"><center><input name="b_buscar" type="button" id="b_buscar" class="botones" onclick="javascript:document.formData.submit();" value="Buscar" /></center></th>
                    </tr>
                </table>
            </fieldset>
            <br/>
            <fieldset>
                <legend class="txtResaltadoV">Listado de Facturas</legend>
                <table width="100%">
                    <tr>
                        <th width="70">N&deg; de Factura</th>
                        <th width="70">Fecha de Factura</th>
                        <th width="70">Rut Cliente</th>
                        <th>Nombre Cliente</th>
                        <th width="100">neto</th>
                        <th width="100">iva</th>
                        <th width="100">Total Factura</th>
                    </tr>
                    <tr>
                        <th colspan="7">Nuevas</th>
                    </tr>
                    <%
                        ArrayList lista_facturas_nuevas = (ArrayList) request.getAttribute("lista_facturas_nuevas");
                        if (lista_facturas_nuevas.isEmpty()) {
                    %>
                    <tr>
                        <td colspan="7" class="txtResaltadoV">No Hay Facturas Nuevas</td>
                    </tr>
                    <%                    } else {
                        Iterator fn = lista_facturas_nuevas.iterator();
                        while (fn.hasNext()) {
                            Factura factura = (Factura) fn.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><%=factura.getNum_factura()%></td>
                        <td><%=factura.getFecha_facturaStr()%></td>
                        <%
                            if (factura.getClientes() == null) {
                        %>
                        <td></td>
                        <td></td>
                        <%                        } else {
                        %>
                        <td><fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getClientes().getRut()%></fmt:formatNumber>-<%=factura.getClientes().getDv()%></td>
                        <td><%=factura.getClientes().getNombre()%></td>
                        <%
                            }
                        %>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getNeto()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getIva()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getTotal()%></fmt:formatNumber></td>
                        <th width="1" class="nobg"><a href="javascript:modificar(<%=factura.getId_factura()%>);"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Modificar"></a></th>
                        <th width="1" class="nobg"><a href="javascript:eliminar(<%=factura.getId_factura()%>);"><img src="<%=request.getContextPath()%>/images/iconos/eliminar.png" border="0" width="20" height="20" title="Eliminar"></a></th>
                    </tr>
                    <%
                            }
                        }
                    %>
                    <tr>
                        <th colspan="7">Emitidas</th>
                    </tr>
                    <%
                        ArrayList lista_facturas_emitidas = (ArrayList) request.getAttribute("lista_facturas_emitidas");
                        if (lista_facturas_emitidas.isEmpty()) {
                    %>
                    <tr>
                        <td colspan="7" class="txtResaltadoV">No Hay Facturas Emitidas</td>
                    </tr>
                    <%                    } else {
                        Iterator fe = lista_facturas_emitidas.iterator();
                        while (fe.hasNext()) {
                            Factura factura = (Factura) fe.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><%=factura.getNum_factura()%></td>
                        <td><%=factura.getFecha_facturaStr()%></td>
                        <%
                            if (factura.getClientes() == null) {
                        %>
                        <td></td>
                        <td></td>
                        <%                        } else {
                        %>
                        <td><fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getClientes().getRut()%></fmt:formatNumber>-<%=factura.getClientes().getDv()%></td>
                        <td><%=factura.getClientes().getNombre()%></td>
                        <%
                            }
                        %>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getNeto()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getIva()%></fmt:formatNumber></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getTotal()%></fmt:formatNumber></td>
                        <th width="1" class="nobg"><a href="javascript:imprimir_factura(<%=factura.getNum_factura()%>);"><img src="<%=request.getContextPath()%>/images/iconos/imprimir.png" border="0" width="20" height="20" title="Imprimir" /></a></th>
                        <th width="1" class="nobg"><a href="javascript:anular(<%=factura.getId_factura()%>);"><img src="<%=request.getContextPath()%>/images/iconos/anular.png" border="0" width="20" height="20" title="Anular" /></a></th>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </fieldset>
        </form>
    </body>
</html>