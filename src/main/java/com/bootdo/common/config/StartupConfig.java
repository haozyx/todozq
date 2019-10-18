package com.bootdo.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.bootdo.adminm.service.CategoryService;
import com.bootdo.system.domain.OptionsDO;
import com.bootdo.system.service.OptionsService;


/**
 * 项目启动需要预加载的一些东西
 * @author Administrator
 *
 */
@Configuration
public class StartupConfig implements ApplicationListener<ContextRefreshedEvent> {

 
	@Autowired
	CategoryService categoryService;
	
/*	@Autowired
    WebApplicationContext applicationContext;
	*/
	@Autowired
	OptionsService optionService;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	 
		loadIndexmenu();
		setoptions();
    }

	/**
	 * 访问博客页面的时候预加载的信息
	 */
	public void loadIndexmenu() {
	}
	public void setoptions() {
		List<OptionsDO> os = optionService.list();
		
		if(os.size()>0) {
			for(OptionsDO d : os) {
				Constant.OPTIONS.put(d.getOptionName(),d.getOptionValue());
			}
		}
		/**
		 * 经过一段时间观察发现，keywords不能从数据库存储
		 */
		Constant.OPTIONS.put("title","好帮手资源分享网");
		Constant.OPTIONS.put("keywords","资源分享，老司机，破解版，精品资源，安卓破解，老司机软件，手机软件，免费分享，精品教程，网站源码，福利APP");
		Constant.OPTIONS.put("description","好帮手资源分享网，专业的、有态度的分享网站。致力于分享手机软件、电脑软件、网站源码、福利APP、便民工具等。");
/*		Constant.OPTIONS.put("blog_url","haozyx.com");
		Constant.OPTIONS.put("comment_gitalk_clientid","af0fbf5f7c1e7ecda8ba");
		Constant.OPTIONS.put("comment_gitalk_clientid","533a065ed120b8e387bd");
		Constant.OPTIONS.put("comment_gitalk_clientSecret","ffa2c811cbf3cd7d0a164d59db4add2bfe3c631c");
		Constant.OPTIONS.put("comment_gitalk_clientSecret","018e719215758101702cf1dfdf82dbb2e1fa9378");
		Constant.OPTIONS.put("comment_gitalk_repo","hints");
		Constant.OPTIONS.put("comment_gitalk_owner","haozyx");
		Constant.OPTIONS.put("comment_gitalk_admin","haozyx");
		Constant.OPTIONS.put("blog_name","知识改变人生，分享创造快乐");
		Constant.OPTIONS.put("blog_logo","/img/haozyx.png");
		Constant.OPTIONS.put("email_username","wanghb1133@126.com");
		Constant.OPTIONS.put("title","好帮手资源分享网");
		Constant.OPTIONS.put("keywords","百度云资源分享,免费,资源,精品，教程，分享,网站,福利,破解");
		Constant.OPTIONS.put("description","好帮手资源分享网，专业的、有态度的资源分享网。");*/
	}
	
/*	public void loadAllUrls() {
		RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo,HandlerMethod> map = mapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()){
            //获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            for (String url : patterns){
            	if(StrUtil.isNotBlank(url))
            		Constant.SB.append(url).append(",");
            }
        }
 
	}*/
}
