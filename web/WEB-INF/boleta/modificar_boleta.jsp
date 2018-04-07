<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Boleta.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Clientes.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Boleta_Productos.js'></script>
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
        <title>.:: Agregar Boleta ::.</title>
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
            function buscarBoleta(id_boleta) {
                Boleta.buscar_boleta(id_boleta, cargarboleta);
            }

            function cargarboleta(data) {
                dwr.util.setValues(data);
                document.getElementById("fecha_boleta").value = data.fecha_boletaStr;

                if (data.clientes == null) {
                    ver_Clientes();
                } else {
                    Clientes.buscar_Clientes(data.clientes.rut, cargarCliente);
                }
                Boleta_Productos.buscar_Productos(data.id_boleta, cargaboletaProductos);
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
                var id_boleta = document.getElementById("id_boleta").value;
                Boleta.agrega_Cliente(rut, id_boleta);
                Clientes.buscar_Clientes(rut, cargarCliente);
            }

            function cargarCliente(data) {
                dwr.util.setValues(data);
            }

            function agregar_Producto() {
                var id_producto = document.getElementById("id_producto").value;
                var id_boleta = document.getElementById("id_boleta").value;
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
                var id_boleta = document.getElementById("id_boleta").value;
                var cantidad = document.getElementById("cantidad").value;
                if (cantidad > data) {
                    alert("La cantidad que esta ingresando es mayor la del Stock de Bodega el Stock es de " + data + " unidades");
                    return;
                }
                ;

                Boleta_Productos.agregar_Productos(id_producto, id_boleta, cantidad, cargaboletaProductos);
                document.getElementById("cantidad").value = 0;
            }

            function cargaboletaProductos(data) {
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
                        return "<a href=\"javascript:Confirmar(" + data.boleta.id_boleta + "," + data.productos.id_productos + ");\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a>"
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

            function Confirmar(id_boleta, id_productos) {
                if (!confirm("Esta seguro que desea Eliminar este Registro?")) {
                    return;
                }
                Boleta_Productos.eliminar_Productos(id_boleta, id_productos, cargaboletaProductos);
            }

            function CalculaIva(sub_total) {
                var por_desc = document.getElementById("por_desc").value;
                var desc = redond(((parseInt(sub_total) * parseInt(por_desc)) / 100), 0);
                var neto = parseInt(sub_total) - parseInt(desc);
                var iva = redond((parseInt(neto) * 0.19), 0);
                var total = parseInt(neto) + parseInt(iva);
                var id_boleta = document.getElementById("id_boleta").value;

                document.getElementById("sub_total").value = sub_total;
                document.getElementById("neto").value = neto;
                document.getElementById("iva").value = iva;
                document.getElementById("total").value = total;

                Boleta.agregar_valores(sub_total, desc, neto, iva, total, id_boleta);
            }

            function modifica_fecha_boleta() {
                var fecha_boleta = document.getElementById("fecha_boleta").value;
                var id_boleta = document.getElementById("id_boleta").value;
                Boleta.modifica_fecha(fecha_boleta, id_boleta);
            }

            function graba_envio(id_tipo_envio) {
                var id_boleta = document.getElementById("id_boleta").value;
                Boleta.graba_envio(id_tipo_envio, id_boleta);
            }

            function emitir_boleta() {
                document.formData.submit();
            }

        </script>

    </head>

    <body onLoad="javascript:buscarBoleta(<%=request.getParameter("id_boleta")%>);">
        <form name="formData" action="<%=request.getContextPath()%>/controller/EmitirBoleta.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Modificar Boleta</th>
                </tr>
            </table>
            <br>
            <table width="100%">
                <tr>
                    <td>
                        <table width="320" align="center">
                            <tr>
                                <th width="142" class="specalt">N de Boleta  </th>
                                <td width="166"><input name="num_boleta" type="text" class="cuadroTexto" id="num_boleta" readonly="true"><input name="id_boleta" type="hidden" id="id_boleta"></td>
                            </tr>
                        </table>
                        <fieldset>
                            <legend class="txtResaltadoV">Fecha Boleta</legend>
                            <table width="356">
                                <tr>
                                    <th width="140" valign="top" class="specalt">Fecha Boleta</th>
                                    <td width="204" valign="middle">
                                        <input name="fecha_boleta" type="text" class="cuadroTexto" id="fecha_boleta" align="left" onChange="javascript:modifica_fecha_boleta();" readonly>
                                        <button onClick="javascript:displayCalendar(document.forms[0].fecha_boleta, 'dd/mm/yyyy', this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <fieldset> 
                            <legend class="txtResaltadoV">Datos del Cliente</legend>
                            <table>
                                <tr>
                                    <th class="specalt"><div align="left">Nombre </div></th>
                                    <td><input name="nombre" type="text" class="cuadroTexto" id="nombre" size="30" readonly="true"></td>
                                    <th class="specalt">Rut </th>
                                    <td>
                                        <input name="rut" type="text" class="cuadroTexto" id="rut" size="8" maxlength="8" readonly="true">
                                        -
                                        <input name="dv" type="text" class="cuadroTexto" id="dv" size="1" maxlength="1" readonly="true">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Direcci&oacute;n </div></th>
                                    <td><input name="direccion_particular" type="text" class="cuadroTexto" id="direccion_particular" size="50" readonly="true"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Giro </div></th>
                                    <td><input name="giro" type="text" class="cuadroTexto" id="giro" readonly="true"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" id="emailt"><div align="left">E-Mail </div></th>
                                    <td><input name="email" type="text" class="cuadroTexto" id="email" size="50" maxlength="50" readonly="true"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Contacto </div></th>
                                    <td><input name="nombre_contacto" type="text" class="cuadroTexto" id="nombre_contacto" size="30" readonly="true"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Forma de Env&iacute;o </div></th>
                                    <td>
                                        <select name="id_tipo_envio" class="cuadroTexto" id="id_tipo_envio">
                                            <%
                                                Tipo_EnvioDAO envioDAO = new Tipo_EnvioDAO();
                                                ArrayList lista_envio = envioDAO.traerTipo_Envio();
                                                Iterator h = lista_envio.iterator();
                                                while (h.hasNext()) {
                                                    Tipo_Envio tipo_envio = (Tipo_Envio) h.next();
                                            %>
                                            <option value="<%=tipo_envio.getId_tipo_envio()%>" onClick="javascript:graba_envio(<%=tipo_envio.getId_tipo_envio()%>);"><%=tipo_envio.getDescripcion()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </td>
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
                                    <td><input name="cantidad" type="text" class="cuadroTexto" id="cantidad" value="0" size="4" maxlength="4" onKeyUp="this.value = this.value.replace(/[^0-9]/, '');"></td>
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
                                    <th>% Desc. </th>
                                    <td><input name="por_desc" type="text" class="cuadroTexto" id="por_desc" size="8" style="text-align: right" value="0" onKeyUp="this.value = this.value.replace(/[^0-9]/, '');" onChange="javascript:CalculaIva(sub_total.value);"></td>
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
                    <td><a href="javascript:emitir_boleta();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/doc.png" border="0" width="30" height="30" title="Emitir"></a></td>
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