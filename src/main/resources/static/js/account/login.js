
$(function(){
	//登录按钮事件
	$("#login_btn").click(function(){
		login();
	});
	$("#register_btn").click(function(){
		register();
	});
	
});

function login(){
	//获取表单参数
	var param=$("#login_form").serialize();
	$.ajax({
		type:"POST",
		url:"/account/login",
		data:param,
		success:function(data){
			var m=$.parseJSON(data);
			if(m.code==0){
				window.location.href="/";
			}else{
				alert(m.msg);
			}
		},
		error:function(){
			alert("系统错误");
		}
	});
}
function register(){
	var param=$("#register_form").serialize();
	$.ajax({
		type:"POST",
		url:"/account/register",
		data:param,
		success:function(data){
			var m=$.parseJSON(data);
			if(m.code==0){
				window.location.href="/menu";
			}else{
				alert(m.msg);
			}
		},
		error:function(){
			alert("系统错误");
		}
	});
}