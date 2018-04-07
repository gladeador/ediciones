<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Boleta ::.</title>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/ediciones.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>

        <script language="JavaScript">
            noF5();
        </script>

    </head>

    <%
        Boleta boleta = (Boleta) request.getAttribute("Boleta");
    %>

    <body>
	
        <script language="JavaScript">
		alert("Los Datos Han Sido Cargados a la Boleta con Exito!");
		//window.history.go(-2);
                window.history.back();
             // location.href='WEB-INF/proveedores/agregar_Proveedor.jsp';
        </script>

	
       <!-- <form name="formData" action="">
          
        </form>-->
    </body>
</html>