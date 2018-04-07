<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Comuna.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Comuna ::.</title>
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
            function traerTodos_Comuna(){
                Comuna.traer_todos(cargarComuna_Envio);
            }
            
            function valida_envia(){
                var descripcion = document.getElementById("descripcion").value;
                var id_region = document.getElementById("id_region").value;
                if(descripcion == ""){
                    alert("Debe ingresar una descripción para la Comuna");
                    return;
                }
                document.formData.reset();
                Comuna.agregar_Comuna(descripcion, id_region, cargarAgregar_Comuna);
            }
            
            function cargarAgregar_Comuna(data){
                if(data == "ok"){
                    alert("Exito al Agregar la Comuna");
                    traerTodos_Comuna();
                }else{
                    alert("Ha ocurrido un Error, por favor vuelva a intentarlo");
                }
            }
            
            function cargarComuna_Envio(data){
                dwr.util.useLoadingMessage("Cargando...");
                var id;
                var cont = 1;
                var cellFuncs = [
                    function(data) { id++;
                        return cont++; },
                    function(data) { return data.region.descripcion; },
                    function(data) { return data.descripcion; },
                    function(data) { return "<a href=\"javascript:Confirmar("+data.id_comuna+");\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a>"}
                ]
                id=-1;
                
                dwr.util.removeAllRows("tabla_comuna", 
                { filter:function(tr) 
                    {return (tr.id != "pattern");}});
                dwr.util.addRows("tabla_comuna", data, cellFuncs,{ escapeHtml:false });
            }
            
            function Confirmar(id_comuna){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Comuna.eliminar_Comuna(id_comuna);
                alert("El Registro ha sido Eliminado con Exito");
                traerTodos_Comuna();
            }
        </script>

    </head>

    <body onload="javascript:traerTodos_Comuna();">
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Agregar Comuna</th>
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
                                    <td><input name="descripcion" type="text" class="cuadroTexto" id="descripcion" size="30" onblur="javscript:this.value = this.value.toUpperCase();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Region </th>
                                    <td valign="top">
                                        <select name="id_region" id="id_region" class="cuadroTexto">
                                            <%
                                                RegionDAO regDAO = new RegionDAO();
                                                ArrayList lista_regiones = regDAO.traerRegion();
                                                Iterator r = lista_regiones.iterator();
                                                while (r.hasNext()) {
                                                    Region region = (Region) r.next();
                                            %>
                                            <option value="<%=region.getId_region()%>"><%=region.getDescripcion()%></option>
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
                            <legend class="txtResaltadoV">Comunas </legend>
                            <table width="100%">
                                <tr>
                                    <th width="5%">#</th>
                                    <th width="40%">Region</th>
                                    <th width="50%">Comuna</th>
                                </tr>
                                <tbody id="tabla_comuna" class="subTitulos">
                                    <tr>
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