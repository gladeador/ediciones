<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Cheque.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Cuota.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Letra.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Protesto.js'></script>
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
            function agregar_documento(id_factura, forma_pago, rut_cliente){
                var id = "agrega_"+forma_pago;
                var titulo = "Agrega "+forma_pago;
                
                Dialog.info($(id).innerHTML,{className:"alphacube", title:titulo, height:220, top:100});
                document.getElementById("rut_cliente").value = rut_cliente;
                
                document.getElementById("id_factura").value = id_factura;
            }
            
            function agrega_cheque(){
                var num_cheque = document.getElementById("num_cheque").value;
                var id_banco = document.getElementById("id_banco").value;
                var monto = document.getElementById("monto_cheque").value;
                var rut_cliente = document.getElementById("rut_cliente").value;
                var fecha_recepcion_documento = document.getElementById("fecha_recepcion_documento").value;
                var fecha_vencimiento = document.getElementById("fecha_vencimiento_cheque").value;
                var id_factura = document.getElementById("id_factura").value;
                Cheque.agregar_Cheque(num_cheque, id_banco, monto, rut_cliente, fecha_recepcion_documento, fecha_vencimiento, id_factura, recupera_AgregaDoc);
            }
            
            function ventana_pagar_cheque(id_cheque, num_cheque, monto, fecha_vencimiento){
                Dialog.info($('pagar_cheque').innerHTML,{className:"alphacube", title:"Pagar Cheque", height:220, top:100});
                document.getElementById("id_cheque").value = id_cheque;
                document.getElementById("num_cheque").value = num_cheque;
                document.getElementById("monto_cheque").value = monto;
                document.getElementById("fecha_vencimiento_cheque").value = fecha_vencimiento;
            }
            
            function cancela_cheque(){
                var id_cheque = document.getElementById("id_cheque").value;
                var monto_cheque = document.getElementById("monto_cancelado_cheque").value;
                var fecha_cancela_cheque = document.getElementById("fecha_cancela_cheque").value;
                Cheque.cancelar_Cheque(id_cheque, monto_cheque, fecha_cancela_cheque, recupera_CancelarDoc);
            }
            
            function revertir_pagar_cheque(id_cheque){
                if (!confirm("¿Esta seguro que desea Revertir el Pago de este Registro?")){
                    return;
                }
                Cheque.revertir_cancelar_Cheque(id_cheque, recupera_AgregaDoc);
            }
            
            function protestar_cheque(id_cheque, rut_cliente){
                if (!confirm("¿Esta seguro que desea Protestar el Pago de este Registro?")){
                    return;
                }
                Protesto.crea_Protesto_Cheque(id_cheque, rut_cliente, recupera_AgregaDoc)
            }
            
            function revertir_protestar_cheque(id_cheque){
                if (!confirm("¿Esta seguro que desea Revertir el Protesto de este Registro?")){
                    return;
                }
                Protesto.revertir_Protesto_Cheque(id_cheque, recupera_AgregaDoc)
            }
            
            function agrega_cuota(){
                var monto = document.getElementById("monto_cuota").value;
                var rut_cliente = document.getElementById("rut_cliente").value;
                var fecha_vencimiento = document.getElementById("fecha_vencimiento_cuota").value;
                var id_factura = document.getElementById("id_factura").value;
                Cuota.agregar_Cuota(monto, rut_cliente, fecha_vencimiento, id_factura, recupera_AgregaDoc);
            }
            
            function ventana_pagar_cuota(id_cuota, num_cuota, monto, fecha_vencimiento){
                Dialog.info($('pagar_cuota').innerHTML,{className:"alphacube", title:"Pagar Cuota", height:220, top:100});
                document.getElementById("id_cuota").value = id_cuota;
                document.getElementById("num_cuota").value = num_cuota;
                document.getElementById("monto_cuota").value = monto;
                document.getElementById("fecha_vencimiento_cuota").value = fecha_vencimiento;
            }
            
            function cancela_cuota(){
                var id_cuota = document.getElementById("id_cuota").value;
                var monto_cuota = document.getElementById("monto_cancelado_cuota").value;
                var fecha_cancela_cuota = document.getElementById("fecha_cancela_cuota").value;
                Cuota.cancelar_Cuota(id_cuota, monto_cuota, fecha_cancela_cuota, recupera_CancelarDoc);
            }
            
            function revertir_pagar_cuota(id_cuota){
                if (!confirm("¿Esta seguro que desea Revertir el Pago de este Registro?")){
                    return;
                }
                Cuota.revertir_cancelar_Cuota(id_cuota, recupera_AgregaDoc);
            }
            
            function agrega_letra(){
                var num_letra = document.getElementById("num_letra").value;
                var monto = document.getElementById("monto_letra").value;
                var rut_cliente = document.getElementById("rut_cliente").value;
                var fecha_recepcion_documento = document.getElementById("fecha_recepcion_documento_letra").value;
                var fecha_vencimiento = document.getElementById("fecha_vencimiento_letra").value;
                var id_factura = document.getElementById("id_factura").value;
                Letra.agregar_Letra(num_letra, monto, rut_cliente, fecha_recepcion_documento, fecha_vencimiento, id_factura, recupera_AgregaDoc);
            }
            
            function ventana_pagar_letra(id_letra, num_letra, monto, fecha_vencimiento){
                Dialog.info($('pagar_letra').innerHTML,{className:"alphacube", title:"Pagar Letra", height:220, top:100});
                document.getElementById("id_letra").value = id_letra;
                document.getElementById("num_letra").value = num_letra;
                document.getElementById("monto_letra").value = monto;
                document.getElementById("fecha_vencimiento_letra").value = fecha_vencimiento;
            }
            
            function cancela_letra(){
                var id_letra = document.getElementById("id_letra").value;
                var monto_letra = document.getElementById("monto_cancelado_letra").value;
                var fecha_cancela_letra = document.getElementById("fecha_cancela_letra").value;
                Letra.cancelar_Letra(id_letra, monto_letra, fecha_cancela_letra, recupera_CancelarDoc);
            }
            
            function revertir_pagar_letra(id_letra){
                if (!confirm("¿Esta seguro que desea Revertir el Pago de este Registro?")){
                    return;
                }
                Letra.revertir_cancelar_Letra(id_letra, recupera_AgregaDoc);
            }
            
            function protestar_letra(id_letra, rut_cliente){
                if (!confirm("¿Esta seguro que desea Protestar el Pago de este Registro?")){
                    return;
                }
                Protesto.crea_Protesto_Letra(id_letra, rut_cliente, recupera_AgregaDoc)
            }
            
            function revertir_protestar_letra(id_letra){
                if (!confirm("¿Esta seguro que desea Revertir el Protesto de este Registro?")){
                    return;
                }
                Protesto.revertir_Protesto_Letra(id_letra, recupera_AgregaDoc)
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
        <form name="formData" method="post" action="<%=request.getContextPath()%>/controller/ActualizarPagos.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Pago de Facturas</th>
                </tr>
            </table>
            <br/>
            <table align="center">
                <tr>
                    <th class="specalt">Busqueda por Cliente</th>
                    <td>
                        <select name="rut_cliente_b" class="cuadroTexto" id="rut_cliente_b">
                            <option>Todos</option>
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
                    <th class="nobg"><a href="javascript:document.formData.submit();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/buscar.png" border="0" width="30" height="30" title="Buscar"/></a></th>
                </tr>
            </table>
            <br/>
            <fieldset>
                <legend class="txtResaltadoV">Listado de Facturas Emitidas</legend>
                <table width="100%">
                    <tr class="cuadroTexto">
                        <th width="1%"></th>
                        <th width="70">N&deg; de Factura</th>
                        <th width="70">Rut Cliente</th>
                        <th>Nombre Cliente</th>
                        <th width="70">Forma de Pago</th>
                        <th width="100">Total Factura</th>
                        <th width="100">Saldo Factura</th>
                    </tr>
                    <%
                        ArrayList lista_facturas = (ArrayList) request.getAttribute("lista_facturas");
                        Iterator i = lista_facturas.iterator();
                        while (i.hasNext()) {
                            Factura factura = (Factura) i.next();
                    %>
                    <tr class="cuadroTexto">
                        <td align="center"><a href="javascript:showhide('pago_factura_<%=factura.getId_factura()%>');"><img src="<%=request.getContextPath()%>/images/mostrar.png" border="0" width="13" height="13" title="Ver Detalle" /></a></td>
                        <td><%=factura.getNum_factura()%></td>
                        <%
                            if (factura.getClientes() == null) {
                        %>
                        <td></td>
                        <td></td>
                        <%                        } else {
                        %>
                        <td align="right"><%=factura.getClientes().getRut()%>-<%=factura.getClientes().getDv()%></td>
                        <td><%=factura.getClientes().getNombre()%></td>
                        <%
                            }
                        %>
                        <td><%=factura.getForma_pago().getDescripcion()%></td>
                        <td align="right">$<%=factura.getTotal()%></td>
                        <td align="right">$<%=factura.getSaldo_pago()%></td>
                        <th width="1" class="nobg"><a href="javascript:agregar_documento('<%=factura.getId_factura()%>','<%=factura.getForma_pago().getDescripcion()%>','<%=factura.getClientes().getRut()%>');"><img src="<%=request.getContextPath()%>/images/iconos/agregar_documento.png" border="0" width="20" height="20" title="Agregar Cheque o Cuota"></a></th>
                    </tr>
                    <tr id="pago_factura_<%=factura.getId_factura()%>" style="display:none;">
                        <th colspan="8" class="nobg">
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
                                <td align="right"><%=cheque.getMonto()%></td>
                                <td><%=cheque.getFecha_recepcion_documentoStr()%></td>
                                <td><%=cheque.getFecha_vencimientoStr() == null ? "" : cheque.getFecha_vencimientoStr()%></td>
                                <td align="right"><%=cheque.getMonto_cancelado()%></td>
                                <td><%=cheque.getFecha_cancelaStr() == null ? "" : cheque.getFecha_cancelaStr()%></td>
                                <%
                                    if (cheque.getEstado_cheque().getEstado().equals("N")) {
                                %>
                                <th class="nobg" width="1%"><a href="javascript:ventana_pagar_cheque('<%=cheque.getId_cheque()%>','<%=cheque.getNum_cheque()%>',<%=cheque.getMonto()%>,'<%=cheque.getFecha_vencimientoStr() == null ? "" : cheque.getFecha_vencimientoStr()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/seleccionar.png" border="0" width="20" height="20" title="Pagar Cheque"/></a></th>
                                <th class="nobg" width="1%"><a href="javascript:protestar_cheque('<%=cheque.getId_cheque()%>','<%=cheque.getRut_cliente()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/protesto.png" border="0" width="20" height="20" title="Protestar Cheque"/></a></th>
                                        <%
                                        } else if (cheque.getEstado_cheque().getEstado().equals("PA")) {
                                        %>
                                <th class="nobg" width="1%"><a href="javascript:revertir_pagar_cheque('<%=cheque.getId_cheque()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Revertir Pago Cheque"/></a></th>
                                        <%
                                        } else if (cheque.getEstado_cheque().getEstado().equals("PR")) {
                                        %>
                                <th class="nobg" width="1%"><a href="javascript:ventana_pagar_cheque('<%=cheque.getId_cheque()%>','<%=cheque.getNum_cheque()%>',<%=cheque.getMonto()%>,'<%=cheque.getFecha_vencimientoStr() == null ? "" : cheque.getFecha_vencimientoStr()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/seleccionar.png" border="0" width="20" height="20" title="Pagar Cheque"/></a></th>
                                <th class="nobg" width="1%"><a href="javascript:revertir_protestar_cheque('<%=cheque.getId_cheque()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/revertir.png" border="0" width="20" height="20" title="Revertir Protesto"/></a></th>
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
                                <td align="right"><%=cuota.getMonto()%></td>
                                <td><%=cuota.getFecha_vencimientoStr() == null ? "" : cuota.getFecha_vencimientoStr()%></td>
                                <td align="right"><%=cuota.getMonto_cancelado()%></td>
                                <td><%=cuota.getFecha_cancelaStr() == null ? "" : cuota.getFecha_cancelaStr()%></td>
                                <%
                                    if (cuota.getMonto_cancelado() == 0) {
                                %>
                                <th class="nobg" width="1%"><a href="javascript:ventana_pagar_cuota('<%=cuota.getId_cuota()%>','<%=cont++%>',<%=cuota.getMonto()%>,'<%=cuota.getFecha_vencimientoStr() == null ? "" : cuota.getFecha_vencimientoStr()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/seleccionar.png" border="0" width="20" height="20" title="Pagar Cheque"/></a></th>
                                        <%
                                        } else {
                                        %>
                                <th class="nobg" width="1%"><a href="javascript:revertir_pagar_cuota('<%=cuota.getId_cuota()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Revertir Pago Cuota"/></a></th>
                                        <%
                                            }
                                        %>
                            </tr>
                            <%
                                    sum_monto = sum_monto + cuota.getMonto();
                                    sum_monto_can = sum_monto_can + cuota.getMonto_cancelado();
                                }
                            %>
                            <tr class="cuadroTexto">
                                <td align="right">Total</td>
                                <td align="right"><%=sum_monto%></td>
                                <td align="right">Total</td>
                                <td align="right"><%=sum_monto_can%></td>
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
                                <td align="right"><%=letra.getMonto()%></td>
                                <td><%=letra.getFecha_recepcion_documentoStr()%></td>
                                <td><%=letra.getFecha_vencimientoStr() == null ? "" : letra.getFecha_vencimientoStr()%></td>
                                <td align="right"><%=letra.getMonto_cancelado()%></td>
                                <td><%=letra.getFecha_cancelaStr() == null ? "" : letra.getFecha_cancelaStr()%></td>
                                <%
                                    if (letra.getEstado_letra().getEstado().equals("N")) {
                                %>
                                <th class="nobg" width="1%"><a href="javascript:ventana_pagar_letra('<%=letra.getId_letra()%>','<%=letra.getNum_letra()%>',<%=letra.getMonto()%>,'<%=letra.getFecha_vencimientoStr() == null ? "" : letra.getFecha_vencimientoStr()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/seleccionar.png" border="0" width="20" height="20" title="Pagar Letra"/></a></th>
                                <th class="nobg" width="1%"><a href="javascript:protestar_cheque('<%=letra.getId_letra()%>','<%=letra.getRut_cliente()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/protesto.png" border="0" width="20" height="20" title="Protestar Cheque"/></a></th>
                                        <%
                                        } else if (letra.getEstado_letra().getEstado().equals("PA")) {
                                        %>
                                <th class="nobg" width="1%"><a href="javascript:revertir_pagar_letra('<%=letra.getId_letra()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Revertir Pago Letra"/></a></th>
                                        <%
                                        } else if (letra.getEstado_letra().getEstado().equals("PR")) {
                                        %>
                                <th class="nobg" width="1%"><a href="javascript:ventana_pagar_letra('<%=letra.getId_letra()%>','<%=letra.getNum_letra()%>',<%=letra.getMonto()%>,'<%=letra.getFecha_vencimientoStr() == null ? "" : letra.getFecha_vencimientoStr()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/seleccionar.png" border="0" width="20" height="20" title="Pagar Letra"/></a></th>
                                <th class="nobg" width="1%"><a href="javascript:revertir_protestar_letra('<%=letra.getId_letra()%>');" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/revertir.png" border="0" width="20" height="20" title="Revertir Protesto"/></a></th>
                                        <%
                                        }
                                        %>
                            </tr>
                            <%
                                    sum_monto = sum_monto + letra.getMonto();
                                    sum_monto_can = sum_monto_can + letra.getMonto_cancelado();
                                }
                            %>
                            <tr class="cuadroTexto">
                                <td align="right">Total</td>
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
                    <%                            }
                    %>
                    </th>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </fieldset>

            <div id="agrega_Cheque" style="display:none; background:#123" >
                <fieldset>
                    <table align="center">
                        <tr>
                            <th class="specalt">N° Factura</th>
                            <td><input name="id_factura" type="text" class="cuadroTexto" id="id_factura"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> N° de Cheque </th>
                            <td><input name="num_cheque" type="text" class="cuadroTexto" id="num_cheque"/></td>
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
                            <th class="specalt"> Cliente </th>
                            <td>
                                <select name="rut_cliente" class="cuadroTexto" id="rut_cliente">
                                    <%
                                        ArrayList lista_clientes = cliDAO.traerTodos_Clientes();
                                        Iterator c = lista_clientes.iterator();
                                        while (c.hasNext()) {
                                            Clientes clientes = (Clientes) c.next();
                                    %>
                                    <option value="<%=clientes.getRut()%>"><%=clientes.getNombre()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top">fecha Recepcion </th>
                            <td valign="middle">
                                <input name="fecha_recepcion_documento" type="text" class="cuadroTexto" id="fecha_recepcion_documento" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_recepcion_documento'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top">fecha Vencimiento </th>
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
                        <td><a href="javascript:agrega_cheque();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/agregar.png" border="0" width="30" height="30" title="Agregar Cheque"/></a></td>
                    </tr>
                </table>
            </div>

            <div id="pagar_cheque" style="display:none; background:#123" >
                <fieldset>
                    <table align="center">
                        <tr>
                            <th class="specalt"> N° de Cheque </th>
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
                        <td><a href="javascript:cancela_cheque();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/agregar.png" border="0" width="30" height="30" title="Cancela Cheque"/></a></td>
                    </tr>
                </table>
            </div>

            <div id="agrega_Cuota" style="display:none; background:#123" >
                <fieldset>
                    <table align="center">
                        <tr>
                            <th class="specalt">N° Factura</th>
                            <td><input name="id_factura" type="text" class="cuadroTexto" id="id_factura"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Monto </th>
                            <td><input name="monto_cuota" type="text" class="cuadroTexto" id="monto_cuota" onkeyup="this.value = this.value.replace (/[^0-9]/, '');"></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Cliente </th>
                            <td>
                                <select name="rut_cliente" class="cuadroTexto" id="rut_cliente">
                                    <%
                                        ArrayList lista_clientes_cuota = cliDAO.traerTodos_Clientes();
                                        Iterator cc = lista_clientes_cuota.iterator();
                                        while (cc.hasNext()) {
                                            Clientes clientes = (Clientes) cc.next();
                                    %>
                                    <option value="<%=clientes.getRut()%>"><%=clientes.getNombre()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top">fecha Vencimiento </th>
                            <td valign="middle">
                                <input name="fecha_vencimiento_cuota" type="text" class="cuadroTexto" id="fecha_vencimiento_cuota" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_vencimiento_cuota'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <br/>
                <table align="center">
                    <tr>
                        <td><a href="javascript:cerrar_ventana();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"/></a></td>
                        <td><a href="javascript:agrega_cuota();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/agregar.png" border="0" width="30" height="30" title="Agregar Cheque"/></a></td>
                    </tr>
                </table>
            </div>

            <div id="pagar_cuota" style="display:none; background:#123" >
                <fieldset>
                    <table align="center">
                        <tr>
                            <th class="specalt"> N° de Cuota </th>
                            <td><input name="num_cuota" type="text" class="cuadroTexto" id="num_cuota" readonly="true"/><input name="id_cuota" type="hidden" id="id_cuota"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Monto </th>
                            <td><input name="monto_cuota" type="text" class="cuadroTexto" id="monto_cuota" readonly="true" /></td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top"> Fecha Vencimiento </th>
                            <td><input name="fecha_vencimiento_cuota" type="text" class="cuadroTexto" id="fecha_vencimiento_cuota" align="left" readonly="true"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Monto Cancelado </th>
                            <td><input name="monto_cancelado_cuota" type="text" class="cuadroTexto" id="monto_cancelado_cuota" onkeyup="this.value = this.value.replace (/[^0-9]/, '');"></td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top"> Fecha Cancela </th>
                            <td valign="middle">
                                <input name="fecha_cancela_cuota" type="text" class="cuadroTexto" id="fecha_cancela_cuota" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_cancela_cuota'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <br/>
                <table align="center">
                    <tr>
                        <td><a href="javascript:cerrar_ventana();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"/></a></td>
                        <td><a href="javascript:cancela_cuota();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/agregar.png" border="0" width="30" height="30" title="Cancela Cheque"/></a></td>
                    </tr>
                </table>
            </div>

            <div id="agrega_Letra" style="display:none; background:#123" >
                <fieldset>
                    <table align="center">
                        <tr>
                            <th class="specalt">N° Factura</th>
                            <td><input name="id_factura" type="text" class="cuadroTexto" id="id_factura"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> N° de Letra </th>
                            <td><input name="num_letra" type="text" class="cuadroTexto" id="num_letra"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Monto </th>
                            <td><input name="monto_letra" type="text" class="cuadroTexto" id="monto_letra" onkeyup="this.value = this.value.replace (/[^0-9]/, '');"></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Cliente </th>
                            <td>
                                <select name="rut_cliente" class="cuadroTexto" id="rut_cliente">
                                    <%
                                        ArrayList lista_clientes_letra = cliDAO.traerTodos_Clientes();
                                        Iterator cl = lista_clientes_letra.iterator();
                                        while (cl.hasNext()) {
                                            Clientes clientes = (Clientes) cl.next();
                                    %>
                                    <option value="<%=clientes.getRut()%>"><%=clientes.getNombre()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top">fecha Recepcion </th>
                            <td valign="middle">
                                <input name="fecha_recepcion_documento_letra" type="text" class="cuadroTexto" id="fecha_recepcion_documento_letra" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_recepcion_documento_letra'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top">fecha Vencimiento </th>
                            <td valign="middle">
                                <input name="fecha_vencimiento_letra" type="text" class="cuadroTexto" id="fecha_vencimiento_letra" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_vencimiento_letra'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <br/>
                <table align="center">
                    <tr>
                        <td><a href="javascript:cerrar_ventana();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"/></a></td>
                        <td><a href="javascript:agrega_letra();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/agregar.png" border="0" width="30" height="30" title="Agregar Letra"/></a></td>
                    </tr>
                </table>
            </div>

            <div id="pagar_letra" style="display:none; background:#123" >
                <fieldset>
                    <table align="center">
                        <tr>
                            <th class="specalt"> N° de Letra </th>
                            <td><input name="num_letra" type="text" class="cuadroTexto" id="num_letra" readonly="true"/><input name="id_letra" type="hidden" id="id_letra"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Monto </th>
                            <td><input name="monto_letra" type="text" class="cuadroTexto" id="monto_letra" readonly="true" /></td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top"> Fecha Vencimiento </th>
                            <td><input name="fecha_vencimiento_letra" type="text" class="cuadroTexto" id="fecha_vencimiento_letra" align="left" readonly="true"/></td>
                        </tr>
                        <tr>
                            <th class="specalt"> Monto Cancelado </th>
                            <td><input name="monto_cancelado_letra" type="text" class="cuadroTexto" id="monto_cancelado_letra" onkeyup="this.value = this.value.replace (/[^0-9]/, '');"></td>
                        </tr>
                        <tr>
                            <th class="specalt" valign="top"> Fecha Cancela </th>
                            <td valign="middle">
                                <input name="fecha_cancela_letra" type="text" class="cuadroTexto" id="fecha_cancela_letra" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                <button onclick="javascript:displayCalendar(document.getElementById('fecha_cancela_letra'),'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <br/>
                <table align="center">
                    <tr>
                        <td><a href="javascript:cerrar_ventana();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"/></a></td>
                        <td><a href="javascript:cancela_letra();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/agregar.png" border="0" width="30" height="30" title="Cancela Letra"/></a></td>
                    </tr>
                </table>
            </div>

        </form>
    </body>
</html>