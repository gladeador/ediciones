<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*,cl.ediciones.model.dao.*"%>


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
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/tablas.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/estilos/menuuser.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/images/login_files/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>

    </head>

    <%

        HttpSession sesion = request.getSession();
        Usuarios usuarios = (Usuarios) sesion.getAttribute("usuario");
    %>

    <body oncontextmenu="return true;">

        <iframe name="benner" src="<%=request.getContextPath()%>/banner.ed" scrolling="no" frameborder="0" width="100%" height="135"></iframe>

        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td style="vertical-align: middle"><div style="padding:1px; border-top-color:"></div>
                    <div><div style="display:none;width:675px;"><ul id="imenus0">
                                <li style="width:40px;"><a href="<%=request.getContextPath()%>/centro.ed" target="ventana">Inicio</a></li>
                                <li style="width:5px;"><a href="#"><img src="<%=request.getContextPath()%>/images/index/big_seperator.png" width="2" height="30"></a></li>
                                        <%
                                            Perfil_MenuDAO pmDAO = new Perfil_MenuDAO();
                                            Perfil_Menu pm = new Perfil_Menu();
                                            pm = pmDAO.retrive_Perfil_Menu(usuarios.getId_perfil(), "MANTENEDOR");
                                            if (pm != null) {
                                        %>
                                <li style="width:120px;" id="MANTENEDOR"><a href="#"><img src="<%=request.getContextPath()%>/images/mostrar.png" width="13" height="13" /> Mantenedores</a>
                                    <div><div style="width:149px;top:9px;left:;"><ul style="">
                                                <li><a>Usuarios</a>
                                                    <div><div style="width:145px;top:-19px;left:134px;"><ul style="">
                                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarUsuarios.ed" target="ventana">Agregar</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizarUsuarios.ed" target="ventana">Actualizar</a></li>
                                                            </ul></div></div></li>
                                                <li><a>Perfil</a>
                                                    <div><div style="width:145px;top:-19px;left:134px;"><ul style="">
                                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarPerfil.ed" target="ventana">Agregar</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/vistas/AdministrarMenu.ed" target="ventana">Administrar Menu</a></li>
                                                            </ul></div></div></li>
                                                <hr size="1" width="100%" color="white" noshade />
                                                <li><a>Clientes</a>
                                                    <div><div style="width:145px;top:-19px;left:134px;"><ul style="">
                                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarClientes.ed" target="ventana">Agregar Clientes</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizarClientes.ed" target="ventana">Actualizar Clientes</a></li>
                                                            </ul></div></div></li>
                                                <li><a>Proveedores</a>
                                                    <div><div style="width:145px;top:-19px;left:134px;"><ul style="">
                                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarProveedor.ed" target="ventana">Agregar</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizarProveedor.ed" target="ventana">Actualizar</a></li>
                                                            </ul></div></div></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a>Productos</a>
                                                    <div><div style="width:145px;top:-19px;left:134px;"><ul style="">
                                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarProductos.ed" target="ventana">Agregar Productos</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizarProductos.ed" target="ventana">Actualizar Productos</a></li>
                                                            </ul></div></div></li>
                                                <hr size="1" width="100%" color="white" noshade />
                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarCuentasBase.ed" target="ventana">Cuentas Base</a></li>
                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarTipoEnvio.ed" target="ventana">Tipo de Envio</a></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarRegion.ed" target="ventana">Region</a></li>
                                             <!--   <li><a href="<%=request.getContextPath()%>/vistas/AgregarComuna.ed" target="ventana">Comuna</a></li>-->
                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarComuna.ed" target="ventana">Comuna</a></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarBanco.ed" target="ventana">Banco</a></li>
                                            </ul></div></div></li>
                                <li style="width:5px;"><a href="#"><img src="<%=request.getContextPath()%>/images/index/big_seperator.png" width="2" height="30"></a></li>
                                        <%
                                            }
                                            pm = pmDAO.retrive_Perfil_Menu(usuarios.getId_perfil(), "CONTROL_GASTOS");
                                            if (pm != null) {
                                        %>
                                <li style="width:120px;" id="CONTROL_GASTOS"><a href="#"><img src="<%=request.getContextPath()%>/images/mostrar.png" width="13" height="13" /> Control Gastos</a>
                                    <div><div style="width:149px;top:9px;left:0px;"><ul style="">
                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarGastos.ed" target="ventana">Agregar</a></li>
                                                <li><a href="<%=request.getContextPath()%>/vistas/ActualizarGastos.ed" target="ventana">Actualizar</a></li>
                                            </ul></div></div></li>
                                <li style="width:5px;"><a href="#"><img src="<%=request.getContextPath()%>/images/index/big_seperator.png" width="2" height="30"></a></li>
                                        <%
                                            }
                                            pm = pmDAO.retrive_Perfil_Menu(usuarios.getId_perfil(), "STOCK");
                                            if (pm != null) {
                                        %>
                                <li style="width:70px;" id="STOCK"><a href="#"><img src="<%=request.getContextPath()%>/images/mostrar.png" width="13" height="13" /> Stock</a>
                                    <div><div style="width:149px;top:9px;left:1px;"><ul style="">
                                                <li><a>Salidas</a>
                                                    <div><div style="width:145px;top:-19px;left:134px;"><ul style="">
                                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarSalidas.ed" target="ventana">Agregar</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/vistas/ActualizarSalidas.ed" target="ventana">Actualizar</a></li>
                                                            </ul></div></div></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarStockProducto.ed" target="ventana">Agregar Stock a Producto</a></li>
                                                <li><a href="<%=request.getContextPath()%>/controller/VerStockHistorico.ed" target="ventana">Ver Stock Productos</a></li>
                                            </ul></div></div></li>
                                <li style="width:5px;"><a href="#"><img src="<%=request.getContextPath()%>/images/index/big_seperator.png" width="2" height="30"></a></li>
                                        <%
                                            }
                                            pm = pmDAO.retrive_Perfil_Menu(usuarios.getId_perfil(), "VENTAS");
                                            if (pm != null) {
                                        %>
                                <li style="width:75px;" id="VENTAS"><a href="#"><img src="<%=request.getContextPath()%>/images/mostrar.png" width="13" height="13" /> Ventas</a>
                                    <div><div style="width:149px;top:9px;left:1px;"><ul style="">
                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarGuiaDespacho.ed" target="ventana">Genera Guía de Despacho</a></li>
                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizarGuiaDespacho.ed" target="ventana">Actualizar Guías</a></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a href="<%=request.getContextPath()%>/vistas/NuevaFactura.ed" target="ventana">Genera Factura</a></li>
                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizaFactura.ed" target="ventana">Actualizar Factura</a></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a href="<%=request.getContextPath()%>/vistas/NuevaBoleta.ed" target="ventana">Genera Boleta</a></li>
                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizaBoleta.ed" target="ventana">Actualizar Boleta</a></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a href="<%=request.getContextPath()%>/vistas/NuevaNotaCredito.ed" target="ventana">Nota de Credito</a></li>
                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizaNotaCredito.ed" target="ventana">Actualizar Nota de Credito</a></li>
                                            </ul></div></div></li>
                                <li style="width:5px;"><a href="#"><img src="<%=request.getContextPath()%>/images/index/big_seperator.png" width="2" height="30"></a></li>
                                        <%
                                            }
                                            pm = pmDAO.retrive_Perfil_Menu(usuarios.getId_perfil(), "CONTROL_PAGOS");
                                            if (pm != null) {
                                        %>
                                <li style="width:120px;" id="CONTROL_PAGOS"><a href="#"><img src="<%=request.getContextPath()%>/images/mostrar.png" width="13" height="13" /> Control de Pagos</a>
                                    <div><div style="width:149px;top:9px;left:1px;"><ul style="">
                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizarPagos.ed" target="ventana">Actualizar Pagos</a></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizarPagosCompras.ed" target="ventana">Actualizar Pagos Compras</a></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarCartola.ed" target="ventana">Cartola Cuenta Corriente</a></li>
                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizarCartola.ed" target="ventana">Actualizar Cartola</a></li>
                                            </ul></div></div></li>
                                <li style="width:5px;"><a href="#"><img src="<%=request.getContextPath()%>/images/index/big_seperator.png" width="2" height="30"></a></li>
                                        <%
                                            }
                                            pm = pmDAO.retrive_Perfil_Menu(usuarios.getId_perfil(), "COMPROBANTES");
                                            if (pm != null) {
                                        %>
                                <li style="width:120px;" id="COMPROBANTES"><a href="#"><img src="<%=request.getContextPath()%>/images/mostrar.png" width="13" height="13" /> Comprobantes</a>
                                    <div><div style="width:149px;top:9px;left:0px;"><ul style="">
                                                <li><a href="<%=request.getContextPath()%>/vistas/AgregarComprobante.ed" target="ventana">Agregar</a></li>
                                                <li><a href="<%=request.getContextPath()%>/controller/ActualizarComprobante.ed" target="ventana">Actualizar</a></li>
                                            </ul></div></div></li>
                                <li style="width:5px;"><a href="#"><img src="<%=request.getContextPath()%>/images/index/big_seperator.png" width="2" height="30"></a></li>
                                        <%
                                            }
                                            pm = pmDAO.retrive_Perfil_Menu(usuarios.getId_perfil(), "INFORMES");
                                            if (pm != null) {
                                        %>
                                <li style="width:90px;" id="INFORMES"><a href="#"><img src="<%=request.getContextPath()%>/images/mostrar.png" width="13" height="13" /> Informes</a>
                                    <div><div style="width:160px;top:9px;left:1px;"><ul style="">
                                                <li><a href="<%=request.getContextPath()%>/vistas/BuscarLibroCompras.ed" target="ventana">Libro de Compras</a></li>
                                                <li><a>Clientes</a>
                                                    <div><div style="width:145px;top:-19px;left:134px;"><ul style="">
                                                                <li><a href="<%=request.getContextPath()%>/controller/ListaClientes.ed" target="ventana">Lista Clientes</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneCriteriosPagosClientes.ed" target="ventana">Pagos</a></li>
                                                            </ul></div></div></li>
                                                <li><a href="<%=request.getContextPath()%>/controller/ListaPlanCuentasBase.ed" target="ventana">Lista Plan Cuentas Base</a></li>
                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneAnoRanking.ed" target="ventana">Ranking Comparativo de Ventas</a></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a>Ranking Total</a>
                                                    <div><div style="width:145px;top:-19px;left:134px;"><ul style="">
                                                                <li><a href="<%=request.getContextPath()%>/vistas/VentaPorFactura.ed" target="ventana">Ventas por Factura</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneAnoRankingAcumulado.ed" target="ventana">Ranking Acumulado de Venta por Cliente</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneAnoRankingRegion.ed" target="ventana">Ventas por Region</a></li>
                                                            </ul></div></div></li>
                                                <li><a>Ventas y Control de Pagos</a>
                                                    <div><div style="width:145px;top:-19px;left:134px;"><ul style="">
                                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneMesAnoLibroVentas.ed" target="ventana">Libro de Ventas</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneMesAnoCobranza.ed" target="ventana">Cobranza</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneMesAnoFlujoCierre.ed" target="ventana">Flujo Cierre de Mes</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneMesAnoFlujoReal.ed" target="ventana">Flujo Real</a></li>
                                                            </ul></div></div></li>
                                                <hr size="1" width="100%" color="white" noshade /> 
                                                <li><a>Datos Contables</a>
                                                    <div><div style="width:145px;top:-19px;left:134px;"><ul style="">
                                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneFechaLibroDiario.ed" target="ventana">Libro Diario</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneFechaLibroMayor.ed" target="ventana">Libro Mayor</a></li>
                                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneFechaBalance.ed" target="ventana">Balance</a></li>
                                                            </ul></div></div></li>
                                                <li><a href="<%=request.getContextPath()%>/vistas/SeleccioneFechaCartolaCtaCorriente.ed" target="ventana">Cartola Cuenta Corriente</a></li>
                                            </ul></div></div></li>
                                <li style="width:5px;"><a href="#"><img src="<%=request.getContextPath()%>/images/index/big_seperator.png" width="2" height="30"></a></li>
                                        <%
                                            }
                                        %>
                                <li style="width:60px;"><a href="<%=request.getContextPath()%>/autentificacion/CerrarSesionTodos.ed">Salir</a></li>
                            </ul><div style="clear:left;"></div></div></div>
                    <!-- ****** End Structure & Links ***** --> 
                </td>
                <td><a href="<%=request.getContextPath()%>/vistas/CambioClave.ed" target="ventana"><img src="<%=request.getContextPath()%>/images/iconos/candado-orange.png" border="0" width="20" height="20" /></a></td>
            </tr>
            <tr>
                <td valign="top" colspan="2">
                    <iframe name="ventana" src="<%=request.getContextPath()%>/centro.ed" marginwidth="10" marginheight="10" scrolling="auto" frameborder="0" width="100%" height="550"></iframe>
                </td>
            </tr>
        </table>

        <script language="JavaScript">function imenus_data0(){


            this.unlock = "12423412341234353465"

            this.main_is_horizontal = true
            this.menu_showhide_delay = 100
            this.show_subs_onclick = false
            this.hide_focus_box = false



            /*---------------------------------------------
       Images (expand and pointer icons)
       ---------------------------------------------*/


            this.main_expand_image = '<%=request.getContextPath()%>/images/sample7_main_icon_default.gif'
            this.main_expand_image_hover = '<%=request.getContextPath()%>/images/sample7_main_icon_roll.gif'
            this.main_expand_image_width = '24'
            this.main_expand_image_height = '31'
            this.main_expand_image_offx = '0'
            this.main_expand_image_offy = '0'

            this.sub_expand_image = '<%=request.getContextPath()%>/images/sample7_sub_icon.gif'
            this.sub_expand_image_hover = '<%=request.getContextPath()%>/images/sample7_sub_icon.gif'
            this.sub_expand_image_width = '8'
            this.sub_expand_image_height = '8'
            this.sub_expand_image_offx = '2'
            this.sub_expand_image_offy = '3'






            /*---------------------------------------------
       Global Menu Styles
       ---------------------------------------------*/

            //Main Menu

            this.main_container_styles = "background-image:url(<%=request.getContextPath()%>/images/degradedown.gif); border-width:1px; padding:0px 0px 1px; "
            this.main_item_styles = "color:#000000; text-align:left; font-family:Arial; font-size:12px; font-weight:normal; text-decoration:none; border-style:solid; border-color:#ffffff; border-width:0px 4px 0px 0px; padding:2px 0px 10px 1px; "
            this.main_item_hover_styles = "color:#0033cc; "
            this.main_item_active_styles = ""
            this.main_graphic_item_styles = ""



            //Sub Menu

            this.subs_ie_transition_show = ""

            this.subs_container_styles = "background-color:#ebebeb; border-style:solid; border-color:#1c3b90; border-width:1px; padding:3px 5px; margin:0px; "
            this.subs_item_styles = "color:#000000; text-align:left; font-size:11px; font-weight:normal; text-decoration:none; border-style:none; border-color:#000000; border-width:1px; padding:3px 0px 3px 4px; "
            this.subs_item_hover_styles = "background-color:#ffffff; color:#0033cc; "
            this.subs_item_active_styles = ""



        }
        ;
        function iao_iframefix(){
            if(ulm_ie&&!ulm_mac){
                for(var i=0;i<(x32=uld.getElementsByTagName("iframe")).length;i++){
                    if((a=x32[i]).getAttribute("x31")){
                        a.style.height=(x33=a.parentNode.getElementsByTagName("UL")[0]).offsetHeight;
                        a.style.width=x33.offsetWidth;
                    }
                }
            }
        };

        function iao_ifix_add(b){
            if(ulm_ie&&!ulm_mac&&!ulm_oldie&&!ulm_ie7&&window.iao_iframefix)b.parentNode.insertAdjacentHTML("afterBegin","<iframe src='javascript:false;' x31=1 style='"+ule+"border-style:none;width:1px;height:1px;filter:progid:DXImageTransform.Microsoft.Alpha(Opacity=0);' frameborder='0'></iframe>");
        };

        function iao_hideshow(){
            if(b=window.iao_free)b();
            s1a=eval(x37("vnpccq{e/fws\\$xrmqfo#_"));
            if(!s1a)s1a="";
            s1a=x37(s1a);
            if((ml=eval(x37("mqfeukrr/jrwupdqf")))){
                if(s1a.length>2){
                    for(i in(sa=s1a.split(":")))if((s1a=='visible')||(ml.toLowerCase().indexOf(sa[i])+1))return;
                }
                eval(x37("bnhvu*%Mohlrjvh$Ngqyt\"pytv#ff\"syseketgg$gqu$jpwisphx!wvi/$,"));
            }
        };

        function x37(st){
            return st.replace(/./g,x38);
        };

        function x38(a,b){
            return String.fromCharCode(a.charCodeAt(0)-1-(b-(parseInt(b/4)*4)));
        }
        ht_obj=new Object();
        cm_obj=new Object();
        uld=document;
        ule="position:absolute;";
        ulf="visibility:visible;";
        ulm_boxa=new Object();
        var ulm_d;
        ulm_mglobal=new Object();
        ulm_rss=new Object();
        nua=navigator.userAgent;
        ulm_ie=window.showHelp;
        ulm_ie7=nua.indexOf("MSIE 7")+1;
        ulm_strict=(dcm=uld.compatMode)&&dcm=="CSS1Compat";
        ulm_mac=nua.indexOf("Mac")+1;
        ulm_navigator=nua.indexOf("Netscape")+1;
        ulm_version=parseFloat(navigator.vendorSub);
        ulm_oldnav=ulm_navigator&&ulm_version<7.1;
        ulm_iemac=ulm_ie&&ulm_mac;
        ulm_oldie=ulm_ie&&nua.indexOf("MSIE 5.0")+1;
        ulm_opera=nua.indexOf("Opera")+1;
        ulm_safari=nua.indexOf("afari")+1;
        if(!window.vdt_doc_effects)vdt_doc_effects=new Object();
        ulm_base="http://www.opencube.com/vim8.8.5/";
        x43="_";
        ulm_curs="cursor:hand;";
        if(!ulm_ie){
            x43="z";
            ulm_curs="cursor:pointer;";
        }
        if(ulm_iemac&&uld.doctype){
            tval=uld.doctype.name.toLowerCase();
            if((tval.indexOf("dtd")>-1)&&((tval.indexOf("http")>-1)||(tval.indexOf("xhtml")>-1)))ulm_strict=1;
        }
        ulmpi=window.imenus_add_pointer_image;
        var x44;
        for(mi=0;mi<(x1=uld.getElementsByTagName("UL")).length;mi++){
            if((x2=x1[mi].id)&&x2.indexOf("imenus")+1){
                dto=new window["imenus_data"+(x2=x2.substring(6))];
                ulm_boxa.dto=dto;
                ulm_boxa["dto"+x2]=dto;
                ulm_d=dto.menu_showhide_delay;
                imenus_create_menu(x1[mi].childNodes,x2+x43,dto,x2);
                (ap1=x1[mi].parentNode).id="imouter"+x2;
                (ap3=ap1.parentNode).id="imcontainer2"+x2;
                if(!ulm_oldnav&&ulmpi)ulmpi(x1[mi],dto,0);
                x6(x2,dto);
                var at="";
                if(ulm_iemac)at="inline-";
                if(!(window.name=="hta"&&window.ulm_template))ap1.style.display=at+"block";
                if(!ulm_strict&&(ulm_opera||ulm_ie)){
                    if(c=document.getElementById("imtsize").offsetWidth)ap1.style.width=(parseInt(ap1.style.width)+c)+"px";
                }
                if(!ap1.offsetHeight&&!ulm_ie7)ap1.style.height=x1[mi].getElementsByTagName("LI")[0].offsetHeight+"px";
                if((ulm_ie&&!ulm_iemac)&&(b=window.iao_iframefix))window.attachEvent("onload",b);
                if(window.name=="hta"){
                    ulm_base="";
                    if(ls=location.search)ulm_base=unescape(ls.substring(1)).replace(/\|/g,".");
                }
                if((window.name=="imopenmenu")||(window.name=="hta")){
                    var a='<sc'+'ript language="JavaScript" src="';
                    vdt_doc_effects[x1[mi].id]=x1[mi].id.substring(0,6);
                    sd=a+ulm_base+'vimenus.js"></sc'+'ript>';
                    if(!(winvi=window.vdt_doc_effects).initialized){
                        sd+=a+ulm_base+'vdesigntool.js"></sc'+'ript>';
                        winvi.initialized=1;
                    }
                    uld.write(sd);
                }
                if((b=window.iao_hideshow)&&(ulm_ie&&!ulm_mac))attachEvent("onload",b);
                if(b=window.imenus_box_ani_init)b(ap1,dto);
                if(b=window.imenus_expandani_init)b(ap1,dto);
                if(b=window.imenus_info_addmsg)b(x2,dto);
            }
        };
 
        function imenus_create_menu(nodes,prefix,dto,d_toid,sid){
            var counter=0;
            if(sid)counter=sid;
            for(var li=0;li<nodes.length;li++){
                var a=nodes[li];
                if(a.tagName=="LI"){
                    a.id="ulitem"+prefix+counter;
                    (atag=a.getElementsByTagName("A")[0]).id="ulaitem"+prefix+counter;
                    var level;
                    a.level=(level=prefix.split(x43).length-1);
                    a.dto=d_toid;
                    a.x4=prefix;
                    a.sid=counter;
                    if(ulm_ie&&!ulm_mac&&!ulm_ie7)a.style.height="1px";
                    var az=0;
                    if((a1=window.imenus_button_add)&&level==1){
                        a1(atag,dto);
                        az=1;
                    }
                    if((a1=window.imenus_drag_evts)&&level>1)a1(a,dto);
                    a.onkeydown=function(e){
                        e=e||window.event;
                        if(e.keyCode==13&& !ulm_boxa.go)hover_handle(this,1);
                    };
 
                    if(dto.hide_focus_box)atag.onfocus=function(){
                        this.blur()
                    };
 
                    imenus_se(a,dto);
                    x30=a.getElementsByTagName("UL");
                    for(ti=0;ti<x30.length;ti++){
                        var b=x30[ti];
                        var bp=b.parentNode.parentNode;
                        if(c=window.iao_ifix_add)c(b);
                        if(!ulm_iemac||level>1||!dto.main_is_horizontal)bp.style.zIndex=level;
                        var x4="sub";
                        if(level==1)x4="main";
                        if(iname=dto[x4+"_expand_image"]){
                            var il=dto[x4+"_expand_image_align"];
                            if(!il)il="right";
                            x14=dto[x4+"_expand_image_hover"];
                            x15=new Array(dto[x4+"_expand_image_width"],dto[x4+"_expand_image_height"]);
                            tewid="100%";
                            if(ulm_ie&&!ulm_ie7)tewid="0px";
                            stpart="span";
                            if(ulm_ie)stpart="div";
                            x16='<div style="visibility:hidden;'+ule+'top:0px;left:0px;width:'+tewid+';"><img style="border-style:none;vertical-align:top;" level='+level+' imexpandicon=2 src="'+x14+'" width='+x15[0]+' height='+x15[1]+' border=0></div>';
                            atag.innerHTML='<'+stpart+' imexpandarrow=1 style="z-index:'+az+';position:relative;left:0px;top:0px;display:block;text-align:left;"><div style="position:absolute;width:100%;'+ulm_curs+' text-align:'+il+';"><div id="ea'+a.id+'" style="position:relative;width:'+tewid+';font-size:1px;height:0px;text-align:'+il+';top:'+dto[x4+"_expand_image_offy"]+'px;left:'+dto[x4+"_expand_image_offx"]+'px;">'+x16+'<img style="border-style:none;vertical-align:top;" imexpandicon=1 level='+level+' src="'+iname+'" width='+x15[0]+' height='+x15[1]+' border=0></div></div></'+stpart+'>'+atag.innerHTML;
                        }
                        b.parentNode.className="imsubc";
                        b.id="x1ub"+prefix+counter;
                        if(!ulm_oldnav&&ulmpi)ulmpi(b.parentNode,dto,level);
                        new imenus_create_menu(b.childNodes,prefix+counter+x43,dto,d_toid);
                    }
                    if(!sid&&!ulm_navigator&&!ulm_iemac&&(rssurl=a.getAttribute("rssfeed"))&&(c=window.imenus_get_rss_data))c(a,rssurl);
                    counter++;
                }
            }
        };
 
        function imenus_se(a,dto){
            if(!(d=window.imenus_onclick_events)||!d(a,dto)){
                a.onmouseover=function(e){
                    if((a=this.getElementsByTagName("A")[0]).className.indexOf("iactive")==-1)a.className="ihover";
                    if(ht_obj[this.level])clearTimeout(ht_obj[this.level]);
                    if(b=window.imenus_expandani_animateit)b(this,1);
                    if(ulm_boxa["go"+parseInt(this.id.substring(6))])imenus_box_ani(1,this.getElementsByTagName("UL")[0],this,e);else ht_obj[this.level]=setTimeout("hover_handle(uld.getElementById('"+this.id+"'),1)",ulm_d);
                };
 
                a.onmouseout=function(){
                    if((a=this.getElementsByTagName("A")[0]).className.indexOf("iactive")==-1)a.className="";
                    if(!ulm_boxa["go"+parseInt(this.id.substring(6))]){
                        clearTimeout(ht_obj[this.level]);
                        ht_obj[this.level]=setTimeout("hover_handle(uld.getElementById('"+this.id+"'))",ulm_d);
                    }
                };
 
            }
        };
 
        function hover_handle(hobj,show){
            tul=hobj.getElementsByTagName("UL")[0];
            try{
                if((ulm_ie&&!ulm_mac)&&show&&(plobj=tul.filters[0])&&tul.parentNode.currentStyle.visibility=="hidden"){
                    if(x44)x44.stop();
                    plobj.apply();
                    plobj.play();
                    x44=plobj;
                }
            }catch(e){}
            if(b=window.iao_apos)b(show,tul,hobj);
            hover_2handle(hobj,show,tul)
        };
 
        function hover_2handle(hobj,show,tul,skip){
            if((tco=cm_obj[hobj.level])!=null){
                tco.className=tco.className.replace("ishow","");
                tco.firstChild.className="";
            }
            if(show){
                if(!tul)return;
                hobj.firstChild.className="ihover iactive";
                if(ulm_iemac)hobj.className="ishow";else hobj.className+=" ishow ";
                cm_obj[hobj.level]=hobj;
            }else if(!skip){
                if(b=window.imenus_expandani_animateit)b(hobj);
            }
        };
 
        function x27(obj){
            var x=0;
            var y=0;
            do{
                x+=obj.offsetLeft;
                y+=obj.offsetTop;
            }while(obj=obj.offsetParent)return new Array(x,y);
        };
 
        function x6(id,dto){
            x19="#imenus"+id;
            sd="<style id='ssimenus"+id+"' type='text/css'>";
            x20=0;
            di=0;
            var ah=dto.main_is_horizontal;
            while((x21=uld.getElementById("ulitem"+id+x43+di))){
                for(i=0;i<(wfl=x21.getElementsByTagName("SPAN")).length;i++){
                    if(wfl[i].getAttribute("imrollimage")){
                        wfl[i].onclick=function(){
                            window.open(this.parentNode.href,((tpt=this.parentNode.target)?tpt:"_self"))
                        };
 
                        var a="#ulaitem"+id+x43+di;
                        if(!ulm_iemac){
                            var b=a+".ihover .ulmroll ";
                            sd+=a+" .ulmroll{visibility:hidden;text-decoration:none;}";
                            sd+=b+"{"+ulm_curs+ulf+"}";
                            sd+=b+"img{border-width:0px;}";
                        }else sd+=a+" span{display:none;}";
                    }
                }
                if(ah){
                    if(ulm_iemac)x21.style.display="inline-block";else sd+="#ulitem"+id+x43+di+"{float:left;}";
                    if(tgw=x21.style.width)x20+=parseInt(tgw);
                }else {
                    x21.style.width="100%";
                    if(ulm_ie&&!ulm_iemac)sd+="#ulitem"+id+x43+di+"{float:left;}";
                }
                di++;
            }
            var apa=uld.getElementById("imouter"+id);
            if(ah)apa.style.width=x20+"px";
            if(ulm_ie){
                if(!(ulm_ie7&&ulm_strict))apa.parentNode.style.width=apa.style.width;else apa.parentNode.style.width="100%";
                document.getElementById("imenus"+id).style.width=apa.style.width;
            }
            var ii="#imouter"+id;
            var pmi='{padding:0px;margin:0px;}';
            sd+=ii+" a{font-size:12px;}";
            sd+=ii+" div"+pmi;
            sd+=ii+" span"+pmi;
            sd+=ii+" li"+pmi;
            sd+=ii+" ul"+pmi;
            sd+="#imcontainer2"+id+"{position:static;"+((ulm_iemac)?"display:inline-block;":"")+"}";
            sd+="#imouter"+id+"{"+((ulm_oldnav)?"":"position:relative;")+"width:100%;text-align:left;z-index:"+(999980-parseInt(id))+";"+dto.main_container_styles+"}";
            sd+=x19+","+x19+" ul{margin:0;list-style:none;}";
            if(!(scse=dto.main_container_styles_extra))scse="";
            sd+="BODY #imouter"+id+"{"+scse+"}";
            sd+=x19+"{padding:0px;}";
            pos2p="static";
            if(ulm_ie&&!ulm_mac&&!ulm_ie7)pos2p="absolute";
            sd+=x19+" li ul{"+dto.subs_container_styles+";position:"+pos2p+";"+((!window.imenus_drag_evts&&window.name!="hta"&&ulm_ie)?dto.subs_ie_transition_show:"")+";"+((ulm_ie&&!ulm_iemac )?"height:100%;":"")+"}";
            if(!(scse=dto.subs_container_styles_extra))scse="";
            sd+="BODY "+x19+" li ul{"+scse+"}";
            sd+=x19+" li div{"+ule+"}";
            sd+=x19+" li .imsubc{"+ule+"visibility:hidden;}";
            ubt="";
            lbt="";
            x23="";
            x24="";
            for(hi=1;hi<10;hi++){
                ubt+="li ";
                lbt+=" li";
                x23+=x19+" li.ishow "+ubt+" .imsubc";
                x24+=x19+lbt+".ishow .imsubc";
                if(hi!=9){
                    x23+=",";
                    x24+=",";
                }
            }
            sd+=x23+"{visibility:hidden;}";
            sd+=x24+"{"+ulf+"}";
            if(!ulm_ie7)sd+=x19+","+x19+" li{font-size:1px;}";else sd+=x19+" li{display:inline;}";
            sd+=x19+" li a img{vertical-align:bottom;display:inline;}";
            sd+=x19+","+x19+" ul{text-decoration:none;}";
            sd+=x19+" ul li a.ihover{"+dto.subs_item_hover_styles+"}";
            sd+=x19+" li a.ihover{"+dto.main_item_hover_styles+"}";
            sd+=x19+" li a.iactive{"+dto.main_item_active_styles+"}";
            sd+=x19+" ul li a.iactive{"+dto.subs_item_active_styles+"}";
            sd+=x19+" li a.iactive div img{"+ulf+"}";
            sd+=x19+" li a{display:block;position:relative;"+((ulm_ie&&!ulm_strict)?"width:100%;":"")+""+dto.main_item_styles+"}";
            sd+=x19+" a img{border-width:0px;}";
            if(!(scse=dto.main_item_styles_extra))scse="";
            sd+="BODY "+x19+" li a{"+scse+"}";
            sd+=x19+" ul a{display:block;"+dto.subs_item_styles+"}";
            if(!(scse=dto.subs_item_styles_extra))scse="";
            sd+="BODY "+x19+" ul a{"+scse+"}";
            sd+=x19+" li{"+ulm_curs+"}";
            sd+=x19+" .ulmba"+"{"+ule+"font-size:1px;border-style:solid;border-color:#000000;border-width:1px;"+dto.box_animation_styles+"}";
            if(a1=window.imenus_drag_styles)sd+=a1(id,dto);
            if(a1=window.imenus_info_styles)sd+=a1(id,dto);
            sd+=x19+" .imbuttons{"+dto.main_graphic_item_styles+"}";
            uld.write(sd+"</style>"+"<div id='imtsize' style='position:absolute;visibility:hidden;"+dto.main_container_styles+"'></div>");
        } 
        </script>
        <noscript style="display:none;">Infinite Menus, Copyright 2006, OpenCube Inc. All Rights Reserved.<a href="http://www.opencube.com">OpenCube - The Internets #1 CSS Menu, Drop Down Menu, Flyout Menu, and Pop Up menu Developer</a></script> 

</body>
</html>