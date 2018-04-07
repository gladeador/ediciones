/***************************/
//@Author: Adrian "yEnS" Mato Gondelle
//@website: www.yensdesign.com
//@email: yensamg@gmail.com
//@license: Feel free to use it, but keep this credits please!					
/***************************/
CurrPopup = '';
//loading popup with jQuery magic!
function loadPopup(popid){
	//loads popup only if it is disabled
	if($("#"+popid).attr('popupStatus')==0){
		$("#"+popid).attr('popupStatus',1);
		$("#"+$("#"+popid).attr('backgrounddivid')).css({
			"opacity": "0.7"
		});
		$("#"+$("#"+popid).attr('backgrounddivid')).fadeIn("slow");
		$("#"+popid).fadeIn("slow");
	    CurrPopup=popid;
	}
}

//disabling popup with jQuery magic!
function disablePopup(popid){
	//disables popup only if it is enabled
	if($("#"+popid).attr('popupStatus')==1){
		$("#"+popid).attr('popupStatus',0);
		$("#"+$("#"+popid).attr('backgrounddivid')).fadeOut("slow");
		$("#"+popid).fadeOut("slow");
	}
}

//centering popup
function centerPopup(popid){
	//request data for centering
	var windowWidth = Geometry.getViewportWidth();//document.documentElement.clientWidth;
	//if(windowWidth==0)$(body).width();
	var windowHeight = Geometry.getViewportHeight();//document.documentElement.clientHeight;
	//if(windowHeight==0)$(body).height();
	if(windowWidth==0)
	{
    windowWidth=1024;
	}
	
	if(windowHeight==0)
	{
    windowHeight=768;
	}
   $("#"+popid).show(); 
	var popupHeight = $("#"+popid).height();
	var popupWidth = $("#"+popid).width();
	$("#"+popid).hide();
	//centering
	var intTop = windowHeight/2-popupHeight/2;
	if(intTop <0)intTop=0;
	var intLeft = windowWidth/2-popupWidth/2;
	if(intLeft <0)intLeft=0;
	
	

	
	// alert([windowWidth,windowHeight,popupWidth,popupHeight]);
	
	$("#"+popid).css({
		"position": "absolute",
		"top": intTop,
		"left": intLeft,
		"height": popupHeight 
	});
	$(".popupClose").css({
		"position": "absolute",
		"right": '6px',
		"top": '4px'
	});
	//only need force for IE6
	
	$("#"+$("#"+popid).attr('backgrounddivid')).css({
		"height": windowHeight
	});
	
}


//CONTROLLING EVENTS IN jQuery
$(document).ready(function(){

Geometry = {};

if (window.screenLeft) { // IE and others
    Geometry.getWindowX = function( ) { return window.screenLeft; };
    Geometry.getWindowY = function( ) { return window.screenTop; };
}

else if (window.screenX) { // Firefox and others
    Geometry.getWindowX = function( ) { return window.screenX; };
    Geometry.getWindowY = function( ) { return window.screenY; };

}

if (window.innerWidth) { // All browsers but IE
    Geometry.getViewportWidth = function( ) { return window.innerWidth; };
    Geometry.getViewportHeight = function( ) { return window.innerHeight; };
    Geometry.getHorizontalScroll = function( ) { return window.pageXOffset;
};

    Geometry.getVerticalScroll = function( ) { return window.pageYOffset; };
}

else if (document.documentElement && document.documentElement.clientWidth) {
    // These functions are for IE 6 when there is a DOCTYPE
    Geometry.getViewportWidth =
        function( ) { return document.documentElement.clientWidth; };
    Geometry.getViewportHeight =
        function( ) { return document.documentElement.clientHeight; };
    Geometry.getHorizontalScroll =
        function( ) { return document.documentElement.scrollLeft; };
    Geometry.getVerticalScroll =
        function( ) { return document.documentElement.scrollTop; };
}

else if (document.body && document.body.clientWidth) {
    // These are for IE4, IE5, and IE6 without a DOCTYPE
    Geometry.getViewportWidth =
        function( ) { return document.body.clientWidth; };
    Geometry.getViewportHeight =
        function( ) { return document.body.clientHeight; };
    Geometry.getHorizontalScroll =
        function( ) { return document.body.scrollLeft; };
    Geometry.getVerticalScroll =
        function( ) { return document.body.scrollTop; };

}

// These functions return the size of the document. They are not window
// related, but they are useful to have here anyway.
if (document.documentElement && document.documentElement.scrollWidth) {
    Geometry.getDocumentWidth =
        function( ) { return document.documentElement.scrollWidth; };
    Geometry.getDocumentHeight =
        function( ) { return document.documentElement.scrollHeight; };
}

else if (document.body && document.body.scrollWidth) {
    Geometry.getDocumentWidth =
        function( ) { return document.body.scrollWidth; };
    Geometry.getDocumentHeight =
        function( ) { return document.body.scrollHeight; };

} 
	
	//CLOSING POPUP
	//Click the x event!
	$(".popupClose").click(function(){
		disablePopup(CurrPopup);
	});
	//Click out event!
	$(".popupBackground").click(function(){
		disablePopup(CurrPopup);
	});
	//Press Escape event!
	$(document).keypress(function(e){
		if(e.keyCode==27 && $("#"+CurrPopup).attr('popupStatus')==1){
			disablePopup(CurrPopup);
		}
	});

});


