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

import com.bootdo.adminm.domain.SqlmanagerDO;
import com.bootdo.adminm.service.SqlmanagerService;
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
@RequestMapping("/adminm/sqlmanager")
public class SqlmanagerController {
	@Autowired
	private SqlmanagerService sqlmanagerService;
	
	@GetMapping()
	@RequiresPermissions("adminm:sqlmanager:sqlmanager")
	String Sqlmanager(){
	    return "adminm/sqlmanager/sqlmanager";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:sqlmanager:sqlmanager")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SqlmanagerDO> sqlmanagerList = sqlmanagerService.list(query);
		int total = sqlmanagerService.count(query);
		PageUtils pageUtils = new PageUtils(sqlmanagerList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:sqlmanager:add")
	String add(){
	    return "adminm/sqlmanager/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:sqlmanager:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SqlmanagerDO sqlmanager = sqlmanagerService.get(id);
		model.addAttribute("sqlmanager", sqlmanager);
	    return "adminm/sqlmanager/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:sqlmanager:add")
	public R save( SqlmanagerDO sqlmanager){
		if(sqlmanagerService.save(sqlmanager)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:sqlmanager:edit")
	public R update( SqlmanagerDO sqlmanager){
		sqlmanagerService.update(sqlmanager);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:sqlmanager:remove")
	public R remove( Integer id){
		if(sqlmanagerService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:sqlmanager:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		sqlmanagerService.batchRemove(ids);
		return R.ok();
	}
	
}
