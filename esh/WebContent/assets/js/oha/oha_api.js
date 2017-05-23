var apiConfig={
    Version:0.930,
}
var __toastCode=
    '<div id="toastViewDiv">\
        <div id="toastView">\
            <text></text>\
        </div>\
    </div>';
$("html").append(__toastCode);

$(window).load(function(){
//	$(".main").css({"padding-top":($(window).height()*5)/100+"px"});
    document.documentElement.style.webkitUserSelect='none';
    var uAgent=navigator.userAgent;
//    console.log("userAgent="+uAgent)
//    var _pat="/oPlateform[ ]*[^;]+!([ ]*|$)";
    var _pat=/oPlateform[ ]*:[ ]*[^; ]+(?=[ ]*([;]|$))/g;
    var _plateform=uAgent.match(_pat);
    if(_plateform!=null) {
        var _p=_plateform[_plateform.length-1];
        _p=_p.substring(_p.indexOf(":")+1).trim();
        if(_p.toLowerCase()=="android") {
            oha_api.ohaConfig.plateForm="Android";
    //        $("#titleText").css({"color":"red"});

        }
        else if(_p.toLowerCase()=="ios") {
            oha_api.ohaConfig.plateForm="iOS";
       }
    }
    _pat=/appVer[ ]*:[ ]*[0-9]+(?=[ ]*([;]|$))/g;
    var _appVer=uAgent.match(_pat);
    console.log("App Version="+_appVer);
    if(_appVer!=null) {
        _p=_appVer[_appVer.length-1];
        _p=_p.substring(_p.indexOf(":")+1).trim();
         oha_api.ohaConfig.appVer=parseInt(_p);
    }
    var _vIdx=uAgent.indexOf("oVer:");
    var ohaVer=-1;
    if(_vIdx>=0) {
        ohaVer=parseInt(uAgent.substr(_vIdx+5, 2));
        if(isNaN(ohaVer)) {
            ohaVer=0;
        }
        oha_api.ohaConfig.ohaVer=ohaVer;
    }
    var ual=uAgent.length;
    var _lang=oha_api.ohaConfig.default_language;
    switch(ohaVer) {
    case 0:
        _lang=uAgent.substr(ual-2,2);
//		alert("Lang(0)="+_lang);
    break;
    case 1:
        var i=uAgent.indexOf("oLang:");
            if(i>0) {
                _lang=uAgent.substr(i+6,2);
            }
//		alert("Lang(1)="+_lang);
    break;
    }
//	_lang="KR";
    _lang=_lang.toUpperCase();
    if(_lang=="CN") {
        oha_api.langSet=oha_api.langCode.SimpleChinese;
//        oha_api.ohaConfig.html=oha_api.langCode.SimpleChinese+"?OHA_Local=true";
//        oha_api.ohaConfig.title=oha_api.ohaConfig.title_conf.title.cn;
//        oha_api.ohaConfig.subtitle=oha_api.ohaConfig.title_conf.subtitle.cn;
    }
    else if((_lang=="US")||(_lang=="EN")){
        oha_api.langSet=oha_api.langCode.English;
//        oha_api.ohaConfig.html=oha_api.ohaConfig.html_config.en+"?OHA_Local=true";
//        oha_api.ohaConfig.title=oha_api.ohaConfig.title_conf.title.en;
//        oha_api.ohaConfig.subtitle=oha_api.ohaConfig.title_conf.subtitle.en;
    }
    else if((_lang=="KR")||(_lang=="KO")){
        oha_api.langSet=oha_api.langCode.Korean;
//        oha_api.ohaConfig.html=oha_api.ohaConfig.html_config.kr+"?OHA_Local=true";
//        oha_api.ohaConfig.title=oha_api.ohaConfig.title_conf.title.kr;
//        oha_api.ohaConfig.subtitle=oha_api.ohaConfig.title_conf.subtitle.kr;
    }
    else if((_lang=="TW")||(_lang=="ZH")){
        oha_api.langSet=oha_api.langCode.TraditionChinese;;
//        oha_api.ohaConfig.html=oha_api.ohaConfig.html_config.tw+"?OHA_Local=true";
//        oha_api.ohaConfig.title=oha_api.ohaConfig.title_conf.title.tw;
//        oha_api.ohaConfig.subtitle=oha_api.ohaConfig.title_conf.subtitle.tw;
    }
//    console.log("hello, lang="+_lang)
//    console.log("hello, userAgent end, this.ohaConfig.html="+oha_api.ohaConfig.html)
    oha_api.initToastSize();
//    oha_api.a();
});

function OHA_DevReady(_id, _model){
//    console.log("OHA_DevReady, _model="+_model+", ohaConfig.model="+oha_api.ohaConfig.model);
    var _modelX=_model.split(":");
    for(var i=0;i<_modelX.length;i++) {
        if(_modelX[i]=="RO") {
            oha_api.ohaConfig.isReadOnly=true;
        }
    }
 	if(_modelX[0]==oha_api.ohaConfig.model) {
//        console.log("Hello, model matches")
 		oha_api.DeviceId=_id;
        oha_api.DevPlugIn();
 	}
}

function OHA_DevOff(_id){
    oha_api.DeviceId=-1;
    oha_api.DevPlugOut();
}

var oha_api=new function (){
    var __urlCmdQ=[];
    var __toastQ=[];
    var __isUrlCmdActive=false;
    var _isToastQNotEmpty=false;
    var __urlCmdInterval;
    this.langSet="us";
    this.langCode={
        English:"en",
        TraditionChinese:"tw",
        SimpleChinese:"cn",
        Korean:"kr",
    }
    var self=this;

    this.ohaConfig={
        apiVer:-1,
        title:"",
        subtitle:"",
        plateForm:"PC",
        appVer:-1,
        html:"",
        default_language:this.langCode.English,
        api_path:"assets/js/oha/",
        isReadOnly:false,
    }

    this.DeviceId=-1;

    this.DevPlugIn=function(){
        console.log("Device is plugged in!!")
    }

    this.DevPlugOut=function(){
        console.log("Device is plugged out!!")
    }

    this.initToastSize=function() {
        this.__x.__initSize();
    }

    this.getPlateForm=function() {
        return this.ohaConfig.plateForm;
    }

    this.getAppVer=function() {
        return this.ohaConfig.appVer;
    }

    this.isReadOnly=function() {
        return this.ohaConfig.isReadOnly;
    }
//    this.set = function (_config, _htmlConfig) {
//        $.extend(true, this.ohaConfig, _config);
//        this.ohaConfig.html=_htmlConfig[this.ohaConfig.default_language]+"?OHA_Local=true";
//
//    }
//    if (typeof oha_config !== 'undefined') {
//        $.extend(true, this.ohaConfig, oha_config);
//    }
//    if(typeof html_config !== 'undefined') {
//        this.ohaConfig.html=html_config[this.ohaConfig.default_language]+"?OHA_Local=true";
//    }
    //console.log("hello, api, this.ohaConfig.html="+this.ohaConfig.html);

    function getNum(_p) {
        var _pat=/0[xX].+/;
        if(_pat.test(_p)) {
            return parseInt(_p.substr(2),16);
        }
        else {
            return parseInt(_p);
        }
    }

    this.runOhaCmd = function(_cmdStr) {
//        console.log('runOhaCmd('+_cmdStr+');')
        if(this.ohaConfig.plateForm=="Android" && this.ohaConfig.appVer >= 184) {
    //        console.log('OhaJsInterface.CMD('+_cmdStr+');')
    //        OhaJsInterface.CMD(_cmdStr);
            var _a=OhaJsInterface.CMD(_cmdStr);
//            console.log('runOhaCmd('+_cmdStr+")="+_a);
            return _a;
        }
        else if(this.ohaConfig.plateForm=="iOS" && this.ohaConfig.appVer >459) {
    //        alert("window.OHANativeApis.JSCommand("+"_cmdStr"+")");
            return window.OHANativeApis.JSCommand(_cmdStr);
        }
        else {
            if(__isUrlCmdActive) {
                __urlCmdQ.push(_cmdStr);
            }
            else {
                __isUrlCmdActive=true;
                window.location.href=_cmdStr;
    //            console.log('Url command('+_cmdStr+');')
                setTimeout(function() {
                    self.__x.__runUrlCmd();
                }, 200);
            }
        }
        return null;
    }

    this.ApiCmdWr=function(_cmd) {
        var _cmdStr="cw_"+this.DeviceId+"_G:"+_cmd;
        this.runOhaCmd(_cmdStr);
    }

    this.ApiCmdRd=function(_cmd) {
        var _pat=/((?:0x)?[0-9a-fA-F]+):((?:0x)?[0-9a-fA-F]+):((?:0x)?[0-9a-fA-F]+)((?::(?:0x)?[0-9a-fA-F]+)*)$/g;
        if(_pat.test(_cmdStr)) {   // ex. oha://cr_2024_G:04:05:H,06
//    inStr=inStr.replace(/\S\S/g, function addSpace(x) {return x+" "}).trim();
//    inStr=inStr.replace(/\b\S\b/g, function addSpace(x) {return " 0"+x+" "}).trim();
//    inStr=inStr.replace(/\s+/g, " ");
//    inStr=inStr.toUpperCase();
            var _cmdNo=getNum(RegExp.$1);
            var _sCmdNo=getNum(RegExp.$2);
            var _count=getNum(RegExp.$3);
            return OhaJsInterface.OHA_CMDR(DeviceId, _cmdNo, _sCmdNo, _count);
        }
    }

    this.setToastPos=function(_percent) {
        if(typeof _percent=="undefined") {
            $("#toastViewDiv").css({"top":"90%"});
            return;
        }
        var _p=parseInt(_percent);
        if(typeof _p !== "number") {
            console.log("setToastPos Error! _percent="+_percent);
            return;
        }
        if(_p<0 || _p>100) {
            console.log("setToastPos range Error! _percent="+_p);
        }
        $("#toastViewDiv").css({"top":_p+"%"});
    }

    this.showToast=function(_msg, _percent) {
        __toastQ.push(_msg);
        if(__toastQ.length>1) {
            return;
        }
        this.__x.__showToast();
    //    __showToast();
    }

    this.getHtml=function() {
        var _html="";
        switch(oha_api.langSet) {
            case oha_api.langCode.TraditionChinese:
                _html=this.ohaConfig.html_config.tw;
                break;
            case oha_api.langCode.SimpleChinese:
                _html=this.ohaConfig.html_config.cn;
                break;
            case oha_api.langCode.English:
                _html=this.ohaConfig.html_config.en;
                break;
            case oha_api.langCode.Korean:
                _html=this.ohaConfig.html_config.kr;
                break;
        }
        _html+="?OHA_Local=true";
        return _html;
    }

    this.getTitle=function() {
        var _title="";
        switch(oha_api.langSet) {
            case oha_api.langCode.TraditionChinese:
                _title=this.ohaConfig.title.tw;
                break;
            case oha_api.langCode.SimpleChinese:
                _title=this.ohaConfig.title.cn;
                break;
            case oha_api.langCode.English:
                _title=this.ohaConfig.title.en;
                break;
            case oha_api.langCode.Korean:
                _title=this.ohaConfig.title.kr;
                break;
        }
        return _title;
    }

    this.getSubTitle=function() {
        var _html="";
        switch(oha_api.langSet) {
            case oha_api.langCode.TraditionChinese:
                _html=this.ohaConfig.subtitle.tw;
                break;
            case oha_api.langCode.SimpleChinese:
                _html=this.ohaConfig.subtitle.cn;
                break;
            case oha_api.langCode.English:
                _html=this.ohaConfig.subtitle.en;
                break;
            case oha_api.langCode.Korean:
                _html=this.ohaConfig.subtitle.kr;
                break;
        }
        return _html;
    }

    this.setUiAlpha=function(_alpha) {
        if(typeof(_alpha)=="number") {
            if(_alpha <=1 && _alpha >=0) {
                this.runOhaCmd("oha://alpha_"+_alpha)
            }
            else {
                showToast("Alpha value is not correct("+_alpha+")");
            }
        }
        else {
            showToast("Alpha value is not a number("+_alpha+")");
        }
    }

    this.cookieWrite=function(_key, _data) {
        if(__cookieCmdOk()) {
            this.runOhaCmd('oha://cookie_write_'+_key+"_"+_data);
        }
        else {
            $.cookie(_key, _data, {expires:365});
        }
    }

    this.cookieRead=function(_key, _default) {
        if(__cookieCmdOk()) {
            return this.runOhaCmd('oha://cookie_read_'+_key+"_"+_default);
        }
        else {
            var _c=$.cookie(_key);
            if(_c==null) {
                _c=_default;
            }
            return _c;
        }
    }

    this.cookieRemove=function(_key) {
        if(__cookieCmdOk()) {
            this.runOhaCmd('oha://cookie_remove_'+_key);
        }
        else {
            $.removeCookie(_key);
        }
    }

    this.cookieRemoveAll=function() {
        if(__cookieCmdOk()) {
            this.runOhaCmd('oha://cookie_removeAll');
        }
        else {
            for(var _x in $.cookie()) {
                $.removeCookie(_x);
            }
        }
    }

    function __cookieCmdOk () {
        return ((self.ohaConfig.plateForm=="Android") && (self.ohaConfig.appVer > 189));
    }

    this.__x=new function () {
        var self=this;
        this.__showToast=function() {
            if(__toastQ.length==0) {
                return;
            }
            var _msg=__toastQ[0]
            $("#toastViewDiv").stop();
            $("#toastView").text(_msg);
            $("#toastViewDiv").fadeIn(500);
            setTimeout(function() {
        //        $("#toastViewDiv").fadeOut(1000, function(){
        //            __toastQ.splice(0,1);
        //            __showToast();
        //        });
                $("#toastViewDiv").fadeOut(1000);
                __toastQ.splice(0,1);
                self.__showToast();
            }, 2000);

        }

        this.__initSize=function () {
            var _w=$(window).width();
            $("#toastViewDiv").css({"font-size":0.04*_w+"px"});
            $("#toastView").css({"border-radius":0.02*_w+"px"});
        }

        this.__runUrlCmd=function() {
            if(__urlCmdQ.length==0) {
                __isUrlCmdActive=false;
            }
            else {
                var _cmd=__urlCmdQ[0];
                __urlCmdQ.splice(0,1);
                window.location.href=_cmd;
        //        console.log('Url command('+_cmd+');')
                setTimeout(function() {
                    self.__runUrlCmd();
                }, 200);
            }
        }
    }


}

if(typeof oha_config!=="undefined") {
    $.extend(true, oha_api.ohaConfig, oha_config);
}
//var scriptTag = document.createElement('script');
//    scriptTag.type="text/javascript";
//    scriptTag.src = oha_api.ohaConfig.api_path+"jquery.cookie.js";
//    document.head.appendChild(scriptTag);
$('head').append('<link rel="stylesheet" href="assets/js/oha/oha_api_style.css" type="text/css" />');
//oha_api.ohaConfig.html=oha_api.ohaConfig.html_config[oha_api.ohaConfig.default_language]+"?OHA_Local=true";
