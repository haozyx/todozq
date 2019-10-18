package com.bootdo.common.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.UpYun;
import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.R;
import com.upyun.UpException;

import cn.hutool.core.util.StrUtil;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/common/file")
public class FileController extends BaseController {

	private static final Map<String, String> configs = Constant.OPTIONS;

	@GetMapping()
	@RequiresPermissions("common:sysFile:sysFile")
	String sysFile(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "adminm/file/file";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysFile:sysFile")
	public PageUtils list(@RequestParam Map<String, Object> params,Model model) {
		// 查询列表数据
		UpYun upyun = new UpYun(configs.get("upyuname"), configs.get("up_username"), configs.get("up_password"));
		
		upyun.setTimeout(60);
		upyun.setApiDomain(UpYun.ED_AUTO);
		
/*		List<String> dateList = new ArrayList<String>();
		Calendar cd = Calendar.getInstance();
		cd.setTime(new Date());
		for(int i=1;i<6;i++) {
			cd.add(Calendar.DAY_OF_MONTH, -i);
			Date date = cd.getTime();
			dateList.add(DateUtils.format(date, "yyyyMMdd"));
		}
		String datepath = (String) params.get("datepath");
		if(StrUtil.isBlank(datepath)) datepath = ;*/
		
		String path = "/haozyx/ckupload/"+DateUtils.format(new Date(), "yyyyMMdd")+"/";
		
		model.addAttribute("path", path);
		
		List<UpYun.FolderItem> items;
		try {
			items = upyun.readDir(path,null);
			int total = items.size();
			PageUtils pageUtils = new PageUtils(items, total);
			return pageUtils;
		} catch (IOException | UpException e) {
			e.printStackTrace();
			return null;
		}

	}
 
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	public R remove(HttpServletRequest request) { 
		String name = request.getParameter("name");
		String datepath = request.getParameter("datepath");
		// 查询列表数据
		UpYun upyun = new UpYun(configs.get("upyuname"), configs.get("up_username"), configs.get("up_password"));
		
		upyun.setTimeout(60);
		upyun.setApiDomain(UpYun.ED_AUTO);
		String path = "/haozyx/ckupload/"+datepath + name  ;
		try {
			upyun.deleteFile(path, null);
		} catch (IOException | UpException e) {
			 e.printStackTrace();
			 return R.error("删除失败");
		}
		return R.ok();
		
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:remove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
 
		return R.ok();
	}
 

}
