package com.bootdo.adminm.controller;

import java.util.Date;
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

import com.bootdo.adminm.domain.ThinglogsDO;
import com.bootdo.adminm.service.ThinglogsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-05-15 10:49:48
 */
 
@Controller
@RequestMapping("/adminm/thinglogs")
public class ThinglogsController {
	@Autowired
	private ThinglogsService thinglogsService;
	
	@GetMapping()
	@RequiresPermissions("adminm:thinglogs:thinglogs")
	String Thinglogs(){
	    return "adminm/thinglogs/thinglogs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:thinglogs:thinglogs")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ThinglogsDO> thinglogsList = thinglogsService.list(query);
		int total = thinglogsService.count(query);
		PageUtils pageUtils = new PageUtils(thinglogsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:thinglogs:add")
	String add(){
	    return "adminm/thinglogs/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:thinglogs:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ThinglogsDO thinglogs = thinglogsService.get(id);
		model.addAttribute("thinglogs", thinglogs);
	    return "adminm/thinglogs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:thinglogs:add")
	public R save( ThinglogsDO thinglogs){
		thinglogs.setRecordtime(new Date());
		if(thinglogsService.save(thinglogs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:thinglogs:edit")
	public R update( ThinglogsDO thinglogs){
		thinglogsService.update(thinglogs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:thinglogs:remove")
	public R remove( Integer id){
		if(thinglogsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:thinglogs:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		thinglogsService.batchRemove(ids);
		return R.ok();
	}
	
}
