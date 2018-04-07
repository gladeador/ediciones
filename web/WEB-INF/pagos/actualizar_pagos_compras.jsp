<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Cheque_Pago.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<!-- inclusion de js para control de ventanas -->
<script type='text/javascript' src='<%=request.getContextPath()%>/js/jwindows/prototype.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/js/jwindows/window.js'> </script>
<link href="<%=request.getContextPath()%>/js/jwindows/themes/default.css" rel="stylesheet" type="text/css"></link>
<link href="<%=request.getContextPath()%>/js/jwindows/themes/alphacube.css" rel="stylesheet" type="text/css"> </link>
<!-- fin inclusion -->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Pagos ::.</title>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/ediciones.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/tablas.css">
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/js_calendar/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/js_calendar/dhtmlgoodies_calendar.js?random=20060118"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>

        <script language="JavaScript">
            noF5();
        </script>

        <script>
            function agregar_documento_factura(num_factura, rut_proveedor){
                Dialog.info($("agrega_Cheque_Pago").innerHTML,{className:"alphacube", title:"Agrega Cheque Pago", height:250, top:100});
                document.getElementById("rut_proveedor").value = rut_proveedor;
                document.getElementById("num_factura").value = num_factura;
            }
            
            function agregar_documento_gastos(id_gastos, rut_proveedor){
                Dialog.info($("agrega_Cheque_Pago_Gastos").innerHTML,{className:"alphacube", title:"Agrega Cheque Pago", height:250, top:100});
                document.getElementById("rut_proveedor").value = rut_proveedor;
                document.getElementById("id_gastos").value = id_gastos;
            }
            
            function agrega_cheque(){
                var num_cheque = document.getElementById("num_cheque").value;
                var id_banco = document.getElementById("id_banco").value;
                var monto = document.getElementById("monto_cheque").value;
                var rut_proveedor = document.getElementById("rut_proveedor").value;
                var fecha_recepcion_documento = document.getElementById("fecha_recepcion_documento").value;
                var fecha_vencimiento = document.getElementById("fecha_vencimiento_cheque").value;
                var num_factura = document.getElementById("num_factura").value;
                Cheque_Pago.agregar_Cheque_Pago(num_cheque, id_banco, monto, rut_proveedor, fecha_recepcion_documento, fecha_vencimiento, num_factura, recupera_AgregaDoc);
            }
            
            function agrega_cheque_gastos(){
                var num_cheque = document.getElementById("num_cheque").value;
                var id_banco = document.getElementById("id_banco").value;
                var monto = document.getElementById("monto_cheque").value;
                var rut_proveedor = document.getElementById("rut_proveedor").value;
                var fecha_recepcion_documento = document.getElementById("fecha_recepcion_documento").value;
                var fecha_vencimiento = document.getElementById("fecha_vencimiento_cheque").value;
                var id_gastos = document.getElementById("id_gastos").value;
                Cheque_Pago.agregar_Cheque_Pago_Gastos(num_cheque, id_banco, monto, rut_proveedor, fecha_recepcion_documento, fecha_vencimiento, id_gastos, recupera_AgregaDoc);
            }
            
            function ventana_pagar_cheque(id_cheque, num_cheque, monto, fecha_vencimiento){
                Dialog.info($('pagar_cheque').innerHTML,{className:"alphacube", title:"Pagar Cheque Pago", height:220, top:100});
                document.getElementById("id_cheque").value = id_cheque;
                document.getElementById("num_cheque").value = num_cheque;
                document.getElementById("monto_cheque").value = monto;
                document.getElementById("fecha_vencimiento_cheque").value = fecha_vencimiento;
            }
            
            function cancela_cheque(){
                var id_cheque = document.getElementById("id_cheque").value;
                var monto_cheque = document.getElementById("monto_cancelado_cheque").value;
                var fecha_cancela_cheque = document.getElementById("fecha_cancela_cheque").value;
                Cheque_Pago.cancelar_Cheque_Pago(id_cheque, monto_cheque, fecha_cancela_cheque, recupera_CancelarDoc);
            }
            
            function revertir_pagar_cheque(id_cheque){
                if (!confirm("¿Esta seguro que desea Revertir el Pago de este Registro?")){
                    return;
                }
                Cheque_Pago.revertir_cancelar_Cheque_Pago(id_cheque, recupera_AgregaDoc);
            }
            
            function recupera_AgregaDoc(data){
                cerrar_ventana();
                if(data == "ok"){
                    document.formData.submit();
                    alert("Registro agregado con Exito");
                }else{
                    alert("Ha ocurrido un error al intentar agregar el Registro\nPor favor, vuelva a intentarlo.");
                }
            }
            
            function recupera_CancelarDoc(data){
                cerrar_ventana();
                if(data == "ok"){
                    document.formData.submit();
                    alert("Registro cancelado con Exito");
                }else{
                    alert("Ha ocurrido un error al intentar cancelar el Registro\nPor favor, vuelva a intentarlo.");
                }
            }
            
        </script>

    </head>

    <%
        HttpSession sesion = request.getSession();
        Fecha_Actual fecha = (Fecha_Actual) sesion.getAttribute("fecha_actual");
    %>

    <body>
        <form name="formData" method="post" action="<%=request.getContextPath()%>/controller/ActualizarPagosCompras.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Pago de Stock_Productos</th>
                </tr>
            </table>
            <br/>
            <table align="center">
                <tr>
                    <th class="specalt">Busqueda por Proveedor</th>
                    <td>
                        <select name="rut_proveedor_b" class="cuadroTexto" id="rut_proveedor_b">
                            <option>Todos</option>
                            <%
                                ProveedoresDAO cliDAO = new ProveedoresDAO();
                                ArrayList lista_proveedores_busqueda = cliDAO.traerTodos_Proveedores();
                                Iterator cb = lista_proveedores_busqueda.iterator();
                                while (cb.hasNext()) {
                                    Proveedores proveedores = (Proveedores) cb.next();
                            %>
                            <option value="<%=proveedores.getRut()%>"><%=proveedores.getNombre()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                    <th class="nobg"><a href="javascript:document.formData.submit();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/buscar.png" border="0" width="30" height="30" title="Buscar"/></a></th>
                </tr>
            </table>
            <br/>
            <fieldset>
                <legend class="txtResaltadoV">Listado de Facturas de Compra</legend>
                <table width="100%">
                    <tr class="cuadroTexto">
                        <th width="1%"></th>
                        <th width="70">N&deg; de Factura</th>
                        <th width="70">Rut Proveedor</th>
                        <th>Nombre Proveedor</th>
                        <th width="100">Total Factura</th>
                    </tr>
                    <%
                        ArrayList lista_facturas = (ArrayList) request.getAttribute("lista_facturas");
                        Iterator i = lista_facturas.iterator();
                        while (i.hasNext()) {
                            Stock_Producto factura = (Stock_Producto) i.next();
                    %>
                    <tr class="cuadroTexto">
                        <td align="center"><a href="javascript:showhide('pago_factura_<%=factura.getNum_factura()%>_<%=factura.getProveedores().getRut()%>');"><img src="<%=request.getContextPath()%>/images/mostrar.png" border="0" width="13" height="13" title="Ver Detalle" /></a></td>
                        <td><%=factura.getNum_factura()%></td>
                        <%
                            if (factura.getProveedores() == null) {
                        %>
                        <td></td>
                        <td></td>
                        <%                        } else {
                        %>
                        <td align="right"><%=factura.getProveedores().getRut()%>-<%=factura.getProveedores().getDv()%></td>
                        <td><%=factura.getProveedores().getNombre()%></td>
                        <%
                            }
                        %>
                        <td align="right">$<%=factura.getTotal()%></td>
                        <th width="1" class="nobg"><a href="javascript:agregar_documento_factura('<%=factura.getNum_factura()%>','<%=factura.getProveedores().getRut()%>');"><img src="<%=request.getContextPath()%>/images/iconos/agregar_documento.png" border="0" width="20" height="20" title="Agregar Cheque Pago"></a></th>
                    </tr>
                    <tr id="pago_factura_<%=factura.getNum_factura()%>_<%=factura.getProveedores().getRut()%>" style="display:none;">
                        <th class="nobg"></th>
                        <td colspan="6" class="nobg">
                            <fieldset>
                                <legend class="txtResaltadoV">Cheque_Pagos</legend>
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
                                        Cheque_PagoDAO cheDAO = new Cheque_PagoDAO();
                                        ArrayList lista_cheque = cheDAO.traerTodos_Cheque_Pago_por_Stock_Producto(factura.getNum_factura(), factura.getProveedores().getRut());
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
                                            Cheque_Pago cheque = (Cheque_Pago) ch.next();
                                    %>
                                    <tr class="cuadroTexto">
                                        <td><%=cheque.getNum_cheque_pago()%></td>
                                        <td><%=cheque.getBanco().getDescripcion()%></td>
                                        <td align="right"><%=cheque.getMonto()%></td>
                                        <td><%=cheque.getFecha_recepcion_documentoStr()%></td>
                                        <td><%=cheque.getFecha_vencimientoStr() == null ? "" : cheque.getFecha_vencimientoStr()%></td>
                                        <td align="right"><%=cheque.getMonto_cancelado()%></td>
                                        <td><%=cheque.getFecha_cancelaStr() == null ? "" : cheque.getFecha_cancelaStr()%></td>
                                        <%
                                            if (cheque.getMonto_cancelado() == 0) {
                                        %>
                                        <th class="nobg"><a href="javascript:ventana_pagar_cheque('<%=cheque.getId_cheque_pago()%>','<%=cheque.getNum_cheque_pago()%>',<%=cheque.getMonto()%>,'<%=cheque.getFecha_vencimientoStr() == null ? "" : cheque.getFecha_vencimientoStr()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/seleccionar.png" border="0" width="20" height="20" title="Pagar Cheque Pago"/></a></th>
                                                <%
                                                } else {
                                                %>
                                        <th class="nobg"><a href="javascript:revertir_pagar_cheque('<%=cheque.getId_cheque_pago()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Revertir Pago Cheque Pago"/></a></th>
                                                <%
                                                    }
                                                %>
                                    </tr>
                                    <%
                                            sum_monto = sum_monto + cheque.getMonto();
                                            sum_monto_can = sum_monto_can + cheque.getMonto_cancelado();
                                        }
                                    %>
                                    <tr class="cuadroTexto">
                                        <td align="right" colspan="2">Total</td>
                                        <td align="right"><%=sum_monto%></td>
                                        <td align="right" colspan="2">Total</td>
                                        <td align="right"><%=sum_monto_can%></td>
                                        <td></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </fieldset>

            <br/>
            <fieldset>
                <legend class="txtResaltadoV">Listado de Gastos</legend>
                <table width="100%">
                    <tr class="cuadroTexto">
                        <th width="1%"></th>
                        <th width="70">N&deg; de Boleta</th>
                        <th width="70">Rut Proveedor</th>
                        <th>Nombre Proveedor</th>
                        <th width="100">Total Boleta</th>
                    </tr>
                    <%
                        ArrayList lista_gastos = (ArrayList) request.getAttribute("lista_gastos");
                        Iterator g = lista_gastos.iterator();
                        while (g.hasNext()) {
                            Gastos gastos = (Gastos) g.next();
                    %>
                    <tr class="cuadroTexto">
                        <td align="center"><a href="javascript:showhide('pago_gastos_<%=gastos.getId_gastos()%>');"><img src="<%=request.getContextPath()%>/images/mostrar.png" border="0" width="13" height="13" title="Ver Detalle" /></a></td>
                        <td><%=gastos.getNum_boleta()%></td>
                        <%
                            if (gastos.getProveedores() == null) {
                        %>
                        <td></td>
                        <td></td>
                        <%                        } else {
                        %>
                        <td align="right"><%=gastos.getProveedores().getRut()%>-<%=gastos.getProveedores().getDv()%></td>
                        <td><%=gastos.getProveedores().getNombre()%></td>
                        <%
                            }
                        %>
                        <td align="right">$<%=gastos.getTotal_afecto()%></td>
                        <th width="1" class="nobg"><a href="javascript:agregar_documento_gastos('<%=gastos.getId_gastos()%>','<%=gastos.getProveedores().getRut()%>');"><img src="<%=request.getContextPath()%>/images/iconos/agregar_documento.png" border="0" width="20" height="20" title="Agregar Cheque Pago"></a></th>
                    </tr>
                    <tr id="pago_gastos_<%=gastos.getId_gastos()%>" style="display:none;">
                        <th class="nobg"></th>
                        <td colspan="6" class="nobg">
                            <fieldset>
                                <legend class="txtResaltadoV">Cheque_Pagos</legend>
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
                                        Cheque_PagoDAO cheDAO = new Cheque_PagoDAO();
                                        ArrayList lista_cheque = cheDAO.traerTodos_Cheque_Pago_por_Gastos(gastos.getId_gastos());
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
                                            Cheque_Pago cheque = (Cheque_Pago) ch.next();
                                    %>
                                    <tr class="cuadroTexto">
                                        <td><%=cheque.getNum_cheque_pago()%></td>
                                        <td><%=cheque.getBanco().getDescripcion()%></td>
                                        <td align="right"><%=cheque.getMonto()%></td>
                                        <td><%=cheque.getFecha_recepcion_documentoStr()%></td>
                                        <td><%=cheque.getFecha_vencimientoStr() == null ? "" : cheque.getFecha_vencimientoStr()%></td>
                                        <td align="right"><%=cheque.getMonto_cancelado()%></td>
                                        <td><%=cheque.getFecha_cancelaStr() == null ? "" : cheque.getFecha_cancelaStr()%></td>
                                        <%
                                            if (cheque.getMonto_cancelado() == 0) {
                                        %>
                                        <th class="nobg"><a href="javascript:ventana_pagar_cheque('<%=cheque.getId_cheque_pago()%>','<%=cheque.getNum_cheque_pago()%>',<%=cheque.getMonto()%>,'<%=cheque.getFecha_vencimientoStr() == null ? "" : cheque.getFecha_vencimientoStr()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/seleccionar.png" border="0" width="20" height="20" title="Pagar Cheque Pago"/></a></th>
                                                <%
                                                } else {
                                                %>
                                        <th class="nobg"><a href="javascript:revertir_pagar_cheque('<%=cheque.getId_cheque_pago()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Revertir Pago Cheque Pago"/></a></th>
                                                <%
                                                    }
                                                %>
                                    </tr>
                                    <%
                                            sum_monto = sum_monto + cheque.getMonto();
                                            sum_monto_can = sum_monto_can + cheque.getMonto_cancelado();
                                        }
                                    %>
                                    <tr class="cuadroTexto">
                                        <td align="right" colspan="2">Total</td>
                                        <td align="right"><%=sum_monto%></td>
                                        <td align="right" colspan="2">Total</td>
                                        <td align="right"><%=sum_monto_can%></td>
                                        <td></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </fieldset>

            <div id="agrega_Cheque_Pago" style="display:none; background:#123" >
                <fieldset>
                    <table align="center">
                        <tr>
                            <th class="specalt"> N° de Cheque_Pago </th>
                            <td><input name="num_cheque" type="text" class="cuadroTexto" id="num_cheque"/><input name="num_factura" type="hidden" id="num_factura"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Banco </th>
                            <td>
                                <select name="id_banco" class="cuadroTexto" id="id_banco">
                                    <%
                                        BancoDAO banDAO = new BancoDAO();
                                        ArrayList lista_bancos = banDAO.traerBanco();
                                        Iterator b = lista_bancos.iterator();
                                        while (b.hasNext()) {
                                            Banco banco = (Banco) b.next();
                                    %>
                                    <option value="<%=banco.getId_banco()%>"><%=banco.getDescripcion()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt"> Monto </th>
                            <td><input name="monto_cheque" type="text" class="cuadroTexto" id="monto_cheque" onkeyup="this.value = this.value.replace (/[^0-9]/, '');"></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Proveedor </th>
                            <td>
                                <select name="rut_proveedor" class="cuadroTexto" id="rut_proveedor">
                                    <%
                                        ArrayList lista_proveedores = cliDAO.traerTodos_Proveedores();
                                        Iterator c = lista_proveedores.iterator();
                                        while (c.hasNext()) {
                                            Proveedores proveedores = (Proveedores) c.next();
                                    %>
                                    <option value="<%=proveedores.getRut()%>"><%=proveedores.getNombre()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top">Fecha Recepcion </th>
                            <td valign="middle">
                                <input name="fecha_recepcion_documento" type="text" class="cuadroTexto" id="fecha_recepcion_documento" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_recepcion_documento'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top">Fecha Vencimiento </th>
                            <td valign="middle">
                                <input name="fecha_vencimiento_cheque" type="text" class="cuadroTexto" id="fecha_vencimiento_cheque" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_vencimiento_cheque'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <br/>
                <table align="center">
                    <tr>
                        <td><a href="javascript:cerrar_ventana();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"/></a></td>
                        <td><a href="javascript:agrega_cheque();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/agregar.png" border="0" width="30" height="30" title="Agregar Cheque Pago"/></a></td>
                    </tr>
                </table>
            </div>

            <div id="agrega_Cheque_Pago_Gastos" style="display:none; background:#123" >
                <fieldset>
                    <table align="center">
                        <tr>
                            <th class="specalt"> N° de Cheque_Pago </th>
                            <td><input name="num_cheque" type="text" class="cuadroTexto" id="num_cheque"/><input name="id_gastos" type="hidden" id="id_gastos"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Banco </th>
                            <td>
                                <select name="id_banco" class="cuadroTexto" id="id_banco">
                                    <%
                                        Iterator b2 = lista_bancos.iterator();
                                        while (b2.hasNext()) {
                                            Banco banco = (Banco) b2.next();
                                    %>
                                    <option value="<%=banco.getId_banco()%>"><%=banco.getDescripcion()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt"> Monto </th>
                            <td><input name="monto_cheque" type="text" class="cuadroTexto" id="monto_cheque" onkeyup="this.value = this.value.replace (/[^0-9]/, '');"></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Proveedor </th>
                            <td>
                                <select name="rut_proveedor" class="cuadroTexto" id="rut_proveedor">
                                    <%
                                        Iterator c2 = lista_proveedores.iterator();
                                        while (c2.hasNext()) {
                                            Proveedores proveedores = (Proveedores) c2.next();
                                    %>
                                    <option value="<%=proveedores.getRut()%>"><%=proveedores.getNombre()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top">Fecha Recepcion </th>
                            <td valign="middle">
                                <input name="fecha_recepcion_documento" type="text" class="cuadroTexto" id="fecha_recepcion_documento" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_recepcion_documento'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top">Fecha Vencimiento </th>
                            <td valign="middle">
                                <input name="fecha_vencimiento_cheque" type="text" class="cuadroTexto" id="fecha_vencimiento_cheque" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_vencimiento_cheque'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <br/>
                <table align="center">
                    <tr>
                        <td><a href="javascript:cerrar_ventana();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"/></a></td>
                        <td><a href="javascript:agrega_cheque_gastos();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/agregar.png" border="0" width="30" height="30" title="Agregar Cheque Pago"/></a></td>
                    </tr>
                </table>
            </div>

            <div id="pagar_cheque" style="display:none; background:#123" >
                <fieldset>
                    <table align="center">
                        <tr>
                            <th class="specalt"> N° de Cheque_Pago </th>
                            <td><input name="num_cheque" type="text" class="cuadroTexto" id="num_cheque" readonly="true"/><input name="id_cheque" type="hidden" id="id_cheque"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Monto </th>
                            <td><input name="monto_cheque" type="text" class="cuadroTexto" id="monto_cheque" readonly="true" /></td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top"> Fecha Vencimiento </th>
                            <td><input name="fecha_vencimiento_cheque" type="text" class="cuadroTexto" id="fecha_vencimiento_cheque" align="left" readonly="true"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Monto Cancelado </th>
                            <td><input name="monto_cancelado_cheque" type="text" class="cuadroTexto" id="monto_cancelado_cheque" onkeyup="this.value = this.value.replace (/[^0-9]/, '');"></td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top"> Fecha Cancela </th>
                            <td valign="middle">
                                <input name="fecha_cancela_cheque" type="text" class="cuadroTexto" id="fecha_cancela_cheque" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_cancela_cheque'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <br/>
                <table align="center">
                    <tr>
                        <td><a href="javascript:cerrar_ventana();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"/></a></td>
                        <td><a href="javascript:cancela_cheque();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/agregar.png" border="0" width="30" height="30" title="Cancela Cheque Pago"/></a></td>
                    </tr>
                </table>
            </div>

        </form>
    </body>
</html>