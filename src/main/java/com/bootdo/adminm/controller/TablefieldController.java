package com.bootdo.adminm.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;

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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.bootdo.adminm.domain.TablefieldDO;
import com.bootdo.adminm.service.AppCustomService;
import com.bootdo.adminm.service.TablefieldService;
import com.bootdo.common.utils.ExcelUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import cn.hutool.core.util.StrUtil;

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
	
	@GetMapping("/list/{tableid}")
	@RequiresPermissions("adminm:tablefield:tablefield")
	public String list(@PathVariable("tableid") Integer tableid,Model model){
		//查询列表数据
		String sql = "select * from admin_tablefield where tableid="+tableid;
		
		List<Map<String,Object>> selectList = appService.selectList(sql);
		model.addAttribute("tablefieldlist", selectList);
		//获取表名
		String gettablename = "select entablename from admin_tables where id="+tableid;
		String tablename = appService.selectOne(gettablename);
		model.addAttribute("tablename", tablename);
		
		model.addAttribute("tableid", tableid);
		
		
		return "adminm/tablefield/tablefield";
	}
	
	
	@ResponseBody
	@PostMapping("/import")
	@RequiresPermissions("adminm:tablefield:import")
	R importtablefield(@RequestParam("tableid") String tableid,@RequestParam("file") MultipartFile file){
		//首先是文件后缀名的校验
		String fileName = file.getOriginalFilename();
		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return R.error("上传文件格式不正确");
        }
		
		if(StrUtil.isEmptyOrUndefined(tableid)) return R.error("关联字段丢失");
		
		//已经存在的字段不进行插入
		String existsql = "select * from admin_tablefield where tableid="+tableid;
		List<Map<String, Object>> fieldlist = appService.selectList(existsql);
		String fields[] = null;
		
		
		//读取文件中的记录
		List<TablefieldDO> list = ExcelUtils.readExcel("", TablefieldDO.class, file);
		if(fieldlist.size()>0) {
			fields = new String[fieldlist.size()];
			for(int i=0;i<fieldlist.size();i++) {
				fields[i] = fieldlist.get(i).get("fieldname").toString();
			}
		}
		//如果有人录入了部分字段--这里排除掉导入文件的中的记录
		if(null!=fields&& fields.length>0) {
			Iterator<TablefieldDO> it = list.iterator();
	        while(it.hasNext()){
	        	TablefieldDO td =  it.next();
	            if(Arrays.asList(fields).contains(td.getFieldname().toLowerCase())){
	                it.remove();
	            }        
	        }
		}
		
        if(list.size()==0) return R.ok();
        for(TablefieldDO t : list) {
        	t.setTableid(Integer.parseInt(tableid));
        	t.setFieldname(t.getFieldname().toLowerCase());
        }
		
        tablefieldService.insertBatch(list);
        
	    return R.ok();
	}
	
	@GetMapping("/add/{tableid}")
	@RequiresPermissions("adminm:tablefield:add")
	String add(@PathVariable("tableid") Integer tableid,Model model){
		model.addAttribute("tableid", tableid);
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
		
		tablefield.setFieldname(tablefield.getFieldname().toLowerCase());
		
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
		tablefield.setFieldname(tablefield.getFieldname().toLowerCase());
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
