$().ready(function() {
	validateRule();
	
	loadmodule(); //初始化下拉框的值
	loadVersion();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : cctx+"adminm/svn/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 200) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		debug:false,
		ignore: ":hidden:not(select)",//解决无法校验select 
		errorPlacement: function(error, element) {
			element.parent().append(error);
		},
		rules : {
			svnName : {
				required : true
			},
			svnVersion:{
				required : true
			},
			svnModule:{
				required: true
			}
			
		},
		messages : {
			svnName : {
				required : icon + "请输入名称"
			},svnVersion:{
				required : "请选择版本"
			},
			svnModule:{
				required: "请选择模块"
			}
		}
	})
}

/**
 * 初始化下拉框的值
 * @returns
 */
function loadmodule(){
	var html = "";
	$.ajax({
		url :  cctx+'adminm/dic/getdicdata',
		type:'post',
		data:{typecode : 'VERSION'},
		success : function(data) {
		
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].disCode + '">' + data[i].disName + '</option>'
			}
			$("#svnVersion").append(html);
			
		}
	});
}

/**
 * 初始化下拉框的值
 * @returns
 */
function loadVersion(){
	var html = "";
	$.ajax({
		url : cctx+ 'adminm/dic/getdicdata',
		type:'post',
		data:{typecode : 'MODULE'},
		success : function(data) {
		
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].disCode + '">' + data[i].disName + '</option>'
			}
			$("#svnModule").append(html);
			//必须重新渲染表单
			layui.use('form',function(){
				var form = layui.form;
				form.render();
			});
		}
	});
}

/**
 * 初始化下拉框的值
 * @returns
 */
function getdepusername(depid){
	var html = "";
	$.ajax({
		url : cctx+ 'adminm/svn/getuserbydepid?depid='+depid,
		type:'get' ,
		success : function(data) {
			var userlist = data.userlist;
			// 加载数据
			for (var i = 0; i < userlist.length; i++) {
				html += '<option value="' + userlist[i].user_id + '">' + userlist[i].name + '</option>'
			}
			$("#depusername").append(html);
			//必须重新渲染表单
			layui.use('form',function(){
				var form = layui.form;
				form.render();
			});
		}
	});
}

//打开部门的选择框
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '250px' ],
		content: cctx + "system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
	getdepusername(deptId);
}