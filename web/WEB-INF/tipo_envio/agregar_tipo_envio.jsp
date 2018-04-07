<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Tipo_Envio.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Tipo Envio ::.</title>
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
            function traerTodos_Tipo_Envio(){
                Tipo_Envio.traer_todos(cargarTipos_Envio);
            }
            
            function valida_envia(){
                var descripcion = document.getElementById("descripcion").value;
                if(descripcion == ""){
                    alert("Debe ingresar una descripción para el Tipo de Envío");
                    return;
                }
                document.formData.reset();
                Tipo_Envio.agregar_Tipo_Envio(descripcion, cargarAgregar_Tipo_Envio);
            }
            
            function cargarAgregar_Tipo_Envio(data){
                if(data == "ok"){
                    alert("Exito al Agregar el  Tipo de Envio");
                    traerTodos_Tipo_Envio();
                }else{
                    alert("Ha ocurrido un Error, por favor vuelva a intentarlo");
                }
            }
            
            function cargarTipos_Envio(data){
                dwr.util.useLoadingMessage("Cargando...");
                var id;
                var cont = 1;
                var cellFuncs = [
                    function(data) { id++;
                        return cont++; },
                    function(data) { return data.descripcion; },
                    function(data) { return "<a href=\"javascript:Confirmar("+data.id_tipo_envio+");\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a>"}
                ]
                id=-1;
                
                dwr.util.removeAllRows("tabla_tipo_envio", 
                { filter:function(tr) 
                    {return (tr.id != "pattern");}});
                dwr.util.addRows("tabla_tipo_envio", data, cellFuncs,{ escapeHtml:false });
            }
            
            function Confirmar(id_tipo_envio){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Tipo_Envio.eliminar_Tipo_Envio(id_tipo_envio);
                    alert("El Registro ha sido Eliminado con Exito");
                traerTodos_Tipo_Envio();
            }
        </script>

    </head>

    <body onload="javascript:traerTodos_Tipo_Envio();">
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Agregar Tipo Envio</th>
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
                                    <td><input name="descripcion" type="text" class="cuadroTexto" id="descripcion" size="30" onchange="javscript:Capitaliza(this.id);"></td>
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
                            <legend class="txtResaltadoV">Envios </legend>
                            <table width="100%">
                                <tr>
                                    <th width="5%">#</th>
                                    <th width="90%">Descripción</th>
                                </tr>
                                <tbody id="tabla_tipo_envio" class="subTitulos">
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