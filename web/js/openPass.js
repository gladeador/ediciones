/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function strim(strString){ 
    var strCopy = new String(strString);
    strCopy = strCopy.replace(/^\s+/, "");
    strCopy = strCopy.replace(/\s+$/, "");
    strString = strCopy;
    return strString;
}
		
function openPassword()
{
    $('#PassPopProc').show();
    centerPopup('popupContact');
    //$('iframe#frmpass').attr('src','');
    //$('iframe#frmpass').hide();
    loadPopup('popupContact');
    $('iframe#frmpass').attr('src','ediciones/jsp/olvido_password.jsp?QInd=false&org_id=');
//$('iframe#frmpass').hide();
//            var el = document.getElementById('ForgotIDPass');
//            el.style.visibility = (el.style.visibility == 'visible') ? 'hidden':'visible';
//popup('ForgotIDPass');
//			var strWin = window.open("forgot_password.aspx?org_id=","winPrint",'toolbar=no,menubar=n0,resizable=no,scrollbars=no,width=445,height=330')
//			strWin.focus();			
}
		      
function buttonmouseover(id)
{
    $('#'+id).find('.stl').attr('class','stlmo');
    $('#'+id).find('.stc').attr('class','stcmo');
    $('#'+id).find('.str').attr('class','strmo');
    $('#'+id).find('.sml').attr('class','smlmo');
    $('#'+id).find('.smc').attr('class','smcmo');
    $('#'+id).find('.smr').attr('class','smrmo');
    $('#'+id).find('.sbl').attr('class','sblmo');
    $('#'+id).find('.sbc').attr('class','sbcmo');
    $('#'+id).find('.sbr').attr('class','sbrmo');
    $('#'+id+'btn').attr('src','Images/arrow_mo.png');
}
function buttonmouseout(id)
{
    $('#'+id).find('.stlmo').attr('class','stl');
    $('#'+id).find('.stcmo').attr('class','stc');
    $('#'+id).find('.strmo').attr('class','str');
    $('#'+id).find('.smlmo').attr('class','sml');
    $('#'+id).find('.smcmo').attr('class','smc');
    $('#'+id).find('.smrmo').attr('class','smr');
    $('#'+id).find('.sblmo').attr('class','sbl');
    $('#'+id).find('.sbcmo').attr('class','sbc');
    $('#'+id).find('.sbrmo').attr('class','sbr');
    $('#'+id+'btn').attr('src','Images/arrow_ol.png');
}
        
$(document).ready(function(){
    if ($.browser.mozilla){
        $('iframe#frmComp').attr('height','180px')
    }
    if ($.browser.safari || $.browser.opera){
        $('iframe#frmComp').attr('height','250px')
    }            
    $('iframe#frmpass').load(function(){
        $('#PassPopProc').hide();
    });
    $('iframe#frmComp').load(function(){
        $('#CompPopProc').hide();                
    });
});