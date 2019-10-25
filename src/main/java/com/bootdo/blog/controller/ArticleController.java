package com.bootdo.blog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.UpYun;
import com.bootdo.adminm.domain.CategoryDO;
import com.bootdo.adminm.service.CategoryService;
import com.bootdo.blog.domain.ArticleDO;
import com.bootdo.blog.domain.Tags;
import com.bootdo.blog.service.ArticleCustomService;
import com.bootdo.blog.service.ArticleService;
import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.BaiduUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-06-25 15:25:48
 */
 
@Controller
@RequestMapping("/blog/article")
public class ArticleController extends BaseController{
	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleCustomService acService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BootdoConfig config;
	
	//private static final Map<String, String> configs = Constant.OPTIONS;
	
//	@Autowired
//	private BootdoConfig config;
	@GetMapping()
	@RequiresPermissions("blog:article:article")
	String Article(){
	    return "adminm/article/article";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("blog:article:article")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ArticleDO> articleList = articleService.list(query);
		int total = articleService.count(query);
		PageUtils pageUtils = new PageUtils(articleList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/listTags")
	@RequiresPermissions("blog:article:add")
	public List<Tags> listTags(){
		//查询所有的标签，只是为了展示标签
		List<Tags> articleList = acService.findAllTags();
		 
		return articleList;
	}
	
	
	
	@GetMapping("/add")
	@RequiresPermissions("blog:article:add")
	String add(){
	    return "adminm/article/add";
	}
	@GetMapping("/uploadfile")
	String addfile(){
	    return "adminm/article/upload";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("blog:article:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ArticleDO article = articleService.get(id);
		model.addAttribute("article", article);
	    return "adminm/article/edit";
	}
	
	/**
	 * 文章置顶操作
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/puttop/{id}")
	@ResponseBody
	@RequiresPermissions("blog:article:article")
	public R  puttop(@PathVariable("id") Integer id,Model model){
		ArticleDO article = articleService.get(id);
		
		R r = new R();
		
		if(0 == article.getArticleTop()) {
			article.setArticleTop(1);
			r.put("msg", "置顶成功");
		}else {
			article.setArticleTop(0);
			r.put("msg", "取消置顶成功");
		}
		 
		try {
			articleService.update(article);
		}catch (Exception e) {
			return R.error("系统异常请稍后再试。");
		}
		
	    return r;
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@Log("添加文章")
	@PostMapping("/save")
	@RequiresPermissions("blog:article:add")
	public R save( ArticleDO article){

		try {
			if (StrUtil.isEmpty(article.getArticleTitle())) {
				return R.error("标题不能为空");
			}

			// 判断文章链接是否重复
			if (!StrUtil.isEmpty(article.getArticleUrl())) {
				if(article.getArticleUrl().length()>50) {
					return R.error("路径不能大于50");
				}
				// 查询url是否重复
				int repeat = acService.findRepeatByUrl(article.getArticleUrl());
				if (repeat != 0) {
					return R.error("路径已存在");
				}
			}
			article.setUserId(getUserId().intValue());
			article.setArticleNewstime(DateUtil.date());
			article.setArticleUpdatetime(DateUtil.date());
			article.setArticleViews(0l);
			article.setArticleTop(0);
			article.setIsPublic(1);
			// 如果自定义链接为空则按时间戳生成链接
			if (StrUtil.isEmpty(article.getArticleUrl())) {
				article.setArticleUrl(String.valueOf(System.currentTimeMillis() / 1000));
			}
			// 如果没有选择略缩图则随机一张图
			if (StrUtil.isEmpty(article.getArticleThumbnail())) {
				article.setArticleThumbnail("/img/rand/" + RandomUtil.randomInt(1, 19) + ".jpg");
			}
			// 判断摘要是否为空
			if (StrUtil.isEmpty(article.getArticleSummary())) {
				// 如果摘要为空则取前五十字为摘要
				int post_summary = 100;

				// 清理html标签和空白字符
				String summaryText = StrUtil.cleanBlank(HtmlUtil.cleanHtmlTag(article.getArticleContent()));
				summaryText = summaryText.replaceAll("&nbsp;", " ");
				// 设置文章摘要
				if (summaryText.length() > post_summary) {
					article.setArticleSummary(summaryText.substring(0, post_summary));
				} else {
					article.setArticleSummary(summaryText);
				}
			}
			
			//APP专用将文章中的一些不支持的字符替换掉
			String ahtml = article.getArticleContent();
			ahtml = ahtml.replaceAll("<figure class=\"image\">", "<div style='display: flex;flex-direction: row;justify-content: center;'>").replaceAll("</figure>", "</div>");
			ahtml = ahtml.replaceAll("<img", "<img width='95%' ");
			article.setArticleContent(ahtml);
			
			articleService.save(article);
			saveTags(article.getTags());
		
		} catch (Exception e) {
			 e.printStackTrace();
			return R.error("添加文章发生异常");
		}

		return R.ok();
		
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@Log("修改文章")
	@RequestMapping("/update")
	@RequiresPermissions("blog:article:edit")
	public R update( ArticleDO article){
		
		try {
			if (StrUtil.isEmpty(article.getArticleTitle())) {
				return R.error("标题不能为空");
			}
			if(1 == article.getIsPublic()) {
				article.setArticlePassword("");//1是不加密
			}
			// 如果没有选择略缩图则随机一张图
			if (StrUtil.isEmpty(article.getArticleThumbnail())) {
				article.setArticleThumbnail("/img/rand/" + RandomUtil.randomInt(1, 19) + ".jpg");
			}
			// 判断摘要是否为空
			if (StrUtil.isEmpty(article.getArticleSummary())) {
				// 如果摘要为空则取前五十字为摘要
				int post_summary = 100;
				 
				// 清理html标签和空白字符
				String summaryText = StrUtil.cleanBlank(HtmlUtil.cleanHtmlTag(article.getArticleContent()));
				summaryText = summaryText.replaceAll("&nbsp;", " ");
				// 设置文章摘要
				if (summaryText.length() > post_summary) {
					article.setArticleSummary(summaryText.substring(0, post_summary));
				} else {
					article.setArticleSummary(summaryText);
				}
			}
			// 文章最后修改时间
			article.setArticleUpdatetime(DateUtil.date());
			//article.setArticleNewstime(DateUtil.date());
			
			//APP专用将文章中的一些不支持的字符替换掉
			String ahtml = article.getArticleContent();
			ahtml = ahtml.replaceAll("<figure class=\"image\">", "<div style='display: flex;flex-direction: row;justify-content: center;'>").replaceAll("</figure>", "</div>");
			ahtml = ahtml.replaceAll("<img", "<img width='95%' ");
			article.setArticleContent(ahtml);
			
			articleService.update(article);
			 
			saveTags(article.getTags());
			
		}catch(Exception e) {
			e.printStackTrace();
			return R.error("更新文章发生异常");
		}
		 
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("blog:article:remove")
	public R remove( Integer id){
		if(articleService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("blog:article:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		articleService.batchRemove(ids);
		return R.ok();
	}
	
	@PostMapping( "/pushBaidu")
	@ResponseBody
	@RequiresPermissions("blog:article:article")
	public R pushBaidu(@RequestParam("ids[]") Integer[] ids) {
		
		List<ArticleDO> lists = acService.getByIds(ids);
		
		StringBuffer urls = new StringBuffer();
		for (ArticleDO article : lists) {
			urls.append(config.getDomain()).append("/blog/post/").append(article.getArticleUrl()).append("\n");
		}
		String result = BaiduUtils.baiduPost(config.getDomain(), config.getBaiduToken(), urls.toString());
		if (StrUtil.isEmpty(result)) {
			return R.error("推送失败");
		}
		
		acService.pushBaidu(ids);
		return R.ok();
	}
	
	/**
	 * 保存标签
	 * @param tags
	 */
	public void saveTags(String tags) {
		if(!StrUtil.isBlank(tags)) {
			String tag[] = tags.split(",");
			Tags t = null;
			for(String s : tag) {
				t= acService.findTagsByName(s);
				if(null == t) {
					t= new Tags(s);
					acService.saveTags(t);
				}
			}
		}
	}
	
	
	@ResponseBody
	@PostMapping("/getCategory")
	@RequiresPermissions("blog:article:add")
	public List<CategoryDO> getCategory(){
		List<CategoryDO> list = categoryService.list(null);
		return list;
	}
	
	@ResponseBody
	@Log("普通上传文件")
	@PostMapping("/upload")
	public R uploadImg(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
		if (!file.isEmpty()) {
			try {
				// 保存目录
				StringBuffer path = new StringBuffer("/haozyx/vupload/"+DateUtil.format(new Date(), "yyyyMMdd")+"/");
				 
//				UpYun upyun = new UpYun("blog-image123", "wanghby", "chpEUJeMr8xyRgC3dzt0xG0QuAvQwFOX");
				UpYun upyun = new UpYun(config.getUpyuname(), config.getUp_username(), config.getUp_password());
				upyun.setTimeout(300);
				upyun.setApiDomain(UpYun.ED_AUTO);
		 
				// 文件后缀
//				String fileSuffix = file.getOriginalFilename()
//						.substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				// 上传文件名加后缀
				String fileName = file.getOriginalFilename();
				boolean isupload = upyun.writeFile(path.toString()+fileName, file.getBytes(), true);
				
				if(isupload) {
					String accessurl = config.getUploadPath()+path.toString()+fileName;
					return R.ok(accessurl);
				}else {
					return R.error("上传失败");
				}
				
			} catch (Exception e) {
				 
				return R.error("系统未知错误");
			}
		} else {
			return R.error("文件为空！");
		}
	}
	
	
	@ResponseBody
	@Log("ckeditor上传附件")
	@PostMapping("/ckupload")
	public R clickuploadImg(@RequestParam(value = "upload") MultipartFile file, HttpServletRequest request){
		if (!file.isEmpty()) {
			try {
				// 保存目录
				StringBuffer path = new StringBuffer("/haozyx/ckupload/"+DateUtil.format(new Date(), "yyyyMMdd")+"/");
				 
//				UpYun upyun = new UpYun("blog-image123", "wanghby", "chpEUJeMr8xyRgC3dzt0xG0QuAvQwFOX");
				UpYun upyun = new UpYun(config.getUpyuname(), config.getUp_username(), config.getUp_password());
				upyun.setTimeout(60);
				upyun.setApiDomain(UpYun.ED_AUTO);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				// 生成文件名称
				String nameSuffix = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."))
						.replaceAll(" ", "_").replaceAll(",", "") + format.format(DateUtil.date())
						+ new Random().nextInt(1000);
				// 文件后缀
				String fileSuffix = file.getOriginalFilename()
						.substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				// 上传文件名加后缀
				String fileName = nameSuffix + "." + fileSuffix;
			 
				 
				boolean isupload = upyun.writeFile(path.toString()+fileName, file.getBytes(), true);
				
				if(isupload) {
					String accessurl = config.getUploadPath()+path.toString()+fileName;
					return new R(1,accessurl);
				}else {
					return R.error("上传失败");
				}
				
			} catch (Exception e) {
				 
				return R.error("系统未知错误");
			}
		} else {
			return R.error("文件为空！");
		}
	}
}
