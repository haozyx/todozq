 var myEditor =  null;
 var prefix = cctx+"blog/article";
$(function(){
	loadmodule(); //初始化下拉框的值
	var tags = $("#articletags").val();
	if(tags){
		$("#myTags").addTags(tags);
	}
	
	$("#isPublic").on('change',function(){
		if($("#isPublic").val() == 0){
			$("#articlePassword").removeAttr("disabled");
		}else{
			$("#articlePassword").attr('disabled','disabled');
		}
	});
	 ClassicEditor.create( document.querySelector( '#editor' ),{
         ckfinder: {
             uploadUrl : prefix+'/ckupload'
			 
     } 
	 }).then(editor => {
	        myEditor = editor;
	    }).catch( error => {
             console.error( error );
     } );
	
});
/**
 * 初始化下拉框的值
 * @returns
 */
function loadmodule(){
	var html = "";
	$.ajax({
		url : prefix+'/getCategory',
		type:'post',
		success : function(data) {
		
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				if(6== data[i].id) continue;
				html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
			}
			$("#category").append(html);
	 
			//设置选中值 必须进行更新
			$("#category").val(a_c); 
			$("#category").trigger("chosen:updated"); 
		}
	});
}

//保存文章
function save(articleStatus){
	
	var a_c = $("#category").val();
	if(!a_c){
		layer.msg("请选择文章分类");
		return;
	}
	var at = $("#myTags").tagsValues();
	if(at.length == 0){
		layer.msg("请输入标签！");
		return;
	}
	if(at.length >5 ){
		layer.msg("最多输入5个标签");
		return;
	} 
//	alert(at.join(","));
	$("#tags").val(at.join(","));
	
	if(myEditor.getData()!=""){
		
		$("#articleContent").val(myEditor.getData());
		$("#articleStatus").val(articleStatus);
		var obj=$("#articleFrom").serialize();
		 $.ajax({
				type : "post",
				url : prefix+"/update",
				data : obj,
				async: false,
				dataType : "json",
				success : function(data){
					if(data.code==0){
						
						layer.open({
							  content: '操作成功！',
							  yes: function(index, layero){
								//layer.close(index);
								closePage("修改资源");
								//关闭之后是不会继续执行后面的代码所以打开无效。
								
							  }
							});    
							        
 					 	
					}else{
						layer.msg("操作失败");//(data.msg,"error","3000");
					}
				}
			}); 
	}else{
		layer.msg("请输入文章内容！");
   }
	
}
function runSuggestions(element,query) {
    /*
    using ajax to populate suggestions
     */
    let sug_area=$('#myTags .autocomplete-items');
/*    $.getJSON(prefix+"/listTags", function( data ) {
    	 _tag_input_suggestions_data = data;
	    $.each(data,function (key,value) {
	      let template = $("<div>"+value.name+"</div>").hide();
	      sug_area.append(template);
	      template.show();
	    })
	  });*/

}
