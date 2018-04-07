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
        <title>.:: Agregar Comprobante ::.</title>
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
            function recupera_descripcion(){
                var num_comprobante = document.formData.num_comprobante.value;
                var fecha_comprobante = document.formData.fecha_comprobante.value;
                Comprobante.recupera_Descripcion_Comprobante(num_comprobante, fecha_comprobante, recupera);
            }
            
            function recupera(data){
                if(data != null){
                    document.formData.descripcion.value = data;
                }
            }
            
            function checkAll(field_t, field){
                if(field_t.checked == true){
                    if(field.name != null){
                        field.checked = true ;
                    }else{
                        for (i = 0; i < field.length; i++)
                            field[i].checked = true ;
                    }
                }else{
                    if(field.name != null){                    
                        field.checked = false ;
                    }else{
                        for (i = 0; i < field.length; i++)
                            field[i].checked = false ;  
                    }
                }
            }
            
            function enviaFacturaVenta(field){
                for(i=0;ele=field.elements[i]; i++){
                    if(ele.type=='checkbox') {
                        if(document.getElementById(ele.id).checked == true){
                            var num_comprobante = document.formData.num_comprobante.value;
                            var fecha_comprobante = document.formData.fecha_comprobante.value;
                            var descripcion = document.formData.descripcion.value;
                            var id_factura = ele.value;
                            Comprobante.enviaComprobante_FacturaVenta(num_comprobante, fecha_comprobante, descripcion, id_factura);
                            document.getElementById(ele.id).checked = false;
                            document.getElementById(ele.id).disabled = true;
                        }
                    }
                }
            }

            function enviaFacturaCompra(field){
                
                alert(field.elements);
                return;
                
                for(i=0;ele=field.elements[i]; i++){
                    if(ele.type=='checkbox') {
                        if(document.getElementById(ele.id).checked == true){
                            var num_comprobante = document.formData.num_comprobante.value;
                            var fecha_comprobante = document.formData.fecha_comprobante.value;
                            var descripcion = document.formData.descripcion.value;
                            var valor = ele.value.split("--");
                            var num_factura = valor[0];
                            var rut_proveedor = valor[1];
                            Comprobante.enviaComprobante_FacturaCompra(num_comprobante, fecha_comprobante, descripcion, num_factura, rut_proveedor);
                            document.getElementById(ele.id).checked = false;
                            document.getElementById(ele.id).disabled = true;
                        }
                    }
                }
            }

            function enviaGastos(field){
                for(i=0;ele=field.elements[i]; i++){
                    if(ele.type=='checkbox') {
                        if(document.getElementById(ele.id).checked == true){
                            var num_comprobante = document.formData.num_comprobante.value;
                            var fecha_comprobante = document.formData.fecha_comprobante.value;
                            var descripcion = document.formData.descripcion.value;
                            var id_gastos = ele.value;
                            Comprobante.enviaComprobante_Gastos(num_comprobante, fecha_comprobante, descripcion, id_gastos);
                            document.getElementById(ele.id).checked = false;
                            document.getElementById(ele.id).disabled = true;
                        }
                    }
                }
            }

            function enviaPagos(field){
                for(i=0;ele=field.elements[i]; i++){
                    if(ele.type=='checkbox') {
                        if(document.getElementById(ele.id).checked == true){
                            var num_comprobante = document.formData.num_comprobante.value;
                            var fecha_comprobante = document.formData.fecha_comprobante.value;
                            var descripcion = document.formData.descripcion.value;
                            var valor = ele.value.split("--");
                            var id_documento = valor[0];
                            var tipo_documento = valor[1];
                            if(tipo_documento == "Cheque"){
                                Comprobante.enviaComprobante_Cheque(num_comprobante, fecha_comprobante, descripcion, id_documento);
                            }else if(tipo_documento == "Cuota"){
                                Comprobante.enviaComprobante_Cuota(num_comprobante, fecha_comprobante, descripcion, id_documento);
                            }else if(tipo_documento == "Letra"){
                                Comprobante.enviaComprobante_Letra(num_comprobante, fecha_comprobante, descripcion, id_documento);
                            }
                            document.getElementById(ele.id).checked = false;
                            document.getElementById(ele.id).disabled = true;
                        }
                    }
                }
            }

            function enviaPagoProveedores(field){
                for(i=0;ele=field.elements[i]; i++){
                    if(ele.type=='checkbox') {
                        if(document.getElementById(ele.id).checked == true){
                            var num_comprobante = document.formData.num_comprobante.value;
                            var fecha_comprobante = document.formData.fecha_comprobante.value;
                            var descripcion = document.formData.descripcion.value;
                            var id_documento = ele.value;
                            var radio;
                            var id_radio = "debe_haber_"+id_documento+"";
                            var debe_haber = document.getElementById(id_radio).value;
                            if(debe_haber == ""){
                                if (!confirm("No ha seleccionado si el registo corresponde a Deb o Haber\n¿Desea continuar sin seleccionar el registo?")){
                                    return;
                                }
                            }
                            Comprobante.enviaComprobante_Cheque_Pago(num_comprobante, fecha_comprobante, descripcion, id_documento, radio);
                            
                            document.getElementById(ele.id).checked = false;
                            document.getElementById(ele.id).disabled = true;
                        }
                    }
                }
            }
            
            function enviaComprobanteManual(){
                var num_comprobante = document.formData.num_comprobante.value;
                var fecha_comprobante = document.formData.fecha_comprobante.value;
                var descripcion = document.formData.descripcion.value;
                var num_cuenta = document.formManual.num_cuenta.value;
                var monto = document.formManual.monto.value;
                var debe_haber = "";
                var observacion = document.formManual.observacion.value;
                for(i=0; i< document.formManual.debe_haber_manual.length;i++){
                    if(document.formManual.debe_haber_manual[i].checked){
                        debe_haber = document.formManual.debe_haber_manual[i].value;
                    }
                }
                Comprobante.agregaComprobanteManual(num_comprobante, fecha_comprobante, descripcion, num_cuenta, monto, debe_haber, observacion);
            }

        </script>

    </head>
    <%
        HttpSession sesion = request.getSession();
        Fecha_Actual fecha = (Fecha_Actual) sesion.getAttribute("fecha_actual");
    %>
    <body>
        <table width="100%">
            <tr>
                <th class="menuSuperior">Agregar Comprobante</th>
            </tr>
        </table>
        <br>
        <form name="formData">
            <table>
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Datos del Comprobante</legend>
                            <table width="100%">
                                <tr>
                                    <th class="specalt">N° Comprobante </th>
                                    <td><input name="num_comprobante" type="text" class="cuadroTexto" id="num_comprobante" value="0"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Fecha Comprobante </th>
                                    <td valign="top">
                                        <input name="fecha_comprobante" type="text" class="cuadroTexto" id="fecha_comprobante" size="10" value="<%=fecha.getFecha_actualStr()%>" onchange="javascript:recupera_descripcion();" readonly>
                                        <button onclick="javascript:displayCalendar(document.forms[0].fecha_comprobante,'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt">Descripcion </th>
                                    <td><textarea name="descripcion" class="cuadroTexto" id="descripcion"></textarea></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
        </form>
        <form name="formFacturaVenta">
            <fieldset>
                <legend class="txtResaltadoV">Facturas de Venta</legend>
                <table width="100%">
                    <tr>
                        <th width="1%"><input name="select_todo" type="checkbox" value="" id="select_todo" onclick="javascript:checkAll(document.formFacturaVenta.select_todo, document.formFacturaVenta.comprobante_factura_venta);" /></th>
                        <th width="70">N° Factura</th>
                        <th width="80">Fecha</th>
                        <th>Cliente</th>
                        <th width="100">Monto</th>
                    </tr>
                    <%
                        FacturaDAO facDAO = new FacturaDAO();
                        ArrayList lista_factura = facDAO.traerTodos_Factura_para_Comprobante();
                        Iterator cf = lista_factura.iterator();
                        int cont = 1;
                        while (cf.hasNext()) {
                            Factura factura = (Factura) cf.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><input name="comprobante_factura_venta" type="checkbox" id="comprobante_factura_venta_<%=cont++%>" value="<%=factura.getId_factura()%>" /></td>
                        <td><%=factura.getNum_factura()%></td>
                        <td><%=factura.getFecha_facturaStr()%></td>
                        <td><%=factura.getClientes().getNombre()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura.getTotal()%></fmt:formatNumber></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <table align="center">
                    <tr>
                        <td><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:enviaFacturaVenta(document.formFacturaVenta);" value="Agregar"></td>
                    </tr>
                </table>
            </fieldset>
        </form>
        <form name="formFacturaCompra">
            <fieldset>
                <legend class="txtResaltadoV">Facturas de Compra</legend>
                <table width="100%">
                    <tr>
                        <th width="1%"><input name="select_todo" type="checkbox" value="" id="select_todo" onclick="javascript:checkAll(document.formFacturaCompra.select_todo, document.formFacturaCompra.comprobante_factura_compra);" /></th>
                        <th width="70">N° Factura</th>
                        <th width="80">Fecha</th>
                        <th>Cliente</th>
                        <th width="100">Monto</th>
                    </tr>
                    <%
                        Stock_ProductoDAO spDAO = new Stock_ProductoDAO();
                        ArrayList lista_factura_compra = spDAO.traerTodos_Factura_Compras_para_Comprobante();
                        Iterator cfc = lista_factura_compra.iterator();
                        int cont_f_c = 1;
                        while (cfc.hasNext()) {
                            Stock_Producto factura_compra = (Stock_Producto) cfc.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><input name="comprobante_factura_compra" type="checkbox" id="comprobante_factura_compra_<%=cont_f_c++%>" value="<%=factura_compra.getNum_factura()%>--<%=factura_compra.getProveedores().getRut()%>" /></td>
                        <td><%=factura_compra.getNum_factura()%></td>
                        <td><%=factura_compra.getFecha_ingresoStr()%></td>
                        <td><%=factura_compra.getProveedores().getNombre()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=factura_compra.getTotal()%></fmt:formatNumber></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <table align="center">
                    <tr>
                        <td><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:enviaFacturaCompra(document.formFacturaCompra);" value="Agregar"></td>
                    </tr>
                </table>
            </fieldset>
        </form>
        <form name="formGastos">
            <fieldset>
                <legend class="txtResaltadoV">Gastos</legend>
                <table width="100%">
                    <tr>
                        <th width="1%"><input name="select_todo" type="checkbox" value="" id="select_todo" onclick="javascript:checkAll(document.formGastos.select_todo, document.formGastos.comprobante_gastos);" /></th>
                        <th width="70">N° Boleta</th>
                        <th width="80">Fecha</th>
                        <th>Proveedor</th>
                        <th width="100">Monto</th>
                    </tr>
                    <%
                        GastosDAO gasDAO = new GastosDAO();
                        ArrayList lista_gastos = gasDAO.traerTodos_Gastos_para_Comprobante();
                        Iterator cg = lista_gastos.iterator();
                        int cont_g = 1;
                        while (cg.hasNext()) {
                            Gastos gastos = (Gastos) cg.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><input name="comprobante_gastos" type="checkbox" id="comprobante_gastos_<%=cont_g++%>" value="<%=gastos.getId_gastos()%>" /></td>
                        <td><%=gastos.getNum_boleta()%></td>
                        <td><%=gastos.getFecha_gastoStr()%></td>
                        <td><%=gastos.getProveedores().getNombre()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=gastos.getTotal_afecto() == 0 ? gastos.getNeto_exento() : gastos.getTotal_afecto()%></fmt:formatNumber></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <table align="center">
                    <tr>
                        <td><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:enviaGastos(document.formGastos);" value="Agregar"></td>
                    </tr>
                </table>
            </fieldset>
        </form>
        <form name="formPagoClientes">
            <fieldset>
                <legend class="txtResaltadoV">Pago Clientes</legend>
                <table width="100%">
                    <tr>
                        <th width="1%"><input name="select_todo" type="checkbox" value="" id="select_todo" onclick="javascript:checkAll(document.formPagoClientes.select_todo, document.formPagoClientes.comprobante_pagos);" /></th>
                        <th width="70">N° Documento</th>
                        <th width="100">Tipo Documento</th>
                        <th width="80">Fecha</th>
                        <th>Cliente</th>
                        <th width="100">Monto</th>
                    </tr>
                    <%
                        ChequeDAO cheDAO = new ChequeDAO();
                        ArrayList lista_cheque = cheDAO.traerTodos_Cheque_para_Comprobante();
                        Iterator cc = lista_cheque.iterator();
                        int cont_p = 1;
                        while (cc.hasNext()) {
                            Cheque cheque = (Cheque) cc.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><input name="comprobante_pagos" type="checkbox" id="comprobante_pagos_<%=cont_p++%>" value="<%=cheque.getId_cheque()%>--Cheque" /></td>
                        <td><%=cheque.getNum_cheque()%></td>
                        <td>Cheque</td>
                        <td><%=cheque.getFecha_recepcion_documentoStr()%></td>
                        <td><%=cheque.getFactura().getClientes().getNombre()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=cheque.getMonto()%></fmt:formatNumber></td>
                    </tr>
                    <%
                        }
                        CuotaDAO cuoDAO = new CuotaDAO();
                        ArrayList lista_cuota = cuoDAO.traerTodos_Cuota_para_Comprobante();
                        Iterator ccu = lista_cuota.iterator();
                        int num_cuota = 1;
                        while (ccu.hasNext()) {
                            Cuota cuota = (Cuota) ccu.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><input name="comprobante_pagos" type="checkbox" id="comprobante_pagos_<%=cont_p++%>" value="<%=cuota.getId_cuota()%>--Cuota" /></td>
                        <td><%=num_cuota++%></td>
                        <td>Cuota</td>
                        <td><%=cuota.getFecha_vencimientoStr()%></td>
                        <td><%=cuota.getFactura().getClientes().getNombre()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=cuota.getMonto()%></fmt:formatNumber></td>
                    </tr>
                    <%
                        }
                        LetraDAO letDAO = new LetraDAO();
                        ArrayList lista_letra = letDAO.traerTodos_Letra_para_Comprobante();
                        Iterator cl = lista_letra.iterator();
                        while (cl.hasNext()) {
                            Letra letra = (Letra) cl.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><input name="comprobante_pagos" type="checkbox" id="comprobante_pagos_<%=cont_p++%>" value="<%=letra.getId_letra()%>--Letra" /></td>
                        <td><%=letra.getNum_letra()%></td>
                        <td>Letra</td>
                        <td><%=letra.getFecha_recepcion_documentoStr()%></td>
                        <td><%=letra.getFactura().getClientes().getNombre()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=letra.getMonto()%></fmt:formatNumber></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <table align="center">
                    <tr>
                        <td><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:enviaPagos(document.formPagoClientes);" value="Agregar"></td>
                    </tr>
                </table>
            </fieldset>
        </form>
        <form name="formPagoProveedores">
            <fieldset>
                <legend class="txtResaltadoV">Pago Proveedores</legend>
                <table width="100%">
                    <tr>
                        <th width="1%"><input name="select_todo" type="checkbox" value="" id="select_todo" onclick="javascript:checkAll(document.formPagoProveedores.select_todo, document.formPagoProveedores.comprobante_pagos);" /></th>
                        <th width="20">Debe / Haber</th>
                        <th width="70">N° Documento</th>
                        <th width="100">Tipo Documento</th>
                        <th width="80">Fecha</th>
                        <th>Proveedor</th>
                        <th width="100">Monto</th>
                    </tr>
                    <%
                        Cheque_PagoDAO cpDAO = new Cheque_PagoDAO();
                        ArrayList lista_cheque_pago = cpDAO.traerTodos_Cheque_Pago_para_Comprobante();
                        Iterator ccp = lista_cheque_pago.iterator();
                        int cont_p_p = 1;
                        while (ccp.hasNext()) {
                            Cheque_Pago cheque = (Cheque_Pago) ccp.next();
                    %>
                    <tr class="cuadroTexto">
                        <td><input name="comprobante_pagos" type="checkbox" id="comprobante_pagos_prov_<%=cont_p_p++%>" value="<%=cheque.getId_cheque_pago()%>" /></td>
                        <td><select name="debe_haber_<%=cheque.getId_cheque_pago()%>" id="debe_haber_<%=cheque.getId_cheque_pago()%>" class="cuadroTexto">
                                <option value=""></option>
                                <option value="Debe">Debe</option>
                                <option value="Haber">Haber</option>
                            </select></td>
                        <td><%=cheque.getNum_cheque_pago()%></td>
                        <td>Cheque</td>
                        <td><%=cheque.getFecha_recepcion_documentoStr()%></td>
                        <td><%=cheque.getProveedores().getNombre()%></td>
                        <td align="right">$<fmt:formatNumber type="number" pattern="##,###,###"><%=cheque.getMonto()%></fmt:formatNumber></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <table align="center">
                    <tr>
                        <td><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:enviaPagoProveedores(document.formPagoProveedores);" value="Agregar"></td>
                    </tr>
                </table>
            </fieldset>
        </form>
        <form name="formManual">
            <fieldset>
                <legend class="txtResaltadoV">Comprobante Manual</legend>
                <table width="100%">
                    <tr>
                        <th width="10%">Cuenta Base</th>
                        <th width="100">Monto</th>
                        <th width="20">Debe</th>
                        <th width="20">Haber</th>
                        <th>Descripcion</th>
                    </tr>
                    <tr class="cuadroTexto">
                        <td><select name="num_cuenta" id="num_cuenta" class="cuadroTexto">
                                <%
                                    Cuentas_BaseDAO cbDAO = new Cuentas_BaseDAO();
                                    ArrayList cuentas = cbDAO.traerTodos_Cuentas_Base();
                                    Iterator cb = cuentas.iterator();
                                    while (cb.hasNext()) {
                                        Cuentas_Base cuentas_base = (Cuentas_Base) cb.next();
                                %>
                                <option value="<%=cuentas_base.getNum_cuenta()%>"><%=cuentas_base.getNum_cuenta()%> <%=cuentas_base.getDescripcion()%></option>
                                <%
                                    }
                                %>
                            </select></td>
                        <td><input name="monto" type="text" class="cuadroTexto" id="monto" style="text-align: right" value="0" /></td>
                        <td><input name="debe_haber_manual" type="radio" id="debe_haber_manual" value="Debe" checked="true"></td>
                        <td><input name="debe_haber_manual" type="radio" id="debe_haber_manual" value="Haber"></td>
                        <td><textarea name="observacion" class="cuadroTexto" id="observacion" cols="100"></textarea></td>
                    </tr>
                </table>
                <table align="center">
                    <tr>
                        <td><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:enviaComprobanteManual();" value="Agregar"></td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </body>
</html>