$().ready(function() {
	validateRule();
	loadmodule(); 
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
		url : "/adminm/tableinfos/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
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
		ignore: ":hidden:not(select)",//解决无法校验select 
		errorPlacement: function(error, element) {
			element.parent().append(error);
		},
		rules : {
			tablemodule : {
				required : true
			},
			tablename : {
				required : true
			},
			zname : {
				required : true
			}
		},
		messages : {
			tablemodule : {
				required : icon + "请选择所属模块"
			},
			tablename : {
				required : icon + "请输入表名"
			},
			zname : {
				required : icon + "请输入中文表名"
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
		url : '/adminm/dic/getdicdata',
		type:'post',
		data:{typecode : 'MODULE'},
		success : function(data) {
		
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].disCode + '">' + data[i].disName + '</option>'
			}
			$("#tablemodule").append(html);
			$("#tablemodule").chosen({
				maxHeight : 200,
				search_contains: true, //启用模糊搜索
				disable_search: true // 启用搜索狂
			});
		 
			$("#tablemodule").trigger("chosen:updated");
			// 点击事件
			$("#tablemodule").on('change', function(e, params) {
				$(this).valid();
			});
		}
	});
}