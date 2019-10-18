package com.bootdo.adminm.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.adminm.domain.DicDO;
import com.bootdo.adminm.domain.DicdatasetDO;
import com.bootdo.adminm.service.DicService;
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
@RequestMapping("/adminm/dic")
public class DicController {
	@Autowired
	private DicService dicService;
	@Autowired
	private DicdatasetService dicdatasetService;
	
	@GetMapping()
	@RequiresPermissions("adminm:dic:dic")
	String Dic(){
	    return "adminm/dic/dic";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:dic:dic")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DicDO> dicList = dicService.list(query);
		int total = dicService.count(query);
		PageUtils pageUtils = new PageUtils(dicList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listAllData")
	@RequiresPermissions("adminm:dic:dic")
	public List<DicDO> listAllDataForSelect(){
		 
		return dicService.list();
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:dic:add")
	String add(){
	    return "adminm/dic/add";
	}

	
	@GetMapping("/add_dicdata")
	@RequiresPermissions("adminm:dic:add")
	String add_dicdata(Model model,@RequestParam String dicId){
		
		model.addAttribute("dicId", dicId);
	    return "adminm/dic/add_dicdata";
	}
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:dic:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		DicDO dic = dicService.get(id);
		model.addAttribute("dic", dic);
	    return "adminm/dic/edit";
	}
	
	@GetMapping("/edit_dicdata/{id}")
	@RequiresPermissions("adminm:dic:edit")
	String edit_dicdata(@PathVariable("id") Integer id,Model model){
		DicdatasetDO dicdataset = dicdatasetService.get(id);
		model.addAttribute("dicdataset", dicdataset);
	    return "adminm/dic/edit_dicdata";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:dic:add")
	public R save( DicDO dic){
		if(dicService.save(dic)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:dic:edit")
	public R update( DicDO dic){
		dicService.update(dic);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:dic:remove")
	public R remove( Integer id){
		if(dicService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:dic:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		dicService.batchRemove(ids);
		return R.ok();
	}
	
	@PostMapping( "/getdicdata")
	@ResponseBody
	@RequiresPermissions("adminm:dic:dic")
	public List<DicdatasetDO> getDataBytypecode(@RequestParam String typecode){
		return dicService.getDicDataByTypecode(typecode);
	}
}
