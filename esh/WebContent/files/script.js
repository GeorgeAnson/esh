// JavaScript Document
	var ohaVer=0;
	
//==== Dynamic loading js in js================
//var head= document.getElementsByTagName('head')[0];
//var script= document.createElement('script');
//script.type= 'text/javascript';
//script.src= 'oha_config.js';
////script.charset='big5';
//head.appendChild(script);
//=================================
$(window).load(function(){
//	$(".main").css({"padding-top":($(window).height()*5)/100+"px"});
	sf=$(window).height()/1.5;
	if(sf>$(window).width()) {
		sf=$(window).width();
	}
/*	$("#titlearea").css({"height":$(window).height()*0.2});*/
	$("#usingtime").css({"padding-top":($(window).height()*0)/100+"px"});
	$("#mainctrl").css({"padding-top":($(window).height()*2)/100+"px"});
	$("#powerctrl").css({"padding-top":($(window).height()*4)/100+"px"});
	$("h3").css({"font-size":sf*0.04+"px","margin":sf*0.01+"px"});
	$("h4").css("font-size",sf*0.03+"px");
    $(".modebtn").css({"font-size":($(window).width()*3)/100+"px","width":($(window).width()*15)/100+"px"});
	$(".timelist").css({"width":($(window).width()*15)/100,"height":($(window).width()*15)/100, "font-size":($(window).width()*4)/100+"px","line-height":($(window).width()*15)/100+"px"});
	$(".strctrl").css({"width":($(window).width()*12)/100,"height":($(window).width()*12)/100});
	$(".timer").css({"font-size":($(window).width()*15)/100+"px"});
	$(".title").css({"font-size":($(window).width()*5)/100+"px"});
	$(".detectpic").css({"width":sf*0.1,"height":sf*0.1});
	$(".loadingdot").css({"width":($(window).width()*2)/100,"height":($(window).width()*2)/100});
	$("#strbtnText").css({"font-size":($(window).width()*8)/100+"px","line-height":($(window).width()*20)/100+"px"});
	$(".powerbtn").width($(window).width()*0.20);
	$(".powerbtn").height($(window).width()*0.20);
    $(".powctrlbtn").width($(window).width()*0.20);
	$(".powctrlbtn").height($(window).width()*0.20);
	$(".powerbtn h3, .powctrlbtn h3").css("bottom",-$(window).width()*0.1+"px");
	setcookies();
	if(oha_config.debug_mode) {
		oha_api.DevPlugIn();
	}
 });
 
//alert("Hello, othe TENS");
var DeviceId=-1;

/*timmer*/
var oha_timer=new oha_timer("#countdown.timer", 80);
oha_timer.timeup=function() {
    console.log("set timeup")
    jQuery("#powctrlbtn").hide();
    jQuery(".powerbtn.active").hide();
    jQuery(".powerbtn.noactive").show();
    jQuery("#timelimit.modebtn").removeClass("working");
}
//var oha_timer;
//$(window).load(function() {
//    oha_timer=new oha_timer("#countdown.timer", 60);
//})
  function setTimeBtn(num) {
//    		alert("setTimeBtn("+num+")");
  	jQuery(".timelist").each(function(i){
  		if(num==i) {
//   		alert(jQuery(this).text());
			jQuery("#timelimit.modebtn span.nowtime").text(jQuery(this).text());
			var newtime= jQuery(this).find("span.time").text();
			jQuery(".timer").text(newtime+":00");
            oha_timer.setTime(newtime*60);
            oha_timer.stop();
//			settime(i);
  		}
  	});
  }

  function setModeBtn(num) {
//    		alert("setModeBtn("+num+")");
    jQuery(".modelist").each(function(i){
    	if(num==i) {
//    		alert(jQuery(this).text());
			jQuery("#mode.modebtn span").text(jQuery(this).text());
			newmode= i;
			setPattern(i);
    	}
    })
  }


function setcookies(){
	
    if(oha_api.cookieRead('testmode', 'no')!="yes"){	
        jQuery("#status div").text(oha_api.cookieRead('strength', '1'));
		num=oha_api.cookieRead('mode', '0');
		setModeBtn(num);
		num=oha_api.cookieRead('timer', '0');
		setTimeBtn(num);
  }
	
}
  
  function sendOhaCmd(param) {
  	var _cmdStr="oha://";
  	for(i=0;i<param.length;i++) {
  		if(i!=0) {
  			_cmdStr+="_";
  		}
  		_cmdStr+=cmdTrans(param[i]);
  	}
  	setTimeout(function() {
  		window.location.href=_cmdStr;
//  	alert("sendOhaCmd("+_cmdStr+")");
  		}, 100);
//  	window.location.href=_cmdStr;
  }
  
  function cmdTrans(_str) {
		var _tmpStr=_str.replace(/%/g, "%%");
		_tmpStr=_tmpStr.replace(/_/g, "%_");
		return _tmpStr;
  }
  


oha_api.DevPlugIn=function (){
    DeviceId=oha_api.DeviceId;
    jQuery("#detect").hide();
    jQuery("#powerbtn").show();
}
	 
oha_api.DevPlugOut=function (){
    jQuery(".loadingdot").removeClass("active");
    jQuery("#detect").show();
    jQuery("#powerbtn").hide();
    jQuery("#NoDevMsg").show();
    jQuery(".powctrlbtn.stop").click();
    jQuery("#powctrlbtn").hide();
    jQuery(".powerbtn.active").hide();
    DeviceId=oha_api.DeviceId;
}


jQuery(document).ready(function(){

 function circle(rad,cname,angle){
	var radius = rad;
	var avd = angle/($(cname).length-1); 
     if(Math.abs(angle)==360) {
         avd = angle/$(cname).length; 
     }
	var ahd = avd*Math.PI/180;
	 $(cname).each(function(i){
            $(this).css({"left":Math.sin((ahd*i))*radius,"top":Math.cos((ahd*i))*radius*(-1)-10});
        });	
	 }
 
// function usingtime(){
//	return Number(jQuery(".resettimer").text()*60)-Number($("#countdown").text().substr(0,2)*60)-Number($("#countdown").text().substr(3,2));
//	 }	 



/*POWER*/	
  jQuery(".powerbtn.noactive").click(function(){
	  //当电源键按下后，添加强度、方式等设置代码
	  oha_api.runOhaCmd("omass://opmode_0");
	  //oha_api.runOhaCmd("omass://pattern_2");
	  oha_api.runOhaCmd("omass://str_5");
	  oha_api.runOhaCmd("omass://freq_5");
	  oha_api.runOhaCmd("omass://start");
	  oha_timer.play();
	  jQuery(this).hide();
	  jQuery(".powerbtn.active").show();
      jQuery("#timelimit.modebtn").addClass("working");
	  jQuery(".strctrl").addClass("working");
  })
  jQuery(".powerbtn.active").click(function(){
	  oha_timer.pause()
	  jQuery(this).hide();
	  jQuery("#powctrlbtn").show();
      jQuery("#timelimit.modebtn").removeClass("working");
  })
   jQuery(".powctrlbtn.play").click(function(){
	  oha_timer.play(); 
	  jQuery("#powctrlbtn").hide();
	  jQuery(".powerbtn.active").show();
      jQuery("#timelimit.modebtn").addClass("working");
	  $("#playstus").text("暂停_测试版");
	  jQuery(".strctrl").addClass("working");
  })
     jQuery(".powctrlbtn.stop").click(function(){
	  oha_timer.stop();
	  jQuery("#powctrlbtn").hide();
	  jQuery(".powerbtn.noactive").show();
          jQuery("#timelimit.modebtn").removeClass("working");
//		  jQuery(".strctrl").removeClass("working");
	    
	  //$("#playstus").text("啟動");
  })

  jQuery("#timelimit.modebtn").click(function(){
      if(!jQuery(this).hasClass("working")){  
        jQuery(this).toggleClass("active"); 
        jQuery(".blackbg").fadeToggle(); 
        /*jQuery(".modelist").toggleClass("active");*/
        if(jQuery(this).hasClass("active")){
            circle($(window).width()/3.5,"#timelimit.modebtn.active .timelist",-360);
        }else{
            circle(0,"#timelimit.modebtn .timelist",-360);
        } 
      }
  }) 
  jQuery(".blackbg").click(function(){
	jQuery("#timelimit.modebtn.active,#mode.modebtn.active").click();
  
  })
  
  jQuery(".timelist").each(function(i){
	  jQuery(this).addClass("timelist"+i);
	 
	  jQuery(this).click(function(){
	   
	   	setTimeBtn(i);
          oha_api.cookieWrite('timer',i);
//		$.cookie('timer',i, { expires: 365 }); 
		
	  })
  })
  
})

 // detecting() 偵測中
 // detectok() 偵測完畢 顯示POWER
 // plugout()  回到最初畫面
 // timeset(n) 輸入時間
 // circle(rad,cname,angle) 半徑、class name、圓周角度
