// JavaScript Document
var oha_config={
	debug_mode:true,			// Debug mode, must be set as "false" before release
	sw_ver:0.920,
	model:"omass",				// Model Name, must be the same as Config.html
	page_name:"page0",			// Page Name, different page must be different page name
    default_language:"cn",
    api_path:"assets/js/oha/",
    html_config:{
		en:"default_us.html", 		// English
		tw:"default_tw.html", 		// Traditional Chinese
		cn:"default_cn.html", 		// Simplify Chinese
		kr:"default_kr.html",		// Korean
    },
};

var title_conf={
	title: {
		en:"oTHE Demo(v"+oha_config.sw_ver+")", 	// English
		tw:"奧樂Demo(v"+oha_config.sw_ver+")", 		// Traditional Chinese
		cn:"奥乐Demo(v"+oha_config.sw_ver+")", 		// Simplify Chinese
		kr:"oTHE Demo(v"+oha_config.sw_ver+")", 		// Korean
	},
	subtitle: {
		en:"oTHE OHA API Demo",  	// English
		tw:"奧樂OHA程式界面Demo", 		// Traditional Chinese
		cn:"奥乐OHA程式界面Demo", 		// Simplify Chinese
		kr:"oTHE OHA API Demo", 		// Korean
	},
};

oha_config.title=title_conf.title;
oha_config.subtitle=title_conf.subtitle;
