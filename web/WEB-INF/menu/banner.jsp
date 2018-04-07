<center>
    <object style="visibility: visible;" id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" height="122" width="978" valign="top">
        <param name="movie" value="<%=request.getContextPath()%>/flash/banner.swf">
        <param name="quality" value="high">
        <param name="wmode" value="opaque">
        <param name="swfversion" value="8.0.35.0">
        <!-- Esta etiqueta param indica a los usuarios de Flash Player 6.0 r65 o posterior que descarguen la versi?n m?s reciente de Flash Player. Elim?nela si no desea que los usuarios vean el mensaje. -->
        <param name="expressinstall" value="Scripts/expressInstall.swf">
        <!-- La siguiente etiqueta object es para navegadores distintos de IE. Oc?ltela a IE mediante IECC. -->
        <!--[if !IE]>-->
        <object type="application/x-shockwave-flash" data="<%=request.getContextPath()%>/flash/banner.swf" height="122" width="978">
            <!--<![endif]-->
            <param name="quality" value="high">
            <param name="wmode" value="opaque">
            <param name="swfversion" value="8.0.35.0">
            <param name="expressinstall" value="/Scripts/expressInstall.swf">
            <!-- El navegador muestra el siguiente contenido alternativo para usuarios con Flash Player 6.0 o versiones anteriores. -->
            <div>
                <h4 class="subTitulos">El contenido de esta p?gina requiere una versi?n m?s reciente de Adobe Flash Player.</h4>
                <p><a href="http://www.adobe.com/go/getflashplayer;"><img src="<%=request.getContextPath()%>/images/flash.jpg" alt="Obtener Adobe Flash Player" width="60" height="60" title="Obtener Adobe Flash Player"></a></p>
            </div>
            <!--[if !IE]>-->
        </object>
        <!--<![endif]-->
    </object>
</center>