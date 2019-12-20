$().ready(function() {
	validateRule();
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
		url : cctx + "adminm/tablefield/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 200) {
				parent.layer.msg("操作成功");
				parent.reload();
				//关闭添加的窗口 
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
		ignore: ":hidden:not(select)", //解决jquerychoose 不验证的的问题
		rules : {
			fieldname:{
				required : true
			},
			fieldtype:{
				required : true
			}
		},
		messages : {
			fieldname : {
				required : icon + "请输入字段名"
			},
			fieldtype : {
				required : icon + "请输入字段类型"
			} 
		},
		errorPlacement:function (error, element) {
		        error.appendTo(element.parent());
	    }
	})
}