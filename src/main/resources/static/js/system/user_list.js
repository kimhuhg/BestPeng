

$(function(){
	loadDateTable();
	
	$("#btn_msg").on("click",function(){
		layer.open({
			title: '在线调试'
			,content: '可以填写任意的layer代码'
		});  
	});
});

function addMenu(){
	var param=$("#add_menu_form").serialize();
	$.post("/api/menu",param,function(data){
		if(data){
			$("#modal-add_menu").modal("hide");
			//弹出提示框
		}
	});
}

function loadDateTable(){
	$("#table-demo").html('<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%"></table>');
	
	$('#example').DataTable({
		"ajax": {
				"url":"/api/user",
				"type": "GET",
				"data":{  //提交到服务器的参数
					//"title":"123"
				}
		},
		"columns": [
		            {
		            	"title":"ID",
		            	"data":"id",
		            	"visible":false
		            },
		            {
		            	"title":"姓名",
		            	"data":"userName"
		            },
		            { 
		            	  "title":"邮箱",
		            	  "className":"hidden-480",
		            	  "data": "email"
		             },
		             {
		            	 "title":"创建日期",
		            	 "className":"hidden-480",//设置此列的class,'hidden-480':小于480的宽度后,不现实此列
		            	 "data": "createDate",
		            	  "render":function(data,type,row){
		            		  var d=new Date(data);
		            		  return d.getFullYear()+'年'+(d.getMonth()+1) +'月'+d.getDate() +'日';
		            	  }
		             },
		             { 
		            	"title":"角色",
		            	"data": "role",
		            	"render":function(data,type,row){
		            		return data.name;
		            	}
		             },
		             { 
			            "title":"用户组",
			            "data": "groupId"
			         },
		             { 
			            "title":"状态",
			            "data": "status"
			         },
		             { 
		            	"title":"操作",
		            	"orderable":false,//在该列上允许或者禁止排序功能
		            	 "data": "id",
		            	 "render":function(data, type, row ){
		            		 return "<button>编辑</button>&nbsp;<button>详情</button>";
		            	 }
		             }
		          ]
	});
}