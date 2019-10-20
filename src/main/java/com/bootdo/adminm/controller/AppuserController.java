package com.bootdo.adminm.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.adminm.domain.AppuserDO;
import com.bootdo.adminm.service.AppuserService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-10-19 16:49:41
 */
 
@Controller
@RequestMapping("/adminm/appuser")
public class AppuserController {
	@Autowired
	private AppuserService appuserService;
	
	@GetMapping()
	@RequiresPermissions("adminm:appuser:appuser")
	String Appuser(){
	    return "adminm/appuser/appuser";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:appuser:appuser")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AppuserDO> appuserList = appuserService.list(query);
		int total = appuserService.count(query);
		PageUtils pageUtils = new PageUtils(appuserList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:appuser:add")
	String add(){
	    return "adminm/appuser/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:appuser:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AppuserDO appuser = appuserService.get(id);
		model.addAttribute("appuser", appuser);
	    return "adminm/appuser/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:appuser:add")
	public R save( AppuserDO appuser){
		if(appuserService.save(appuser)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:appuser:edit")
	public R update( AppuserDO appuser){
		appuserService.update(appuser);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:appuser:remove")
	public R remove( Integer id){
		if(appuserService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:appuser:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		appuserService.batchRemove(ids);
		return R.ok();
	}
	
}
