<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Perfil.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Perfil ::.</title>
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
            function traerTodos_Perfil(){
                Perfil.traer_todos(cargarPerfil_Envio);
            }
            
            function valida_envia(){
                var descripcion = document.getElementById("descripcion").value;
                if(descripcion == ""){
                    alert("Debe ingresar una descripción para el Perfil");
                    return;
                }
                document.formData.reset();
                Perfil.agregar_Perfil(descripcion, cargarAgregar_Perfil);
            }
            
            function cargarAgregar_Perfil(data){
                if(data == "ok"){
                    alert("Exito al Agregar el Perfil");
                    traerTodos_Perfil();
                }else{
                    alert("Ha ocurrido un Error, por favor vuelva a intentarlo");
                }
            }
            
            function cargarPerfil_Envio(data){
                dwr.util.useLoadingMessage("Cargando...");
                var id;
                var cont = 1;
                var cellFuncs = [
                    function(data) { id++;
                        return cont++; },
                    function(data) { return data.descripcion; },
                    function(data) { return "<a href=\"javascript:Confirmar("+data.id_perfil+");\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a>"}
                ]
                id=-1;
                
                dwr.util.removeAllRows("tabla_perfil", 
                { filter:function(tr) 
                    {return (tr.id != "pattern");}});
                dwr.util.addRows("tabla_perfil", data, cellFuncs,{ escapeHtml:false });
            }
            
            function Confirmar(id_perfil){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Perfil.eliminar_Perfil(id_perfil);
                    alert("El Registro ha sido Eliminado con Exito");
                traerTodos_Perfil();
            }
        </script>

    </head>

    <body onload="javascript:traerTodos_Perfil();">
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Agregar Perfil</th>
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
                            <legend class="txtResaltadoV">Perfils </legend>
                            <table width="100%">
                                <tr>
                                    <th width="5%">#</th>
                                    <th width="90%">Descripción</th>
                                </tr>
                                <tbody id="tabla_perfil" class="subTitulos">
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