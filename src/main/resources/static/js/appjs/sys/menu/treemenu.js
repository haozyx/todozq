var prefix = cctx + "sys/menu";
 
$(function() {
	//左侧树数据 
	getTreeData();
	load() ;
 
});

/**
 * 加载菜单详细数据
 * @returns
 */
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/listmenu", // 服务器数据的加载地址
				// showRefresh : true,
				// showToggle : true,
				// showColumns : true,
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
				// search : true, // 是否显示搜索框
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
				// "server"
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit:params.limit,
						offset:params.offset,
						parentId:0
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
						field : 'menuId', // 列字段名
						title : '编号' // 列标题
					},
					{
						field : 'name',
						title : '名称'
					},
					{
						field : 'icon',
						title : '图标',
						width:'5%',
						formatter: function (item, index) {
	                            return item == null ? ''
	                                : '<i class="' + item
	                                + ' fa-lg"></i>';
	                        }
					},{
                        title: '类型',
                        field: 'type',
                        align: 'center',
                        valign: 'center',
                        width : '10%',
                        formatter: function (item, index) {
                            if (item === 0) {
                                return '<span class="label label-primary">目录</span>';
                            }
                            if (item === 1) {
                                return '<span class="label label-success">菜单</span>';
                            }
                            if (item === 2) {
                                return '<span class="label label-warning">按钮</span>';
                            }
                        }
                    },
                    {
                        title: '地址',
                        valign: 'center',
                        width : '20%',
                        field: 'url'
                    },
                    {
                        title: '权限标识',
                        valign: 'center',
                        width : '20%',
                        field: 'perms'
                    },
                    {
                        title: '操作',
                        field: 'menuId',
                        align: 'center',
                        valign: 'center',
                        formatter: function (item, index) {
                        
                           var e = '<a class="btn btn-primary btn-sm '
                                + ''
                                + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + item
                                + '\')"><i class="fa fa-edit"></i></a> ';
                       
                            var d = '<a class="btn btn-warning btn-sm '
                                + ''
                                + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + item
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d; 
                        }
                    }]
			});
}
function reLoad() {
	var opt={
			query:{
				parentId:$("#pId").val()
			}
	};
	$('#exampleTable').bootstrapTable('refresh',opt);
	$("#jstree").jstree(true).destroy(); //销毁树然后重新建设
	getTreeData(); 
	//重新附加点击事件
}

function add() {
 
	//$("#jstree").jstree(true).open_node('103');
	var pId = $("#pId").val();
	if(!pId) {
		 layer.msg("请选择节点进行操作");
		 return;
	}
    layer.open({
        type: 2,
        title: '增加菜单',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add/' + pId // iframe的url
    });
}
function getTreeData() {
	$.ajax({
		type : "GET",
		url :  cctx +"sys/menu/tree",
		success : function(tree) {
			loadTree(tree);
		}
	});
	
}
function loadTree(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search" ]
	}) .on('loaded.jstree',function(e,data){
		var inst = data.instance;
		var pid = $("#pId").val();
		//选中节点 并打开节点
		if(pid){
			inst.select_node(pid);
			inst.open_node(pid);
		}
		
	}) ;
	$('#jstree').jstree().open_all();
	treeonclick();
}

function treeonclick(){
	$('#jstree').on("changed.jstree", function(e, data) {
		
		if (data.selected == -1) {
			$("#pId").val('0')
			var opt = {
				query : {
					parentId : 0,
				}
			}
			$('#exampleTable').bootstrapTable('refresh', opt);
		} else {$("#pId").val(data.selected[0]) ;
			var opt = {
				query : {
					parentId : data.selected[0],
				}
			}
			$('#exampleTable').bootstrapTable('refresh',opt);
		}

	});
}


function edit(id) {
    layer.open({
        type: 2,
        title: '菜单修改',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (data) {
                if (data.code == 0) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg(data.msg);
                }
            }
        });
    })
}