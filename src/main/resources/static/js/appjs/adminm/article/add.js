 

var prefix = cctx+"blog/article";

var myEditor = null;
function initEditor(){
	 ClassicEditor.create( document.querySelector( '#editor' ),{
         ckfinder: {
             uploadUrl : prefix +'/ckupload'
			 
     } 
	 }).then(editor => {
	        myEditor = editor;
	    }).catch( error => {
             console.error( error );
     } );
}   


   
   $().ready(function() {
	 
		loadmodule(); //初始化下拉框的值
		initEditor();
		$("#isPublic").on('change',function(){
			if($("#isPublic").val() == 0){
				$("#articlePassword").removeAttr("disabled");
			}else{
				$("#articlePassword").attr('disabled','disabled');
			}
		});
		 
	});
   
 
   
   
   function loadmodule(){

		var html = "";
		$.ajax({
			url : prefix+'/getCategory',
			type:'post',
			success : function(data) {
				// 加载数据
				for (var i = 0; i < data.length; i++) {
					 
					if(6== data[i].id) continue;
					html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
				}
				$("#category").append(html);
				$("#category").chosen({
					maxHeight : 200,
					search_contains: true, //启用模糊搜索
					disable_search: true // 启用搜索狂
				});
			 
				$("#category").trigger("chosen:updated");
				// 点击事件
			/*	$("#category").on('change', function(e, params) {
					$(this).valid();
				});*/
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
		if(at.length >5 ){
			layer.msg("最多输入5个标签");
			return;
		} 
		$("#tags").val(at.join(","));
		
		
		if(myEditor.getData()!=""){
			 
			$("#articleContent").val(myEditor.getData());
			$("#articleStatus").val(articleStatus);
			var obj=$("#articleFrom").serialize();
			 $.ajax({
					type : "post",
					url : prefix+"/save",
					data : obj,
					async: false,
					dataType : "json",
					success : function(data){
						if(data.code==0){
					 
							layer.open({
								  content: '操作成功！',
								  yes: function(index, layero){
									//layer.close(index);
									closePage("发布资源");
				 			 
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
/*        $.getJSON(prefix+"/listTags", function( data ) {
        	 _tag_input_suggestions_data = data;
		    $.each(data,function (key,value) {
		      let template = $("<div>"+value.name+"</div>").hide();
		      sug_area.append(template);
		      template.show();
		    })
		  });*/

    }