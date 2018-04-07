<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Usuarios ::.</title>
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
            function ValidaEmail(){                
                var email = document.formData.email.value;
                var resp = emailCheck(email);
                if(resp!=null){
                    alert(resp);
                    document.formData.email.value = "";
                }
            }
            
            function valida_envia(){
                var rut = document.getElementById("rut").value;
                if(rut == ""){
                    alert("Debe ingresar un Rut valido");
                    return;
                }
                document.formData.submit();
            }
            
        </script>       

    </head>

    <body>
        <form name="formData" action="<%=request.getContextPath()%>/controller/AgregarUsuarios.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Agregar Usuarios</th>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Datos del Usuario</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Rut </th>
                                    <td>
                                        <input name="rut" type="text" class="cuadroTexto" id="rut" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" size="8" maxlength="8">
                                        -
                                        <input name="dv" type="text" class="cuadroTexto" id="dv" size="1" maxlength="1" onblur="javascript:revisarDV(rut.id, this.id, 'nombres');" onchange="javscript:Capitaliza(this.id);">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt">Nombre </th>
                                    <td><input name="nombres" type="text" class="cuadroTexto" id="nombres" size="30" onchange="javscript:Capitaliza(this.id);"></td>
                                </tr>
                                <tr>
                                    <th class="specalt" id="emailt">E-Mail </th>
                                    <td><input name="email" type="text" class="cuadroTexto" id="email" size="50" maxlength="50" onblur="javascript:ValidaEmail();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt">Perfil </th>
                                    <td><select name="id_perfil" class="cuadroTexto" id="id_perfil">
                                            <option value="">Seleccione un Perfil</option>
                                            <%
                                                PerfilDAO perDAO = new PerfilDAO();
                                                ArrayList lista = perDAO.traerPerfil();
                                                Iterator i = lista.iterator();
                                                while (i.hasNext()) {
                                                    Perfil perfil = (Perfil) i.next();
                                            %>
                                            <option value="<%=perfil.getId_perfil()%>"><%=perfil.getDescripcion()%></option>
                                            <%
                                                }
                                            %>
                                        </select></td>
                                </tr>
                                <tr>
                                    <th class="specalt">Observación </th>
                                    <td><input name="observacion" type="text" class="cuadroTexto" id="observacion" size="30"></td>
                                </tr>
                            </table>
                        </fieldset> 
                    </td>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <th><input name="b_enviar" type="reset" id="b_enviar" class="botones" value="Limpiar"></th>
                    <th><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:valida_envia();" value="Agregar"></th>
                </tr>
            </table>
        </form>
    </body>
</html>