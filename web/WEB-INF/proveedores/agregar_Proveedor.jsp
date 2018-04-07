<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="cl.ediciones.model.*" %>
<%@page import="cl.ediciones.model.dao.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:: Agregar Proveedores ::.</title>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="expires" content="-1">

        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/Comuna.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/ediciones.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/estilos/tablas.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsUtil/jsUtil.js"></script>

        <script language="JavaScript">
            noF5();
        </script>
        <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
        <script>
            $(document).ready(function () {
                $('#formData').validate({
                    'rules': {
                        'dv': 'required',
                        'nombre': 'required',
                        'giro': 'required',
                        'direccion_particular': 'required',
                        'nombre_contacto': 'required',
                        'desc_corta': 'required',
                        'nombre_contacto': 'required',
                        'email': {
                            'required': false,
                            'email': true
                        },
           /*             'ciudad': 'required',
                        'comments': {
                            'required': true,
                            'minlength': 10
                        },
                        'pais': 'required',
                        'id_region': 'required',
                        'id_comuna': 'required',*/
                        'id_nacionalidad': 'required',
                        'comments': {
                            'required': true,
                            'minlength': 10
                        },
                        'tipo_proveedor': 'required',
                        'comments': {
                            'required': true,
                            'minlength': 10
                        }
                    }
                });
            });
        </script>
        <script type="text/javascript">
            num = 1;
            function crear(obj) {
                fi = document.getElementById('fiel'); // 1
                contenedor = document.createElement('div'); // 2
                contenedor.id = 'div' + num; // 3
                fi.appendChild(contenedor); // 4

                ele = document.createElement('input'); // 5
                ele.type = 'text'; // 6
                ele.name = 'telefono_' + num; // 6
                contenedor.appendChild(ele); // 7

                img = document.createElement('img'); // 5
                img.src = '<%=request.getContextPath()%>/images/desactivar3.png'; // 6
                img.value = 'Borrar'; // 8
                img.name = 'div' + num; // 8
                img.onclick = function () {
                    borrar(this.name)
                } // 9
                contenedor.appendChild(img); // 7
                document.formData.num.value = num;
                num++;
            }
            function borrar(obj) {
                fi = document.getElementById('fiel'); // 1 
                fi.removeChild(document.getElementById(obj)); // 10
            }
        </script>

        <script>
            function revisarDV(rut_id, dv_id, next) {
                var rut = document.getElementById(rut_id).value;
                var dvr = validaDV(rut);

                var dv = document.getElementById(dv_id).value;
                if (dv == dvr) {
                    document.getElementById(next).focus();
                } else {
                    alert("Rut Ingresado, no es Valido");
                    document.getElementById(rut_id).value = "";
                    document.getElementById(dv_id).value = "";
                    document.getElementById(rut_id).focus();
                }
            }
        </script>


        <script>
            $(function () {

                $('.c_pais').hide();
                $('.c_ciudad').hide();
                $('.c_region').hide();
                $('.c_comuna').hide();

                $(document).ready(function ()
                {
                    $('#id_nacionalidad').change(function () {
                        if ($(this).val() == "1") {
                            $('.c_pais').hide();
                            $('.c_ciudad').hide();
                            $('.c_region').show();
                            $('.c_comuna').show();
                        } else if ($(this).val() == "2") {
                            $('.c_pais').show();
                            $('.c_ciudad').show();
                            $('.c_region').hide();
                            $('.c_comuna').hide();
                        } else {
                            $('.c_pais').hide();
                            $('.c_ciudad').hide();
                            $('.c_region').hide();
                            $('.c_comuna').hide();
                        }

                    });

                });
                /*         if ($('#id_nacionalidad').val().length > 0)
                 //$('#mostrar').show();
                 else
                 alert("2");
                 // $('#mostrar').hide();*/

            });
            function traer_Comuna() {
                var id_region = document.getElementById("id_region").value;
                Comuna.traer_todos_por_Region(id_region, cargar_Comunas);
            }
            function cargar_Comunas(data) {
                document.getElementById("id_comuna").options.length = 0;
                for (i = 0; i < data.length; i++) {
                    document.getElementById("id_comuna").options[i] = new Option(data[i].descripcion, data[i].id_comuna);
                }
            }
            function ValidaEmail() {
                var email = document.formData.email.value;
                var resp = emailCheck(email);
                if (resp != null) {
                    alert(resp);
                    document.formData.email.value = "";
                }
            }
        </script>  
        <script>
            function Limpia() {
                document.getElementById("formData").reset();
            }
        </script>

    </head>

    <body onLoad="Limpia()">
        <form name="formData" id="formData" action="<%=request.getContextPath()%>/controller/AgregarProveedor.ed">
            <table width="100%">
                <tr>
                    <th class="menuSuperior">Agregar Proveedor</th>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Datos del Proveedor</legend>
                            <table>
                                <tr>
                                    <th class="specalt"><div align="left">Rut <strong class="txtResaltadoR">:</strong></div></th>
                                    <td>
                                        <input name="rut" type="text" class="cuadroTexto" id="rut" onKeyUp="this.value = this.value.replace(/[^0-9]/, '');" size="8" />
                                        -
                                        <input name="dv" type="text" class="cuadroTexto" id="dv" size="1" maxlength="1" />
                                    </td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Nombre <strong class="txtResaltadoR">:</strong></div></th>
                                    <td><input name="nombre" type="text" class="cuadroTexto" id="nombre" size="40" onChange="this.value = this.value.toUpperCase();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Giro <strong class="txtResaltadoR">:</strong></div></th>
                                    <td><input name="giro" type="text" class="cuadroTexto" id="giro" onChange="this.value = this.value.toUpperCase();" size="40"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Dirección <strong class="txtResaltadoR">:</strong></div></th>
                                    <td><input name="direccion_particular" type="text" class="cuadroTexto" id="direccion_particular" size="40" onChange="this.value = this.value.toUpperCase();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Nacionalidad<strong class="txtResaltadoR">:</strong></div></th>
                                    <td><select name="id_nacionalidad" id="id_nacionalidad" class="cuadroTexto">
                                            <option class="prueba" value="0">---0---</option>
                                            <%
                                                NacionalidadDAO nacionalidadDao = new NacionalidadDAO();
                                                ArrayList lista_nacionalidad = nacionalidadDao.traerNacionalidad();
                                                Iterator p = lista_nacionalidad.iterator();
                                                while (p.hasNext()) {
                                                    Nacionalidad nacionalidad = (Nacionalidad) p.next();
                                            %>
                                            <option class="prueba" value="<%=nacionalidad.getId_nacionalidad()%>"><%=nacionalidad.getDescripcion()%></option>
                                            <%
                                                }
                                            %>
                                        </select></td>
                                </tr>

                                <tr class="c_pais">
                                    <th class="specalt"><div align="left" class="">Pais <strong class="txtResaltadoR">:</strong></div></th>
                                    <td><input name="pais" type="text" class="cuadroTexto" id="pais" onChange="this.value = this.value.toUpperCase();" size="40">  </td>

                                </tr>

                                <tr class="c_ciudad">
                                    <th class="specalt"><div align="left">Ciudad <strong class="txtResaltadoR">:</strong></div></th>
                                    <td><!--<select name="ciudad" id="ciudad" class="cuadroTexto">
                                            <option selected="selected" value="">--0--</option>
                                            <option value="I Regin">I Region</option>
                                            <option value="II Regin">II Regin</option>
                                            <option value="II Regin">II Regin</option>
                                            <option value="IV Regin">IV Regin</option>
                                            <option value="V Regin">V Regin</option>
                                            <option value="VI Regin">VI Regin</option>
                                            <option value="II Regin">VII Regin</option>
                                            <option value="Regin Metropolitana">Regin Metropolitana</option>
                                        </select>--><input name="ciudad" type="text" class="cuadroTexto" id="ciudad" onChange="this.value = this.value.toUpperCase();" size="40"></td>

                                </tr>
                                <tr class="c_region">
                                    <th class="specalt"><div align="left">Region <strong class="txtResaltadoR">:</strong></div></th>
                                    <td valign="top">
                                        <select name="id_region" id="id_region" class="cuadroTexto" onchange="javascript:traer_Comuna();">
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
                                <tr class="c_comuna">
                                    <th class="specalt"><div align="left">Comuna <strong class="txtResaltadoR">:</strong></div></th>
                                    <td> <select name="id_comuna" id="id_comuna" class="cuadroTexto">
                                            <option value=""></option>
                                        </select></td>

                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">E-Mail <strong class="txtResaltadoR">:</strong></div></th>
                                    <td><input name="email" type="text" class="cuadroTexto" id="email" size="40" maxlength="50"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Contacto <strong class="txtResaltadoR">:</strong></div></th>
                                    <td><input name="nombre_contacto" type="text" class="cuadroTexto" id="nombre_contacto" size="40" onChange="this.value = this.value.toUpperCase();"></td>
                                </tr>
                                <tr>
                                    <th class="specalt"><div align="left">Descripcion Corta <strong class="txtResaltadoR">:</strong></div></th>
                                    <td><input name="desc_corta" type="text" class="cuadroTexto" id="desc_corta" size="40" onChange="this.value = this.value.toUpperCase();"></td>
                                </tr>

                                <tr>
                                    <th class="specalt"><div align="left">Tipo Proveedor<strong class="txtResaltadoR">:</strong></div></th>
                                    <td><select name="tipo_proveedor" class="cuadroTexto">
                                            <option value="">---0---</option>
                                            <%
                                                TipoPoveedorDAO tipo_proveedorDAO = new TipoPoveedorDAO();
                                                ArrayList lista_tipo_proveedor = tipo_proveedorDAO.traerTipoProveedor();
                                                Iterator a = lista_tipo_proveedor.iterator();
                                                while (a.hasNext()) {
                                                    Tipo_Proveedor tipo_proveedor = (Tipo_Proveedor) a.next();
                                            %>
                                            <option value="<%=tipo_proveedor.getId_tipo_proveedor()%>"><%=tipo_proveedor.getDescripcion()%></option>
                                            <%
                                                }
                                            %>
                                        </select></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fieldset>
                            <legend class="txtResaltadoV">Telfonos de Contacto</legend>
                            <table>
                                <tr>
                                    <th class="specalt">Telfonos <strong class="txtResaltadoR">:</strong></th>
                                    <td><div id="fiel" >


                                            <input type="text" name="telefono_0" id="telefono" size="20" >
                                            <input type="hidden" name="num" id="telefono">
                                            <a href="javascript:crear(this)" ><img src="<%=request.getContextPath()%>/images/mostrar.png"></a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <th><input name="b_enviar" type="reset" id="b_enviar" class="botones" value="Limpiar"></th>
                    <th><input name="b_enviar" type="submit" id="b_enviar" class="botones" value="Agregar"></th>
                </tr>
            </table>
        </form>
    </body>
</html>