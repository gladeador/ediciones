<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Guia_Despacho.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Clientes.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Guia_Productos.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Bodega.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<!-- inclusion de js para control de ventanas -->
<script type='text/javascript' src='<%=request.getContextPath()%>/js/jwindows/prototype.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/js/jwindows/window.js'></script>
<link href="<%=request.getContextPath()%>/js/jwindows/themes/default.css" rel="stylesheet" type="text/css"></link>
<link href="<%=request.getContextPath()%>/js/jwindows/themes/alphacube.css" rel="stylesheet" type="text/css"> </link>
<!-- fin inclusion -->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Modificar Guía  de Despacho ::.</title>
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
            function buscarGuia(id_guia_despacho) {
                Guia_Despacho.buscar_guia(id_guia_despacho, cargarGuia);
            }

            function cargarGuia(data) {
                dwr.util.setValues(data);
                document.getElementById("fecha_guia").value = data.fecha_guiaStr;
                if (data.clientes == null) {
                    ver_Clientes();
                } else {
                    Clientes.buscar_Clientes(data.clientes.rut, cargarCliente);
                }
                Guia_Productos.buscar_Productos(data.id_guia_despacho, cargaGuiaProductos);
            }

            function ver_Clientes() {
                Dialog.info($('ver_clientes').innerHTML, {className: "alphacube", title: "Clientes", width: 600, height: 200, top: 100});
                Clientes.listar_Clientes(cargarClientes);
            }

            function cargarClientes(data) {
                dwr.util.useLoadingMessage("Cargando...");
                var id;
                var cont = 1;
                var cellFuncs = [
                    function (data) {
                        id++;
                        return data.rut + "-" + data.dv;
                    },
                    function (data) {
                        return data.nombre;
                    },
                    function (data) {
                        return data.giro;
                    },
                    function (data) {
                        return "<a href=\"javascript:entregarCliente(" + data.rut + ");\" target=\"_self\"><img src=\"<%=request.getContextPath()%>/images/iconos/seleccionar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Seleccionar\"></a>"
                    }
                ]
                id = -1;

                dwr.util.removeAllRows("tabla_clientes",
                        {filter: function (tr)
                            {
                                return (tr.id != "pattern");
                            }});
                dwr.util.addRows("tabla_clientes", data, cellFuncs, {escapeHtml: false});
            }

            function entregarCliente(rut) {
                cerrar_ventana();
                var id_guia_despacho = document.getElementById("id_guia_despacho").value;
                Guia_Despacho.agrega_Cliente(rut, id_guia_despacho);
                Clientes.buscar_Clientes(rut, cargarCliente);
            }

            function cargarCliente(data) {
                dwr.util.setValues(data);
            }

            function agregar_Producto() {
                var id_producto = document.getElementById("id_producto").value;
                var id_guia_despacho = document.getElementById("id_guia_despacho").value;
                var cantidad = document.getElementById("cantidad").value;
                if (parseInt(cantidad) <= 0) {
                    alert("La cantidad de productos, debe ser superior a 0.");
                    return;
                }

                Bodega.buscarStockBodega(id_producto, cargaStockBodega);

            }

            function cargaStockBodega(data) {
                dwr.util.useLoadingMessage("Cargando...");
                var id_producto = document.getElementById("id_producto").value;
                var id_guia_despacho = document.getElementById("id_guia_despacho").value;
                var cantidad = document.getElementById("cantidad").value;
                if (cantidad > data) {
                    alert("La cantidad que esta ingresando es mayor la del Stock de Bodega, El Stock existente es de " + data);
                    return;
                }
                ;


                Guia_Productos.agregar_Productos(id_producto, id_guia_despacho, cantidad, cargaGuiaProductos);
                document.getElementById("cantidad").value = 0;
            }

            function cargaGuiaProductos(data) {
                dwr.util.useLoadingMessage("Cargando...");
                var id;
                var sub_total = 0;
                var cellFuncs = [
                    function (data) {
                        id++;
                        return data.cantidad;
                    },
                    function (data) {
                        return data.productos.descripcion;
                    },
                    function (data) {
                        return "<div align=\"right\">$" + data.productos.valor_neto + "</div>";
                    },
                    function (data) {
                        sub_total = parseInt(sub_total) + (parseInt(data.cantidad) * parseInt(data.productos.valor_neto));
                        return "<div align=\"right\">$" + (parseInt(data.cantidad) * parseInt(data.productos.valor_neto)) + "</div>";
                    },
                    function (data) {
                        return "<a href=\"javascript:Confirmar(" + data.guia_despacho.id_guia_despacho + "," + data.productos.id_productos + ");\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a>"
                    }
                ]
                id = -1;

                dwr.util.removeAllRows("tabla_productos",
                        {filter: function (tr)
                            {
                                return (tr.id != "pattern");
                            }});
                dwr.util.addRows("tabla_productos", data, cellFuncs, {escapeHtml: false});
                CalculaIva(sub_total);
            }

            function Confirmar(id_guia_despacho, id_productos) {
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")) {
                    return;
                }
                Guia_Productos.eliminar_Productos(id_guia_despacho, id_productos, cargaGuiaProductos);
            }

            function CalculaIva(sub_total) {
                var descuento = document.getElementById("descuento").value;
                var neto = parseInt(sub_total) - parseInt(descuento);
                var iva = redond((parseInt(neto) * 0.19), 0);
                var total = parseInt(neto) + parseInt(iva);
                var id_guia_despacho = document.getElementById("id_guia_despacho").value;

                document.getElementById("sub_total").value = sub_total;
                document.getElementById("neto").value = neto;
                document.getElementById("iva").value = iva;
                document.getElementById("total").value = total;

                Guia_Despacho.agregar_valores(sub_total, descuento, neto, iva, total, id_guia_despacho);
            }

            function modifica_fecha_Guia() {
                var fecha_guia = document.getElementById("fecha_guia").value;
                var id_guia_despacho = document.getElementById("id_guia_despacho").value;
                Guia_Despacho.modifica_fecha(fecha_guia, id_guia_despacho);
            }

            function emitir_guia() {

                var id_guia_despacho = document.getElementById("id_guia_despacho").value;
                var num_guia = document.getElementById("num_guia").value;
                var sub_total = document.getElementById("sub_total").value;

                if (num_guia == "" || num_guia == 0) {
                    alert("Debe Crear la Guia de Despacho");
                    return;
                }
                if (sub_total == "" || sub_total == 0) {
                    alert("Debe Ingresar los Productos a Despachar");
                    return;
                }
                document.formData.submit();
            }

        </script>

    </head>

    <body onload="javascript:buscarGuia(<%=request.getParameter("id_guia_despacho")%>);">
        <form name="formData" method="post" action="<%=request.getContextPath()%>/controller/EmitirGuiaDespacho.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Modificar Guía de Despacho</th>
                </tr>
            </table>
            <br>
            <table width="100%">
                <tr>
                    <td>
                        <table align="center">
                            <tr>
                                <th class="specalt">Nº de Guía  </th>
                                <td><input name="num_guia" type="text" class="cuadroTexto" id="num_guia" readonly="true"><input name="id_guia_despacho" type="hidden" id="id_guia_despacho"></td>
                            </tr>
                        </table>
                        <fieldset>
                            <legend class="txtResaltadoV">Fecha Guía de Despacho</legend>
                            <table>
                                <tr>
                                    <th class="specalt" valign="top">Fecha Guía </th>
                                    <td valign="middle">
                                        <input name="fecha_guia" type="text" class="cuadroTexto" id="fecha_guia" align="left" onchange="javascript:modifica_fecha_Guia();" readonly>
                                        <button onclick="javascript:displayCalendar(document.forms[0].fecha_guia, 'dd/mm/yyyy', this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <fieldset>
                            <legend class="txtResaltadoV">Datos del Cliente</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Nombre </th>
                                    <td><input name="nombre" type="text" class="cuadroTexto" id="nombre" size="30" readonly="true"> <a href="javascript:ver_Clientes();" target="_self" type="button"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Modificar"></a></td>
                                    <th class="specalt">Rut </th>
                                    <td>
                                        <input name="rut" type="text" class="cuadroTexto" id="rut" size="8" maxlength="8" readonly="true">
                                        -
                                        <input name="dv" type="text" class="cuadroTexto" id="dv" size="1" maxlength="1" readonly="true">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt">Dirección </th>
                                    <td><input name="direccion_particular" type="text" class="cuadroTexto" id="direccion_particular" size="50" readonly="true"></td>
                                </tr>
                                <tr>
                                    <th class="specalt">Giro </th>
                                    <td><input name="giro" type="text" class="cuadroTexto" id="giro" readonly="true"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" id="emailt">E-Mail </th>
                                    <td><input name="email" type="text" class="cuadroTexto" id="email" size="50" maxlength="50" readonly="true"></td>
                                </tr>
                                <tr>
                                    <th class="specalt">Contacto </th>
                                    <td><input name="nombre_contacto" type="text" class="cuadroTexto" id="nombre_contacto" size="30" readonly="true"></td>
                                </tr>
                            </table>
                        </fieldset>
                        <br>
                        <fieldset>
                            <legend class="txtResaltadoV">Productos</legend>
                            <table>
                                <tr>
                                    <th class="specalt" valign="top">Producto </th>
                                    <td valign="top">
                                        <select name="id_producto" class="cuadroTexto" id="id_producto">
                                            <%
                                                ProductosDAO proDAO = new ProductosDAO();
                                                ArrayList lista_productos = proDAO.traerTodos_Productos();
                                                Iterator p = lista_productos.iterator();
                                                while (p.hasNext()) {
                                                    Productos productos = (Productos) p.next();
                                            %>
                                            <option value="<%=productos.getId_productos()%>"><%=productos.getDescripcion()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </td>
                                    <th class="specalt">Cantidad </th>
                                    <td><input name="cantidad" type="text" class="cuadroTexto" id="cantidad" value="0" size="4" maxlength="4" onkeyup="this.value = this.value.replace(/[^0-9]/, '');"></td>
                                    <td><a href="javascript:agregar_Producto();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/agregar.png" border="0" width="20" height="20" title="Agregar"></a></td>
                                </tr>
                            </table>
                            <br>
                            <table align="center" width="100%">
                                <tr>
                                    <th width="10%">Cantidad</th>
                                    <th>Producto</th>
                                    <th width="10%">Valor<br>Unitario</th>
                                    <th width="10%">Total</th>
                                    <td width="1%"></td>
                                </tr>
                                <tbody id="tabla_productos" class="subTitulos">
                                    <tr>
                                        <td></td>
                                    </tr>
                                </tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <th>Sub Total </th>
                                    <td><input name="sub_total" type="text" class="cuadroTexto" id="sub_total" size="8" style="text-align: right" value="0" readonly="true"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <th>Descuento </th>
                                    <td><input name="descuento" type="text" class="cuadroTexto" id="descuento" size="8" style="text-align: right" value="0" onkeyup="this.value = this.value.replace(/[^0-9]/, '');" onchange="javascript:CalculaIva(sub_total.value);"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <th>Neto </th>
                                    <td><input name="neto" type="text" class="cuadroTexto" id="neto" size="8" style="text-align: right" value="0" readonly="true"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <th>19% IVA </th>
                                    <td><input name="iva" type="text" class="cuadroTexto" id="iva" size="8" style="text-align: right" value="0" readonly="true"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <th>Total </th>
                                    <td><input name="total" type="text" class="cuadroTexto" id="total" size="8" style="text-align: right" value="0" readonly="true"></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <th><input name="b_enviar" type="reset" id="b_enviar" class="botones" value="Limpiar"></th>
                    <th><input name="b_enviar" type="submit" id="b_enviar" class="botones" onclick="javascript:valida_envia();" value="Agregar"></th>
                    <td><a href="javascript:emitir_guia();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/doc.png" border="0" width="30" height="30" title="Emitir"></a></td>
                </tr>
            </table>
        </form>

        <div id="ver_clientes" style="display:none; background:#123" >
            <fieldset>
                <table align="center" width="100%">
                    <tr>
                        <th width="20%">rut</th>
                        <th width="50%">Nombre</th>
                        <th width="30%">Giro</th>
                    </tr>
                    <tbody id="tabla_clientes" class="subTitulos">
                        <tr>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </fieldset>
            <br>
            <table align="center">
                <tr>
                    <td><a href="javascript:cerrar_ventana();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"></a></td>
                </tr>
            </table>
        </div>

    </body>
</html>