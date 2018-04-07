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
        <title>.:: Actualizar Gastos ::.</title>
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
            function buscarGastos(){
                var mes = document.getElementById("mes").value;
                var ano = document.getElementById("ano").value;
                Gastos.buscar_por_Mes_Ano(mes, ano, cargarbuscarGastos);
            }
            
            function cargarbuscarGastos(data){
                var id;
                var cont = 1;
                var cellFuncs = [
                    function(data) { id++;
                        return data.tipo_documento.id_tipo_documento; },
                    function(data) { return data.num_boleta; },
                    function(data) { return "<div align=\"center\">"+data.fecha_gastoStr+"</div>"; },
                    function(data) { return data.proveedores.nombre; },
                    function(data) { return "<div align=\"right\">$"+data.neto_exento+"</div>"; },
                    function(data) { return "<div align=\"right\">$"+data.neto_afecto+"</div>"; },
                    function(data) { return "<div align=\"right\">$"+data.iva_afecto+"</div>"; },
                    function(data) { return "<div align=\"right\">$"+data.total_afecto+"</div>"; },
                    function(data) { return "<th width=\"1\" class=\"nobg\"><a href=\"javascript:modificar("+data.id_gastos+");\"><img src=\"<%=request.getContextPath()%>/images/iconos/modificar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Modificar\"></a></th>";},
                    function(data) { return "<th width=\"1\" class=\"nobg\"><a href=\"javascript:Confirmar("+data.id_gastos+");\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a></th>";}
                ]
                id=-1;
                
                dwr.util.removeAllRows("tabla_detalle_gastos", 
                { filter:function(tr) 
                    {return (tr.id != "pattern");}});
                dwr.util.addRows("tabla_detalle_gastos", data, cellFuncs,{ escapeHtml:false });
            }
            
            function modificar(id_gastos){
                document.formModificar.id_gastos.value = id_gastos;
                document.formModificar.submit();
            }
        </script>

        <script>
            function Confirmar(id_gastos){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Borrar(id_gastos);
            }
        </script>

        <script>
            function Borrar(id_gastos){
                document.formBorrar.id_gastos.value = id_gastos;
                document.formBorrar.submit();
                document.formBorrar.reset();
            }
        </script>

    </head>
    <%
        HttpSession sesion = request.getSession();
        Fecha_Actual fecha = (Fecha_Actual) sesion.getAttribute("fecha_actual");
    %>
    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarGastos.ed">
            <input name="id_gastos" type="hidden">
        </form>

        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminarGastos.ed">
            <input name="id_gastos" type="hidden">
        </form>

        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Gastos</th>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <th class="specalt">Seleccione Mes</th>
                    <th class="specalt">Seleccione Año</th>
                </tr>
                <tr>
                    <td align="center">
                        <select name="mes" id="mes" class="cuadroTexto">
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
                    <td align="center">
                        <select name="ano" id="ano" class="cuadroTexto">
                            <%
                                for (int i = 2011; i <= Integer.parseInt(fecha.getAno()); i++) {
                            %>
                            <option selected=""><%=i%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><a href="javascript:buscarGastos()"><img src="<%=request.getContextPath()%>/images/iconos/buscar.png" border="0" width="30" height="30" title="Buscar"></a></td>
                </tr>
            </table>
            <table align="center" width="100%">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Listado de Gastos</legend>
                            <table width="100%">
                                <tr>
                                    <th>Tipo</th>
                                    <th>Num Boleta</th>
                                    <th>Fecha Gasto</th>
                                    <th>Proveedor</th>
                                    <th>Exento</th>
                                    <th>Afecto</th>
                                    <th>IVA</th>
                                    <th>Total</th>
                                </tr>
                                <tbody id="tabla_detalle_gastos" class="cuadroTexto">
                                    <tr class="cuadroTexto">
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>
                        </fieldset>   
                    </td>
                </tr> 
            </table>
        </form>
    </body>
</html>