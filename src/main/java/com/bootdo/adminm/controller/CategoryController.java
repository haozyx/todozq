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

import com.bootdo.adminm.domain.CategoryDO;
import com.bootdo.adminm.service.CategoryService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 文章分类表
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-22 15:50:41
 */
 
@Controller
@RequestMapping("/adminm/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	@RequiresPermissions("adminm:category:category")
	String Category(){
	    return "adminm/category/category";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:category:category")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CategoryDO> categoryList = categoryService.list(query);
		int total = categoryService.count(query);
		PageUtils pageUtils = new PageUtils(categoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:category:add")
	String add(){
	    return "adminm/category/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:category:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CategoryDO category = categoryService.get(id);
		model.addAttribute("category", category);
	    return "adminm/category/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:category:add")
	public R save( CategoryDO category){
		if(categoryService.save(category)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:category:edit")
	public R update( CategoryDO category){
		categoryService.update(category);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:category:remove")
	public R remove( Integer id){
		if(categoryService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:category:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		categoryService.batchRemove(ids);
		return R.ok();
	}
	
}
