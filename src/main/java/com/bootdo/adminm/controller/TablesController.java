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

import com.bootdo.adminm.domain.DicdatasetDO;
import com.bootdo.adminm.domain.TablesDO;
import com.bootdo.adminm.service.AppCustomService;
import com.bootdo.adminm.service.DicdatasetService;
import com.bootdo.adminm.service.TablesService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-12-05 14:28:24
 */
 
@Controller
@RequestMapping("/adminm/tables")
public class TablesController {
	@Autowired
	private TablesService tablesService;
	
	@Autowired
	private DicdatasetService dicdatasetService;
	@Autowired
	private AppCustomService appService;
	
	@GetMapping()
	@RequiresPermissions("adminm:tables:tables")
	String Tables(){
	    return "adminm/tables/tables";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:tables:tables")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TablesDO> tablesList = tablesService.list(query);
		int total = tablesService.count(query);
		PageUtils pageUtils = new PageUtils(tablesList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:tables:add")
	String add(){
	    return "adminm/tables/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:tables:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TablesDO tables = tablesService.get(id);
		model.addAttribute("tables", tables);
	    return "adminm/tables/edit";
	}
	
	
	@GetMapping("/view/{id}")
	@RequiresPermissions("adminm:tables:view")
	String view(@PathVariable("id") Integer id,Model model){
		
		TablesDO tables = tablesService.get(id);
		
		Integer categoryid = tables.getTablecategory();
		
		DicdatasetDO do1 = dicdatasetService.get(categoryid);
		
		model.addAttribute("tables", tables);
		model.addAttribute("categoryname", do1.getDisName());
		
	    return "adminm/tables/view";
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:tables:add")
	public R save( TablesDO tables){
		
		//保存之前进行表名的小写转换
		String tablename = tables.getEntablename().toLowerCase();
		String existsql = "select count(1) from admin_tables where entablename='"+tablename+"'";
		
		if("1".equals(appService.selectOne(existsql))) return R.error("该表已经存在");
		
		tables.setEntablename(tablename);
		if(tablesService.save(tables)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:tables:edit")
	public R update( TablesDO tables){
		tablesService.update(tables);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:tables:remove")
	public R remove( Integer id){
		if(tablesService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:tables:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tablesService.batchRemove(ids);
		return R.ok();
	}
	
}
