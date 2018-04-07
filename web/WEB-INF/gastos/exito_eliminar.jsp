<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script>
            function alerta(){
                alert("Exito al Eliminar el Gasto");
                document.formData.submit();            
            }
        </script>

    </head>

    <body onload="javascript:alerta();">
        <form name="formData" method="post" action="<%=request.getContextPath()%>/vistas/ActualizarGastos.ed"></form>
    </body>
</html>
