<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Usuarios.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Modificar Usuarios ::.</title>
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
            function ValidaEmail(){                
                var email = document.formData.email.value;
                var resp = emailCheck(email);
                if(resp!=null){
                    alert(resp);
                    document.formData.email.value = "";
                }
            }
            
            function buscarUsuarios(rut){
                Usuarios.buscar_Usuarios(rut, cargarUsuarios);
            }
                
            function cargarUsuarios(data){
                dwr.util.setValues(data);
                document.formData.rut.value = data.rut;
            }
            
            function limpiar(rut){
                document.formData.reset();
                buscarUsuarios(rut);
            }
        </script>

    </head>

    <body onload="javascript:buscarUsuarios(<%=request.getParameter("rut")%>);" >
        <form name="formData" action="<%=request.getContextPath()%>/controller/ModificarUsuarios.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Modificar Usuarios</th>
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
                                        <input name="rut" type="text" id="rut" class="cuadroTexto" readonly="true" size="8" maxlength="8">
                                        -
                                        <input name="dv" type="text" id="dv" class="cuadroTexto" size="1" maxlength="1" readonly="true">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt">Nombre </th>
                                    <td><input name="nombres" type="text" id="nombres" class="cuadroTexto" size="30" onblur="javscript:Capitaliza(this.id);"></td>
                                </tr>
                                <tr>
                                    <th class="specalt">E-Mail </th>
                                    <td><input name="email" type="text" id="email" class="cuadroTexto" size="50" maxlength="50" onblur="javascript:ValidaEmail();"></td>
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
                                    <td><input name="observacion" type="text" id="observacion" class="cuadroTexto" size="30"></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <th><input name="b_enviar" type="button" id="b_enviar" class="botones" value="Limpiar" onclick="javscript:limpiar(rut.value);"></th>
                    <th><input name="b_enviar" type="submit" id="b_enviar" class="botones" value="Modificar"></th>
                </tr>
            </table>
        </form>
    </body>
</html>