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

import com.bootdo.adminm.domain.LinkDO;
import com.bootdo.adminm.service.LinkService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-15 19:19:55
 */
 
@Controller
@RequestMapping("/adminm/link")
public class LinkController {
	@Autowired
	private LinkService linkService;
	
	@GetMapping()
	@RequiresPermissions("adminm:link:link")
	String Link(){
	    return "adminm/link/link";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:link:link")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LinkDO> linkList = linkService.list(query);
		int total = linkService.count(query);
		PageUtils pageUtils = new PageUtils(linkList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:link:add")
	String add(){
	    return "adminm/link/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:link:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		LinkDO link = linkService.get(id);
		model.addAttribute("link", link);
	    return "adminm/link/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:link:add")
	public R save( LinkDO link){
		if(linkService.save(link)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:link:edit")
	public R update( LinkDO link){
		linkService.update(link);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:link:remove")
	public R remove( Integer id){
		if(linkService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:link:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		linkService.batchRemove(ids);
		return R.ok();
	}
	
}
