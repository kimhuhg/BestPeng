/**
 * 	l - Length changing 每页显示多少条数据选项
	f - Filtering input 搜索框
	t - The Table 表格
	i - Information 表格信息
	p - Pagination 分页按钮
	r - pRocessing 加载等待显示信息
*/


$(document).ready(function() {
	// datatables默认设置
	$.extend( $.fn.dataTable.defaults, {
		"dom": 'rtilp',
		"serverSide": true,//启用服务器端处理模式
		"searching": false,//禁用搜索
		"ordering": true,//排序
		"processing": true,//打开加载提示
		//"scrollY": "200px",//垂直滚动
		"pagingType": "full_numbers",//分页显示方式
		language: {
			"sProcessing": "处理中...",
			"sLengthMenu": "显示 _MENU_ 项结果",
			"sZeroRecords": "没有匹配结果",
			"sInfo": "当前第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			"sInfoEmpty": "当前第 0 至 0 项结果，共 0 项",
			"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix": "",
			"sSearch": "搜索:",
			"sUrl": "",
			"sEmptyTable": "查询结果为空!",
			"sLoadingRecords": "载入中...",
			"sInfoThousands": ",",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "上页",
				"sNext": "下页",
				"sLast": "末页",
				"sJump": "跳转"
			},
			"oAria": {
				"sSortAscending": ": 以升序排列此列",
				"sSortDescending": ": 以降序排列此列"
			}
		}
	} );
} );