/**********************************
 * @ESH API
 * @NISAL 2017-3-16 
**********************************/
var esh = {

  // private
  _modeList:[["omass://str_1", "omass://str_2", "omass://str_3", "omass://str_4", "omass://str_5", "omass://str_6", "omass://str_7", "omass://str_8", "omass://str_9", "omass://str_10", "omass://str_11", "omass://str_12", "omass://str_13", "omass://str_14", "omass://str_15", "omass://str_16"],
             ["omass://freq_1", "omass://freq_2", "omass://freq_3", "omass://freq_4", "omass://freq_5", "omass://freq_6", "omass://freq_7", "omass://freq_8", "omass://freq_9", "omass://freq_10", "omass://freq_11", "omass://freq_12", "omass://freq_13", "omass://freq_14", "omass://freq_15", "omass://freq_16"],
             ["omass://width_1", "omass://width_2", "omass://width_3", "omass://width_4", "omass://width_5", "omass://width_6", "omass://width7", "omass://width_8", "omass://width_9", "omass://width_10", "omass://width_11", "omass://width_12", "omass://width_13", "omass://width_14", "omass://width_15", "omass://width_15"]],

  _opmode: 0,
  _value: [1, 1, 4],
  _patternMode: 0,
  _start: 0,
  _dualMode: "on",

  _setValue: function(type, value){
    this._value[type] = value;
  },

  // public

  isWorking: function(){
    return this._start === 1 || this._start === 2 ? 1 : 0;
  },

  getState: function(){
    return this._start;
  },

  start: function(){
    if(this._start === 1) return 0;
    oha_api.runOhaCmd("omass://start");
    this._start = 1;
    return 233;
  },

  pause: function(){
    if(this._start !== 1 && this._start === 0) return 0;
    oha_api.runOhaCmd("omass://pause");
    this._start = 2;
    return 233;
  },

  stop: function(){
    if(!(this.isWorking())) return 0;
    oha_api.runOhaCmd("omass://stop");
    this._start = 0;
    return 233;
  },

  getLevel: function(type){
    if(!(this.isWorking())) return 10;
    if(type !== 0 && type !== 1 && type !== 2) return -1;
    return this._value[type];
  },

  getPatternMode: function(){
    return this._patternMode;
  },

  getOpmode: function(){
    return this._opmode;
  },

  getDualMode: function(){
    return this._dualMode === "on" ? 0 : 1;
  },

  setOpMode: function(type){
    if(!(this.isWorking())) return 10;
    if(type !== 0 && type !== 1) return 0;
    if(type === 1 && this._value[0] >= 13) this.setLevel(0, 1);
    if(type === 1) this.setDualMode("off");
    oha_api.runOhaCmd("omass://opmode_" + type);
    this._opmode = type;
    return 233;
  },

  setDualMode: function(mode){
    if(!(this.isWorking())) return 10;
    if(mode !== "off" && mode !== "on") return 0;
    if(this._opmode !== 0) return 1;
    oha_api.runOhaCmd("omass://dual_" + mode);
    this._dualMode = mode;
    return 233;
  },

  setParameterMode: function(type){
    if(!(this.isWorking())) return 10;
    if(type !== 0 && type !== 1 && type !== 2) return 0;
    if(this._opmode !== 1) return 1;
    oha_api.runOhaCmd("omass://pattern_" + type);
    this._patternMode = type;
    return 233;
  },

  setLevel: function(type, value){
    if(!(this.isWorking())) return 10;
    if(type !== 0 && type !== 1 && type !== 2) return 0;
    if(this._opmode === 1 && value >= 13) return 2;
    if(value <= 0 || value >=17) return 1;
    oha_api.runOhaCmd(this._modeList[type][value - 1]);
    this._setValue(type, value);
    return 233;
  },

  minus: function(type, value){
    if(!(this.isWorking())) return 10;
    if(type !== 0 && type !== 1 && type !== 2) return 0;
    if(this._value[type] - value <= 0) return 1;
    this.setLevel(type, this._value[type] - value);
    return 233;
  },

  plus: function(type, value){
    if(!(this.isWorking())) return 10;
    if(type !== 0 && type !== 1 && type !== 2) return 0;
    if(this._value[type] + value >= 17) return 1;
    this.setLevel(type, this._value[type] + value);
    return 233;
  },

  notice: function(){oha_api.runOhaCmd("omass://notice");}
};

oha_api.DevPlugIn = function(){
  $.alert("设备插入");
}

oha_api.DevPlugIn = function(){
  $.alert("设备拔出");
}