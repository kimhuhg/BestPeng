var zTree;
var table;
var validator=false;


$(function(){
	
	$("#table-demo").html('<table id="example" class="table table-striped table-bordered dt-responsive" cellspacing="0" width="100%"></table>');
	
	loadDateTable();
	//初始化树形菜单
	treeInit();	
	
	$("#menu_tree_btn").click(function(){
		treeBtn();
	});
	
	//接收表单验证通过的事件,验证通过后执行
	$('#add_menu_form').bind('valid.form', function(){
		addMenu();
	});
	
	
	//排序框初始化
	$('#spinner1').ace_spinner({value:0,min:0,max:100,step:10, btn_up_class:'btn-default' , btn_down_class:'btn-default'});
	
	//模态框隐藏后事件
	$('#modal-add_menu').on('hidden.bs.modal', function (e) {
		//重置表单
		$('#add_menu_form')[0].reset();
		$('#spinner1').val(0);//表单重置会设为空,所以设置0
	});
});

function treeInit(){
	var setting = {
    		data: {
    			simpleData: {
    				enable: true,
    				idKey: "id",
    				pIdKey: "parentId",
    				rootPId: -1
    			},
    			key: {
    				url:"nourl",
    				name:"menuName"
    			}
    		}
    };

    $.get("/api/menu/list",function(data){
		var menus=$.parseJSON(data);
		menus.push({id:0,menuName:"根菜单",open:true});
		zTree=$.fn.zTree.init($("#treeDemo"), setting, menus);
	});
}

//上级菜单选择
function treeBtn(){
	layer.open({
		type: 1,
		offset: '50px',
//		skin: 'layui-layer-molv',
		title: "选择菜单",
		area: ['300px', '450px'],
		shade: 0.1,//遮罩层
		shadeClose: false,//是否点击遮罩关闭
		content: jQuery("#menuLayer"),
		btn: ['确定', '取消'],
		btn1: function (index) {
			var node = zTree.getSelectedNodes();
			//选择上级菜单
			$("#menu_pname").val(node[0].menuName);
			$("#menu_pid").val(node[0].id);
			layer.close(index);
        }
	});
}

//添加或修改菜单
function addMenu(){
	//获取表单参数
	var param=$("#add_menu_form").serialize();
	$.post("/api/menu",param,function(data){
		var m=$.parseJSON(data);
		if(m.code==0){
			//隐藏模态框
			$("#modal-add_menu").modal("hide");
			// 刷新表格数据，分页信息不会重置
			table.ajax.reload(null,false);
			treeInit();
			//弹出提示框
			layer.msg(m.msg);
		}else{
			layer.msg(m.msg);
		}
	});
}
//删除菜单
function delMenu(id){
	$.ajax({
		type:"DELETE",
		url:"/api/menu/"+id,
		success:function(data){
			var m=$.parseJSON(data);
			
			// 刷新表格数据，分页信息不会重置
			table.ajax.reload(null,false);
			treeInit();
			layer.msg(m.msg);
		}
	});
	
}

/**dataTables**/
function loadDateTable() {
	table=$('#example').DataTable({
		"destroy":true,//销毁，重新加载
		"searching": true,//启用搜索
		"serverSide": false,//服务器端处理模式
		"processing": true,//打开加载提示
		//"stateSave": true,//保存最后一次分页信息、排序信息，当页面刷新，或者重新进入这个页面，恢复上次的状态。
		"ajax" : {
			"url" : "/api/menu",
			"type" : "GET",//请求方式
			"data" : { //提交到服务器的参数
				//"title":"123"
			}
		},
		"columns" : [ {
			            "className":'details-control',
			            "orderable":false,//禁止该列排序
			            "data":null,
			            "width":50,
			            "defaultContent": ''
			        },{
						"title" : "ID",
						"data" : "id",
						"visible":false,
						"className":"hidden-480"
					}, {
						"title" : "名称",
						"data" : "menuName"
					}, {
						"title" : "URL",
						"data" : "menuLink"
					}, {
						"title" : "上级菜单",
						"data" : "parentName",
						"className":"hidden-480 hidden-xs",
						"render":function(data,type,row){
							return data;
						}
					},{
						"title":"权限类型",
						"data":"permissionType",
						"render":function(data,type,row){
							if(data==1){
								return "菜单";
							}
							return "按钮";
						}
					},{
						"title":"授权标识",
						"data":"permissionCode"
					},{
						"title" : "ICON",
						"data" : "iconName",
						"className":"hidden-480"
					}, {
						"title" : "排序编号",
						"data" : "sortNo",
						"className":"hidden-480 hidden-xs"
					}, {
						"title" : "是否可见",
						"data" : "visible",
						"className":"hidden-480 hidden-xs",
						"render":function(data,type,row){
							if(data){
								return "<label><input name='switch-field-1' onclick='checkedBox("+row.id+",$(this))' checked class='ace ace-switch ace-switch-6' type='checkbox' />"+
								"<span class='lbl'></span></label>";
							}
							return "<label><input name='switch-field-1' onclick='checkedBox("+row.id+",$(this))' class='ace ace-switch ace-switch-6' type='checkbox' />"+
									"<span class='lbl'></span></label>";
						}
					}, {
						"title" : "操作",
						"data" : "id",
						"className":"hidden-480",
						"orderable":false,//禁止该列排序
						"render" : function(data, type, row) {
							return '<button onclick="editMenu('+data+',\''+row.menuName+'\',\''+row.menuLink+'\','+row.parentId+',\''+row.parentName+'\',\''+row.iconName+'\','+row.sortNo
							+','+row.valid+','+row.visible+','+row.permissionType+',\''+row.permissionCode+'\');" data-toggle="modal" data-target="#modal-add_menu" class="btn btn-minier btn-info" title="修改菜单"><i class="ace-icon fa fa-pencil bigger-120"></i></button>&nbsp;'+
							'<button onclick="delMenu('+data+');" class="btn btn-minier btn-danger" title="删除菜单"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
						}
					} 
				],
		"order":[[1,'asc']]  //初始排序列

	});
	
	// 添加点击详情事件
    $('#example tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()) ).show();
            tr.addClass('shown');
        }
    } );
    return table;
}

/* 详情 */
function format ( d ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>菜单ID:</td>'+
            '<td>'+d.id+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>菜单名称:</td>'+
            '<td>'+d.menuName+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>菜单URL:</td>'+
            '<td>'+d.menuLink+'</td>'+
        '</tr>'+
    '</table>';
}

//禁用和可见的设置,type=1:是否启用,type=2:是否可见
function checkedBox(id,obj){
//	console.log(obj.is(':checked'));//选中状态
	var state=obj.is(':checked')?1:0;
	$.ajax({
		"url":"/api/menu/"+id+"/"+state,
		"type":"PUT",
		"success":function(data){
			var m=$.parseJSON(data);
			// 刷新表格数据，分页信息不会重置
			table.ajax.reload(null,false);
			treeInit();
			layer.msg(m.msg);
		}
	});
	
}
//加载菜单到表单
function editMenu(id,menuName,menuLink,parentId,parentName,iconName,sortNo,valid,visible,permissionType,permissionCode){
	var menu={id:id,menuName:menuName,menuLink:menuLink,parentId:parentId,parentName:parentName,iconName:iconName,sortNo:sortNo,valid:valid,visible:visible,permissionType:permissionType,permissionCode:permissionCode};
	loadData(menu);
}

//表单填充
function loadData(jsonObj){
	var obj =jsonObj;
	var key,value,tagName,type,arr;
	for(x in obj){
		key = x;
		value = obj[x];
		
		$("[name='"+key+"'],[name='"+key+"[]']").each(function(){
			tagName = $(this)[0].tagName;
			type = $(this).attr('type');
			if(tagName=='INPUT'){
				if(type=='radio'){
					$(this).attr('checked',$(this).val()==value);
				}else if(type=='checkbox'){
					arr = value.split(',');
					for(var i =0;i<arr.length;i++){
						if($(this).val()==arr[i]){
							$(this).attr('checked',true);
							break;
						}
					}
				}else{
					$(this).val(value);
				}
			}else if(tagName=='SELECT' || tagName=='TEXTAREA'){
				$(this).val(value);
			}
			
		});
	}
}