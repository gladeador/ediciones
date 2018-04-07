<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Clientes.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Comuna.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Clientes ::.</title>
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
            function revisarDV(rut_id, dv_id, next){
                var rut = document.getElementById(rut_id).value;
                var dvr = validaDV(rut);
                var dv = document.getElementById(dv_id).value;
                if(dv == dvr){
                    document.getElementById(next).focus();
                    buscarClientes(rut);
                }
                else{
                    alert("Rut Ingresado, no es Valido");
                    document.getElementById(rut_id).value = "";
                    document.getElementById(dv_id).value = "";
                    document.getElementById(rut_id).focus();
                }
            }
            
            function traer_Comuna(){
                var id_region = document.getElementById("id_region").value;
                Comuna.traer_todos_por_Region(id_region, cargar_Comunas);
            }
            
            function cargar_Comunas(data){
                document.getElementById("id_comuna").options.length = 0;
                for(i=0;i<data.length;i++){
                    document.getElementById("id_comuna").options[i] = new Option(data[i].descripcion,data[i].id_comuna);
                }
            }
            
            function valida_envia(){
                var rut = document.getElementById("rut").value;
                var dv = document.getElementById("dv").value;
                if(rut == "" || dv == ""){
                    alert("El Campo Rut, es obligatorio");
                }
                else{
                    document.formData.submit();
                }
            }
            
            function buscarClientes(rut){
                Clientes.buscar_Clientes(rut, cargarClientes);
            }
                
            function cargarClientes(data){
                if(data != null){
                    dwr.util.setValues(data);
                    alert("Este Cliente ya ha sido ingresado con anterioridad. \nEn Actualiza, puede administrar los datos de un Cliente ya ingresado.");
                    document.formData.reset();
                }
            }
            
            function ValidaEmail(){                
                var email = document.formData.email.value;
                var resp = emailCheck(email);
                if(resp!=null){
                    alert(resp);
                    document.formData.email.value = "";
                }
            }
        </script>       

    </head>

    <body onload="javascript:traer_Comuna();">
        <form name="formData" action="<%=request.getContextPath()%>/controller/AgregarClientes.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Agregar Clientes</th>
                </tr>
            </table>
            <br>
            <table align="center" width="100%">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Datos del Cliente</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Rut </th>
                                    <td>
                                        <input name="rut" type="text" class="cuadroTexto" id="rut" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="8" maxlength="8">
                                        -
                                        <input name="dv" type="text" class="cuadroTexto" id="dv" size="1" maxlength="1"  onblur="javascript:revisarDV(rut.id, this.id, 'nombre');" onblur="javscript:Capitaliza(this.id);">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt">Nombre </th>
                                    <td><input name="nombre" type="text" class="cuadroTexto" id="nombre" size="40" onblur="this.value = this.value.toUpperCase();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt">Giro </th>
                                    <td><input name="giro" type="text" class="cuadroTexto" id="giro" onblur="this.value = this.value.toUpperCase();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt">Dirección </th>
                                    <td><input name="direccion_particular" type="text" class="cuadroTexto" id="direccion_particular" size="50" onblur="this.value = this.value.toUpperCase();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" valign="top">Region </th>
                                    <td valign="top">
                                        <select name="id_region" id="id_region" class="cuadroTexto" onchange="javascript:traer_Comuna();">
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
                                    <th class="specalt">Comuna </th>
                                    <td valign="top">
                                        <select name="id_comuna" id="id_comuna" class="cuadroTexto">
                                            <option value=""></option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt">E-Mail </th>
                                    <td><input name="email" type="text" class="cuadroTexto" id="email" size="50" maxlength="50" onblur="javascript:ValidaEmail();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt">Contacto </th>
                                    <td><input name="nombre_contacto" type="text" class="cuadroTexto" id="nombre_contacto" size="30" onblur="this.value = this.value.toUpperCase();"></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Teléfonos de Contacto</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Teléfonos </th>
                                    <td><input name="telefono_1" type="text" class="cuadroTexto" id="telefono_1" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="15" maxlength="15"></td>
                                </tr>
                                <tr>
                                    <td></d>
                                    <td><input name="telefono_2" type="text" class="cuadroTexto" id="telefono_2" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="15" maxlength="15"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input name="telefono_3" type="text" class="cuadroTexto" id="telefono_3" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="15" maxlength="15"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input name="telefono_4" type="text" class="cuadroTexto" id="telefono_4" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="15" maxlength="15"></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Datos de Facturación</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Rut de Facturación </th>
                                    <td>
                                        <input name="rut_facturacion" type="text" class="cuadroTexto" id="rut_facturacion" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="8" maxlength="8">
                                        -
                                        <input name="dv_facturacion" type="text" class="cuadroTexto" id="dv_facturacion" size="1" maxlength="1"  onblur="javascript:revisarDV_Fact(rut_facturacion.id, this.id, 'direccion_facturacion');">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt">Dirección de Facturación </th>
                                    <td><input name="direccion_facturacion" type="text" class="cuadroTexto" id="direccion_facturacion" size="50" onblur="this.value = this.value.toUpperCase();"></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="center">
                            <tr>
                                <td align="center"><input name="b_enviar" type="reset" id="b_enviar" class="botones" value="Limpiar"></td>
                                <td align="center"><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:valida_envia();" value="Agregar"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>