Function.prototype.bind = function (obj) {
    var fn = this;
    return function () {
        var args = [this];
        for (var i = 0, ix = arguments.length; i < ix; i++) {
            args.push(arguments[i]);
        }
        return fn.apply(obj, args);
    };
};
String.prototype.Trim=function(){
    return this.replace(/^\s+/, "").replace(/\s+$/, "");
};
if(typeof AjaxDebug == 'undefined')AjaxDebug=false;
if(typeof gblMsgDivFlag == 'undefined')gblMsgDivFlag=true;
if(typeof objMsgDiv == 'undefined')var objMsgDiv;
if(typeof AjaxIframe == 'undefined')AjaxIframe=false;
if(typeof AjaxImagePath == 'undefined')AjaxImagePath=false;
AjaxRequest=function(caller,handle,beforeUpdate,afterUpdate){
    this.TargetPage='Current';
    this.SetTarget=function(strUrl){
        this.TargetPage=(getOnlyUrl(strUrl).toUpperCase()==getOnlyUrl().toUpperCase())?'Current':'Other';
    };
    this.Script=[];
    this.responseText='';
    this.Caller=caller;
    this.BeforeUpdate=(beforeUpdate)?beforeUpdate.bind(this):null;
    this.AfterUpdate=(afterUpdate)?afterUpdate.bind(this):null;
    this.Handle=function(){
		if ((this.Request.readyState == 4 || this.Request.readyState == 'complete')){// && (AjaxReqs[i].status == 200)){ commented to chk null ref error.
			this.responseText=this.Request.responseText;
			if(this.BeforeUpdate)this.BeforeUpdate();
			if(!AjaxIframe)stripScript(this);
		    if(handle)handle(this.responseText,this.Caller);
			if(!AjaxIframe)evalAjax(this.Script);
			HideAjaxProgressBar();
			if(this.Caller && !this.Caller.NopregressBar)HideAjaxProcessBar(this);
			if(this.AfterUpdate)this.AfterUpdate();
		}
	};
    this.Request=InitializeRequest(this.Handle.bind(this));
	this.ProcessBar=null;
    if(this.Request){
        if(this.Caller && !this.Caller.NopregressBar)this.ProcessBar=ShowAjaxProcessBar(this);
		getMsgDiv();
		ShowAjaxProgressBar();
	}
}
function InitializeRequest(Handle){
    var ActObjs = ["Msxml2.XMLHTTP.6.0", "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP"] //activeX versions to check for in IE
	var ZmAR=null;
    if (window.ActiveXObject){ //Test for support for ActiveXObject in IE first (as XMLHttpRequest in IE7 is broken)
        for (var i=0; i<ActObjs.length; i++){
            try{
                ZmAR = new ActiveXObject(ActObjs[i]);
                if(Handle)ZmAR.onreadystatechange = Handle;
                break;
            }catch(e){
                //suppress error
            }
        }
    }else if (window.XMLHttpRequest) // if Mozilla, Safari etc
        ZmAR = new XMLHttpRequest();
        if(Handle)ZmAR.onreadystatechange = Handle;
    else
        return null;
	if(!ZmAR){
		alert('Your Browser does not support Ajax, Please use w3c compliant browser.');
		return false;
	}
    if (ZmAR.overrideMimeType) {
		ZmAR.overrideMimeType('text/html');
	}
	return ZmAR;
}
function GetAjaxData(strUrl,Handle,caller,beforeUpdate,afterUpdate){
	var Ajax = new AjaxRequest(caller,Handle,beforeUpdate,afterUpdate);
	if(Ajax.Request){
        addQPara2URL("__AsyncReq=Y",strUrl)
		Ajax.Request.open("GET", strUrl, true);				
		Ajax.Request.send(null);
	}
}
function PostAjaxData(strUrl,strData,Handle,caller,beforeUpdate,afterUpdate){
	var Ajax = new AjaxRequest(caller,Handle,beforeUpdate,afterUpdate);
	if(Ajax.Request){
	    strUrl=getValidUrl(strUrl);
		if(AjaxDebug)alert(strUrl);
		Ajax.SetTarget(strUrl);
		if(AjaxDebug)alert(Ajax.TargetPage);
		Ajax.Request.open("POST", strUrl, true);				
		Ajax.Request.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
		if(!strData){
		    strData=getFormData();
		}else{
		    strData=((Ajax.TargetPage=='Current')?getBasicFormData():'')+strData;
		}
		if(AjaxDebug)alert(strData);
		Ajax.Request.setRequestHeader("Content-length", strData.length);			
		//Ajax.Request.setRequestHeader("Connection", "close");	 Commented to Fix  NTLM Authentication Issue		
		Ajax.Request.send(strData);
	}
}
function getFrameBasicData(){
    var formData='';
    var obj=document.getElementById('__FRAMEVIEWSTATE');
    if(obj)formData += '&__VIEWSTATE='+escapeAll(obj.value);
    return formData;
}
function getBasicFormData(){
    var formData='';
    var obj=document.getElementById('_CSRFTOKEN');
    if(obj)formData += '&'+escapeAll(obj.name)+'='+escapeAll(obj.value);
    obj=document.getElementById('__VIEWSTATE');
    if(obj)formData += '&'+escapeAll(obj.name)+'='+escapeAll(obj.value);
    obj=document.getElementById('__EVENTTARGET');
    if(obj)formData += '&'+escapeAll(obj.name)+'='+escapeAll(obj.value);
    obj=document.getElementById('__EVENTARGUMENT');
    if(obj)formData += '&'+escapeAll(obj.name)+'='+escapeAll(obj.value);
    obj=document.getElementById('__LASTFOCUS');
    if(obj)formData += '&'+escapeAll(obj.name)+'='+escapeAll(obj.value);
    if(AjaxDebug)alert(formData);
    return formData;
}
function getFormData(objElement){
    var frmData='';
    if(checkParentSubmitted(objElement))return frmData;
    if(!objElement)objElement=document;
    var ele = objElement.getElementsByTagName('input');
    for(var i=0;i<ele.length;i++){
            if (ele[i].type == "checkbox") {
               if (ele[i].checked) {
                  frmData += '&'+escapeAll(ele[i].name)+'='+escapeAll(ele[i].value);
               } else {
                  frmData += '&'+escapeAll(ele[i].name)+'='+escapeAll('');
               }
            }else if (ele[i].type == "radio") {
               if (ele[i].checked) {
                  frmData += '&'+escapeAll(ele[i].name)+'='+escapeAll(ele[i].value);
               }
            }else {
               frmData += '&'+escapeAll(ele[i].name)+'='+escapeAll(ele[i].value);
            }
    }
    ele = objElement.getElementsByTagName('select');
    var blnMultiple=false;
    for(var i=0;i<ele.length;i++){
        blnMultiple=false;
        frmData += '&'+escapeAll(ele[i].name)+'=';
        for(var j=0; j<ele[i].length;j++){
            if(ele[i][j].selected){
                if(blnMultiple)
                    frmData += ',';
                frmData += escapeAll(ele[i][j].value);
                blnMultiple=true;
            }
        }
    }
    ele = objElement.getElementsByTagName('textarea');
    for(var i=0;i<ele.length;i++){
        frmData += '&'+escapeAll(ele[i].name)+'='+escapeAll(ele[i].value);
    }
    if(AjaxDebug)alert(frmData);
    return frmData;
}
function checkParentSubmitted(objElement){
    if(!objElement)return false;
    var objparent = objElement.parentNode;
    while(objparent && objparent!=document){
        if(objparent.className=='AjaxPanel' && objparent.postcontents=='true')return true;
        objparent = objparent.parentNode;
    }
    return false;
}
function escapeAll(v){
    return escape(v).replace(/\+/g,"%2B");
}
function ShowAjaxProcessBar(Ajax){
	if(Ajax.Caller){
		Ajax.Caller.enabled=false;
		var obj = document.createElement('img');
		obj.id=Ajax.Caller.id+'_processing_bar';
		if(AjaxImagePath)
		    obj.src=AjaxImagePath+'pross_crcl.gif';
		else
		    obj.src='WebResource.axd?d=7-uyK0Xs8Bc8dsTgc9SmNo19SjHIHOdw_4VDagWh6qM6neIssLfbk4y0gWCVS1wD0&t=634183894940000000';
		Ajax.Caller.parentNode.insertBefore(obj,Ajax.Caller);
		return obj;
	}
}
function HideAjaxProcessBar(Ajax){
	if(Ajax.Caller)Ajax.Caller.enabled=true;
	if(Ajax.ProcessBar)Ajax.ProcessBar.parentNode.removeChild(Ajax.ProcessBar);
}
function ShowAjaxProgressBar(object){
	if(!object)object=objMsgDiv;
	if(object)
		object.innerHTML='<img src="WebResource.axd?d=7-uyK0Xs8Bc8dsTgc9SmNo19SjHIHOdw_4VDagWh6qM6neIssLfbk4y0gWCVS1wD0&t=634183894940000000">';
}
function HideAjaxProgressBar(object){
	if(!object)object=objMsgDiv;
	if(object)
		object.innerHTML='';
}
function getMsgDiv(){
	if(gblMsgDivFlag && !objMsgDiv){
		gblMsgDivFlag=false;
		var Doc=window;
		var flag=true;
		while(flag){
			if(Doc.document.getElementById('dvMsg')){
				flag=false;
				objMsgDiv=Doc.document.getElementById('dvMsg');
			}else{
				if(Doc.parent!=Doc.self)
					Doc=Doc.parent;
				else
					flag=false;
			}
		}
	}
}
function stripScript(Ajax){
    if(AjaxDebug)alert(Ajax.responseText);
    var arr = Ajax.responseText.Trim().split(/<(\/)?script?[^\/>]+>/gi);
    var i,y,x=0;
    if(Ajax.responseText.Trim().toLowerCase().indexOf("<script")==0)x=1;
    Ajax.responseText='';
    if(BrowserDetect.browser!="Explorer"){
        y=x;
        for(i=0;i<arr.length;i++){
            if(i%2!=y){
                arr.splice(i,1);
                i--;
                if(y==1)
                    y=0;
                else
                    y=1;
            }
        }
    }
    for(i=0;i<arr.length;i++){
        if(i%2==x){
           Ajax.responseText+=arr[i];
        }else{
            try{
                if(Ajax.Script)
                    Ajax.Script.push(arr[i]);
                else
                    eval(arr[i]);
            }catch(e){
                if(AjaxDebug)alert(e.description);
            }
        }
    }
    if(AjaxDebug)alert(Ajax.responseText);
}
function evalAjax(arrScript){
    for(i=0;i<arrScript.length;i++){
        if(AjaxDebug)alert(arrScript[i]);
        try{
            eval(arrScript[i]);
        }catch(e){
            if(AjaxDebug)alert(e.description);
        }
    }
}
function addQPara2URL(strPara,strUrl){
  	strUrl=getValidUrl(strUrl);
	if (strUrl.indexOf('?')>0){
    if(strPara.substring(0,1)!='&')
		strUrl+='&'+strPara;
		else
		strUrl+=strPara;
	}else{
		strUrl+='?'+strPara;
	}
	return strUrl;
}
function getValidUrl(strUrl){
  	if(!strUrl)
		strUrl = location.href;
	if(strUrl.indexOf('#')>0)
      strUrl = strUrl.substring(0,strUrl.indexOf('#'));
    return strUrl;
}
function getOnlyUrl(strUrl){
  	strUrl=getValidUrl(strUrl);
	if(strUrl.indexOf('?')>0)
      strUrl = strUrl.substring(0,strUrl.indexOf('?'));
    return strUrl;
}
function UpdateAjaxControl(ControlId,Action,CallBack,Control,beforeUpdate,afterUpdate){
    if(!CallBack)CallBack=UpdateAjaxControl_Handle;
    document.getElementById('__AsyncReq').value='Y';
    document.getElementById('__AsyncTargetId').value=ControlId;
    document.getElementById('__AsyncAction').value=(Action)?Action:'';
    Control=(Control)?Control:document.getElementById(ControlId);
    var strUrl = (Control.url)?Control.url:null;
    strUrl = (typeof getSearchString != 'undefined')?addQPara2URL(getSearchString(),strUrl):strUrl;
    if(AjaxDebug)alert(strUrl);
    PostAjaxData(strUrl,getAjaxPostData(),CallBack,Control,beforeUpdate,afterUpdate);
    document.getElementById('__AsyncReq').value='';
    document.getElementById('__AsyncTargetId').value='';
    document.getElementById('__AsyncAction').value='';
}
function UpdateAjaxControl_Handle(strData,Caller){
    if(AjaxDebug)alert(Caller.outerHTML);
    replaceControl(Caller,strData);
}
function getAjaxPostData(){
    var formData='';
    var ele = getElementsByClassName('div','AjaxPanel');
    var i;
    for(i=0;i<ele.length;i++){
        if(ele[i].postcontents=='true'){
            formData += getFormData(ele[i]);
        }
    }
    if(AjaxDebug)alert(formData);
    return formData;
}
function getElementsByClassName(EleType, classname){
	var Elements = [];
	//var regexp = new RegExp('(^| )'+classname+'( |$)');
	var tmpElements = document.getElementsByTagName(EleType);
	for(var i=0,j=tmpElements.length; i<j; i++)
		//if(regexp.test(tmpElements[i].className)){Elements.push(tmpElements[i]);
		if(tmpElements[i].className==classname)Elements.push(tmpElements[i]);
	return Elements;
}
function addShowHide(obj){
    if(obj)obj.show=function(){obj.style.display='block';};
    if(obj)obj.hide=function(){obj.style.display='none';};
}
function addUpdate(obj){
    if(obj)obj.update=function(Action,CallBack,beforeUpdate,afterUpdate){UpdateAjaxControl(obj.id,Action,CallBack,obj,beforeUpdate,afterUpdate);};
}
function replaceControl(Control,strData){
    var blnreplaced = true;
    if(Control.outerHTML){
        try{
            Control.outerHTML=strData;
        }catch(e){blnreplaced=false;}
    }else{blnreplaced=false;}
    if(!blnreplaced){
        try{
            var NewControl = document.createElement("div");
            NewControl.innerHTML=strData;
            Control.parentNode.replaceChild(NewControl.childNodes[0],Control);
        }catch(e){alert(e);}
    }
}


/****************************************************************************
Browser Detect
****************************************************************************/

var BrowserDetect = {
	init: function () {
		this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "an unknown version";
		this.OS = this.searchString(this.dataOS) || "an unknown OS";
	},
	searchString: function (data) {
		for (var i=0;i<data.length;i++)	{
			var dataString = data[i].string;
			var dataProp = data[i].prop;
			this.versionSearchString = data[i].versionSearch || data[i].identity;
			if (dataString) {
				if (dataString.indexOf(data[i].subString) != -1)
					return data[i].identity;
			}
			else if (dataProp)
				return data[i].identity;
		}
	},
	searchVersion: function (dataString) {
		var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
		return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
	},
	dataBrowser: [
		{
			string: navigator.userAgent,
			subString: "Chrome",
			identity: "Chrome"
		},
		{ 	string: navigator.userAgent,
			subString: "OmniWeb",
			versionSearch: "OmniWeb/",
			identity: "OmniWeb"
		},
		{
			string: navigator.vendor,
			subString: "Apple",
			identity: "Safari",
			versionSearch: "Version"
		},
		{
			prop: window.opera,
			identity: "Opera"
		},
		{
			string: navigator.vendor,
			subString: "iCab",
			identity: "iCab"
		},
		{
			string: navigator.vendor,
			subString: "KDE",
			identity: "Konqueror"
		},
		{
			string: navigator.userAgent,
			subString: "Firefox",
			identity: "Firefox"
		},
		{
			string: navigator.vendor,
			subString: "Camino",
			identity: "Camino"
		},
		{		// for newer Netscapes (6+)
			string: navigator.userAgent,
			subString: "Netscape",
			identity: "Netscape"
		},
		{
			string: navigator.userAgent,
			subString: "MSIE",
			identity: "Explorer",
			versionSearch: "MSIE"
		},
		{
			string: navigator.userAgent,
			subString: "Gecko",
			identity: "Mozilla",
			versionSearch: "rv"
		},
		{ 		// for older Netscapes (4-)
			string: navigator.userAgent,
			subString: "Mozilla",
			identity: "Netscape",
			versionSearch: "Mozilla"
		}
	],
	dataOS : [
		{
			string: navigator.platform,
			subString: "Win",
			identity: "Windows"
		},
		{
			string: navigator.platform,
			subString: "Mac",
			identity: "Mac"
		},
		{
			   string: navigator.userAgent,
			   subString: "iPhone",
			   identity: "iPhone/iPod"
	    },
		{
			string: navigator.platform,
			subString: "Linux",
			identity: "Linux"
		}
	]

};
BrowserDetect.init();
