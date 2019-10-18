package com.bootdo.adminm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.bootdo.adminm.domain.ActivecodeDO;
import com.bootdo.adminm.service.ActivecodeService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-08-14 14:55:31
 */
 
@Controller
@RequestMapping("/adminm/activecode")
public class ActivecodeController {
	@Autowired
	private ActivecodeService activecodeService;
	
	@GetMapping()
	@RequiresPermissions("adminm:activecode:activecode")
	String Activecode(){
	    return "adminm/activecode/activecode";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:activecode:activecode")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ActivecodeDO> activecodeList = activecodeService.list(query);
		int total = activecodeService.count(query);
		PageUtils pageUtils = new PageUtils(activecodeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:activecode:add")
	String add(){
	    return "adminm/activecode/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("adminm:activecode:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ActivecodeDO activecode = activecodeService.get(id);
		model.addAttribute("activecode", activecode);
	    return "adminm/activecode/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:activecode:add")
	public R save(HttpServletRequest req){
		
		String type = req.getParameter("type");
		String quantity = req.getParameter("quantity");
		
		ActivecodeDO ad = null;
		try {
			int sl = Integer.parseInt(quantity);
			for(int i=0;i<sl;i++) {
				String code = MD5Utils.genRandomNum();
				ad =new ActivecodeDO(code,type,"1");
				activecodeService.save(ad);
			}
			return R.ok();
		}catch(Exception e) {
			return R.error();
		}
		
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:activecode:edit")
	public R update( ActivecodeDO activecode){
		activecodeService.update(activecode);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:activecode:remove")
	public R remove( Integer id){
		if(activecodeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:activecode:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		activecodeService.batchRemove(ids);
		return R.ok();
	}
	
}
