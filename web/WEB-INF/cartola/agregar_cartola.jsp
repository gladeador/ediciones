<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Cartola ::.</title>
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
            function valida_envia(){
                var abonos = document.getElementById("abonos").value;
                var cargos = document.getElementById("cargos").value;
                if(abonos == "" || cargos == ""){
                    alert("Los Campos de Abonos y Cargos, no pueden estar en Blanco.");
                    return;
                }
                if(abonos == "0" && cargos == "0"){
                    if (!confirm("No ha ingresado ni Cargos, ni Abonos\n¿Desea continuar sin ingresar al menos un Registro?")){
                        return;
                    }
                }
                document.formData.submit();
            }
            
        </script>       

    </head>

    <%
        HttpSession sesion = request.getSession();
        Fecha_Actual fecha = (Fecha_Actual) sesion.getAttribute("fecha_actual");
    %>

    <body>
        <form name="formData" action="<%=request.getContextPath()%>/controller/AgregarCartola.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Agregar Cartola</th>
                </tr>
            </table>
            <br>
            <fieldset>
                <legend class="txtResaltadoV">Cartola Cuenta Corriente</legend>
                <table>
                    <tr>
                        <th class="specalt" valign="top">Fecha Cartola </th>
                        <td valign="middle">
                            <input name="fecha" type="text" class="cuadroTexto" id="fecha" align="left" value="<%=fecha.getFecha_actualStr()%>" readonly>
                            <button onClick="javascript:displayCalendar(document.forms[0].fecha,'dd/mm/yyyy',this)" type="button"><img src="<%=request.getContextPath()%>/images/iconos/cal.png" border="0" width="20" height="20" title="Calendario"></button>
                        </td>
                    </tr>
                    <tr>
                        <th class="specalt">Descripcion </th>
                        <td><textarea name="descripcion" class="cuadroTexto" id="descripcion" cols="50"></textarea></td>
                    </tr>
                    <tr>
                        <th class="specalt">Documento </th>
                        <td><input name="documento" type="text" class="cuadroTexto" id="documento" /></td>
                    </tr>
                    <tr>
                        <th class="specalt">Abonos </th>
                        <td><input name="abonos" type="text" class="cuadroTexto" id="abonos" value="0" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" /></td>
                    </tr>
                    <tr>
                        <th class="specalt">Cargos </th>
                        <td><input name="cargos" type="text" class="cuadroTexto" id="cargos" value="0" onkeyup="this.value = this.value.replace (/[^0-9]/, '');" /></td>
                    </tr>
                </table>
            </fieldset>
            <br/>
            <table align="center">
                <tr>
                    <td align="center"><input name="b_enviar" type="reset" id="b_enviar" class="botones" value="Limpiar" /></td>
                    <td align="center"><input name="b_enviar" type="button" id="b_enviar" class="botones" onclick="javascript:valida_envia();" value="Agregar" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>