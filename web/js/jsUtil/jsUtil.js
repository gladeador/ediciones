
//**** Valida E-Mail. ****
function emailCheck(email){
    
    var resp = null;
    var emailStr = email;
    var emailPat=/^(.+)@(.+)$/;
    var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
    var validChars="\[^\\s" + specialChars + "\]";
    var quotedUser="(\"[^\"]*\")";
    var ipDomainPat=/^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
    var atom=validChars + '+';
    var word="(" + atom + "|" + quotedUser + ")";
    var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
    var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$");
    var matchArray=emailStr.match(emailPat);
    
    if (matchArray==null){
        resp = "Su Dirección de correo es Incorrecta";
        return resp;
    }
    
    var user=matchArray[1];
    var domain=matchArray[2];
    
    if (user.match(userPat)==null){
        resp = "El nombre de usuario no es válido.";
        return resp;
    }
    
    var IPArray=domain.match(ipDomainPat);
    
    if (IPArray!=null){
        for (var i=1;i<=4;i++){
            if (IPArray[i]>255){
                resp = "IP de destino invalida";
                return resp;
            }
        }
        return true;
    }
    
    var domainArray=domain.match(domainPat);
    
    if (domainArray==null){
        resp = "El dominio parece no ser válido.";
        return resp;
    }
    
    var atomPat=new RegExp(atom,"g");
    var domArr=domain.match(atomPat);
    var len=domArr.length;
    
    if (domArr[domArr.length-1].length<2 || domArr[domArr.length-1].length>3){
        resp = "La dicrección debe tener 3 letras si es .'com' o 2 si en de algún pais.";
        return resp;
    }
    
    if (len<2){
        resp = "Su Dirección de correo es Incorrecta";
        return resp;
    }
    
    return resp;
}
//**** Fin Valida E-Mail. ****

//**** Valida digito verificador del Rut. ****
function validaDV(rut){
    var dvr = '0';
    suma = 0;
    mul = 2;
    
    for (i= rut.length -1 ; i >= 0; i--){
        suma = suma + rut.charAt(i) * mul;
        if (mul == 7)
            mul = 2;
        else
            mul++;
    }
    
    res = suma % 11;
    if (res==1)
        dvr = 'K';
    else if (res==0)
        dvr = '0';
    
    else{
        dvi = 11-res;
        dvr = dvi;
    }
    
    return dvr;
}
//**** Fin Valida digito verificador del Rut. ****

//**** Showhide. ****
function showhide(id){
    if (document.getElementById){
        obj = document.getElementById(id);
        if (obj.style.display == "none"){
            obj.style.display = "";
        }
        else {
            obj.style.display = "none";
        }
    }
}
//**** Fin Showhide. ****

//**** Capitaliza. ****
function Capitaliza(id){ 
    document.getElementById(id).value = document.getElementById(id).value.toUpperCase();
    palabras = document.getElementById(id).value.split(" ");
    
    for(a=0;a<palabras.length;a++){
        letra=palabras[a].charAt(0);
        palabras[a] = letra + palabras[a].substring(1 , palabras[a].length).toLowerCase();
    }
    
    texto="";
    
    for(a=0;a<palabras.length;a++){
        if(a>0){texto+=" ";}
        texto+=palabras[a];
    }
    
    document.getElementById(id).value=texto;
}
//**** Fin Capitaliza. ****

function noF5(){
    document.onkeydown = function(e){
        if(e)
        document.onkeypress = function(){return true;}
        
        var evt = e?e:event;
        if(evt.keyCode==116){
            if(e){
                document.onkeypress = function(){return false;}
            }
            else{
                evt.keyCode = 0; evt.returnValue = false; 
                }
            }
        }
    }

function cerrar_ventana(){
    Dialog.closeInfo();
    return;
    }
    
//**** Redondea Decimales. ****
function redond(num, ndec) {
    var fact = Math.pow(10, ndec); // 10 elevado a ndec
    /* Se desplaza el punto decimal ndec posiciones,
    se redondea el número y se vuelve a colocar
    el punto decimal en su sitio. */
    return Math.round(num * fact) / fact;
    }
//**** Fin Redondea Decimales. ****
    