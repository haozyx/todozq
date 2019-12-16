$().ready(function() {
	validateRule();
	loadmodule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function loadmodule(){
	var html = "";
	$.ajax({
		url :  cctx+'adminm/dic/getdicdata',
		type:'post',
		data:{typecode : 'DATABASETABLE'},
		success : function(data) {
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].disName + '</option>'
			}
			$("#tablecategory").append(html);
			$("#tablecategory").chosen({
				maxHeight : 200,
				search_contains: true, //启用模糊搜索
				disable_search: false // 启用搜索狂
			});
			$("#tablecategory").val(t_c);
			$("#tablecategory").trigger("chosen:updated");
			// 点击事件
			$("#tablecategory").on('change', function(e, params) {
				$(this).valid();
			});
		}
	});

}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : cctx + "adminm/tables/update",
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
		ignore: ":hidden:not(select)", //解决jquerychoose 不验证的的问题
		rules : {
			tablecategory:{
				required : true
			},
			entablename:{
				required : true
			},
			zntablename:{
				required : true
			}
		},
		messages : {
			tablecategory : {
				required : icon + "请选择模块"
			},
			entablename : {
				required : icon + "请输入表名"
			},
			zntablename : {
				required : icon + "请输入中文表名"
			}
		},
		errorPlacement:function (error, element) {
		        error.appendTo(element.parent());
	    }
	})
}