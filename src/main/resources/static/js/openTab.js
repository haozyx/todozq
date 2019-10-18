function openPage(url,title) {
    var nav = $(window.parent.document).find('.J_menuTabs .page-tabs-content ');
    $(window.parent.document).find('.J_menuTabs .page-tabs-content ').find(".J_menuTab.active").removeClass("active");
    $(window.parent.document).find('.J_mainContent').find("iframe").css("display", "none");
    var iframe = '<iframe class="J_iframe" name="iframe10000" width="100%" height="100%" src="' + url + '" frameborder="0" data-id="' + url
        + '" seamless="" style="display: inline;"></iframe>';
    $(window.parent.document).find('.J_menuTabs .page-tabs-content ').append(
        ' <a href="javascript:;" class="J_menuTab active" data-id="'+url+'">' + title + ' <i class="fa fa-times-circle"></i></a>');
    $(window.parent.document).find('.J_mainContent').append(iframe);
   
}

function openPage2(href,title){
	let exist = false;
	var nav = $(window.parent.document);
	 nav.find(".page-tabs.J_menuTabs").find(".page-tabs-content a").each(function(index , value){
		 
		 if($(value).text().indexOf(title)>-1){
			 
			 exist = true;
			 $(value).find("i").click();
			 openPage(href,title);
			 return;
		 }
		 
	 });

	 if(!exist){
		 openPage(href,title);
	 }
}


function closePage(title){
	var nav = $(window.parent.document);
	 nav.find(".page-tabs.J_menuTabs").find(".page-tabs-content a").each(function(index , value){
		 
		 if($(value).text().indexOf(title)>-1){
			 
			 
			 $(value).find("i").click();
			 
			 return;
		 }
		 
	 });
	 
}
//$('.openPage').on('click', openPage('',''));