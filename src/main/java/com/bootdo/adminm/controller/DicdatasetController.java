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
import com.bootdo.adminm.service.DicdatasetService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-08 08:52:38
 */
 
@Controller
@RequestMapping("/adminm/dicdataset")
public class DicdatasetController {
	@Autowired
	private DicdatasetService dicdatasetService;
	
	@GetMapping()
	@RequiresPermissions("adminm:dicdataset:dicdataset")
	String Dicdataset(){
		//不执行跳转
	    return "adminm/dicdataset/dicdataset";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:dicdataset:dicdataset")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DicdatasetDO> dicdatasetList = dicdatasetService.list(query);
		int total = dicdatasetService.count(query);
		PageUtils pageUtils = new PageUtils(dicdatasetList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:dicdataset:add")
	String add(){
	    return "adminm/dicdataset/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:dicdataset:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		DicdatasetDO dicdataset = dicdatasetService.get(id);
		model.addAttribute("dicdataset", dicdataset);
	    return "adminm/dicdataset/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:dicdataset:add")
	public R save( DicdatasetDO dicdataset){
		if(dicdatasetService.save(dicdataset)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:dicdataset:edit")
	public R update( DicdatasetDO dicdataset){
		dicdatasetService.update(dicdataset);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:dicdataset:remove")
	public R remove( Integer id){
		if(dicdatasetService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:dicdataset:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		dicdatasetService.batchRemove(ids);
		return R.ok();
	}
	
}
