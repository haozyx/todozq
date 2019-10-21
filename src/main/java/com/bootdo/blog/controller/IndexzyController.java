package com.bootdo.blog.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.adminm.domain.CategoryDO;
import com.bootdo.adminm.domain.LinkDO;
import com.bootdo.adminm.service.CategoryService;
import com.bootdo.adminm.service.DicService;
import com.bootdo.adminm.service.IndexmenuService;
import com.bootdo.adminm.service.LinkService;
import com.bootdo.blog.domain.ArticleDO;
import com.bootdo.blog.service.ArticleCustomService;
import com.bootdo.blog.service.ArticleService;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.MapCache;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;

/**
 * @author wanghby@163.com
 */
@RequestMapping("/zytest")
@Controller
public class IndexzyController extends BaseController{
	@Autowired
    ContentService bContentService;
	@Autowired
	private ArticleCustomService acService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	IndexmenuService imservice;
	@Autowired
	DicService dicService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	LinkService linkService;
	
	protected MapCache cache = MapCache.single();


	@GetMapping(value = { "/","", "/index" })
	public String index(Model model) {
		
//		throw new RuntimeException("123");
		// 调用方法渲染首页
		return this.index(model, 1,"");
	}
	
	@GetMapping(value = { "/baidu_verify_UjgmuF5wfc.html" })
	public String baiduvalid(Model model) {
		
		// 百度验证
		return this.render("baiduvalid");
	}
	/**
	 * 请求首页
	 *
	 * @param model
	 * @return 模板路径
	 */
	@GetMapping(value = {"/page/{page}/{article_category}","/page/{page}"})
	public String index(Model model, @PathVariable(value = "page") Integer page,@PathVariable(required = false,value = "article_category") String article_category) {
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("articleStatus", "0");
		
		if(StrUtil.isNotBlank(article_category)) {
			params.put("category", article_category);
			model.addAttribute("category", article_category);
			
		}
		
		int count = articleService.count(params);// 先计算总数  总数为0 不做操作
		if(count>0) {
			//将当前页和总页数传递到前台
			int totalpages = (count + Constant.PAGESIZE -1) /Constant.PAGESIZE;
			//对不合理的分页数进行处理
			page = page < 0  ? 1 :page;
			page = page > totalpages ? totalpages : page;
			
			
			params.put("offset", (page-1)*Constant.PAGESIZE);
			params.put("limit", Constant.PAGESIZE);
			Query query = new Query(params);
		    List<ArticleDO> articleList = articleService.list(query);	
		    model.addAttribute("articles", articleList);
		    model.addAttribute("page", page);
		    model.addAttribute("totalpages", totalpages);
		}
	
	    
	    //推荐 浏览量 置顶
	    List<ArticleDO> carticles = acService.getCommendArticle();
	    List<ArticleDO> varticles  =acService.getArticleByViews();
	    List<ArticleDO> tarticles =acService.getTopArticles();
	    model.addAttribute("carticles", carticles);
	    model.addAttribute("varticles", varticles);
	    model.addAttribute("tarticles", tarticles);
	    
	  
	    List<CategoryDO> list = categoryService.list(null);
		Map<String,String> cmap = new HashMap<>();
		for(CategoryDO c : list) {
			cmap.put(c.getId().toString(), c.getName());
		}
		
	    model.addAttribute("cmap", cmap);  //
//	    model.addAttribute("articles", articleList);
//	    model.addAttribute("page", page);
//	    model.addAttribute("totalpages", totalpages);
		// 调用方法渲染首页
		return this.render("index");
	}

	
	/**
	 * 请求首页
	 *
	 * @param model
	 * @return 模板路径
	 */
	@RequestMapping(value = "/search/{page}/{query}", method = {RequestMethod.POST,RequestMethod.GET})
	public String search(Model model, @PathVariable(value = "page") Integer page,@PathVariable(value = "query") String queryp) {

		Map<String, Object> params = new HashMap<String,Object>();
		params.put("articleStatus", "0");
		try {
			params.put("articleContent", URLDecoder.decode(queryp, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 
		
		int count = acService.count(params);// 先计算总数 //总数如果为0没必要进行下面的查询
		if(count>0) {
			//将当前页和总页数传递到前台
			int totalpages = (count + Constant.PAGESIZE -1) /Constant.PAGESIZE;
			//对不合理的分页数进行处理
			page = page <= 0  ? 1 :page;
			page = page > totalpages ? totalpages : page;
			
			params.put("offset", (page-1)* Constant.PAGESIZE);
			params.put("limit", Constant.PAGESIZE);
			Query query = new Query(params);
		    List<ArticleDO> articleList = acService.findByContent(query);
		    
		    model.addAttribute("articles", articleList);
		    model.addAttribute("page", page);
		    model.addAttribute("totalpages", totalpages);
		}
		
 
		
	    //推荐 浏览量 置顶
	    List<ArticleDO> carticles = acService.getCommendArticle();
	    List<ArticleDO> varticles  =acService.getArticleByViews();
	    List<ArticleDO> tarticles =acService.getTopArticles();
	    model.addAttribute("carticles", carticles);
	    model.addAttribute("varticles", varticles);
	    model.addAttribute("tarticles", tarticles);
	    
	    List<CategoryDO> list = categoryService.list(null);
		Map<String,String> cmap = new HashMap<>();
		for(CategoryDO c : list) {
			cmap.put(c.getId().toString(), c.getName());
		}
		
	    model.addAttribute("cmap", cmap);  //
	    model.addAttribute("query",queryp); //

		// 调用方法渲染首页
		return this.render("search");
	}
	
	
	/**
	 * 文章页
	 * 
	 * @param model
	 * @param articleUrl
	 *            文章url
	 * @return
	 */
	@GetMapping(value = { "/post/{articleUrl}", "post/{articleUrl}.html" })
	public String post(Model model, @PathVariable(value = "articleUrl") String articleUrl, HttpServletRequest request) {
		ArticleDO ac = acService.findByArticleUrl(articleUrl);
		if (ac == null) {
			return this.render_404();
		}
		
	    List<CategoryDO> list = categoryService.list(null);
		Map<String,String> categories = new HashMap<>();
		for(CategoryDO c : list) {
			categories.put(c.getId().toString(), c.getName());
		}
		
		model.addAttribute("categories", categories);
		if (!checkRepeatIp(request, ac.getId())) {
			updateArticleViews(ac.getId(), ac.getArticleViews());
		}
		
	    //推荐 浏览量 置顶
	    List<ArticleDO> carticles = acService.getCommendArticle();
	    List<ArticleDO> varticles  =acService.getArticleByViews();
	    List<ArticleDO> tarticles =acService.getTopArticles();
	    model.addAttribute("carticles", carticles);
	    model.addAttribute("varticles", varticles);
	    model.addAttribute("tarticles", tarticles);
		
		String idkey = ServletUtil.getClientIP(request) + "_" +ac.getId();
		String access = cache.get("article_p"+idkey);
		if(!(StrUtil.isNotBlank(access)&& "getpass".equals(access))) {
			//说明没有访问权限
			if (ac.getIsPublic() == 0) {// 加密文章

				model.addAttribute("access", "no");
				model.addAttribute("mimaHtml", "");
				//return this.render("encrypt");
			}
			
		}else {
			model.addAttribute("access", "yes");
			System.out.println(ac.getDownloadUrl());
			String  mimaHtml = "<h2><mark class=\"marker-yellow\">下载地址："+ac.getDownloadUrl()+"</mark></h2>";

			model.addAttribute("mimaHtml", mimaHtml);
		}
		
		// 获取上一页下一页
	    ArticleDO prea = acService.getPreArticle(ac.getId(),ac.getCategory());
	    ArticleDO backa = acService.getBackArticle(ac.getId(),ac.getCategory());
		
		model.addAttribute("prea", prea);
		model.addAttribute("backa", backa);
		model.addAttribute("article", ac);

		return this.render("post"); 
		
		
	}
	
	@ResponseBody
	@PostMapping("/validatePass")
	public R validatePass(HttpServletRequest request) {
		String pass = request.getParameter("pass");
		String articleID = request.getParameter("articleId");
		
		ArticleDO ac = articleService.get(Integer.parseInt(articleID));
		if (ac == null) {
			return R.error();
		}
		if( SecureUtil.md5(pass).equals(SecureUtil.md5(ac.getArticlePassword()))) {
		   /**	
		    * 密码输入正确之后缓存起来暂时定为300秒
		    为了测试方便设置为3秒
		    */
			String idkey = ServletUtil.getClientIP(request) + "_" +ac.getId();
			cache.set("article_p" + idkey, "getpass", 300);
			
			return R.ok();
		} 
		
		return R.error("连暗号都不知道还想蒙混过关。");
	}
	
	/**
	 * 分类跳转首页，其实就是多了一个条件
	 * 
	 * @param model
	 * @param articleUrl
	 *            文章url
	 * @return
	 */
	@GetMapping(value = { "/c/{article_category}"})
	public String category_encrypt(Model model,@PathVariable(value = "article_category") String article_category,HttpServletRequest request) {
 
		if(StrUtil.isBlank(article_category)) return this.render_404();
		Integer id = Integer.parseInt(article_category);
		CategoryDO cd = categoryService.get(id);
		if(null ==  cd) return this.render_404();
 
		
		return this.index(model, 1,article_category);
	}
	
	
	
	/**
	 * 分类跳转首页，其实就是多了一个条件
	 * 
	 * @param model
	 * @param articleUrl
	 *            文章url
	 * @return
	 */
	@GetMapping(value = { "/links"})
	public String links(Model model) {
		
		List<LinkDO> links = linkService.list(null);
		 
		model.addAttribute("links", links);
		
		return this.render("links");
	}
	
	
	
	
	
	/**
	 * 修改文章点击率
	 * 
	 * @param id
	 * @param views
	 */
	public void updateArticleViews(Integer id, Long views) {
		if (views == null) {
			views = 0L;
		}
		// 获取缓存数据
		Long cacheViews = cache.hget("article" + id, "cacheViews");
		// 如果缓存数据为null赋值1，反之加1
		cacheViews = cacheViews == null ? 1 : cacheViews + 1;
		// 如果缓存只大于等于设置的次数则修改到数据库
		if (cacheViews >= 1) {
			ArticleDO article = new ArticleDO();
			article.setId(id);
			article.setArticleViews(views + cacheViews);
			acService.updateArticleViews(article);
			cache.hset("article" + id, "cacheViews", null);
		} else {
			cache.hset("article" + id, "cacheViews", cacheViews);
		}
	}
	
	/**
	 * 检测同一IP十分钟以内重复访问同一篇文章
	 * 
	 * @param request
	 * @param id
	 *            文章id
	 * @return
	 */
	public boolean checkRepeatIp(HttpServletRequest request, int id) {

		String value = ServletUtil.getClientIP(request) + ":" + id;
		Integer count = cache.hget("hits:frequency", value);
		if (count != null && count > 0) {
			return true;
		}
		cache.hset("hits:frequency", value, 1, 300); // 五分钟有效期
		return false;
	}
	
	public String render(String pageName) {
		StrBuilder themeStr = new StrBuilder("themes/");
		themeStr.append("pinghsu/");
		return themeStr.append(pageName).toString();
	}

	/**
	 * 404页面
	 * 
	 * @return
	 */
	public String render_404() {
		return "error/404";
	}
	
	

}
