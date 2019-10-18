package com.bootdo.common.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author : 宋浩志
 * @createDate : 2018年12月6日
 */
@Component
public class Commons {

	
	private static final String[] ICONS = { "bg-ico-book", "bg-ico-game", "bg-ico-note", "bg-ico-chat", "bg-ico-code",
			"bg-ico-image", "bg-ico-web", "bg-ico-link", "bg-ico-design", "bg-ico-lock" };

	/**
	 * 显示文章图标
	 *
	 * @param id
	 * @return
	 */
	public static String show_icon(int id) {
		return ICONS[id % ICONS.length];
	}
	
	public static int getYear() {
		return DateUtil.year(new Date());
	}
	 
	/**
	 * 显示标签
	 * 
	 * @param tagsUrl
	 * @return
	 * @throws Exception
	 */
	public static String show_tags(String tagsUrl) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (StrUtil.isNotBlank(tagsUrl)) {
			String[] tag = tagsUrl.split(",");
			for (String t : tag) {
				sb.append("<a href='javascript:;'>" + t + "</a>");
			}
		}
		return sb.toString();
	}
	
}
