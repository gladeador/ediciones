<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*,cl.ediciones.model.dao.*"%>

<link href="<%=request.getContextPath()%>/images/login_files/style.css" rel="Stylesheet">
<script src="<%=request.getContextPath()%>/images/login_files/jquery-1.3.2.js" language="javascript"></script>
<script language="javascript" src="<%=request.getContextPath()%>/images/login_files/Jpopup.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/openPass.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/validaRut.js"></script>
<SCRIPT type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery.js"></SCRIPT>
<SCRIPT type="text/javascript"  src="<%=request.getContextPath()%>/js/jqFancyTransitions.js"></SCRIPT>

<%//No guarda los .dbs en los temporales.
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<html>
    <head>
        <title>.:: Ediciones ::.</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/index/favicon.ico" /> 
        <link href="<%=request.getContextPath()%>/estilos/ediciones.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jssha256/SHA256.js"></script>

        <script>
            function valida_login(){
                var rut = document.formData.rut.value;
                var clave = document.formData.clave.value;
                
              
                if(rut == "" || clave == ""){
                    alert("Los Campos Rut o Clave, estan en blanco.");
                    location.href='index.jsp';
                    return;
                }
                document.formData.clave.value = SHA256(document.formData.clave.value);
                document.formData.submit();
            }
        </script>
    </head>

    <body>
        <table class="LoginBG" width="100%"  border="0" cellpadding="0" cellspacing="0">
            <tbody>
                <tr align="center" height="50px" valign="bottom">
                    <td class="loginNote"></td>
                </tr><tr align="center" valign="middle" height="50">
                    <td class="PAGETITLE" id="OrgLogoBlock" width="100%"></td>
                </tr>
                <tr align="center" valign="top">
                    <td height="260">
                        <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                            </tbody>
                            <tbody>
                                <tr valign="bottom" align="center">
                                    <td><table border="0" cellpadding="0" cellspacing="0" id="test">
                                            <tbody>
                                                <tr>
                                                    <td class="tl"><div class="bgcolor">&nbsp;</div></td>
                                                    <td class="tc"><div class="bgcolor">&nbsp;</div></td>
                                                    <td class="tr"><div class="bgcolor">&nbsp;</div></td>
                                                </tr>
                                                <tr>
                                                    <td class="ml"><div class="bgcolor">&nbsp;</div></td>
                                                    <td class="mc" style="width: 400px; height:100px"><div class="bgcolor">
                                                            <table border="0" cellpadding="0" cellspacing="0">
                                                                <tbody>
                                                                    <tr>
                                                                        <td align="left" width="114px"><img src="<%=request.getContextPath()%>/images/index/RV.JPG" width="120" height="103">
                                                                            <div align="center"><font size="1" face="Verdana, Arial, Helvetica, sans-serif">Sitema  De Gesti&oacute;n <br />
                                                                                R&V@2011</font></div></td>
                                                                        <td class="bsep"></td>
                                                                        <td align="center" width="278">
                                                                            <form name=formData action="<%=request.getContextPath()%>/controller/autentificar.ed" method="post">
                                                                                <table border="0" cellpadding="0" cellspacing="0">
                                                                                    <tbody>
                                                                                        <tr style="height:30px" valign="middle">
                                                                                            <th align="right"> <font class="LOGINLBL">
                                                                                                <label for="txtuserid">Usuario</label>
                                                                                                </font> </th>
                                                                                            <td style="padding-left:15px"><input type="text" name="rut" id="rut" maxlength="10" size="20" class="logintextbox" autocomplete="off" value="" onkeyup="this.value = this.value.replace (/[^0-9]/, '');"><input name="cuerpo" type="hidden" value="S"/></td>
                                                                                        </tr>
                                                                                        <tr style="height:30px" valign="middle">
                                                                                            <th align="right"> <font class="LOGINLBL">
                                                                                                <label for="txtpwd"> Password</label>
                                                                                                </font> </th>
                                                                                            <td style="padding-left: 15px"><input  type="password"  name="clave" id="clave" maxlength="50" size="20" class="logintextbox" autocomplete="off"></td>
                                                                                        </tr>
                                                                                        <tr style="height:36px" valign="middle">
                                                                                            <td colspan="2" align="right">
                                                                                                <input type="image" onClick="javascript:valida_login();" name="login" src="<%=request.getContextPath()%>/images/login_files/login_btn.png" alt="Click Aqui para Ingresar" title="Click Aqui para Ingresar" >
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr style="height:30px" valign="middle">
                                                                                            <th align="center" valign="bottom" colspan="2"> <a style="font-family:Verdana;font-size:10px;color:#404040;text-decoration:underline;" "onmouseover="status=&#39;&#39;; return true;" title="Ingrese Aqui y envie su correo Electronico" onMouseOut="status=&#39;&#39;" href="javascript:openPassword();"> Olvido Su  Password? </a> </th>
                                                                                        </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                            </form>
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div></td>
                                                    <td class="mr"><div class="bgcolor">&nbsp;</div></td>
                                                </tr>
                                                <tr>
                                                    <td class="bl"><div class="bgcolor"></div></td>
                                                    <td class="bc"><div class="bgcolor"></div></td>
                                                    <td class="br"><div class="bgcolor"></div></td>
                                                </tr>
                                            </tbody>
                                        </table></td>
                                </tr>
                                <tr align="center" valign="middle" width="100%" height="70px">
                                    <td id="prospClntBlock"></td>
                                </tr>
                                <tr align="center" height="110px" valign="middle">
                                    <td height="94"><p>&nbsp;</p>
                                        <p>&nbsp;</p></td>
                                </tr>
                            </tbody>
                            <tbody>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>

    </body>

</html>