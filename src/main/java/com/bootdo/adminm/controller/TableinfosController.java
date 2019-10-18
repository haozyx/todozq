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

import com.bootdo.adminm.domain.TableinfosDO;
import com.bootdo.adminm.service.TableinfosService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-05-27 14:42:36
 */
 
@Controller
@RequestMapping("/adminm/tableinfos")
public class TableinfosController {
	@Autowired
	private TableinfosService tableinfosService;
	
	@GetMapping()
	@RequiresPermissions("adminm:tableinfos:tableinfos")
	String Tableinfos(){
	    return "adminm/tableinfos/tableinfos";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:tableinfos:tableinfos")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TableinfosDO> tableinfosList = tableinfosService.list(query);
		int total = tableinfosService.count(query);
		PageUtils pageUtils = new PageUtils(tableinfosList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:tableinfos:add")
	String add(){
	    return "adminm/tableinfos/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:tableinfos:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TableinfosDO tableinfos = tableinfosService.get(id);
		model.addAttribute("tableinfos", tableinfos);
	    return "adminm/tableinfos/edit";
	}
	
	@GetMapping("/view/{id}")
	@RequiresPermissions("adminm:tableinfos:view")
	String view(@PathVariable("id") Integer id,Model model){
		TableinfosDO tableinfos = tableinfosService.get(id);
		model.addAttribute("tableinfos", tableinfos);
	    return "adminm/tableinfos/view";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:tableinfos:add")
	public R save( TableinfosDO tableinfos){
		if(tableinfosService.save(tableinfos)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:tableinfos:edit")
	public R update( TableinfosDO tableinfos){
		tableinfosService.update(tableinfos);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:tableinfos:remove")
	public R remove( Integer id){
		if(tableinfosService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:tableinfos:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tableinfosService.batchRemove(ids);
		return R.ok();
	}
	
}
