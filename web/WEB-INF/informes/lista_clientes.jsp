<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Ciudad.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Actualizar Clientes ::.</title>
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
            function traer_Ciudad(){
                var id_region = document.getElementById("id_region").value;
                if(id_region != ""){
                    Ciudad.traer_todos_por_Region(id_region, cargar_Ciudades);
                }
            }
            
            function cargar_Ciudades(data){
                document.getElementById("id_ciudad").options.length = 0;
                document.getElementById("id_ciudad").options[0] = new Option("Todas","");
                for(i=0,x=1;i<data.length;i++,x++){
                    document.getElementById("id_ciudad").options[x] = new Option(data[i].descripcion,data[i].id_ciudad);
                }
            }
            
        </script>

    </head>

    <body>
        <form name="formData" method="post" action="<%=request.getContextPath()%>/controller/ListaClientes.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Clientes</th>
                </tr>
            </table>
            <br/>
            <fieldset id="fieldset_filtro">
                <legend class="menuSuperior"><a href="javascript:showhide('tabla_filtro');">Filtro de Busqueda</a></legend>
                <table id="tabla_filtro" style="display:none;">
                    <tr>
                        <th class="specalt" valign="top">Region </th>
                        <td valign="top">
                            <select name="id_region" id="id_region" class="cuadroTexto" onchange="javascript:traer_Ciudad();">
                                <option value="">Todas</option>
                                <%
                                    RegionDAO regDAO = new RegionDAO();
                                    ArrayList lista_regiones = regDAO.traerRegion();
                                    Iterator r = lista_regiones.iterator();
                                    while (r.hasNext()) {
                                        Region region = (Region) r.next();
                                %>
                                <option value="<%=region.getId_region()%>"><%=region.getDescripcion()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th class="specalt">Ciudad </th>
                        <td valign="top">
                            <select name="id_ciudad" id="id_ciudad" class="cuadroTexto">
                                <option value="">Todas</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2"><center><input name="b_buscar" type="button" id="b_buscar" class="botones" onclick="javascript:document.formData.submit();" value="Buscar" /></center></th>
                    </tr>
                </table>
            </fieldset>
            <br/>
            <fieldset>
                <table width="100%">
                    <tr>
                        <th>Nombre</th>
                        <th>Contacto</th>
                        <th>E-MAIL</th>
                        <th width="70">Rut</th>
                        <th>Giro</th>
                        <th>Direccion Facturacion</th>
                        <th>Domicilio Particular</th>
                        <th>Telefonos</th>
                        <th>Ciudad</th>
                    </tr>
                    <%
                        lista_regiones = (ArrayList) request.getAttribute("lista_regiones");
                        ArrayList lista_clientes = (ArrayList) request.getAttribute("lista_clientes");
                        r = lista_regiones.iterator();
                        while (r.hasNext()) {
                            Region region = (Region) r.next();
                            Iterator c = lista_clientes.iterator();
                            while (c.hasNext()) {
                                Clientes clientes = (Clientes) c.next();
                                if (region.getId_region() == clientes.getCiudad().getRegion().getId_region()) {
                    %>
                    <tr class="cuadroTexto">
                        <th class="specalt" valign="top"><%=clientes.getNombre()%></th>
                        <td valign="top"><%=clientes.getNombre_contacto()%></td>
                        <td valign="top"><%=clientes.getEmail()%></td>
                        <td valign="top" align="right"><fmt:formatNumber type = "number" pattern = "##,###,###" ><%=clientes.getRut()%></fmt:formatNumber>-<%=clientes.getDv()%></td>
                        <td valign="top"><%=clientes.getGiro()%></td>
                        <td valign="top"><%=clientes.getDireccion_facturacion()%></td>
                        <td valign="top"><%=clientes.getDireccion_particular()%></td>
                        <td valign="top">
                            <table>
                                <%
                                    Fono_ContactoDAO fonDAO = new Fono_ContactoDAO();
                                    ArrayList lista_fonos = fonDAO.retrieveCliente_por_Rut(clientes.getRut());
                                    Iterator f = lista_fonos.iterator();
                                    while (f.hasNext()) {
                                        Fono_Contacto fono = (Fono_Contacto) f.next();
                                %>
                                <tr class="cuadroTexto">
                                    <td><%=fono.getFono()%></td>
                                </tr>
                                <%
                                    }
                                %>  
                            </table>
                        </td>
                        <td valign="top"><%=clientes.getCiudad().getDescripcion()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    <tr class="txtResaltadoR">
                        <th colspan="9"><%=region.getDescripcion()%></th>
                    </tr>
                    <%

                        }
                    %>
                </table>
            </fieldset>
            <br/>
            <table align="center" id="button_imprimir">
                <tr>
                    <th><input name="b_imprimir" type="button" id="b_imprimir" class="botones" onclick="javascript:imprimir()" value="Imprimir"></th>
                </tr>
            </table>
            <script>
                function imprimir(){
                    showhide('button_imprimir');
                    showhide('fieldset_filtro');
                    window.print();
                    showhide('fieldset_filtro');
                    showhide('button_imprimir');
                }
            </script>
        </form>
    </body>
</html>