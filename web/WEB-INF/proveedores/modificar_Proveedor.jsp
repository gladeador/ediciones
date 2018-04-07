<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Proveedor.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Fono_Proveedor.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Modificar Proveedor ::.</title>
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
                }
                else{
                    alert("Rut Ingresado, no es Valido");
                    document.getElementById(rut_id).value = "";
                    document.getElementById(dv_id).value = "";
                    document.getElementById(rut_id).focus();
                }
            }
        </script>
        <script>
            function limpia(){
               location.href='<%=request.getContextPath()%>/vistas/AgregarProveedor.ed';
            }
        </script>

        <script>
            function ValidaEmail(){                
                var email = document.formData.email.value;
                var resp = emailCheck(email);
                if(resp!=null){
                    alert(resp);
                    document.formData.email.value = "";
                }
            }
        </script>       

        <script>
            function buscarProveedor(rut){
                Proveedor.buscar_Proveedor(rut, cargarProveedor);
            }
                
            function cargarProveedor(data){
                dwr.util.setValues(data);
            }
        </script>       
        <script>
            function buscarProveedor(rut){
                Proveedor.buscar_Proveedor(rut, cargarProveedor);
            }
                
            function cargarProveedor(data){
                dwr.util.setValues(data);
                Fono_Proveedor.buscarProveedor_Fonos_por_Rut(data.rut, cargarFonos);
            }
            function cargarFonos(data){
                for(i = 0; i < data.length; i++){
                    alert(data[i].fono);
                    document.getElementById("telefono_"+i).value = data[i].fono;
                   
                   document.getElementById("cajVal_"+i).value = data[i].id;
                    //en=data[i].id;
                    crear(i);
                }
            }
        </script>  
        <script type="text/javascript">
            num=0;
            function crear(num) {
                fi = document.getElementById('fiel'); // 1
                contenedor = document.createElement('div'); // 2
                contenedor.id = 'div'+num; // 3
                fi.appendChild(contenedor); // 4

                ele = document.createElement('input'); // 5
                ele.type = 'text'; // 6
                ele.id='telefono_'+num;
                ele.name = 'telefono_'+num; // 6
                contenedor.appendChild(ele); // 7
                
                ele = document.createElement('input'); // 5
                ele.type = 'hidden'; // 6
                ele.id='cajVal_'+num;
                ele.name = 'cajVal_'+num; // 6
               // ele.value = en; // 6
                contenedor.appendChild(ele); // 7
                
                img = document.createElement('img'); // 5
                img.src = '<%=request.getContextPath()%>/images/desactivar3.png'; // 6
                img.value = 'Borrar'; // 8
                img.name = 'div'+num; // 8
                img.id = 'div'+num; // 8
                img.onclick = function () {borrar(this.name,img.id)} // 9
                contenedor.appendChild(img); // 7
                 
                document.formData.num.value = num;
              
                num++;
            }
           
            function borrar(obj,id) {
                en=obj.split("v");
                enDos=en[1];
       
                fono=document.getElementById("telefono_"+enDos).value;
                
                fi = document.getElementById('fiel'); // 1 
                fi.removeChild(document.getElementById(obj)); // 10
                alert("Se a Borrado el Numero de Telefono");
                rut=document.getElementById("rut").value;
          
                Fono_Proveedor.borraProveedor_Fonos(rut , fono);
               
            }
        </script>
    </head>
    <body onLoad="javascript:buscarProveedor(<%=request.getParameter("rut")%>);" >
        <form name="formData" action="<%=request.getContextPath()%>/controller/ModificarProveedor.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Modificar Proveedor </th>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <td><fieldset>
                            <legend class="txtResaltadoV">Datos del Proveedor</legend>
                            <table>
                                <tr>
                                    <th class="specalt"><div align="left">Rut <strong class="txtResaltadoR">:</strong></div></th>
                                <td><input name="rut" type="text" class="cuadroTexto" id="rut" onKeyUp="this.value = this.value.replace (/[^0-9]/, '');" size="8" />
                                    -
                                    <input name="dv" type="text" class="cuadroTexto" id="dv" size="1" maxlength="1" /></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Nombre <strong class="txtResaltadoR">:</strong></div></th>
                                <td><input name="nombre" type="text" class="cuadroTexto" id="nombre" size="40" onChange="javscript:Capitaliza(this.id);"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Giro <strong class="txtResaltadoR">:</strong></div></th>
                                <td><input name="giro" type="text" class="cuadroTexto" id="giro" onChange="javscript:Capitaliza(this.id);" size="40"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Dirección <strong class="txtResaltadoR">:</strong></div></th>
                                <td><input name="direccion_particular" type="text" class="cuadroTexto" id="direccion_particular" size="40" onChange="javscript:Capitaliza(this.id);"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Ciudad <strong class="txtResaltadoR">:</strong></div></th>
                                <td><select name="ciudad" id="ciudad" class="cuadroTexto">
                                        <option selected="selected" value="">--0--</option>
                                        <option value="I Regin">I Region</option>
                                        <option value="II Regin">II Regin</option>
                                        <option value="II Regin">II Regin</option>
                                        <option value="IV Regin">IV Regin</option>
                                        <option value="V Regin">V Regin</option>
                                        <option value="VI Regin">VI Regin</option>
                                        <option value="II Regin">VII Regin</option>
                                        <option value="Regin Metropolitana">Regin Metropolitana</option>
                                    </select></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Pais <strong class="txtResaltadoR">:</strong></div></th>
                                <td><input name="pais" type="text" class="cuadroTexto" id="pais" onChange="javscript:Capitaliza(this.id);" size="40"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">E-Mail <strong class="txtResaltadoR">:</strong></div></th>
                                <td><input name="email" type="text" class="cuadroTexto" id="email" size="40" maxlength="50"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Contacto <strong class="txtResaltadoR">:</strong></div></th>
                                <td><input name="nombre_contacto" type="text" class="cuadroTexto" id="nombre_contacto" size="40" onChange="javscript:Capitaliza(this.id);"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Descripcion Corta <strong class="txtResaltadoR">:</strong></div></th>
                                <td><input name="desc_corta" type="text" class="cuadroTexto" id="desc_corta" size="40" onChange="javscript:Capitaliza(this.id);"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Nacionalidad<strong class="txtResaltadoR">:</strong></div></th>
                                <td><select name="id_nacionalidad">
                                        <option selected="selected" value="">---0---</option>
                                        <option value="1">Nacional</option>
                                        <option value="2">Extranjero</option>
                                    </select></td>
                                </tr>
                                 <tr>
                                    <th class="specalt"><div align="left">Tipo Proveedor<strong class="txtResaltadoR">:</strong></div></th>
                                <td><select name="tipo_proveedor">
                                        <option selected="selected" value="">---0---</option>
                                        <option value="1">Productos</option>
                                        <option value="2">Gastos</option>
                                    </select></td>
                                </tr>
                            </table>
                        </fieldset></td>
                </tr>
                <tr>
                    <td><fieldset>
                            <legend class="txtResaltadoV">Telfonos de Contacto</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Telfonos <strong class="txtResaltadoR">:</strong></th>
                                    <td><div id="fiel" >
                                            <input type="text" name="telefono_0" id="telefono_0" size="20" >
                                            <input type="hidden" name="cajVal_0" id="cajVal_0" size="20" >
                                            <input type="hidden" name="num" id="telefono">
                                            <a href="javascript:crear(this)" ><img src="<%=request.getContextPath()%>/images/mostrar.png" alt=""></a> </div></td>
                                </tr>
                            </table>
                        </fieldset></td>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>  
                    <th><input name="b_enviar" type="reset" id="b_enviar" onClick="limpia()" class="botones" value="Limpiar"></th>
                    <th><input name="b_enviar" type="submit" id="b_enviar" class="botones" value="Modificar"></th>
                </tr>
            </table>
        </form>
    </body>
</html>