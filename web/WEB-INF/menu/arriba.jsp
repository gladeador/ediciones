<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
  <SCRIPT type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery.js"></SCRIPT>
    <SCRIPT type="text/javascript"  src="<%=request.getContextPath()%>/js/jqFancyTransitions.js"></SCRIPT>
    </head>
    
    <body>
        <div id="pnlISOPolicy" align="center"><span class="AjaxPanel">
                <DIV id="ftHolder" align="center">
                    <DIV id=ft>
                        <IMG alt="<i> ..:: R&V Ediciones ::..</i>" src="<%=request.getContextPath()%>/images/index/1.jpg"><A 
                            title=foto1 href="#"></A> 
                        <IMG alt="<i>Sistema R&V Ediciones</i>" src="<%=request.getContextPath()%>/images/index/2.jpg"><A 
                            title=foto2 href="#"></A>
                        <IMG alt="<i>TelÃ©fono (56 - 2) 681 6451 / contacto@edicionesryv.cl - edicionesryv@gmail.com</i>" src="<%=request.getContextPath()%>/images/index/3.jpg"><A 
                            title=foto3 href="#"></A>
                        <IMG alt="<i>Direcci&oacute;n Web: http://www.edicionesryv.cl</i>" src="<%=request.getContextPath()%>/images/index/4.jpg"> <A 
                            title=foto4 href="#"></A>
                    </DIV>
                </DIV>
                <SCRIPT type="text/javascript"> $('#ft').jqFancyTransitions({ navigation: false, links : true });</SCRIPT>
            </span>
        </div>
    </body>
</html>
