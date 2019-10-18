
var prefix = cctx+"blog/article";
//加载下拉选择初始数据
var c_map =new Map();
var at = ['已发布','草稿','回收站'];

$(function() {
	loaddicdata();
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
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								category : $('#acategory').val(),
								articleContent : $('#searchName').val()
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
									checkbox : true
								},
																{
									field : 'id', 
									title : '编号' 
								}, 
								{
									field : 'articleTitle', 
									title : '资源标题' 
								},
								{
									field : 'articleTop', 
									title : '是否置顶' 
								},
								{
									field : 'articleNewstime', 
									title : '发布时间' ,
									width :'150'
								},								{
									field : 'category', 
									title : '分类' ,
									formatter : function(value, row, index) {
										return c_map.get(parseInt(value));
									}
								},
								
																{
									field : 'articleStatus', 
									title : '资源状态',
									formatter : function(value, row, index) {
										return at[value];
									}
								},
								{
									field : 'articleViews', 
									title : '访问量统计' 
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="查看"  mce_href="#" onclick="view(\''
												+ row.articleUrl
												+ '\')"><i class="fa fa-eye"></i></a> ';
										var g = '<a class="btn btn-success btn-sm" href="#" title="置顶"  mce_href="#" onclick="puttop(\''
											+ row.id
											+ '\')"><i class="fa fa-arrow-up"></i></a> ';
										return e + d + f +g;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function loaddicdata(){
	$.ajax({
		url : prefix+'/getCategory',
		type : "post",
		success : function(data) {
			$.each(data,function(i,v){
				c_map.set(v.id,v.name);
			});
			load();
			// 加载数据
			var html ='';
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
			}
			$("#acategory").append(html);
			$("#acategory").chosen({
				maxHeight : 200,
				search_contains: true, //启用模糊搜索
				disable_search: true // 启用搜索狂
			});
		 
			$("#acategory").trigger("chosen:updated");
		}
	});
}

function add() {
	var alltitle = '';
	
	 var nav = $(window.parent.document);
	 nav.find(".page-tabs.J_menuTabs").find(".page-tabs-content a").each(function(index , value){
		 
		 alltitle+=$(value).text();
		 
	 });
	 if(alltitle.indexOf("发布资源")!=-1){
		 //如果找到了先关闭再打开
		var $a  = nav.find(".page-tabs.J_menuTabs").find(".page-tabs-content a[data-id='blog/article/add']");
		$a.find("i").click();
		openPage(prefix+'/add','发布资源');
	 }else{
		 openPage(prefix+'/add','发布资源');
	 }
	
	//
}

function puttop(id){
	$.ajax({
		url : prefix+"/puttop/"+id,
		type : "get",
		success : function(r) {
			if (r.code==0) {
				layer.msg(r.msg);
			}else{
				layer.msg(r.msg);
			}
		}
	});
}

function edit(id) {
	var href = prefix+"/edit/"+id;
	var exist = false;
	 var nav = $(window.parent.document);
	 nav.find(".page-tabs.J_menuTabs").find(".page-tabs-content a").each(function(index , value){
		 
		 if($(value).text().indexOf("修改资源")>-1){
			 
			 exist = true;
			 $(value).find("i").click();
			 openPage(href,'修改资源');
			 return;
		 }
		 
	 });
 
	 if(!exist){
		 openPage(href,'修改资源');
	 }
	
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
					layer.msg("推送成功！");
					//reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}

//推送百度
function pushBaidu(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要推送的数据");
		return;
	}
	
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
		url : prefix + '/pushBaidu',
		success : function(r) {
			if (r.code == 0) {
				layer.msg(r.msg);
				reLoad();
			} else {
				layer.msg(r.msg);
			}
		}
	});
		 
}

//查看博客

function view(articleurl){
	var href = cctx+"zy/post/"+articleurl;
	window.open(href, "_blank"); 
}
 