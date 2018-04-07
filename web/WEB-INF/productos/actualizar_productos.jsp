<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Productos ::.</title>
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
            function modificar(id_productos){
                document.formModificar.id_productos.value = id_productos;
                document.formModificar.submit();
            }
        </script>

        <script>
            function Confirmar(id_productos){
                if (!confirm("¿Esta seguro que desea Eliminar este Registro?")){
                    return;
                }
                Borrar(id_productos);
            }
        </script>

        <script>
            function Borrar(id_productos){
                document.formBorrar.id_productos.value = id_productos;
                document.formBorrar.submit();
                document.formBorrar.reset();
            }
        </script>

    </head>

    <body>
        <form name="formModificar" method="post" action="<%=request.getContextPath()%>/vistas/ModificarProductos.ed">
            <input name="id_productos" type="hidden">
        </form>

        <form name="formBorrar" method="post" action="<%=request.getContextPath()%>/controller/EliminarProductos.ed">
            <input name="id_productos" type="hidden">
        </form>

        <form name="formData">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Actualizar Productos</th>
                </tr>
            </table>
            <br>
            <table align="center" width="100%">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Listado de Productos</legend>
                            <table width="100%">
                                <tr>
                                    <th>Descripción</th>
                                    <th>Descripción Corta</th>
                                    <th>Valor Neto</th>
                                </tr>
                                <%
                                    ArrayList lista_productos = (ArrayList) request.getAttribute("lista_productos");
                                    Iterator i = lista_productos.iterator();
                                    while (i.hasNext()) {
                                        Productos productos = (Productos) i.next();
                                %>
                                <tr class="cuadroTexto">
                                    <td><%=productos.getDescripcion()%></td>
                                    <td><%=productos.getDesc_corta()%></td>
                                    <td align="right">$ <%=productos.getValor_neto()%></td>
                                    <th width="1" class="nobg"><a href="javascript:modificar(<%=productos.getId_productos()%>);"><img src="<%=request.getContextPath()%>/images/iconos/modificar.png" border="0" width="20" height="20" title="Modificar"></a></th>
                                    <th width="1" class="nobg" title="Solo puede eliminar Productos nuevos"><a href="javascript:Confirmar(<%=productos.getId_productos()%>);"><img src="<%=request.getContextPath()%>/images/iconos/eliminar.png" border="0" width="20" height="20" title="Eliminar"></a></th>
                                </tr>
                                <%
                                    }
                                %>
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>