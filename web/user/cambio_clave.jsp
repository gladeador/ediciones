<%@page import="cl.ediciones.model.Usuarios"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>

<%//No guarda los .dbs en los temporales.
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<html>
    <head>
        <title>Cambio Clave</title>
        <link rel="icon" href="<%=request.getContextPath()%>/images/escudo.ico" type="image/x-icon" />
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/ediciones.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/tablas.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>

        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jssha256/SHA256.js"></script>

        <script language="JavaScript">
            noF5();
        </script>

        <script>
            function Enviar(){
                var clave1 = document.formData.clave.value;
                var clave2 = document.formData.viejaclave2.value;
                var clave3 = document.formData.nuevaclave2.value;
                var clave4 = document.formData.repitenuevaclave2.value;
                
                if(clave1 != "" && clave2 != "" && clave3 != "" && clave4 != ""){
                    clave2 = SHA256(document.formData.viejaclave2.value);
                    clave3 = SHA256(document.formData.nuevaclave2.value);
                    clave4 = SHA256(document.formData.repitenuevaclave2.value);
                    
                    if(clave1 == clave2){
                        if(clave2 == clave3){
                            alert("La nueva clave es identica a la clave original, debe ser distinta");
                            document.formData.nuevaclave2.value = "";
                            document.formData.repitenuevaclave2.value = "";
                            document.formData.nuevaclave2.focus();
                            return;
                        }
                        else{
                            if(clave3 == clave4){
                                document.formData.viejaclave.value = clave2;
                                document.formData.nuevaclave.value = clave3;
                                document.formData.repitenuevaclave.value = clave4;
                                document.formData.submit();
                            }
                            else{
                                alert("La clave ingresada NO es igual a la clave anterior");
                                document.formData.nuevaclave2.value = "";
                                document.formData.repitenuevaclave2.value = "";
                                return;
                            }
                        }
                    }
                    else{
                        alert("La clave ingresada NO corresponde a este usuario");
                        Limpiar();
                    }
                }
                else{
                    alert("La clave ingresada esta en Blanco");
                    Limpiar();
                }
            }
            
            function Limpiar(){
                document.formData.reset();
            }
            
            function Valida(){
                var clave = document.formData.nuevaclave2.value;
                var clave2 = document.formData.repitenuevaclave2.value;
                var caracter = "qwertyuiopñlkjhgfdsazxcvbnmQWERTYUIOPÑLKJHGFDSAZXCVBNM";
                var num = "1234567890";
                
                var contNum = 0;
                var contLet = 0;
                
                if(clave == clave2){
                    for(i=0;i<clave.length;i++){
                        for(x=0;x<caracter.length;x++){
                            if(clave.charAt(i)==caracter.charAt(x)){
                                contLet++;
                            }
                        }
                        
                        for(y=0;y<num.length;y++){
                            if(clave.charAt(i)==num.charAt(y)){
                                contNum++;
                            }
                        }
                    }
                    var total = contLet+contNum;
                    
                    if(contLet == 0 || contNum == 0){
                        alert("La Clave Ingresada es inválida!\nLa Clave debe ser Alfanumerica");
                        document.formData.nuevaclave2.value = "";
                        document.formData.repitenuevaclave2.value = "";
                        document.formData.nuevaclave2.focus();
                        return;
                    }
                    else if(total != clave.length){
                        alert("La Clave Ingresada es inválida!");
                        document.formData.nuevaclave2.value = "";
                        document.formData.repitenuevaclave2.value = "";
                        document.formData.nuevaclave2.focus();
                        return;
                    }
                    else{
                        Enviar();
                    }
                }
                else{
                    alert("La clave ingresada NO es igual a la clave anterior");
                    document.formData.nuevaclave2.value = "";
                    document.formData.repitenuevaclave2.value = "";
                    return;
                }
            }
        </script>

    </head>

    <%
            HttpSession sesion = request.getSession();
            Usuarios user = (Usuarios) sesion.getAttribute("usuario");
    %>

    <body oncontextmenu="return false" onload="javascript:Limpiar();">
        <form name="formData" method="post" action="<%=request.getContextPath()%>/controller/CambioClave.ed">            
            <table height="100%" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="middle">
                        <fieldset>
                            <table align="center">
                                <tr>
                                    <th align="center" class="menuSuperior">Cambio de Clave</th>
                                </tr>
                                <tr>
                                    <th class="txtNormalNegro">Confirme sus datos: </th>
                                </tr>
                            </table>
                            <table>
                                <tr>
                                    <td>
                                        <table align="center">
                                            <tr>
                                                <td><img src="<%=request.getContextPath()%>/images/llaves.jpg"></td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td>
                                        <table>
                                            <tr>
                                                <td colspan="3" class="txtNormalNegro">Don(&ntilde;a): <%=user.getNombres()%>, Rut <%=user.getRut()%>
                                                    <input name="rut" type="hidden" value="<%=user.getRut()%>">
                                                    <input class="cuadroTexto" type="hidden" name="clave" value="<%=user.getClave()%>">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="txtNormalNegro" align="right">Contrase&ntilde;a Actual: </td>
                                                <td>
                                                    <input name="viejaclave2" type="password" class="txtNormalNegro" size="8" maxlength="8" autocomplete="off">
                                                    <input name="viejaclave" type="hidden" id="viejaclave" value="">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="txtNormalNegro" align="right">Nueva Contrase&ntilde;a: </td>
                                                <td>
                                                    <input class="txtNormalNegro" type="password" name="nuevaclave2" style="text-transform:lowercase" maxlength="8" size="8" autocomplete="off">
                                                    <input name="nuevaclave" type="hidden" id="nuevaclave" value="" style="text-transform:lowercase">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="txtNormalNegro" align="right">Repita Contrase&ntilde;a: </td>
                                                <td>
                                                    <input class="txtNormalNegro" type="password" name="repitenuevaclave2" style="text-transform:lowercase" maxlength="8" size="8" autocomplete="off">
                                                    <input name="repitenuevaclave" type="hidden" id="repitenuevaclave" value="" style="text-transform:lowercase">
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <br>
                        <table align="center">
                            <tr>
                                <th><a href="javascript:window.history.go(-1);" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/volver.png" border="0" width="30" height="30" title="Volver"></a></th>
                                <th><a href="javascript:Limpiar();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/limpiar.png" border="0" width="30" height="30" title="Limpiar"></a></th>
                                <th><a href="javascript:Valida();" target="_self"><img src="<%=request.getContextPath()%>/images/iconos/enviar.png" border="0" width="30" height="30" title="Enviar"></a></th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
