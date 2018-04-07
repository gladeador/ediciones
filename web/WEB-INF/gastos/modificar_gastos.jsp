<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Gastos.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Modificar Gastos ::.</title>
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
        '
        <script>
            function buscarGastos(id_gastos){
                Gastos.buscar_Gastos(id_gastos, cargarGastos);
            }
                
            function cargarGastos(data){
                dwr.util.setValues(data);
                document.getElementById("id_tipo_documento").value = data.tipo_documento.id_tipo_documento;
                document.getElementById("rut_proveedor").value = data.proveedores.rut;
                document.getElementById("fecha_gasto").value = data.fecha_gastoStr;
                document.getElementById("num_cuenta").value = data.cuenta_base_neto.num_cuenta;
                document.getElementById("id_forma_pago").value = data.forma_pago.id_forma_pago;
            }
            
            function calculaIVA(){
                var neto_afecto = document.getElementById("neto_afecto").value;
                if(neto_afecto == 0){
                    return;
                }
                var iva_afecto = redond((parseInt(neto_afecto) * 0.19),0);
                var total_afecto = parseInt(neto_afecto) + parseInt(iva_afecto);
                document.getElementById("iva_afecto").value = iva_afecto;
                document.getElementById("total_afecto").value = total_afecto;
                document.getElementById("neto_exento").value = 0;
            }
            
            function calcula_exento(){
                var neto_exento = document.getElementById("neto_exento").value;
                document.getElementById("neto_afecto").value = 0;
                document.getElementById("iva_afecto").value = 0;
                document.getElementById("total_afecto").value = neto_exento;
            }
        </script>

    </head>

    <body onload="javascript:buscarGastos(<%=request.getParameter("id_gastos")%>);" >
        <form name="formData" action="<%=request.getContextPath()%>/controller/ModificarGastos.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Modificar Gastos</th>
                </tr>
            </table>
            <br/>
            <fieldset>
                <legend class="txtResaltadoV">Datos del Gasto</legend>
                <table width="100%">
                    <tr>
                        <th class="specalt">Numero de Boleta </th>
                        <td><input name="num_boleta" type="text" class="cuadroTexto" id="num_boleta" value="0"><input name="id_gastos" type="hidden" id="id_gastos"></td>
                        <th class="specalt" valign="top">Tipo de Documento </th>
                        <td valign="top">
                            <select name="id_tipo_documento" id="id_tipo_documento" class="cuadroTexto">
                                <%
                                    Tipo_DocumentoDAO tdDAO = new Tipo_DocumentoDAO();
                                    ArrayList lista_tipos = tdDAO.traerTodos_Tipo_Documento();
                                    Iterator td = lista_tipos.iterator();
                                    while (td.hasNext()) {
                                        Tipo_Documento tipo_documento = (Tipo_Documento) td.next();
                                %>
                                <option value="<%=tipo_documento.getId_tipo_documento()%>"><%=tipo_documento.getDescripcion()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th class="specalt" valign="top">Fecha Gasto </th>
                        <td valign="top">
                            <input name="fecha_gasto" type="text" class="cuadroTexto" id="fecha_gasto" size="10" readonly>
                            <button onclick="javascript:displayCalendar(document.forms[0].fecha_gasto,'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                        </td>
                        <th class="specalt" valign="top">Proveedores </th>
                        <td valign="top">
                            <select name="rut_proveedor" id="rut_proveedor" class="cuadroTexto">
                                <%
                                    ProveedoresDAO provDAO = new ProveedoresDAO();
                                    ArrayList lista_proveedores = provDAO.traerTodos_Proveedores_Gastos();
                                    Iterator pv = lista_proveedores.iterator();
                                    while (pv.hasNext()) {
                                        Proveedores proveedores = (Proveedores) pv.next();
                                %>
                                <option value="<%=proveedores.getRut()%>"><%=proveedores.getNombre()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2" width="50%">Valor Exento</th>
                        <th colspan="2" width="50%">Valor Afecto</th>
                    </tr>
                    <tr>
                        <th class="specalt">Neto </th>
                        <td><input name="neto_exento" type="text" class="cuadroTexto" id="neto_exento" value="0" onchange="javascript:calcula_exento();"></td>
                        <th class="specalt">Neto </th>
                        <td><input name="neto_afecto" type="text" class="cuadroTexto" id="neto_afecto" value="0" onchange="javascript:calculaIVA();"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <th class="specalt">IVA </th>
                        <td><input name="iva_afecto" type="text" class="cuadroTexto" id="iva_afecto" value="0" readonly="true"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <th class="specalt">Total </th>
                        <td><input name="total_afecto" type="text" class="cuadroTexto" id="total_afecto" value="0" readonly="true"></td>
                    </tr>
                </table>
                <br>
                <table>
                    <tr>
                        <th class="specalt" valign="top">Cuenta Base </th>
                        <td valign="top">
                            <select name="num_cuenta" id="num_cuenta" class="cuadroTexto">
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
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th class="specalt" valign="top">Forma de Pago </th>
                        <td valign="top">
                            <select name="id_forma_pago" id="id_forma_pago" class="cuadroTexto">
                                <%
                                    Forma_Pago_GastosDAO fpgDAO = new Forma_Pago_GastosDAO();
                                    ArrayList lista_forma_pago = fpgDAO.traerTodos_Forma_Pago();
                                    Iterator fpg = lista_forma_pago.iterator();
                                    while (fpg.hasNext()) {
                                        Forma_Pago forma_pago = (Forma_Pago) fpg.next();
                                %>
                                <option value="<%=forma_pago.getId_forma_pago()%>"><%=forma_pago.getDescripcion()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <br>
            <table align="center">
                <tr>
                    <th><input name="b_limpiar" type="reset" id="b_limpiar" class="botones" value="Limpiar" onclick="javascript:buscarGastos(<%=request.getParameter("id_gastos")%>);"></th>
                    <th><input name="b_enviar" type="submit" id="b_enviar" class="botones" value="Modificar"></th>
                </tr>
            </table>
        </form>
    </body>
</html>