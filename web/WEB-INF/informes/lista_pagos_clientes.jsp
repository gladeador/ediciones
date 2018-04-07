<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Ciudad.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

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

        <script>
            function traer_Ciudad(){
                var id_region = document.getElementById("id_region").value;
                if(id_region != ""){
                    Ciudad.traer_todos_por_Region(id_region, cargar_Ciudades);
                }
            }
            
            function cargar_Ciudades(data){
                document.getElementById("id_ciudad").options.length = 0;
                document.getElementById("id_ciudad").options[0] = new Option("Todas","");
                for(i=0,x=1;i<data.length;i++,x++){
                    document.getElementById("id_ciudad").options[x] = new Option(data[i].descripcion,data[i].id_ciudad);
                }
            }
            
        </script>

    </head>

    <%
        Clientes clientes = (Clientes) request.getAttribute("clientes");
        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");
    %>

    <body>
        <form name="formData" method="post" action="<%=request.getContextPath()%>/controller/ListaClientes.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Clientes</th>
                </tr>
            </table>
            <br/>
            <fieldset>
                <table width="100%">
                    <tr>
                        <th>Nombre</th>
                        <th>Contacto</th>
                        <th>E-MAIL</th>
                        <th width="70">Rut</th>
                        <th>Giro</th>
                        <th>Direccion Facturacion</th>
                        <th>Domicilio Particular</th>
                        <th>Telefonos</th>
                        <th>Ciudad</th>
                    </tr>
                    <tr class="cuadroTexto">
                        <th class="specalt" valign="top"><%=clientes.getNombre()%></th>
                        <td valign="top"><%=clientes.getNombre_contacto()%></td>
                        <td valign="top"><%=clientes.getEmail()%></td>
                        <td valign="top" align="right"><fmt:formatNumber type = "number" pattern = "##,###,###" ><%=clientes.getRut()%></fmt:formatNumber>-<%=clientes.getDv()%></td>
                        <td valign="top"><%=clientes.getGiro()%></td>
                        <td valign="top"><%=clientes.getDireccion_facturacion()%></td>
                        <td valign="top"><%=clientes.getDireccion_particular()%></td>
                        <td valign="top">
                            <table>
                                <%
                                    Fono_ContactoDAO fonDAO = new Fono_ContactoDAO();
                                    ArrayList lista_fonos = fonDAO.retrieveCliente_por_Rut(clientes.getRut());
                                    Iterator f = lista_fonos.iterator();
                                    while (f.hasNext()) {
                                        Fono_Contacto fono = (Fono_Contacto) f.next();
                                %>
                                <tr class="cuadroTexto">
                                    <td><%=fono.getFono()%></td>
                                </tr>
                                <%
                                    }
                                %>  
                            </table>
                        </td>
                        <td valign="top"><%=clientes.getCiudad().getDescripcion()%></td>
                    </tr>
                    <tr>
                        <td colspan="9">
                            <table width="100%">
                                <tr>
                                    <th width="70">N&deg; de Factura</th>
                                    <th width="70">Fecha de Factura</th>
                                    <th width="100">neto</th>
                                    <th width="100">iva</th>
                                    <th width="100">Total Factura</th>
                                </tr>
                                <%
                                    FacturaDAO facDAO = new FacturaDAO();
                                    ArrayList lista_facturas = facDAO.traerTodos_Factura_para_ControlPago_por_Cliente(clientes.getRut());
                                    Iterator fn = lista_facturas.iterator();
                                    while (fn.hasNext()) {
                                        Factura factura = (Factura) fn.next();
                                %>
                                <tr class="cuadroTexto">
                                    <td><%=factura.getNum_factura()%></td>
                                    <td><%=factura.getFecha_facturaStr()%></td>
                                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getNeto()%></fmt:formatNumber></td>
                                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getIva()%></fmt:formatNumber></td>
                                    <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getTotal()%></fmt:formatNumber></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <th colspan="4" class="nobg">
                                        <%
                                            if (factura.getForma_pago().getId_forma_pago() == 2) {
                                        %>
                                <fieldset>
                                    <legend class="txtResaltadoV">Cheques</legend>
                                    <table width="100%">
                                        <tr class="cuadroTexto">
                                            <th width="70"># cheque</th>
                                            <th>banco</th>
                                            <th width="100">monto</th>
                                            <th>fecha recepcion documento</th>
                                            <th>fecha vencimiento</th>
                                            <th width="100">monto cancelado</th>
                                            <th>fecha cancela</th>
                                        </tr>
                                        <%
                                            ChequeDAO cheDAO = new ChequeDAO();
                                            ArrayList lista_cheque = cheDAO.traerTodos_Cheque_por_Factura(factura.getId_factura());
                                            if (lista_cheque.isEmpty()) {
                                        %>
                                        <tr>
                                            <td colspan="7" align="center" class="txtResaltadoR">No hay registros de Cheques ingresados</td>
                                        </tr>
                                        <%                                  } else {
                                            Iterator ch = lista_cheque.iterator();
                                            int sum_monto = 0;
                                            int sum_monto_can = 0;
                                            while (ch.hasNext()) {
                                                Cheque cheque = (Cheque) ch.next();
                                        %>
                                        <tr class="cuadroTexto">
                                            <td><%=cheque.getNum_cheque()%></td>
                                            <td><%=cheque.getBanco().getDescripcion()%></td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=cheque.getMonto()%></fmt:formatNumber></td>
                                            <td><%=cheque.getFecha_recepcion_documentoStr()%></td>
                                            <td><%=cheque.getFecha_vencimientoStr() == null ? "" : cheque.getFecha_vencimientoStr()%></td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=cheque.getMonto_cancelado()%></fmt:formatNumber></td>
                                            <td><%=cheque.getFecha_cancelaStr() == null ? "" : cheque.getFecha_cancelaStr()%></td>
                                        </tr>
                                        <%
                                                sum_monto = sum_monto + cheque.getMonto();
                                                sum_monto_can = sum_monto_can + cheque.getMonto_cancelado();
                                            }
                                        %>
                                        <tr class="cuadroTexto">
                                            <td align="right" colspan="2">Total Documentado</td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_monto%></fmt:formatNumber></td>
                                            <td align="right" colspan="2">Total Cancelado</td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_monto_can%></fmt:formatNumber></td>
                                            <td></td>
                                        </tr>
                                        <tr class="cuadroTexto">
                                            <td align="right" colspan="5">Saldo</td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getTotal() - sum_monto_can%></fmt:formatNumber></td>
                                            <td></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </table>
                                </fieldset>
                                <%                            } else if (factura.getForma_pago().getId_forma_pago() == 3) {
                                %>
                                <fieldset>
                                    <legend class="txtResaltadoV">Cuotas</legend>
                                    <table width="100%">
                                        <tr class="cuadroTexto">
                                            <th width="70"># cuota</th>
                                            <th width="100">monto</th>
                                            <th>fecha vencimiento</th>
                                            <th width="100">monto cancelado</th>
                                            <th>fecha cancela</th>
                                        </tr>
                                        <%
                                            CuotaDAO cuoDAO = new CuotaDAO();
                                            ArrayList lista_cuota = cuoDAO.traerTodos_Cuota_por_Factura(factura.getId_factura());
                                            Iterator cu = lista_cuota.iterator();
                                            if (lista_cuota.isEmpty()) {
                                        %>
                                        <tr>
                                            <td colspan="5" align="center" class="txtResaltadoR">No hay registros de Cuotas ingresadas</td>
                                        </tr>
                                        <%                                  } else {
                                            int cont = 1;
                                            int sum_monto = 0;
                                            int sum_monto_can = 0;
                                            while (cu.hasNext()) {
                                                Cuota cuota = (Cuota) cu.next();
                                        %>
                                        <tr class="cuadroTexto">
                                            <td><%=cont%></td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=cuota.getMonto()%></fmt:formatNumber></td>
                                            <td><%=cuota.getFecha_vencimientoStr() == null ? "" : cuota.getFecha_vencimientoStr()%></td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=cuota.getMonto_cancelado()%></fmt:formatNumber></td>
                                            <td><%=cuota.getFecha_cancelaStr() == null ? "" : cuota.getFecha_cancelaStr()%></td>
                                        </tr>
                                        <%
                                                sum_monto = sum_monto + cuota.getMonto();
                                                sum_monto_can = sum_monto_can + cuota.getMonto_cancelado();
                                            }
                                        %>
                                        <tr class="cuadroTexto">
                                            <td align="right">Total Documentado</td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_monto%></fmt:formatNumber></td>
                                            <td align="right">Total Cancelado</td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_monto_can%></fmt:formatNumber></td>
                                            <td></td>
                                        </tr>
                                        <tr class="cuadroTexto">
                                            <td align="right" colspan="3">Saldo</td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getTotal() - sum_monto_can%></fmt:formatNumber></td>
                                            <td></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </table>
                                </fieldset>
                                <%                            } else if (factura.getForma_pago().getId_forma_pago() == 4) {
                                %>
                                <fieldset>
                                    <legend class="txtResaltadoV">Letras</legend>
                                    <table width="100%">
                                        <tr class="cuadroTexto">
                                            <th width="70"># letra</th>
                                            <th width="100">monto</th>
                                            <th>fecha recepcion documento</th>
                                            <th>fecha vencimiento</th>
                                            <th width="100">monto cancelado</th>
                                            <th>fecha cancela</th>
                                        </tr>
                                        <%
                                            LetraDAO letDAO = new LetraDAO();
                                            ArrayList lista_letra = letDAO.traerTodos_Letra_por_Factura(factura.getId_factura());
                                            if (lista_letra.isEmpty()) {
                                        %>
                                        <tr>
                                            <td colspan="6" align="center" class="txtResaltadoR">No hay registros de Letras ingresadas</td>
                                        </tr>
                                        <%                                  } else {
                                            Iterator l = lista_letra.iterator();
                                            int sum_monto = 0;
                                            int sum_monto_can = 0;
                                            while (l.hasNext()) {
                                                Letra letra = (Letra) l.next();
                                        %>
                                        <tr class="cuadroTexto">
                                            <td><%=letra.getNum_letra()%></td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=letra.getMonto()%></fmt:formatNumber></td>
                                            <td><%=letra.getFecha_recepcion_documentoStr()%></td>
                                            <td><%=letra.getFecha_vencimientoStr() == null ? "" : letra.getFecha_vencimientoStr()%></td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=letra.getMonto_cancelado()%></fmt:formatNumber></td>
                                            <td><%=letra.getFecha_cancelaStr() == null ? "" : letra.getFecha_cancelaStr()%></td>
                                        </tr>
                                        <%
                                                sum_monto = sum_monto + letra.getMonto();
                                                sum_monto_can = sum_monto_can + letra.getMonto_cancelado();
                                            }
                                        %>
                                        <tr class="cuadroTexto">
                                            <td align="right">Total Documentado</td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_monto%></fmt:formatNumber></td>
                                            <td align="right" colspan="2">Total Cancelado</td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=sum_monto_can%></fmt:formatNumber></td>
                                            <td></td>
                                        </tr>
                                        <tr class="cuadroTexto">
                                            <td align="right" colspan="4">Saldo</td>
                                            <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getTotal() - sum_monto_can%></fmt:formatNumber></td>
                                            <td></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </table>
                                </fieldset>
                                <%                            }
                                %>
                                </th>
                    </tr>
                    <tr>
                        <th colspan="5" height="1"></th>
                    </tr>
                    <%
                        }
                    %>
                </table>
                </td>
                </tr>
                </table>
            </fieldset>
            <br/>
            <table align="center" id="button_imprimir">
                <tr>
                    <th><input name="b_imprimir" type="button" id="b_imprimir" class="botones" onclick="javascript:imprimir()" value="Imprimir"></th>
                </tr>
            </table>
            <script>
                function imprimir(){
                    showhide('button_imprimir');
                    showhide('fieldset_filtro');
                    window.print();
                    showhide('fieldset_filtro');
                    showhide('button_imprimir');
                }
            </script>
        </form>
    </body>
</html>