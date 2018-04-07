<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Perfil_Menu.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Menu al Perfil ::.</title>
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
            function buscaMenus(id_perfil){
                for(i=0;ele=document.formData.elements[i]; i++){
                    if(ele.type=='checkbox') {
                        document.getElementById(ele.id).checked = false;
                    }
                }
                Perfil_Menu.traer_todos_por_perfil(id_perfil, cargarMenus);
            }
            
            function cargarMenus(data){
                if(data != null){
                    for(i=0;i<data.length;i++){
                        document.getElementById(data[i].id_menu).checked = true;
                    }
                }
            }
            
            function enviar(){
                var id_perfil = document.getElementById('id_perfil').value;
                if(id_perfil == ""){
                    alert("Debe seleccionar un Perfil para asignarle los Menus");
                    return;
                }
                for(i=0;ele=document.formData.elements[i]; i++){
                    if(ele.type=='checkbox') {
                        var id_menu = document.getElementById(ele.id).value;
                        if(document.getElementById(ele.id).checked == true){
                            Perfil_Menu.agregar_Menu(id_perfil, id_menu);
                        }else{
                            Perfil_Menu.eliminar_Menu(id_perfil, id_menu);
                        }
                    }
                }
                alert("Menus actualizados con Exito!");
            }
            
        </script>

    </head>

    <body>
        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Agregar Menu a Perfil</th>
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
                                    <th class="specalt">Perfil </th>
                                    <td><select name="id_perfil" class="cuadroTexto" id="id_perfil" onchange="javascript:buscaMenus(this.value);">
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
                            </table>
                            <br/>
                            <table>
                                <%
                                    MenuDAO menDAO = new MenuDAO();
                                    ArrayList lista_menu = menDAO.traer_todoMenu();
                                    Iterator m = lista_menu.iterator();
                                    while (m.hasNext()) {
                                        Menu menu = (Menu) m.next();
                                %>
                                <tr>
                                    <th class="specalt"><input name="<%=menu.getId_menu()%>" type="checkbox" class="cuadroTexto" id="<%=menu.getId_menu()%>" value="<%=menu.getId_menu()%>" /></th>
                                    <td class="cuadroTexto"><%=menu.getId_menu()%></td>
                                </tr>
                                <%
                                    }
                                %>
                            </table>
                        </fieldset>
                        <br>
                        <table align="center">
                            <tr>
                                <th><input name="b_enviar" type="reset" id="b_enviar" class="botones" value="Limpiar"></th>
                                <th><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:enviar();" value="Agregar"></th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>