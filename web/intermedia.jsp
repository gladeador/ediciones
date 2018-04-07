<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*,cl.ediciones.model.dao.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Ediciones ::.</title>

        <script>
            function alerta(nombre, cuerpo){
                if(cuerpo == "S"){
                    document.formData.action = "<%=request.getContextPath()%>/cuerpo.ed";
                }else{
                    document.formData.action = "<%=request.getContextPath()%>/menuAdmin.ed";
                }
                alert("Bienvenido: "+nombre);
                document.formData.submit();            
            }
        </script>

    </head>

    <%
        HttpSession sesion = request.getSession();
        Usuarios user = (Usuarios) sesion.getAttribute("usuario");
        String cuerpo = request.getParameter("cuerpo");
    %>

    <body onload="javascript:alerta('<%=user.getNombres()%>','<%=cuerpo%>');">
        <form name="formData" method="post" action=""></form>
    </body>
</html>
