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

import com.bootdo.adminm.domain.IndexmenuDO;
import com.bootdo.adminm.service.IndexmenuService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 索引菜单controller
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-04 11:21:48
 */
 
@Controller
@RequestMapping("/adminm/indexmenu")
public class IndexmenuController {
	@Autowired
	private IndexmenuService indexmenuService;
	
	@GetMapping()
	@RequiresPermissions("adminm:indexmenu:indexmenu")
	String Indexmenu(){
	    return "adminm/indexmenu/indexmenu";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:indexmenu:indexmenu")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<IndexmenuDO> indexmenuList = indexmenuService.list(query);
		int total = indexmenuService.count(query);
		PageUtils pageUtils = new PageUtils(indexmenuList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:indexmenu:add")
	String add(){
	    return "adminm/indexmenu/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:indexmenu:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		IndexmenuDO indexmenu = indexmenuService.get(id);
		model.addAttribute("indexmenu", indexmenu);
	    return "adminm/indexmenu/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:indexmenu:add")
	public R save( IndexmenuDO indexmenu){
		if(indexmenuService.save(indexmenu)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:indexmenu:edit")
	public R update( IndexmenuDO indexmenu){
		indexmenuService.update(indexmenu);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:indexmenu:remove")
	public R remove( Integer id){
		if(indexmenuService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:indexmenu:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		indexmenuService.batchRemove(ids);
		return R.ok();
	}
	
}
