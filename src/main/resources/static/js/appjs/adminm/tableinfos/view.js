var m_map =new Map();

$(function() {
	loaddicdata();
	$("#closeframe").on('click',function(){
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	});
});


function setmodule(){
	var name = m_map.get(s_m);
	$("#tablemodule").text(name);
}

function loaddicdata(){
	
	$.ajax({
		url : "/adminm/dic/getdicdata/",
		type : "post",
		data : {
			'typecode' : 'MODULE'
		},
		success : function(r) {
			$.each(r,function(i,v){
				m_map.set(v.disCode,v.disName);
			});
			setmodule();
		}
	});
}