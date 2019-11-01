package com.bootdo.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootdo.adminm.domain.ActivecodeDO;
import com.bootdo.adminm.domain.AppuserDO;
import com.bootdo.adminm.service.ActivecodeService;
import com.bootdo.adminm.service.AppCustomService;
import com.bootdo.adminm.service.AppuserService;
import com.bootdo.blog.domain.ArticleDO;
import com.bootdo.blog.service.ArticleService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/m/")
@Transactional
public class GiveMeFiveController {

	@Autowired
	AppCustomService appService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	AppuserService userService;
	
	@Autowired
	ActivecodeService codeService;
	
	@GetMapping("getcode")
	public R getcode() {
		
		return R.ok().put("code", "888888");
	}
	
	
	
	@GetMapping("getrecommendlist")
	public R getrecommendlist(@RequestParam int page,@RequestParam int pagesize) {
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("articleStatus", "0"); //0 发布 1草稿
		params.put("isCommend", "1"); //推荐
		 
		int count = articleService.count(params);// 先计算总数  总数为0 不做操作
		
		if(count ==0) return R.ok().put("list","");
		
		//将当前页和总页数传递到前台
		int totalpages = (count + pagesize -1) /pagesize;
		//对不合理的分页数进行处理
		page = page < 0  ? 1 :page;
		page = page > totalpages ? totalpages : page;
		pagesize = pagesize<0 ? 5 :pagesize;

		params.put("offset", (page-1)*pagesize);
		params.put("limit", pagesize);
		Query query = new Query(params);
	    List<ArticleDO> articleList = articleService.list(query);	
		
		
		return R.ok().put("list", articleList).put("totalpage", totalpages);
	}
	
	
	@GetMapping("list")
	public R getlist(@RequestParam int page,@RequestParam int pagesize,@RequestParam String category) {
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("articleStatus", "0"); //0 发布 1草稿
		
		//分类点进去的时候才可以用
		if(!StrUtil.isBlankOrUndefined(category)) {
			params.put("category",category);
		}
		int count = articleService.count(params);// 先计算总数  总数为0 不做操作
		
		if(count ==0) return R.ok().put("list","");
		
		//将当前页和总页数传递到前台
		int totalpages = (count + pagesize -1) /pagesize;
		//对不合理的分页数进行处理
		page = page < 0  ? 1 :page;
		page = page > totalpages ? totalpages : page;
		pagesize = pagesize<0 ? 5 :pagesize;

		params.put("offset", (page-1)*pagesize);
		params.put("limit", pagesize);
		Query query = new Query(params);
	    List<ArticleDO> articleList = articleService.list(query);	
		
		
		return R.ok().put("list", articleList).put("totalpage", totalpages);
	}
	
	
	
	@GetMapping("getdetail")
	public R getdetail(@RequestParam String id) {
		
		 
		if(StrUtil.isBlankOrUndefined(id)) {
			 return R.error("ID不能为空!");
		}
		
		ArticleDO article = null;
		
		try {
			article = articleService.get(Integer.valueOf(id));
		}catch(Exception e) {
			e.printStackTrace();
			return R.error("获取详情异常");
		}
		
		return R.ok().put("article", article);
	}
	
	@PostMapping("saveuser")
	public R registerUser(@RequestBody AppuserDO user) {
		
		String username = user.getUsername();
		if(StrUtil.isBlankOrUndefined(username)) return R.error("用户名为空");
		
		String password = MD5Utils.encrypt(username, user.getPassword());
		
		try {
			String getusersql = "select * from app_appuser where username='"+username+"'";
			List<Map<String,Object>> list = appService.selectList(getusersql);
			if(list.size()==0) {
				
				user.setRegistime(new Date());
				user.setPassword(password);
				userService.save(user);
				
			}else {
				
				if(!password.equals(list.get(0).get("password").toString())) {
					return R.error("用户名或密码错误");
				}
				
				return R.ok().put("user", list.get(0));
			}
			
			
			
		} catch (Exception e) {
			return R.error("系统错误联系管理员");
		}
		
		return R.ok().put("user", user);
	}
	
	@GetMapping("getuserstatus")
	public R getuserstatus(@RequestParam String id) {
		if(StrUtil.isBlankOrUndefined(id)) return R.error("必填参数为空");
		
		AppuserDO user = userService.get(Integer.valueOf(id));
		if(null == user) return R.error("根据ID无法找到用户");
		
		user.setPassword("");
		
		Date outdate = user.getOutdate();
		if(null!= outdate) {
			long cha = outdate.getTime() - new Date().getTime();
			if(cha<0) {
				user.setStatus("0");//说明过期了
			}
		}
		
		return R.ok().put("user", user);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@GetMapping("activeuser")
	public R activeuser(@RequestParam String id,@RequestParam String activecode) {
		
		if(StrUtil.isBlankOrUndefined(activecode)||StrUtil.isBlankOrUndefined(id)) return R.error("必填参数为空");
		
		Map<String,Object> map = new HashMap<>();
		map.put("code", activecode);
		map.put("status", "1");
		
		List<ActivecodeDO> list = null;
		try {
			
			list = codeService.list(map);
			if(list.size()==0) return R.error("激活码无效");

			ActivecodeDO ac= list.get(0);
			String type = ac.getType();
			
			AppuserDO user = userService.get(Integer.valueOf(id));
			//获取日期
			Date date = new Date();
			DateTime offsetDay = DateUtil.offsetDay(date,Integer.valueOf(type));
			user.setActiveCode(activecode); //激活码
			user.setActivetime(new Date()); //激活时间
			user.setOutdate(offsetDay); //过期时间
			user.setStatus("1"); //0是游客 1是会员

			//更新用户
			int a = userService.update(user);
			
			if(a==0) return R.error("激活失败,联系管理员");
			//激活码设置为失效
			ac.setStatus("0");
			codeService.update(ac);
			
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return R.error("服务发生错误");
		}		
		
		return R.ok();
	}
}
