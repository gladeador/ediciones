<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Cuentas_Base.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Cuenta Padre ::.</title>
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
            function agregar_cuenta_padre(){
                showhide_2('div_cuenta_padre', '');
                showhide_2('div_cuenta_hijo', 'none');
                Cuentas_Base.traerTodos_Padres(cargarEnviaPadre);
            }
            
            function agregar_cuenta_hijo(){
                showhide_2('div_cuenta_hijo', '');
                showhide_2('div_cuenta_padre', 'none');
                Cuentas_Base.buscar_Todas_Cuentas_final_N(cargarOptionPadre);
                Cuentas_Base.traerTodos_Hijos(cargarEnviaHijo);
                
            }
            
            function cargarOptionPadre(data){
                document.getElementById("cuenta_padre").options.length = 0;
                for(i=0;i<data.length;i++){
                    document.getElementById("cuenta_padre").options[i] = new Option(data[i].num_cuenta+" - "+data[i].descripcion,data[i].num_cuenta);
                }
            }
            
            function showhide_2(id, disp){
                obj = document.getElementById(id);
                obj.style.display = disp;
            }
            
            function valida_Padre(){
                var num_cuenta = document.formPadre.num_cuenta.value;
                Cuentas_Base.valida_Cuenta_Padre(num_cuenta, cargarValida);
            }
            
            function valida_Hijo(){
                var cuenta_padre = document.formHijo.cuenta_padre.value;
                var num_cuenta = document.formHijo.num_cuenta.value;
                Cuentas_Base.valida_Cuenta_Hijo(cuenta_padre, num_cuenta, cargarValida);
            }
            
            function cargarValida(data){
                if(data != null){
                    alert("El Numero de Cuenta ingresado, ya se encuentra en nuestros registros.\n"+data.num_cuenta+" "+data.descripcion);
                    document.formPadre.reset();
                    document.formHijo.reset();
                }
            }
            
            function valida_envia_padre(){
                var num_cuenta = document.formPadre.num_cuenta.value;
                if(num_cuenta == ""){
                    alert("Debe Ingresar un Nuemero de Cuenta");
                    return;
                }
                var num_cuenta = document.formPadre.num_cuenta.value;
                var descripcion = document.formPadre.descripcion.value;
                var tipo = document.formPadre.tipo.value;
                var cuentas = document.formPadre.cuentas.value;
                Cuentas_Base.agregar_Cuentas_Padre(num_cuenta, descripcion, tipo, cuentas, cargarEnviaPadre);
                alert("Cuenta cargada con exito!");
                document.formPadre.reset();
            }
            
            function valida_envia_hijo(){
                var num_cuenta = document.formHijo.num_cuenta.value;
                if(num_cuenta == ""){
                    alert("Debe Ingresar un Nuemero de Cuenta");
                    return;
                }
                var cuenta_padre = document.formHijo.cuenta_padre.value;
                var num_cuenta = document.formHijo.num_cuenta.value;
                var descripcion = document.formHijo.descripcion.value;
                var tipo = document.formHijo.tipo.value;
                var cuentas = document.formHijo.cuentas.value;
                Cuentas_Base.agregar_Cuentas_Hijo(cuenta_padre, num_cuenta, descripcion, tipo, cuentas, cargarEnviaHijo);
                alert("Cuenta cargada con exito!");
                document.formHijo.reset();
                Cuentas_Base.buscar_Todas_Cuentas_final_N(cargarOptionPadre);
            }
            
            function cargarEnviaPadre(data){
                var id;
                var cont = 1;
                var cellFuncs = [
                    function(data) { id++;
                        return data.num_cuenta; },
                    function(data) { return data.descripcion; },
                    function(data) { return data.tipo; },
                    function(data) { return data.cuentas; },
                    function(data) { return "<a href=\"javascript:ConfirmarPadre('"+data.num_cuenta+"');\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a>"}
                ]
                id=-1;
                
                dwr.util.removeAllRows("tabla_detalle", 
                { filter:function(tr) 
                    {return (tr.id != "pattern");}});
                dwr.util.addRows("tabla_detalle", data, cellFuncs,{ escapeHtml:false });
            }
            
            function cargarEnviaHijo(data){
                var id;
                var cont = 1;
                var cellFuncs = [
                    function(data) { id++;
                        return data.num_cuenta; },
                    function(data) { return data.descripcion; },
                    function(data) { return data.tipo; },
                    function(data) { return data.cuentas; },
                    function(data) { return "<a href=\"javascript:ConfirmarHijo('"+data.num_cuenta+"');\"><img src=\"<%=request.getContextPath()%>/images/iconos/eliminar.png\" border=\"0\" width=\"20\" height=\"20\" title=\"Eliminar\"></a>"}
                ]
                id=-1;
                
                dwr.util.removeAllRows("tabla_detalle", 
                { filter:function(tr) 
                    {return (tr.id != "pattern");}});
                dwr.util.addRows("tabla_detalle", data, cellFuncs,{ escapeHtml:false });
            }
            
            function ConfirmarPadre(num_cuenta){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Cuentas_Base.delete_Cuentas_Base(num_cuenta);
                alert("El Registro ha sido Eliminado con Exito");
                Cuentas_Base.traerTodos_Padres(cargarEnviaPadre);
            }
            
            function ConfirmarHijo(num_cuenta){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Cuentas_Base.delete_Cuentas_Base(num_cuenta);
                alert("El Registro ha sido Eliminado con Exito");
                Cuentas_Base.traerTodos_Padres(cargarEnviaHijo);
            }
            
        </script>

    </head>

    <body>
        <table width="100%">
            <tr>
                <th class="menuSuperior">Agregar Cuentas Base</th>
            </tr>
        </table>
        <br>
        <table align="center">
            <tr>
                <td><input name="radioTipo" type="radio" onclick="javascript:agregar_cuenta_padre();"</td>
                <th class="specalt">Cuenta Padre </th>
            </tr>
            <tr>
                <td><input name="radioTipo" type="radio" onclick="javascript:agregar_cuenta_hijo();"</td>
                <th class="specalt">Cuenta Hijo </th>
            </tr>
        </table>
        <table align="center" width="100%">
            <tr>
                <td>
                    <div id="div_cuenta_padre" style="display:none;">
                        <form name="formPadre">
                            <fieldset>
                                <legend class="txtResaltadoV">Datos de la Cuenta</legend>
                                <table>
                                    <tr>
                                        <th class="specalt" width="20%">Num Cuenta </th>
                                        <td><input name="num_cuenta" type="text" class="cuadroTexto" id="num_cuenta" value="" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="2" maxlength="2" onchange="javascript:valida_Padre();"></td>
                                    </tr>
                                    <tr>
                                        <th class="specalt">Descripcion </th>
                                        <td><input name="descripcion" type="text" class="cuadroTexto" id="descripcion_p" size="30" onchange="javscript:Capitaliza(this.id);"></td>
                                    </tr>
                                    <tr>
                                        <th class="specalt" width="20%">Tipo </th>
                                        <td>
                                            <select name="tipo" id="tipo" class="cuadroTexto">
                                                <option>Matriz</option>
                                                <option>Imputable</option>
                                            </select>
                                        </td>
                                        <th class="specalt" width="20%">Cuenta </th>
                                        <td>
                                            <select name="cuentas" id="cuentas" class="cuadroTexto">
                                                <option>Activo</option>
                                                <option>Ganancia</option>
                                                <option>Pasivo</option>
                                                <option>Patrimonio</option>
                                                <option>Perdida</option>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                                <br>
                                <table align="center">
                                    <tr>
                                        <td><input name="b_enviar" type="reset" id="b_enviar" class="botones" value="Limpiar"></td>
                                        <td><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:valida_envia_padre();" value="Agregar"></td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                    </div>
                    <div id="div_cuenta_hijo" style="display:none;">
                        <form name="formHijo">
                            <fieldset>
                                <legend class="txtResaltadoV">Datos de la Cuenta</legend>
                                <table>
                                    <tr>
                                        <th class="specalt" width="20%">Cuenta Padre </th>
                                        <td>
                                            <select name="cuenta_padre" id="cuenta_padre" class="cuadroTexto">
                                                <option></option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th class="specalt">Num Cuenta </th>
                                        <td><input name="num_cuenta" type="text" class="cuadroTexto" id="num_cuenta" value="" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="2" maxlength="2" onchange="javascript:valida_Hijo();"></td>
                                    </tr>
                                    <tr>
                                        <th class="specalt">Descripcion </th>
                                        <td><input name="descripcion" type="text" class="cuadroTexto" id="descripcion_h" size="30" onchange="javscript:Capitaliza(this.id);"></td>
                                    </tr>
                                    <tr>
                                        <th class="specalt" width="20%">Tipo </th>
                                        <td>
                                            <select name="tipo" id="tipo" class="cuadroTexto">
                                                <option>Matriz</option>
                                                <option>Imputable</option>
                                            </select>
                                        </td>
                                        <th class="specalt" width="20%">Cuenta </th>
                                        <td>
                                            <select name="cuentas" id="cuentas" class="cuadroTexto">
                                                <option>Activo</option>
                                                <option>Ganancia</option>
                                                <option>Pasivo</option>
                                                <option>Patrimonio</option>
                                                <option>Perdida</option>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                                <br>
                                <table align="center">
                                    <tr>
                                        <td><input name="b_enviar" type="reset" id="b_enviar" class="botones" value="Limpiar"></td>
                                        <td><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:valida_envia_hijo();" value="Agregar"></td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <fieldset>
                        <table width="100%">
                            <tr>
                                <th width="20%">Num Cuenta</th>
                                <th width="50%">Descripcion</th>
                                <th width="15%">Tipo</th>
                                <th width="15%">Cuentas</th>
                            </tr>
                            <tbody id="tabla_detalle" class="cuadroTexto">
                                <tr class="cuadroTexto">
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
    </body>
</html>