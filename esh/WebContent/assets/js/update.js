/****************************************
 * @Author: NISAL
 * 
 * 
****************************************/

// ajax请求url
var ajaxURL = {
  'signIn'         : './signIn',         // 登入
  'signUp'         : './signUp',         // 注册
  'getAcupInfo'    : './getAcupInfo',    // 获取穴位信息
  'getAcupList'    : './getAcupList',    // 获取穴位列表
  'getAcupOpInfo'  : './getAcupOpInfo',  // 获取穴位理疗时候的参数
  'upLoadOpValues' : './upLoadOpValues', // 上传用户的理疗数据
  'updateUserInfo' : './updateUserInfo', // 更新用户个人资料
  'getHistOpData'  : './getHistOpData',  // 获取历史治疗列表
  'question'       : './question',       // 问卷
  'userInfo'       : './userInfo',       // 个人信息页初始化
};

var userId = oha_api.cookieRead("userId", "null");

document.body.addEventListener('touchstart', function(){}); 
function MsgBox(str){layer.msg(str);}
function ajax(obj){
  var loading = layer.load(2);
  $.ajax({
    type: obj.type ? obj.type : 'GET',
    url: obj.url,
    data: obj.data,
    dataType: obj.dataType ? obj.dataType : 'json',
    success: function(data){layer.close(loading); obj.success(data);},
    error: function(jqXHR){layer.close(loading); layer.msg(jqXHR.status);}
  });
}
$('#backBtn span').click(function(){history.go(-1);});
function setAni(ele, aniName, opts){
  if(! opts) opts = {duration: '1s', delay: '0s', count: '1'};
  typeof ele === 'string' ? ele = $(ele) : ele = ele;
  ele.css({'animation-duration': opts.duration, 'animation-delay': opts.delay, 'animation-iteration-count': opts.count});
  ele.addClass('animated ' + aniName);
  setTimeout(function(){ ele.removeClass('animated ' + aniName); }, (parseFloat(opts.duration) * 1000 + parseFloat(opts.delay) * 1000));
};

function queryString2obj(url){
  var queryString = url.split('?')[1].split('&');
  var obj = {};
  for(var i = 0; i <queryString.length; i ++){
    obj[queryString[i].split('=')[0]] = queryString[i].split('=')[1];
  }
  return obj;
}

var userId  = oha_api.cookieRead("userId", "null") || '1';

// signIn
(function(){
  if(!$('#signIn')[0]) return;
  $('#signIn #login').click(function(){
    var account  = $('#signIn #account').val();
    var password = $('#signIn #password').val();
    if(account === '' || password === '') return MsgBox('用户名或密码不能为空');
    var obj = JSON.stringify({account: account, password: password});
    ajax({
      data: {
        'signin': obj
      },
      url: ajaxURL['signIn'],
      success: function(data){
        if(data.status){
          oha_api.cookieRemoveAll();
          oha_api.cookieWrite("userId", data.userId);
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
  if(!$('#op')[0]) return;
  var timerFlag   = 0;
  var time        = 30 * 60;
  var timer       = null;
  var pageStatus  = 0; // 0选择设备 1穴位说明 2治疗 3推荐
  var infoState   = -100;
  var iconState   = 'glyphicon-chevron-down';
  var cursympId   = "";
  var curAcupId   = "";
  var startFlag   = 1;
  var initObj     = {};
  var curSympNmae = "";
  var curAcupName = "";
  var timerCount   = 0;

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
      esh.setLevel(0, parseInt(initObj.devStrength));
      esh.setLevel(1, parseInt(initObj.devFrequency));
      esh.setLevel(2, parseInt(initObj.devPulse));
      //time = parseInt(initObj.devTime) * 60;
      timer = setInterval(function(){
        $('#op #timerCurVal').html(parseInt(time / 60) + '分' + (time % 60) + '秒');
        if(timerFlag){
          time --;
          timerCount ++;
        }
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
      if(parseInt(initObj.devMode) !== 3){
        var val = esh.setOpMode(1);
        esh.setParameterMode(parseInt(initObj.devMode));
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
    $.ajax({
      type: 'GET',
      url: './getAcupOpInfo',
      dataType: 'json',
      data: {
        'type': 'update',
        'upLoadOpValues': JSON.stringify({
          "userId": userId, 
          "diseaseId": cursympId,
          "acupointId": curAcupId, 
          "devStrength": esh.getLevel(0), 
          "devMode": esh.getPatternMode(), 
          "devFrequency": esh.getLevel(1), 
          "devPulse": esh.getLevel(2), 
          "devTime": (timerCount/60).toFixed(2)
        })
      },
      success: function(data){
        if(data.status) return window.location.href = data.url + '?acupId=' + curAcupId + '&diseaseId=' + cursympId;
        MsgBox(data.msg);
      },
      error: function(jqXHR){
        layer.msg(jqXHR.status);
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
    $('#selectOpList').show(function(){
      $("#describeWarpper #selectOpList").animate({right: "0px"}, 100);
    });
  });

  $("#describeWarpper #hideList").click(function(){
    $("#describeWarpper #selectOpList").animate({right: "-100%"}, 100);
  });

  function getAcupInfo(that){
    ajax({
      type: 'GET',
      url: './getAcupInfo',
      data: {
        'type': 'detail',
        'acupId': that.find('input[type="hidden"]').val()
      },
      success: function(data){
        if(data.status){
          curAcupId = data.msg.acupointId;
          curSympNmae = data.msg.acupointName;
          pageStatus = 1;
          
          $('header #title').html(data.msg.acupointName);
          $('#describeWarpper #describe #describeInfo .article').html(data.msg.description);

          $('#describeWarpper #img img').attr('src', data.msg.img);
          $('#describe').show();
          $('#selectOpMode').show();
          $('#describeInfo').show(function(){
            $('#describe').animate({right: "0"}, 100);
            $('#selectOpMode').animate({right: "15px"}, 100);
            $('#describeInfo').animate({right: "0"}, 100);
          });

          $('#recommand').fadeOut();
          return;
        }
        MsgBox(data.msg);
      }
    });
  }

  // 获取推荐信息
  function getRecommandInfo(that){
    pageStatus = 3;
    var diseaseId = that.find('input').val();
    cursympId = diseaseId;
    curAcupName = that.text();
    ajax({
      url: ajaxURL['getAcupList'],
      data: {
        type: 'init',
        'getAcupList': JSON.stringify({'userId': userId, 'diseaseId': cursympId})
      },
      success: function(data){
    	$('#recommand').empty();
    	$('#selectOpList ul').empty();
        if(data[0]){
          for(var i = 0; i < data[1].length; i ++){
            var acupBlock = $('<div></div>');
            var title = $('<div></div>');
            var star  = $('<div></div>');
            var counts = $('<div></div>');
            var userCount = $('<div></div>');
            var input = $('<input>');

            
            acupBlock.addClass('acupBlock');
            title.addClass('title');
            star.addClass('star');
            counts.addClass('counts');
            userCount.addClass('userCount');

            star.css('display', 'inline-block');
            userCount.css('display', 'inline-block');

            title.html(data[1][i].diseaseName + '-' + data[1][i].acupointName);
            star.html('推荐指数:');
            input.val(data[1][i].acupointId);
            input.attr('type', 'hidden');
            
            var stars = parseInt(data[1][i].suggestEvaluation);
            var starInnerHTML = '';
            for(var j = 0; j < 5; j ++){
              if(stars >= 1) starInnerHTML += '<span class="fa fa-star"></span>';
              if(stars <= 0) starInnerHTML += '<span class="fa fa-star-o"></span>'
              if(stars <1 && stars > 0) starInnerHTML += '<span class="fa fa-star"></span>';
              stars --;
            }
            star.append(starInnerHTML);
            userCount.append('&emsp;<span class="fa fa-fire"></span> ' + data[1][i].sumOfCustomer);

            counts.html(data[1][i].acupointDescribe);
            star.append(userCount);
            acupBlock.append(title, star, counts, input);

            acupBlock.on('click', function(){
              getAcupInfo($(this));
            });

            var $li = $('<li></li>');
            $li.addClass('chgDescribe');
            $li.html('<input type="hidden" value="' + data[1][i].acupointId +'"><a href="javascript:;">' + data[1][i].acupointName + '</a>');
            $('#selectOpList ul').append($li);
            $li.on('click', function(){
              getAcupInfo($(this));
              $('#selectOpList #hideList').click();
            });

            $('#recommand').append(acupBlock);
          }
          $('#selectMode').fadeOut();
          $('#recommand').fadeIn();
          return;
        }
        return MsgBox(data.msg);
      }
    });
  }

  // 初始症状
  ajax({
    url: './nav',
    data: {'type':'init'},
    success: function(data){
      if(data[0]){
        var diselist = data[2];
        for(var i = 0; i < diselist.length; i ++){
          
          var $list = $('<li></li>');
          $list.addClass('list');
          var $menuTitle = $('<div></div>');
          $menuTitle.addClass('menuTitle');
          var $input = $('<input>');
          $input.attr('type', 'hidden');
          $input.attr('value', diselist[i].key);
          $input.addClass('sympId');
          var $ul = $('<ul></ul>');
          var title = '';
          for(var j = 0; j < diselist[i].value.length; j ++){
            if(diselist[i].key === diselist[i].value[j].nodeId){
              title = diselist[i].value[j].diseaseName;
              continue;
            }
            var $li = $('<li></li>');
            var $a = '<a href="javascript:;" class="listItem">' + diselist[i].value[j].diseaseName + '<input type="hidden" value="' + diselist[i].value[j].nodeId + '"></a>';
            $li.append($a);
            $ul.append($li);
            $li.on('click', function(e){
              e.stopPropagation();
              var that = $(this);
              getRecommandInfo(that);
            });
          }
          $menuTitle.html(title);
          $list.append($menuTitle, $input, $ul);

          $list.on('click', function(){
            $('#selectMode .list ul').slideUp();
            $(this).find('ul').slideToggle();
            $('#selectMode .list').removeClass('active');
            $(this).addClass('active');
          });
          // 随机生成手风琴子块左边框
          $('#selectMode .list li a').each(function(){
            $(this).css('border-left', '3px solid #' + (~~(Math.random() * (1 << 24))).toString(16));
          });

          // 随机生成手风琴父块左边框
          $('#selectMode .list .menuTitle').each(function(){
            $(this).css('border-left', '5px solid #' + (~~(Math.random() * (1 << 24))).toString(16));
          });
          $('#itemMemu ul').eq(0).append($list);
        }
        return;
      }
      MsgBox('读取失败 请重试');
    }
  });

  $("#selectMode .list").click(function(){
    $('#selectMode .list ul').slideUp();
    $(this).find('ul').slideToggle();
    $('#selectMode .list').removeClass('active');
    $(this).addClass('active');
  });

  // 穴位说明页开始治疗按钮
  $('#describeWarpper .startBtn').click(function(e){
    e.stopPropagation();
    e.preventDefault();
    pageStatus = 2;

    ajax({
      url: ajaxURL['getAcupOpInfo'],
      data: {
        type: 'init',
        'getAcupOpInfo': JSON.stringify({'userId': userId, 'acupointId': curAcupId, 'diseaseId': cursympId})
      },
      success: function(data){
        if(data.status){
          initObj = data.msg;
          time = parseInt(initObj.devTime) * 60;
          $('#curLevel').html(data.msg.devStrength+'级');
          $('#freqCurVal').html(data.msg.devFrequency);
          $('#timerCurVal').html(parseInt(data.msg.devTime)+ '分' + ((parseFloat(data.msg.devTime) - parseInt(data.msg.devTime)).toFixed(2) * 60) + '秒');
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
      case 3:
        $('#title').text('选择症状');
        $('#recommand').fadeOut();
        $('#selectMode').fadeIn();
        break;
      default:
        break;
    }
  });

  // 查看是否为继续治疗
  if(window.location.href.split("?")[1]){
    var data = queryString2obj(window.location.href);
    initObj = {
      "strength": data.devStrength,
      "frequency": data.devFrequency,
      "width": data.devPulse,
      "mode": data.devMode,
      "time": data.devTime
    };
    $('#curLevel').html(data.devStrength);
    $('#freqCurVal').html(data.devFrequency);
    $('#curLevel').html(data.devPulse);
    $('#timerCurVal').html(parseInt(data.devTime)+ '分' + ((parseFloat(data.devTime) - parseInt(data.devTime)).toFixed(2) * 60) + '秒');
    $('#selectMode').fadeOut();
    $('#op').show(function(){$('#op').animate({'right': '0'});});
    $('#backBtn div').fadeOut();
  }

})();

// userMenu
(function(){
  if(!$('#userMenu')[0]) return;
  $('#menuBtn .glyphicon-log-out').click(function(e){
    e.stopPropagation();
    e.preventDefault();
    var that = $(this);
    $.confirm("确定要退出吗?", function() {
      window.location.href = that.attr('href');
    }, function(){return;});
  });
  $('#userMenu #menuLists ul li').click(function(){
    window.location.href = $(this).find('input').val();
  });

  ajax({
    type: 'GET',
    url: ajaxURL['userInfo'],
    data: {
      'type': 'init',
      'userId': userId
    },
    success: function(data){
      if(data.status){
        $('#totalTime').html(data.msg.toaTime);
        $('header #title').html(data.msg.uname);
        $('#userMenu #nickName').html(data.msg.uname);
        $('#userMenu #head #headImg img').attr('src', data.msg.uhpic);
        return;
      }
      MsgBox(data.msg);
    }
  })

})();

// editor
(function(){
  if(!$('#editor')[0]) return;
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
      data: { 'updateUserInfo': JSON.stringify({sex: sex, birthday: bir, id: userId}) },
      url: ajaxURL['updateUserInfo'],
      success: function(data){ MsgBox(data.msg); }
    });
  });
})();

// home
(function(){
  if(!$('#home')[0]) return;
  setAni('#lastTimeData', 'fadeInLeft');
  var lastData = {};

  // 请求治疗历史
  ajax({
    url: ajaxURL['getHistOpData'],
    data:{
      'type': 'init',
      'userId': userId
    },
    success: function(data){
      if(data[0]){
        lastData = data[1][0];
        $('#home #lastTimeData .right .time').text(data[1][0].lastCueTime);
        $('#home #lastTimeData .right .disease').text(data[1][0].diseaseName);
        $('#home #lastTimeData .right .acupoint').text(data[1][0].acupointName);
        $('#home #lastTimeData .right .devTime').text(data[1][0].devTime);
        $('#home #lastTimeData .right .period').text(data[1][0].usrPeriod + '/' + data[1][0].devPeriod);
        return;
      }
      layer.msg(data[1]);
    },
  });

  $('#home #lastTimeData button').click(function(){
    ajax({
      url: ajaxURL['getAcupOpInfo'],
      data:{
        'type':'init',
        'getAcupOpInfo': JSON.stringify({"userId": userId, "acupointId": lastData.acupointId, "diseaseId": lastData.diseaseId})
      },
      success: function(data){
        if(data.status){
          var str = 'acupointId=' + data.msg.acupointId + '&devFrequency=' + data.msg.devFrequency + 
                    '&devMode=' + data.msg.devMode + '&devPulse=' + data.msg.devPulse + '&devStrength=' + data.msg.devStrength + 
                    '&devTime=' + data.msg.devTime + '&diseaseId=' + data.msg.diseaseId + '&diseaseName=' + data.msg.diseaseName + 
                    '&diseaseParentName=' + data.msg.diseaseParentName + '&userId=' + data.msg.userId;
          return window.location.href = data.url + "?" + str;
        }
        layer.msg(data.msg);
      }
    });
  });

})();

// questionnaire
(function(){
  if(!$('#questionnaire')[0]) return;
  var data = window.location.href.split('?')[1].split('&');
  var acupId = data[0].split('=')[1];
  var diseaseId = data[1].split('=')[1];

  $('#update').click(function(){
    var usaf = $("input[name='usaf']:checked").val();
    var ueffec = $('#ueffec').val();
    ajax({
      type: 'GET',
      url: ajaxURL['question'],
      data: {
        'type': 'add',
        'question': JSON.stringify({
          'userId': userId,
          'acupId': acupId,
          'diseaseId': diseaseId,
          'usaf': usaf,
          'ueffec': ueffec
        })
      },
      success: function(data){
        if(data.status) return window.location.href = data.url;
        MsgBox(data.msg);
      }
    });
  });
})();
