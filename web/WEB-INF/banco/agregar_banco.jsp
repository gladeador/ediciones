<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Banco.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Banco ::.</title>
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
            function traerTodos_Banco(){
                Banco.traer_todos(cargarBanco_Envio);
            }
            
            function valida_envia(){
                var descripcion = document.getElementById("descripcion").value;
                if(descripcion == ""){
                    alert("Debe ingresar una descripción para la Banco");
                    return;
                }
                document.formData.reset();
                Banco.agregar_Banco(descripcion, cargarAgregar_Banco);
            }
            
            function cargarAgregar_Banco(data){
                if(data == "ok"){
                    alert("Exito al Agregar la Banco");
                    traerTodos_Banco();
                }else{
                    alert("Ha ocurrido un Error, por favor vuelva a intentarlo");
                }
            }
            
            function cargarBanco_Envio(data){
                dwr.util.useLoadingMessage("Cargando...");
                var id;
                var cont = 1;
                var cellFuncs = [
                    function(data) { id++;
                        return cont++; },
                    function(data) { return data.descripcion; },
                    function(data) { return "<a href=\"javascript:Confirmar("+data.id_banco+");\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a>"}
                ]
                id=-1;
                
                dwr.util.removeAllRows("tabla_banco", 
                { filter:function(tr) 
                    {return (tr.id != "pattern");}});
                dwr.util.addRows("tabla_banco", data, cellFuncs,{ escapeHtml:false });
            }
            
            function Confirmar(id_banco){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Banco.eliminar_Banco(id_banco);
                    alert("El Registro ha sido Eliminado con Exito");
                traerTodos_Banco();
            }
        </script>

    </head>

    <body onload="javascript:traerTodos_Banco();">
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Agregar Banco</th>
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
                                    <td><input name="descripcion" type="text" class="cuadroTexto" id="descripcion" size="30" onblur="javscript:this.value = this.value.toUpperCase();" /></td>
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
                            <legend class="txtResaltadoV">Bancos </legend>
                            <table width="100%">
                                <tr>
                                    <th width="5%">#</th>
                                    <th width="90%">Descripción</th>
                                </tr>
                                <tbody id="tabla_banco" class="subTitulos">
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