//console.log("oha_timer definition")
function oha_timer(_selector, _t, _max) {
    var defaultVaule={
        maxTime:-1, 
        minTime:1,        
        setTime:600,
        stepTime:60,
    }
    var startT, curT, remainSec;
    var timerH;     // Timer handler
    var setTime=defaultVaule.setTime;
    this.max=defaultVaule.maxTime;
    this.min=defaultVaule.minTime;
    this.setTime=function (_t) {
        setTime=_t;
        lastSetTime=_t;
        remainSec=_t;
        $(timerContainer).text(formatTime(setTime));
    }
   if(typeof _t!=="undefined") {
        var _tx=parseFloat(_t);
        if(_tx > 0) {
            this.setTime(_tx);
        }
    }
    if(typeof _max!=="undefined") {
        var max=parseInt(_max);
        if(max >0) {
            this.max=max;
        }
    }
    var lastSetTime=setTime;
    var timerContainer=_selector;
    var self=this;
    console.log("oha_timer conductor setTime="+setTime);
//    $(init);
    
    this.timeup=function() {
        console.log('this.timeup')
//        timeup();
    }
    
    this.setMax=function(_max) {
        if(typeof _max == "undefined") {
            console.log("oha_timer.setMax has no set time");
            return;
        }
        var max=parseInt(_max);
        if(max >0) {
            this.max=max;
        }
    }
    
     this.setMin=function(_min) {
        if(typeof _min == "undefined") {
            console.log("oha_timer.setMin has no set time");
            return;
        }
        var min=parseInt(_min);
        if(min >0) {
            this.min=min;
        }
    }
    
   this.incTime=function(_ts) {
        var t=defaultVaule.stepTime;    // Default = 60sec
        if(typeof _ts!=="undefined") {
            t=_ts;
        }
        if(this.max>0 && (this.max>Math.floor((setTime+t)/60))) {
            setTime+=t;
            lastSetTime+=t;
            remainSec+=t;
            show(remainSec);
        }
        else {
            if(typeof oha_api !=="undefined") {
                if(this.max>0) {
                    oha_api.showToast("Timer max. value arrives");
                }
            }
        }
       return setTime;
    }

    this.decTime=function(_ts) {
        var t=defaultVaule.stepTime;    // Default = 60sec
        if(typeof _ts!=="undefined") {
            t=parseInt(_ts);
        }
        if(remainSec-t>=this.min) {
            setTime-=t;
            lastSetTime-=t;
            remainSec-=t;
            show(remainSec);
       }
        else {
            if(typeof oha_api !=="undefined") {
                oha_api.showToast("Invalid operation!!");
            }
        }
        return setTime;
    }
    
    
    this.play=function() {
        startT=new Date().getTime();
        timerH=setInterval(function() {
            checkTime()
        }, 250);
    }
    
    this.pause=function(){
        clearInterval(timerH);
        lastSetTime=remainSec;
    }
    
    this.stop=function(){
        stop();
    }
    
    function init () {
//        console.log(timerContainer+","+setTime+","+formatTime(setTime));
        remainSec=_t;
        $(window).ready(function() {
            $(timerContainer).text(formatTime(setTime));
        })
//        $(timerContainer).text(formatTime(setTime));
    }
    
    function stop() {
        clearInterval(timerH);
        lastSetTime=setTime;
        remainSec=setTime;
        $(timerContainer).text(formatTime(setTime));
    }
    
    function checkTime() {
        curT=new Date().getTime();
        var _pastSec=Math.floor((curT-startT)/1000);
        var _remain=lastSetTime-_pastSec;
//        console.log('hello, checkTime, _remain='+_remain+", remainSec="+remainSec)
        if(_remain !==remainSec) {
            remainSec=_remain;
//            if(remainSec<0) {
//                remainSec=0;
//            }
//            $(timerContainer).text(formatTime(remainSec));
            show(remainSec);
        }
        if(remainSec<=0) {
            console.log("oha_timer stop")
            stop();
            self.timeup();
        }
    }
    
//    function timeup() {
//        console.log('timeup')
//        this.timeup();
//    }
    function show(_t) {
        $(timerContainer).text(formatTime(_t));
    }
    
    function formatTime(_sec) {
        var min = Math.floor(_sec / 60);
        sec = _sec - (min * 60);
        /*hundredths = pad(time - (sec * 100) - (min * 6000), 2)*/;
        return (min > 0 ? pad(min, 2) : "00") + ":" + pad(sec, 2);
    }
//    $(window).load(function() {
//        this.init();
//    })
    
    function pad(number, length) {
        var str = '' + number;
        while (str.length < length) {str = '0' + str;}
        return str;
    }

    $(init());
}

