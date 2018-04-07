<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Region.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Ciudad.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Region ::.</title>
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
            function traerTodos_Region(){
              
                Region.traer_todos(cargarRegion_Envio);
            }
            
            function valida_envia(){
                var descripcion = document.getElementById("descripcion").value;
                if(descripcion == ""){
                    alert("Debe ingresar una descripción para la Region");
                    return;
                }
                document.formData.reset();
                Region.agregar_Region(descripcion, cargarAgregar_Region);
            }
            
            function cargarAgregar_Region(data){
                if(data == "ok"){
                    alert("Exito al Agregar la Region");
                    traerTodos_Region();
                }else{
                    alert("Ha ocurrido un Error, por favor vuelva a intentarlo");
                }
            }
            
            function cargarRegion_Envio(data){
                dwr.util.useLoadingMessage("Cargando...");
                var id;
                var cont = 1;
                var cellFuncs = [
                    function(data) { id++;
                        return cont++; },
                    function(data) { return data.n_romano; },
                    function(data) { return data.descripcion; },
                    function(data) { return "<a href=\"javascript:Confirmar("+data.id_region+");\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a>"}
                ]
                id=-1;
                
                dwr.util.removeAllRows("tabla_region", 
                { filter:function(tr) 
                    {return (tr.id != "pattern");}});
                dwr.util.addRows("tabla_region", data, cellFuncs,{ escapeHtml:false });
            }
            
            function Confirmar(id_region){
                document.getElementById("id_region").value = id_region;
                Ciudad.traer_todos_por_Region(id_region, elimina_region);
            }
            
            function elimina_region(data){
                alert(data.length);
                if(data.length == 0 ){
                    var id_region = document.getElementById("id_region").value;
                    if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                        return;
                    }
                    Region.eliminar_Region(id_region);
                    alert("El Registro ha sido Eliminado con Exito");
                }else{
                    alert("La Region seleccionada posee ciudades asociadas y no puede ser eliminada.");
                }
                traerTodos_Region();
                
            }
        </script>

    </head>

    <body onload="javascript:traerTodos_Region();">
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Agregar Region</th>
                </tr>
            </table>
            <br>
            <table width="100%">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Descripción Envio</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Descripción </th>
                                    <td><input name="id_region" type="hidden" id="id_region" /><input name="descripcion" type="text" class="cuadroTexto" id="descripcion" size="30" onchange="javscript:this.value = this.value.toUpperCase();"></td>
                                </tr>
                            </table>
                        </fieldset>
                        <br>
                        <table align="center">
                            <tr>
                                <th><input name="b_enviar" type="reset" id="b_enviar" class="botones" value="Limpiar"></th>
                                <th><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:valida_envia();" value="Agregar"></th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <br>
            <table width="100%">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Regiones </legend>
                            <table width="100%">
                                <tr>
                                    <th width="5%">#</th>
                                    <th width="10%">N. Romano</th>
                                    <th width="90%">Descripción</th>
                                </tr>
                                <tbody id="tabla_region" class="subTitulos">
                                    <tr>
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