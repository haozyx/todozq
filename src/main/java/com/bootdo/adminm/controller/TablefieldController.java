package com.bootdo.adminm.controller;

import java.util.HashMap;
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

import com.bootdo.adminm.domain.TablefieldDO;
import com.bootdo.adminm.service.AppCustomService;
import com.bootdo.adminm.service.TablefieldService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-12-18 19:59:40
 */
 
@Controller
@RequestMapping("/adminm/tablefield")
public class TablefieldController {
	@Autowired
	private TablefieldService tablefieldService;
	
	@Autowired
	private AppCustomService appService;
	
	@GetMapping()
	@RequiresPermissions("adminm:tablefield:tablefield")
	String Tablefield(){
	    return "adminm/tablefield/tablefield";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:tablefield:tablefield")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TablefieldDO> tablefieldList = tablefieldService.list(query);
		int total = tablefieldService.count(query);
		PageUtils pageUtils = new PageUtils(tablefieldList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:tablefield:add")
	String add(){
	    return "adminm/tablefield/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:tablefield:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TablefieldDO tablefield = tablefieldService.get(id);
		model.addAttribute("tablefield", tablefield);
	    return "adminm/tablefield/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:tablefield:add")
	public R save( TablefieldDO tablefield){
		if(tablefieldService.save(tablefield)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:tablefield:edit")
	public R update( TablefieldDO tablefield){
		tablefieldService.update(tablefield);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:tablefield:remove")
	public R remove( Integer id){
		if(tablefieldService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:tablefield:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tablefieldService.batchRemove(ids);
		return R.ok();
	}
	
}
