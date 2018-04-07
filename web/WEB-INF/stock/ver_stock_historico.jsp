<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Stock.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Salidas.js'></script>
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
        <title>.:: Ver Stock Historico ::.</title>
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
            function ver_detalle_Compras(id_productos){
                Dialog.info($('ver_detalle').innerHTML,{className:"alphacube", title:"Detalle Compras", width:600, height:200, top:100});
                Stock.detalle_stock_por_Producto(id_productos, cargarDetalle);
            }
            
            function cargarDetalle(data){
                dwr.util.useLoadingMessage("Cargando...");
                var id;
                var cont = 1;
                var cellFuncs = [
                    function(data) { id++;
                        return data.proveedores.nombre; },
                    function(data) { return "<div align=\"center\">"+data.fecha_ingresoStr+"</div>"; },
                    function(data) { return "<div align=\"center\">"+data.stock+"</div>"; },
                    function(data) { return "<a href=\"javascript:modificar("+data.id_stock+");\"><img src=\"<%=request.getContextPath()%>/images/iconos/modificar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Modificar\"></a>"; }
                ]
                id=-1;
                
                dwr.util.removeAllRows("tabla_detalle", 
                { filter:function(tr) 
                    {return (tr.id != "pattern");}});
                dwr.util.addRows("tabla_detalle", data, cellFuncs,{ escapeHtml:false });
            }
            
            function modificar(id_stock){
                document.formModificar.id_stock.value = id_stock;
                document.formModificar.submit();
            }
            
            function ver_detalle_Salidas(id_tipo_salida, id_productos, descripcion){
                Dialog.info($('ver_detalle_salida').innerHTML,{className:"alphacube", title:"Detalle "+descripcion, width:600, height:200, top:100});
                Salidas.detalle_salidas(id_tipo_salida, id_productos, cargarDetalle_Salidas);
            }
            
            function cargarDetalle_Salidas(data){
                dwr.util.useLoadingMessage("Cargando...");
                var id;
                var cont = 1;
                var cellFuncs = [
                    function(data) { id++;
                        return "<div align=\"center\">"+data.fecha_salidaStr+"</div>"; },
                    function(data) { return "<div align=\"center\">"+data.cantidad+"</div>"; },
                    function(data) { 
                        if(data.observaciones == null || data.observaciones == ""){
                            return "Sin Observaciones";
                        }else{
                            return data.observaciones;
                        }}
                ]
                id=-1;
                
                dwr.util.removeAllRows("tabla_detalle_salidas", 
                { filter:function(tr) 
                    {return (tr.id != "pattern");}});
                dwr.util.addRows("tabla_detalle_salidas", data, cellFuncs,{ escapeHtml:false });
            }
                
        </script>

    </head>

    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarStockProducto.ed">
            <input name="id_stock" type="hidden">
        </form>

        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Ver Stock Historico</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <legend class="txtResaltadoV">Listado de Productos</legend>
                <table width="100%">
                    <tr>
                        <th>Producto</th>
                        <th width="10%">Compras</th>
                        <%
                            ArrayList lista_tipo_salida = (ArrayList) request.getAttribute("lista_tipo_salida");
                            Iterator s = lista_tipo_salida.iterator();
                            while (s.hasNext()) {
                                Tipo_Salida tipo_salida = (Tipo_Salida) s.next();
                        %>
                        <th width="10%"><%=tipo_salida.getDescripcion()%></th>
                        <%
                            }
                        %>
                        <th width="10%">Ventas</th>
                        <th width="10%">Bodega</th>
                    </tr>
                    <%
                        ArrayList lista_stock = (ArrayList) request.getAttribute("lista_stock");
                        Iterator i = lista_stock.iterator();
                        while (i.hasNext()) {
                            Stock_Producto stock = (Stock_Producto) i.next();
                            int bodega = stock.getStock();
                    %>
                    <tr class="cuadroTexto">
                        <td><%=stock.getProductos().getDescripcion()%></td>
                        <td align="right" title="Ver Detalle Compras"><a href="javascript:ver_detalle_Compras(<%=stock.getProductos().getId_productos()%>);"><fmt:formatNumber type="number" pattern="##,###,###"><%=stock.getStock()%></fmt:formatNumber></a></td>
                        <%
                            SalidasDAO salDAO = new SalidasDAO();
                            Iterator s_2 = lista_tipo_salida.iterator();
                            while (s_2.hasNext()) {
                                Tipo_Salida tipo_salida = (Tipo_Salida) s_2.next();
                                int suma = salDAO.suma_Salidas(stock.getProductos().getId_productos(), tipo_salida.getId_tipo_salida());
                                bodega = bodega - suma;
                        %>
                        <td align="right" title="Ver Detalle <%=tipo_salida.getDescripcion()%>"><a href="javascript:ver_detalle_Salidas(<%=tipo_salida.getId_tipo_salida()%>,<%=stock.getProductos().getId_productos()%>,'<%=tipo_salida.getDescripcion()%>');"><fmt:formatNumber type="number" pattern="##,###,###"><%=suma%></fmt:formatNumber></a></td>
                        <%
                            }
                            Factura_ProductosDAO fpDAO = new Factura_ProductosDAO();
                            int ventas = fpDAO.suma_productos_por_Producto(stock.getProductos().getId_productos());
                            bodega = bodega - ventas;
                        %>
                        <td align="right"><fmt:formatNumber type="number" pattern="##,###,###"><%=ventas%></fmt:formatNumber></td>
                        <td align="right"><fmt:formatNumber type="number" pattern="##,###,###"><%=bodega%></fmt:formatNumber></td>
                        </tr>
                    <%
                        }
                    %>
                </table>
            </fieldset>
        </form>

        <div id="ver_detalle" style="display:none; background:#123" >
            <fieldset>
                <table align="center" width="100%">
                    <tr>
                        <th>Proveedor</th>
                        <th>Fecha Ingreso</th>
                        <th width="20%">Compras</th>
                    </tr>
                    <tbody id="tabla_detalle" class="subTitulos">
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

        <div id="ver_detalle_salida" style="display:none; background:#123" >
            <fieldset>
                <table align="center" width="100%">
                    <tr>
                        <th width="20%">Fecha Salida</th>
                        <th width="20%">Cantidad</th>
                        <th>Observaciones</th>
                    </tr>
                    <tbody id="tabla_detalle_salidas" class="subTitulos">
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