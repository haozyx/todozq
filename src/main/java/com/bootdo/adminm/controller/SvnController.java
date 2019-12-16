package com.bootdo.adminm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.bootdo.adminm.domain.SvnDO;
import com.bootdo.adminm.service.AppCustomService;
import com.bootdo.adminm.service.SvnService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;

import cn.hutool.core.util.StrUtil;

/**
 * 
 * 
 */
 
@Controller
@RequestMapping("/adminm/svn")
public class SvnController {
	@Autowired
	private SvnService svnService;
	@Autowired
	private AppCustomService appService;
	
	@GetMapping()
	@RequiresPermissions("adminm:svn:svn")
	String Svn(){
	    return "adminm/svn/svn";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("adminm:svn:svn")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		String userid = ShiroUtils.getUserId()+"";
        params.put("userid", userid);	
		Query query = new Query(params);
		List<SvnDO> svnList = svnService.list(query);
		int total = svnService.count(query);
		PageUtils pageUtils = new PageUtils(svnList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("adminm:svn:add")
	String add(){
	    return "adminm/svn/add";
	}

	@GetMapping("/edit/{svnid}")
	@RequiresPermissions("adminm:svn:edit")
	String edit(@PathVariable("svnid") Integer svnid,Model model){
		SvnDO svn = svnService.get(svnid);
		model.addAttribute("svn", svn);
	    return "adminm/svn/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("adminm:svn:add")
	public R save( SvnDO svn){
		//设置用户的ID
		String userid = ShiroUtils.getUserId()+"";
		svn.setUserid(Integer.parseInt(userid));
		
		if(svnService.save(svn)>0){
			
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("adminm:svn:edit")
	public R update( SvnDO svn){
		svnService.update(svn);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("adminm:svn:remove")
	public R remove( Integer svnid){
		if(svnService.remove(svnid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("adminm:svn:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] svnids){
		svnService.batchRemove(svnids);
		return R.ok();
	}
	@GetMapping( "/getuserbydepid")
	@ResponseBody
	public R getuserbydepid(@RequestParam String depid) {
		try {
			if(StrUtil.isEmptyOrUndefined(depid)) return R.error("deptid为空");
			String sql = "select user_id,name from sys_user where dept_id ="+depid;
			List<Map<String, Object>> userlist = appService.selectList(sql);
			
			return R.ok().put("userlist", userlist);
			
		} catch (Exception e) {
			return R.error("系统错误");
		}
	}
	
}
