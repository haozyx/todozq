package com.bootdo.common.config;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	/** 加密次数 */
	public static int HASH_ITERATIONS = 3;

	public static String LOGIN_USER = "login_user";

	public static String USER_PERMISSIONS = "user_permissions";

	/** 登陆token(nginx中默认header无视下划线) */
	public static String LOGIN_TOKEN = "login-token";
	
    //演示系统账户
    public static String DEMO_ACCOUNT = "test";
    //自动去除表前缀
    public static String AUTO_REOMVE_PRE = "true";
    //停止计划任务
    public static String STATUS_RUNNING_STOP = "stop";
    //开启计划任务
    public static String STATUS_RUNNING_START = "start";
    //通知公告阅读状态-未读
    public static String OA_NOTIFY_READ_NO = "0";
    //通知公告阅读状态-已读
    public static int OA_NOTIFY_READ_YES = 1;
    
    public static int PAGESIZE =30;
    //部门根节点id
    public static Long DEPT_ROOT_ID = 0l;
    //缓存方式
    public static String CACHE_TYPE_REDIS ="ehcache";

    public static String LOG_ERROR = "error";
    //设置博客的基础信息
    public static Map<String,String> OPTIONS = new HashMap<String,String>();
    //前台文章分类信息
    public static Map<String,String> CATEGORIES = new HashMap<String,String>();
    //项目中所有的URL信息
    public static StringBuffer SB = new StringBuffer("/index,");
}
