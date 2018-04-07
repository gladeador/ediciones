<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>

<html>
    <head>
        <title>.:: Ediciones ::.</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>
        
        <script language="JavaScript">
            noF5();
        </script>
        
    </head> 
    
    <style>
        .fondo {
            background-attachment: fixed;
            background-image: url(<%=request.getContextPath()%>/images/index/RV.JPG);
            background-repeat: no-repeat;
            background-position: center top;
            }
    </style>
    
    <body oncontextmenu="return false" topmargin="0" leftmargin="0" class="fondo" scroll="auto">
        <form name="formData">
            <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td></td>
                </tr>
            </table>
        </form>
    </body>
</html>