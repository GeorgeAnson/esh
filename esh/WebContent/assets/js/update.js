/******************************
 * @Author: NISAL
 * 
 *
******************************/

// ajax请求url
var ajaxURL = {
  'signIn':      './signIn',         // 登入
  'signUp':      './signUp',         // 注册
  'getAcupInfo': './getAcupInfo',    // 获取穴位信息
  'getAcupList': './getAcupList',    // 获取穴位列表
  'getAcupOpInfo': './getAcupOpInfo', // 获取穴位理疗时候的参数
  'upLoadOpValues': './upLoadOpValues', // 上传用户的理疗数据
};

document.body.addEventListener('touchstart', function(){}); 
function MsgBox(str){ layer.msg(str); }
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
    if(account === '' || password === ''){
      MsgBox('用户名或密码不能为空');
      return;
    }
    var obj = JSON.stringify({'account': account, 'password': password});
    ajax({
      data: {
        'signin': obj
      },
      url: ajaxURL['signIn'],
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

// signUp
(function(){
  $('#signUp #form button').click(function(){
    var account = $('#signUp #form #account').val();
    var password = $('#signUp #form #password').val();
    if(account === '' || password === ''){
      MsgBox('请输入账号和密码');
      return;
    }
    
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
  var timerFlag = 0;
  var time = 30 * 60;
  var timer = null;
  var pageStatus = 0; // 0选择设备 1穴位说明 2治疗
  var infoState = -100;
  var iconState = 'glyphicon-chevron-down';
  var cursympId = "";
  var curAcupId = "";
  var startFlag = 1;
  var initObj   = {};
  function setTime(val){
    val *= 60;
    if(time + val < 30){
      MsgBox('减去的时间大于剩余时间');
      return;
    }
    if(time + val > (60 * 120)){
      MsgBox('不能大于两个小时');
      return;
    }
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
        upLoadOpValues: JSON.stringify({"symptomId": cursympId, "acupunctureId": curAcupId, "strength": esh.getLevel(0), "frequency": esh.getLevel(1), "width": esh.getLevel(2), "pattrenMode": esh.getPatternMode(), "opMode": esh.getOpMode()})
      },
      url: ajaxURL['upLoadOpValues'],
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

  $('#selectMode .listItem').click(function(e){
    e.stopPropagation();
    e.preventDefault();
    $('.chgDescribe').off('click');
    $('#selectOpList ul').empty();

    cursympId = $(this).parent().parent().parent().find('.sympId').val();
    ajax({
      data: {
        getAcupList: JSON.stringify({"cursympId": cursympId})
      },
      url: ajaxURL['getAcupList'],
      success: function(data){
        if(data.status){
          for(var i = 0; i < data.acupList.length; i ++){
            var id = data.acupList[i].id;
            var name = data.acupList[i].name;
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
    getAcupInfo(cursympId, $(this).find('input').val())
    $('#title').text($(this).text());
    $('#selectMode').fadeOut();
    $('#describe').animate({right: "0"}, 100);
    $('#selectOpMode').animate({right: "15px"}, 100);
    $('#describeInfo').animate({right: "0"}, 100);
    pageStatus = 1;
    return;
  });

  $('#describeWarpper .startBtn').click(function(e){
    e.stopPropagation();
    e.preventDefault();
    pageStatus = 2;

    ajax({
      data: {
        getAcupOpInfo: JSON.stringify({"symptomId": cursympId, "acupunctureId": curAcupId})
      },
      url: ajaxURL['getAcupOpInfo'],
      success: function(data){
        if(data.status){
          alert(JSON.stringify(data));
          initObj = data;
          $('#curLevel').html(data.strength);
          $('#freqCurVal').html(data.frequency);
          $('#curLevel').html(data.width);
          $('#timerCurVal').html(data.time + '分0秒');
          return;
        }
        Msgbox(data.msg);
      }
    });

    $('#title').text('脑壳疼-人中穴');
    $('#op').animate({'right': '0'});
    $('#selectOpMode').animate({right: "-100%"}, 100);
    $('#backBtn div').fadeOut();
    $('#describe').fadeOut();
    $('#selectOpMode').fadeOut();
    $('#describeInfo').fadeOut();
  });

  $('#selectMode .list li a').each(function(){
    $(this).css('border-left', '3px solid #' + (~~(Math.random() * (1 << 24))).toString(16));
  });

  $('#selectMode .list .menuTitle').each(function(){
    $(this).css('border-left', '5px solid #' + (~~(Math.random() * (1 << 24))).toString(16));
  });

  function setChgDescribeEvent(){
    $('.chgDescribe').on('click', function(){
      getAcupInfo(cursympId, $(this).find('input').val())
      $('#title').text($(this).text());
      $("#describeWarpper #selectOpList").animate({right: "-100%"}, 100);
    });
  }

  // 获取穴位信息
  function getAcupInfo(symId, acupId){
    ajax({
      data: {
        'getAcupInfo': JSON.stringify({'symptomId': symId, 'acupunctureId': acupId})
      },
      url: ajaxURL['getAcupInfo'],
      success: function(data){
        if(data.status){
          curAcupId = acupId;
          $('#describe #img img').attr('src', data.imgURL);
          $('#describe #describeInfo .article').text(data.acupunctureText);
          return;
        }
        MsgBox(data.msg);
      }
    });
  }

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
        $('#describeInfo').animate({right: "-100%"}, 100);
        pageStatus = 0;
        $('#selectMode').fadeIn();
        break;
      default:
        break;
    }
  });
})();
