<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Stock.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Modificar Stock Producto ::.</title>
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
            function busca_Stock(id_stock){
                Stock.buscarStock(id_stock, cargabusca_Stock);
            }
            
            function cargabusca_Stock(data){
                dwr.util.setValues(data);
                document.getElementById("producto").value = data.productos.descripcion;
                document.getElementById("proveedor").value = data.proveedores.nombre;
                document.getElementById("fecha_ingreso").value = data.fecha_ingresoStr;
                document.getElementById("id_tipo_moneda").value = data.tipo_moneda.id_tipo_moneda;
                document.getElementById("tipo_moneda").value = data.tipo_moneda.descripcion;
                document.getElementById("id_tipo_documento").value = data.tipo_documento.id_tipo_documento;
                
                tipo_cambio(data.tipo_moneda.id_tipo_moneda);
            }
            
            function tipo_cambio(id_tipo_moneda){
                if(id_tipo_moneda == 2){
                    document.getElementById("tipocambio").style.display = "";
                }else{
                    document.getElementById("tipocambio").style.display = "none";
                    document.getElementById("tipo_de_cambio").value = "0";
                    document.getElementById("porsentaje_gastos").value = "0";
                    document.getElementById("costo_gastos").value = "0";
                }
                calculaIVA();
            }
            
            function calculaIVA(){
                var id_tipo_moneda = document.getElementById("id_tipo_moneda").value;
                var costo_producto = document.getElementById("costo_producto").value;
                var stock = document.getElementById("stock").value;
                var neto = 0;
                if(id_tipo_moneda == 2){
                    var tipo_cambio = document.getElementById("tipo_de_cambio").value;
                    var costo_gastos = document.getElementById("costo_gastos").value;
                    var porsentaje_gastos = document.getElementById("porsentaje_gastos").value;
                    
                    var aux = parseFloat(costo_producto) * parseInt(stock);
                    var sub_total = parseFloat(tipo_cambio) * parseFloat(aux);
                    
                    var costo_gastos = parseFloat(sub_total) * (parseFloat(porsentaje_gastos) / 100);
                    document.getElementById("costo_gastos").value = redond(costo_gastos, 0);
                
                    neto = parseFloat(sub_total) + parseFloat(costo_gastos);
                }else{
                    neto = parseFloat(costo_producto) * parseInt(stock);
                }
                var iva = redond((parseFloat(neto) * 0.19),0);
                var total = parseFloat(neto) + parseFloat(iva);
                document.getElementById("neto").value = neto;
                document.getElementById("iva").value = iva;
                document.getElementById("total").value = total;
            }
        </script>

    </head>
    <%
        HttpSession sesion = request.getSession();
        Fecha_Actual fecha = (Fecha_Actual) sesion.getAttribute("fecha_actual");
    %>
    <body onload="javascript:busca_Stock(<%=request.getParameter("id_stock")%>);">
        <form name="formData" action="<%=request.getContextPath()%>/controller/ModificarStockProducto.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Modificar Stock Productos</th>
                </tr>
            </table>
            <br>
            <table align="center" width="100%">
                <tr>
                    <td valign="top">
                        <fieldset>
                            <legend class="txtResaltadoV">Stock Producto</legend>
                            <table width="100%">
                                <tr>
                                    <th class="specalt" valign="top">Producto </th>
                                    <td><input name="producto" type="text" class="cuadroTexto" id="producto" readonly="true" /><input name="id_stock" type="hidden" id="id_stock" /></td>
                                    <th class="specalt" valign="top">Proveedores </th>
                                    <td><input name="proveedor" type="text" class="cuadroTexto" id="proveedor" readonly="true" /></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Tipo de Moneda </th>
                                    <td><input name="tipo_moneda" type="text" class="cuadroTexto" id="tipo_moneda" readonly="true" /><input name="id_tipo_moneda" type="hidden" id="id_tipo_moneda" /></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Costo del producto<br>Unitario </th>
                                    <td valign="top"><input name="costo_producto" type="text" class="cuadroTexto" id="costo_producto" onkeyup="this.value = this.value.replace (/[^0-9.]/, '');" size="5" maxlength="7" value="0" onchange="javascript:calculaIVA();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Stock </th>
                                    <td valign="top"><input name="stock" type="text" class="cuadroTexto" id="stock" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="5" maxlength="7" value="0" onchange="javascript:calculaIVA();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Fecha Ingreso </th>
                                    <td valign="middle">
                                        <input name="fecha_ingreso" type="text" class="cuadroTexto" id="fecha_ingreso" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                                        <button onclick="javascript:displayCalendar(document.forms[0].fecha_ingreso,'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Nº Guia de Despacho </th>
                                    <td valign="top"><input name="num_guia_despacho" type="text" class="cuadroTexto" id="num_guia_despacho" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="5" maxlength="7" value="0"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Nº Factura </th>
                                    <td valign="top"><input name="num_factura" type="text" class="cuadroTexto" id="num_factura" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="5" maxlength="7" value="0"></td>
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
                                    <th class="specalt" valign="top">Observaciones </th>
                                    <td valign="top" colspan="3"><textarea name="observaciones" class="cuadroTexto" id="observaciones" maxlength="250" cols="100" rows="5"></textarea></td>
                                </tr>
                            </table>
                        </fieldset>
                        <br>
                        <div id="tipocambio" style="display:none;">
                            <fieldset>
                                <legend class="txtResaltadoV">Gastos</legend>
                                <table width="100%">
                                    <tr>
                                        <th class="specalt" valign="top">Tipo de Cambio </th>
                                        <td valign="top"><input name="tipo_de_cambio" type="text" class="cuadroTexto" id="tipo_de_cambio" onkeyup="this.value = this.value.replace (/[^0-9.]/, '');" size="5" maxlength="7" value="0" onchange="javascript:calculaIVA();"></td>
                                    </tr>
                                    <tr>
                                        <th class="specalt" valign="top">Porsentaje de Gastos </th>
                                        <td valign="top"><input name="porsentaje_gastos" type="text" class="cuadroTexto" id="porsentaje_gastos" onkeyup="this.value = this.value.replace (/[^0-9.]/, '');" size="3" maxlength="4" value="0" onchange="javascript:calculaIVA();"> %</td>
                                        <th class="specalt" valign="top">Costo de Gastos </th>
                                        <td valign="top"><input name="costo_gastos" type="text" class="cuadroTexto" id="costo_gastos" size="5" maxlength="7" value="0" readonly="true"></td>
                                    </tr>
                                </table>
                            </fieldset>
                        </div>
                        <br>
                        <fieldset>
                            <table>
                                <tr>
                                    <th class="specalt" valign="top">Neto </th>
                                    <td valign="top"><input name="neto" type="text" class="cuadroTexto" id="neto" size="20" value="0" readonly="true"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Iva </th>
                                    <td valign="top"><input name="iva" type="text" class="cuadroTexto" id="iva" size="20" value="0" readonly="true"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Total </th>
                                    <td valign="top"><input name="total" type="text" class="cuadroTexto" id="total" size="20" value="0" readonly="true"></td>
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
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <td><a href="<%=request.getContextPath()%>/controller/VerStockHistorico.ed"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver" /></a></td>
                    <td><a href="javascript:busca_Stock(<%=request.getParameter("id_stock")%>);"><img src="<%=request.getContextPath()%>/images/iconos/limpiar.png" border="0" width="30" height="30" title="Limpiar" /></a></td>
                    <th><input name="b_enviar" type="submit" id="b_enviar" class="botones" value="Modificar" /></th>
                </tr>
            </table>
        </form>
    </body>
</html>