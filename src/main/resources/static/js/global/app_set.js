//全局页面框架设置js

$(function(){
	//界面设置
	setSkin('no-skin');
	setNavbar(true);
	setSlidebar(false);
	//菜单加载
	loadMenu();
});

//菜单树加载
function loadMenu(){
	var id=$("#menu_id");//当前页面菜单id
	$.get("/api/menu/list/tree/",function(data){
		if(data){
			$("#menu_load").html(data);
		}
	});
	
}

//固定导航栏、侧边栏、面包屑
function setNavbar(isSet){
	if(isSet){
		ace.settingFunction.navbar_fixed(null,true);
		ace.settingFunction.sidebar_fixed(null,true);
		ace.settingFunction.breadcrumbs_fixed(null,true);
	}
}

//简洁侧边栏
function setSlidebar(isSet){
	if(isSet){
		$("#sidebar li").addClass("hover");
		$("#sidebar").addClass("compact");
	}
}

function setSkin(name){
	b=name;//no-skin、skin-1、skin-2、skin-3
	var c = $(document.body);
	c.removeClass("no-skin skin-1 skin-2 skin-3"),
	c.addClass(b),
	ace.data.set("skin", b);
	var d = ["red", "blue", "green", ""];
	$(".ace-nav > li.grey").removeClass("dark"),
	$(".ace-nav > li").removeClass("no-border margin-1"),
	$(".ace-nav > li:not(:last-child)").removeClass("light-pink").find("> a > " + ace.vars[".icon"]).removeClass("pink").end().eq(0).find(".badge").removeClass("badge-warning"),
	$(".sidebar-shortcuts .btn").removeClass("btn-pink btn-white").find(ace.vars[".icon"]).removeClass("white"),
	$(".ace-nav > li.grey").removeClass("red").find(".badge").removeClass("badge-yellow"),
	$(".sidebar-shortcuts .btn").removeClass("btn-primary btn-white");
	var e = 0;
	$(".sidebar-shortcuts .btn").each(function () {
		$(this).find(ace.vars[".icon"]).removeClass(d[e++])
	});
	var f = ["btn-success", "btn-info", "btn-warning", "btn-danger"];
	if ("no-skin" == b) {
		var e = 0;
		$(".sidebar-shortcuts .btn").each(function () {
			$(this).attr("class", "btn " + f[e++ % 4])
		}),
		$(".sidebar[data-sidebar-scroll=true]").ace_sidebar_scroll("updateStyle", ""),
		$(".sidebar[data-sidebar-hover=true]").ace_sidebar_hover("updateStyle", "no-track scroll-thin")
	} else if ("skin-1" == b) {
		$(".ace-nav > li.grey").addClass("dark");
		var e = 0;
		$(".sidebar-shortcuts").find(".btn").each(function () {
			$(this).attr("class", "btn " + f[e++ % 4])
		}),
		$(".sidebar[data-sidebar-scroll=true]").ace_sidebar_scroll("updateStyle", "scroll-white no-track"),
		$(".sidebar[data-sidebar-hover=true]").ace_sidebar_hover("updateStyle", "no-track scroll-thin scroll-white")
	} else if ("skin-2" == b)
		$(".ace-nav > li").addClass("no-border margin-1"), $(".ace-nav > li:not(:last-child)").addClass("light-pink").find("> a > " + ace.vars[".icon"]).addClass("pink").end().eq(0).find(".badge").addClass("badge-warning"), $(".sidebar-shortcuts .btn").attr("class", "btn btn-white btn-pink").find(ace.vars[".icon"]).addClass("white"), $(".sidebar[data-sidebar-scroll=true]").ace_sidebar_scroll("updateStyle", "scroll-white no-track"), $(".sidebar[data-sidebar-hover=true]").ace_sidebar_hover("updateStyle", "no-track scroll-thin scroll-white");
	else if ("skin-3" == b) {
		c.addClass("no-skin"),
		$(".ace-nav > li.grey").addClass("red").find(".badge").addClass("badge-yellow");
		var e = 0;
		$(".sidebar-shortcuts .btn").each(function () {
			$(this).attr("class", "btn btn-primary btn-white"),
			$(this).find(ace.vars[".icon"]).addClass(d[e++])
		}),
		$(".sidebar[data-sidebar-scroll=true]").ace_sidebar_scroll("updateStyle", "scroll-dark no-track"),
		$(".sidebar[data-sidebar-hover=true]").ace_sidebar_hover("updateStyle", "no-track scroll-thin")
	}
	$(".sidebar[data-sidebar-scroll=true]").ace_sidebar_scroll("reset"),
	ace.vars.old_ie&&ace.helper.redraw(document.body, !0)
}