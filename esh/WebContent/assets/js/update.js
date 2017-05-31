/******************************
 * @Author: NISAL
 * 
 * 
******************************/

// ajax请求url
var ajaxURL = {
  'signIn'         : './signIn',         // 登入
  'signUp'         : './signUp',         // 注册
  'getAcupInfo'    : './getAcupInfo',    // 获取穴位信息
  'getAcupList'    : './getAcupList',    // 获取穴位列表
  'getAcupOpInfo'  : './getAcupOpInfo',  // 获取穴位理疗时候的参数
  'upLoadOpValues' : './upLoadOpValues', // 上传用户的理疗数据
  'updateUserInfo' : './updateUserInfo', // 更新用户个人资料
};

document.body.addEventListener('touchstart', function(){}); 
function MsgBox(str){layer.msg(str);}
function ajax(obj){
  var loading = layer.load(2);
  $.ajax({
    type: "GET",
    url: obj.url,
    data: obj.data,
    dataType: "json",
    success: function(data){layer.close(loading); obj.success(data);},
    error: function(jqXHR){layer.close(loading); layer.msg(jqXHR.status);}
  });
}
$('#backBtn span').click(function(){history.go(-1);});

// signIn
(function(){
  $('#signIn #login').click(function(){
    var account  = $('#signIn #account').val();
    var password = $('#signIn #password').val();
    if(account === '' || password === '') return MsgBox('用户名或密码不能为空');
    var obj = JSON.stringify({account: account, password: password});
    ajax({
      data: {
        'signin': obj,
      },
      url: ajaxURL['signIn'],
      success: function(data){
        if(data.status){
          //alert('ajax: '+data.userId);
          localStorage.inputText = data.userId;//保存用户userId
          oha_api.cookieWrite('userId',data.userId);
          //alert('cookie: '+oha_api.cookieRead('userId','null'));
          window.location.href = data.url;
          return;
        }
        MsgBox(data.msg);
      }
    });
  });
})();

// signUp
(function(){
  $('#signUp #form button').click(function(){
    var account = $('#signUp #form #account').val();
    var password = $('#signUp #form #password').val();
    if(account === '' || password === '') return MsgBox('请输入账号和密码');
    if(/((?=[\\x21-\\x7e]+)[^A-Za-z0-9@])/g.test(account)) return MsgBox('用户名不符合规范');
    var obj = JSON.stringify({account: account, password: password});
    ajax({
      data: {
        'signup': obj
      },
      url: ajaxURL['signUp'],
      success: function(data){
        if(data.status){
          
          window.location.href = data.url;
          return;
        }
        MsgBox(data.msg);
      }
    });
  });
})();

// op 
(function(){
  var timerFlag   = 0;
  var time        = 30 * 60;
  var timer       = null;
  var pageStatus  = 0; // 0选择设备 1穴位说明 2治疗
  var infoState   = -100;
  var iconState   = 'glyphicon-chevron-down';
  var cursympId   = "";
  var curAcupId   = "";
  var startFlag   = 1;
  var initObj     = {};
  var curSympNmae = "";
  var curAcupName = "";

  function setTime(val){
    val *= 60;
    if(time + val < 30) return MsgBox('减去的时间大于剩余时间');
    if(time + val > (60 * 120)) return MsgBox('不能大于两个小时');
    time += val;     
    MsgBox('改变时间成功') 
    $('#op #timerCurVal').html(parseInt(time / 60) + '分' + (time % 60) + '秒');
  }

  $('#op .chgTime').click(function(){
    var val = parseInt($(this).val());
    setTime(val);
  });

  $('#op .diyTime').click(function(){
    var val = parseInt($('#op #diyTime').val());
    if($(this).val() === '0'){
      setTime(val);
      return;
    }
    setTime(val * -1);
  });

  $('#op #settings').click(function(){
    $('#op .settings').slideDown();
  });

  function setDevState(str){
    $('#op #devState').html(str);
  }

  $('#op #startOrPauseBtn').click(function(){
    if(startFlag){
      esh.setLevel(0, parseInt(initObj.strength));
      esh.setLevel(1, parseInt(initObj.frequency));
      esh.setLevel(2, parseInt(initObj.width));
      time = parseInt(initObj.time) * 60;
      timer = setInterval(function(){
        $('#op #timerCurVal').html(parseInt(time / 60) + '分' + (time % 60) + '秒');
        if(timerFlag) time --;
        if(time < 0){
          esh.stop();
          clearInterval(timer);
          $.alert({
            title: '结束',
            text: '本次理疗结束',
            onOK: function () {
              upLoadOpValues();
            }
          });
          
        }
      },1000);
      if(parseInt(initObj.mode) !== 3){
        var val = esh.setOpMode(1);
        esh.setParameterMode(parseInt(initObj.mode));
      }
      startFlag = 0;
    }
    if($(this).val() === '1'){
      esh.start();
      $(this).html('暂停');
      $(this).val('2');
      MsgBox('设备开始运行');
      setDevState('运行中');
      timerFlag = 1;
      return;
    }
    esh.pause();
    $(this).html('开始');
    setDevState('暂停');
    $(this).val('1');
    timerFlag = 0;
    MsgBox('设备暂停运行');
  });

  $('#op #stopDev').click(function(){
	    var val = esh.stop();
	    if(val){
	      setDevState('停止');
	      $('#op #startOrPauseBtn').val('1');
	      $('#op #startOrPauseBtn').html('开始');
	      timerFlag = 0;
	      $.alert({
	        title: '结束',
	        text: '本次理疗结束',
	        onOK: function () {
	          upLoadOpValues();
	        }
	      });
	      return;
	    }
	    MsgBox('设备不在工作状态');
	  });

  function upLoadOpValues(){
    ajax({
      data: {
    	type:'update',
    	upLoadOpValues: JSON.stringify({"userId": 9, "acupointId": 2, "diseaseId": 2, "devStrength": 5, "devMode": 1, "devFrequency": 4, "devTime": 23, "devPulse": 3})
        //upLoadOpValues: JSON.stringify({"symptomId": cursympId, "acupunctureId": curAcupId, "strength": esh.getLevel(0), "frequency": esh.getLevel(1), "width": esh.getLevel(2), "pattrenMode": esh.getPatternMode(), "opMode": esh.getOpMode()})
      },
      url: ajaxURL['getAcupOpInfo'],
      success: function(data){
        if(data.status){
          window.location.href = data.url;
          return;
        }
        MsgBox(data.msg);
      }
    });
  }

  $('#op #levelUp').click(function(){
    var val = esh.plus(0, 1);
    val === 233 ? MsgBox('增加强度成功') : MsgBox('增加强度失败');  
    $('#op #curLevel').html(esh.getLevel(0) + '级');

  });

  $('#op #levelDown').click(function(){
    var val = esh.minus(0, 1);
    val === 233 ? MsgBox('减少强度成功') : MsgBox('减少强度失败');
    $('#op #curLevel').html(esh.getLevel(0) + '级');
  });

  $('#op .modeBtn').click(function(){
    if($(this).val() !== '3'){
      var val = esh.setOpMode(1);
      if(val !== 233){
        MsgBox('修改失败');
        return;            
      }
      esh.setParameterMode(parseInt($(this).val()));
      $('#op .modeBtn').removeClass('active');
      $(this).addClass('active');
      MsgBox('修改成功');
      return;
    }
    var val = esh.setOpMode(0);
    if(val !== 233){
      MsgBox('修改失败');
      return;            
    }
    $('#op .modeBtn').removeClass('active');
    $(this).addClass('active');
  });

  $('#op .freqOp').click(function(){
    if($(this).hasClass('glyphicon-plus')){
      var val = esh.plus(1, 1);
      if(val === 233){
        MsgBox('增加频率成功');
        $('#op #freqCurVal').html(esh.getLevel(1));
        return;
      }
      MsgBox('增加频率失败');
    }
    var val = esh.minus(1, 1);
    if(val === 233){
      MsgBox('减少频率成功');
      $('#op #freqCurVal').html(esh.getLevel(1));
      return;
    }
    MsgBox('减少频率失败');
  });

  $('#op .pluseOp').click(function(){
    if($(this).hasClass('glyphicon-plus')){
      var val = esh.plus(2, 1);
      if(val === 233){
        MsgBox('增加脉冲成功');
        $('#op #pluseCurVal').html(esh.getLevel(2));
        return;
      }
      MsgBox('增加脉冲失败');
      return;
    }
    var val = esh.minus(2, 1);
    if(val === 233){
      MsgBox('减少脉冲成功');
      $('#op #pluseCurVal').html(esh.getLevel(2));
      return;
    }
    MsgBox('减少脉冲失败');
  });

  $('#op .diyTimeByNum').click(function(){
    var val = parseInt($('#op #diyTime').val()) || 0;
    if(val == 0) return;
    if($(this).val() == '1') val *= -1;
    setTime(val);
  });

  $('#describeWarpper .pinch-zoom').each(function(){
    new RTP.PinchZoom($(this), {});
  });
  $('#describeWarpper .pinch-zoom').click(function(){
    $(this).css("height", document.body.scrollHeight);
    $(this).off();
  });
  $("#describeWarpper #hideInfo").click(function(){
    $("#describeWarpper #describeInfo").animate({"bottom": infoState + 'px'});
    $(this).find('i').removeClass(iconState);
    iconState = /down$/g.test(iconState) ? 'glyphicon-chevron-up' : 'glyphicon-chevron-down';
    $(this).find('i').addClass(iconState);
    infoState = infoState < 0 ? 0 : -100;
  });

  $("#describeWarpper #selectOpMode").click(function(){
    $("#describeWarpper #selectOpList").animate({right: "0px"}, 100);
  });

  $("#describeWarpper #hideList").click(function(){
    $("#describeWarpper #selectOpList").animate({right: "-100%"}, 100);
  });

  $("#selectMode .list").click(function(){
    $('#selectMode .list ul').slideUp();
    $(this).find('ul').slideToggle();
    $('#selectMode .list').removeClass('active');
    $(this).addClass('active');
  });

  // 显示穴位详情
  $('#selectMode .listItem').click(function(e){
    e.stopPropagation();
    e.preventDefault();
    $('.chgDescribe').off('click');
    $('#selectOpList ul').empty();

    cursympId = $(this).parent().parent().parent().find('.sympId').val();
    curSympNmae = $(this).parent().parent().parent().find('.menuTitle').text();
    ajax({
      data: {
        //getAcupList: JSON.stringify({"cursympId": cursympId})
    	type:'init',
	    getAcupList:JSON.stringify({'userId': 9, 'diseaseId': 2})
      },
      url: ajaxURL['getAcupList'],
      success: function(data){
        if(data[0]){
          var acup=data[1];
          for(var i = 0; i < acup.length; i ++){
            var id = acup[i].acupointId;
            var name = acup[i].acupointName;
            var str = '<input type="hidden" value="' + id + '"><a href="javascript:;">' + name + '</a>';
            var li = $('<li></li>');
            li.addClass('chgDescribe');
            li.append(str);
            $('#selectOpList ul').append(li);
            setChgDescribeEvent();
          }
          return;
        }
        MsgBox(data.msg);
      }
    });
    curAcupName = $(this).text();
    getAcupInfo(cursympId, $(this).find('input').val())
    $('#title').text($(this).text());
    $('#selectMode').fadeOut();
    $('#describe').show();
    $('#selectOpMode').show();
    $('#describeInfo').show(function(){
      $('#describe').animate({right: "0"}, 100);
      $('#selectOpMode').animate({right: "15px"}, 100);
      $('#describeInfo').animate({right: "0"}, 100);
    });
    pageStatus = 1;
    return;
  });

  // 穴位说明页开始治疗按钮
  $('#describeWarpper .startBtn').click(function(e){
    e.stopPropagation();
    e.preventDefault();
    pageStatus = 2;

    // 获取和显示初始化设备运行参数
    ajax({
      data: {
    	  type:'init',
    	  getAcupOpInfo: JSON.stringify({"userId": 9, "acupointId": 2, "diseaseId": 2})
      },
      url: ajaxURL['getAcupOpInfo'],
      success: function(data){
        if(data.status){
         
          initObj = data;
          $('#curLevel').html(data.msg.devStrength);
          $('#freqCurVal').html(data.msg.devFrequency);
          $('#curLevel').html(data.width);
          $('#timerCurVal').html(data.msg.devTime + '分0秒');
          return;
        }
        Msgbox(data.msg);
      }
    });

    $('#title').text(curSympNmae + '-' + curAcupName);
    $('#op').show(function(){$('#op').animate({'right': '0'});});
    $('#selectOpMode').animate({right: "-100%"}, 100);
    $('#backBtn div').fadeOut();
    $('#describe').fadeOut();
    $('#selectOpMode').fadeOut();
    $('#describeInfo').fadeOut(function(){
      $('#describe').hide();
      $('#selectOpMode').hide();
      $('#describeInfo').hide();
    });
  });

  // 随机生成手风琴子块左边框
  $('#selectMode .list li a').each(function(){
    $(this).css('border-left', '3px solid #' + (~~(Math.random() * (1 << 24))).toString(16));
  });

  // 随机生成手风琴父块左边框
  $('#selectMode .list .menuTitle').each(function(){
    $(this).css('border-left', '5px solid #' + (~~(Math.random() * (1 << 24))).toString(16));
  });

  // 修改穴位信息图片和说明
  function setChgDescribeEvent(){
    $('.chgDescribe').on('click', function(){
      getAcupInfo(cursympId, $(this).find('input').val())
      curAcupName = $(this).text();
      $('#title').text($(this).text());
      $("#describeWarpper #selectOpList").animate({right: "-100%"}, 100);
    });
  }

  // 获取穴位信息
  function getAcupInfo(symId, acupId){
    ajax({
      data: {
        'type': 'detail',
        'acupId':acupId
      },
      url: ajaxURL['getAcupInfo'],
      success: function(data){
        if(data.status){
          curAcupId = acupId;
          $('#describe #img img').attr('src', data.msg.img);
          $('#describe #describeInfo .article').text(data.msg.description);
          return;
        }
        MsgBox(data.msg);
      }
    });
  }

  // treat 返回按钮
  $('#backBtn div').click(function(){
    switch (pageStatus) {
      case 0:
        history.go(-1);
        break;
      case 1:
        $('#title').text('选择症状');
        $("#describeWarpper #selectOpList").animate({right: "-100%"}, 100);
        $('#describe').animate({right: "-100%"}, 100);
        $('#selectOpMode').animate({right: "-100%"}, 100);
        $('#describeInfo').animate({right: "-100%"}, 100, function(){
          $('#describeInfo').hide();
          $('#selectOpMode').hide();
          $('#describe').hide();
          $("#describeWarpper #selectOpList").hide();
        });
        pageStatus = 0;
        $('#selectMode').fadeIn();
        break;
      default:
        break;
    }
  });
})();

// userMenu
(function(){
  $('#menuBtn .glyphicon-log-out').click(function(e){
    e.stopPropagation();
    e.preventDefault();
    var that = $(this);
    $.confirm("确定要退出吗?", function() {
    	$.cookie('id', null, {
    	  expires : new Date()
    	 });
      window.location.href = that.attr('href');
    }, function(){return;});
  });
  $('#userMenu #menuLists ul li').click(function(){
    window.location.href = $(this).find('input').val();
  });
})();

// editor
(function(){
  $('#editor #birthday').calendar();
  $('#editor #sex').picker({
    title: '',
    cols: [{
      textAlign: 'center',
      values: ['男', '女']
    }]
  });
  $('#saveUserInfo').click(function(){
    var sex = $('#sex').val() === '男' ? 0 : 1;
    var bir = $('#birthday').val();
    ajax({
      data: {
        'updateUserInfo': JSON.stringify({sex: sex, birthday: bir})
      },
      url: ajaxURL['updateUserInfo'],
      success: function(data){
        MsgBox(data.msg);
      }
    });
  });
})();