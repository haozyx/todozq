
var prefix = cctx + "adminm/tables";
var d_map =new Map();
$(function() {
	
	jqAjax({url :  cctx +"adminm/dic/getdicdata/",
		type : "post",
		data : {
			'typecode' : 'DATABASETABLE'
		}}).then(res=>{
			$.each(res,function(i,v){
				d_map.set(v.id,v.disName);
			});
			//给下拉框赋值
			var html ='';
			for (var i = 0; i < res.length; i++) {
				html += '<option value="' + res[i].id + '">' + res[i].disName + '</option>'
			}
			$("#tablecategory").append(html);
			$("#tablecategory").chosen({
				maxHeight : 200,
				search_contains: true, //启用模糊搜索
				disable_search: false // 启用搜索狂
			});
		 
			$("#tablecategory").trigger("chosen:updated");
			// 点击事件
			$("#tablecategory").on('change', function(e, params) {
				$(this).valid();
			});
			
			
			load();
		});
	
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						search : false, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true,
									width:"1%"
								},
																{
									field : 'id', 
									title : '编号' ,
									width:"2%"
								},
																{
									field : 'tablecategory', 
									title : '模块',
									width:"10%",
									formatter : function(value, row, index) {
										//console.info(d_map);
										//console.info(d_map.get(value));
										return d_map.get(value);
									}
								},
																{
									field : 'entablename', 
									title : '表名',
									width:"10%"
								},
																{
									field : 'zntablename', 
									title : '中文表名' ,
									width:"10%"
								},
																{
									field : 'tablerelations', 
									title : '表之间关联关系' ,
									width:"10%",
									formatter :function(value,row,index){
										return value.length>10 ? "<code>"+value.substr(0,10)+"..."+"</code>":"<code>"+value+"</code>";
									}
								},
								/*							{
									field : 'vwrelations', 
									title : '视图关联' 
								},
																{
									field : 'seqrelation', 
									title : '序列关联' 
								},*/
								{
									field : 'note', 
									title : '备注说明',
									formatter :function(value,row,index){
										return value.length>30 ? "<code>"+value.substr(0,30)+"..."+"</code>":"<code>"+value+"</code>";
									}
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									width:"15%",
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="查看"  mce_href="#" onclick="view(\''
												+ row.id
												+ '\')"><i class="fa fa-eye"></i></a> ';
										return e + d +f;
									}
								} ]
					});
}
 

function reLoad() {
	
	var opt ={
			query :{
				tablecategory : $('#tablecategory').val(),
				tablename : $('#searchName').val()
			}
	};
	
	$('#exampleTable').bootstrapTable('refresh',opt);
}

function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '380px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '380px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}

function view(id) {
	layer.open({
		type : 2,
		title : '详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '500px', '380px' ],
		content : prefix + '/view/' + id // iframe的url
	});
}

function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}