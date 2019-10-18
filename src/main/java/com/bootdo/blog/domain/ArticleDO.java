package com.bootdo.blog.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-08-13 16:04:26
 */
public class ArticleDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//发表用户
	private Integer userId;
	//文章内容html格式
	private String articleContent;
	//文章内容Markdown格式
	private String articleContentMd;
	//标签
	private String tags;
	//分类，动态更改分类，1.java2.C3.技术4.闲聊
	private String category;
	//发布时间
	private Date articleNewstime;
	//文章状态 0已发布1草稿2回收站
	private Integer articleStatus;
	//文章摘要
	private String articleSummary;
	//略缩图
	private String articleThumbnail;
	//文章标题
	private String articleTitle;
	//文章类型0原创1转载
	private Integer articleType;
	//post文章 page页面
	private String articlePost;
	//是否开启评论 0开启1不开启
	private Integer articleComment;
	//文章最后修改时间
	private Date articleUpdatetime;
	//文章路径
	private String articleUrl;
	//访问量统计
	private Long articleViews;
	//是否公开,1加密 0 公开
	private Integer isPublic;
	//文章密码
	private String articlePassword;
	//是否置顶
	private Integer articleTop;
	//置顶时间
	private Date topTime;
	//置顶失效时间
	private Date topLosetime;
	//是否推荐
	private Integer isCommend;
	//下载地址
	private String downloadUrl;
	//
	private String hold1;
	//
	private String hold2;
	//
	private String hold3;
	//
	private String hold4;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：发表用户
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：发表用户
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：文章内容html格式
	 */
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	/**
	 * 获取：文章内容html格式
	 */
	public String getArticleContent() {
		return articleContent;
	}
	/**
	 * 设置：文章内容Markdown格式
	 */
	public void setArticleContentMd(String articleContentMd) {
		this.articleContentMd = articleContentMd;
	}
	/**
	 * 获取：文章内容Markdown格式
	 */
	public String getArticleContentMd() {
		return articleContentMd;
	}
	/**
	 * 设置：标签
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * 获取：标签
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * 设置：分类，动态更改分类，1.java2.C3.技术4.闲聊
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * 获取：分类，动态更改分类，1.java2.C3.技术4.闲聊
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * 设置：发布时间
	 */
	public void setArticleNewstime(Date articleNewstime) {
		this.articleNewstime = articleNewstime;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getArticleNewstime() {
		return articleNewstime;
	}
	/**
	 * 设置：文章状态 0已发布1草稿2回收站
	 */
	public void setArticleStatus(Integer articleStatus) {
		this.articleStatus = articleStatus;
	}
	/**
	 * 获取：文章状态 0已发布1草稿2回收站
	 */
	public Integer getArticleStatus() {
		return articleStatus;
	}
	/**
	 * 设置：文章摘要
	 */
	public void setArticleSummary(String articleSummary) {
		this.articleSummary = articleSummary;
	}
	/**
	 * 获取：文章摘要
	 */
	public String getArticleSummary() {
		return articleSummary;
	}
	/**
	 * 设置：略缩图
	 */
	public void setArticleThumbnail(String articleThumbnail) {
		this.articleThumbnail = articleThumbnail;
	}
	/**
	 * 获取：略缩图
	 */
	public String getArticleThumbnail() {
		return articleThumbnail;
	}
	/**
	 * 设置：文章标题
	 */
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	/**
	 * 获取：文章标题
	 */
	public String getArticleTitle() {
		return articleTitle;
	}
	/**
	 * 设置：文章类型0原创1转载
	 */
	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}
	/**
	 * 获取：文章类型0原创1转载
	 */
	public Integer getArticleType() {
		return articleType;
	}
	/**
	 * 设置：post文章 page页面
	 */
	public void setArticlePost(String articlePost) {
		this.articlePost = articlePost;
	}
	/**
	 * 获取：post文章 page页面
	 */
	public String getArticlePost() {
		return articlePost;
	}
	/**
	 * 设置：是否开启评论 0开启1不开启
	 */
	public void setArticleComment(Integer articleComment) {
		this.articleComment = articleComment;
	}
	/**
	 * 获取：是否开启评论 0开启1不开启
	 */
	public Integer getArticleComment() {
		return articleComment;
	}
	/**
	 * 设置：文章最后修改时间
	 */
	public void setArticleUpdatetime(Date articleUpdatetime) {
		this.articleUpdatetime = articleUpdatetime;
	}
	/**
	 * 获取：文章最后修改时间
	 */
	public Date getArticleUpdatetime() {
		return articleUpdatetime;
	}
	/**
	 * 设置：文章路径
	 */
	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}
	/**
	 * 获取：文章路径
	 */
	public String getArticleUrl() {
		return articleUrl;
	}
	/**
	 * 设置：访问量统计
	 */
	public void setArticleViews(Long articleViews) {
		this.articleViews = articleViews;
	}
	/**
	 * 获取：访问量统计
	 */
	public Long getArticleViews() {
		return articleViews;
	}
	/**
	 * 设置：是否公开,1加密 0 公开
	 */
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	/**
	 * 获取：是否公开,1加密 0 公开
	 */
	public Integer getIsPublic() {
		return isPublic;
	}
	/**
	 * 设置：文章密码
	 */
	public void setArticlePassword(String articlePassword) {
		this.articlePassword = articlePassword;
	}
	/**
	 * 获取：文章密码
	 */
	public String getArticlePassword() {
		return articlePassword;
	}
	/**
	 * 设置：是否置顶
	 */
	public void setArticleTop(Integer articleTop) {
		this.articleTop = articleTop;
	}
	/**
	 * 获取：是否置顶
	 */
	public Integer getArticleTop() {
		return articleTop;
	}
	/**
	 * 设置：置顶时间
	 */
	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}
	/**
	 * 获取：置顶时间
	 */
	public Date getTopTime() {
		return topTime;
	}
	/**
	 * 设置：置顶失效时间
	 */
	public void setTopLosetime(Date topLosetime) {
		this.topLosetime = topLosetime;
	}
	/**
	 * 获取：置顶失效时间
	 */
	public Date getTopLosetime() {
		return topLosetime;
	}
	/**
	 * 设置：是否推荐
	 */
	public void setIsCommend(Integer isCommend) {
		this.isCommend = isCommend;
	}
	/**
	 * 获取：是否推荐
	 */
	public Integer getIsCommend() {
		return isCommend;
	}
	/**
	 * 设置：下载地址
	 */
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	/**
	 * 获取：下载地址
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}
	/**
	 * 设置：
	 */
	public void setHold1(String hold1) {
		this.hold1 = hold1;
	}
	/**
	 * 获取：
	 */
	public String getHold1() {
		return hold1;
	}
	/**
	 * 设置：
	 */
	public void setHold2(String hold2) {
		this.hold2 = hold2;
	}
	/**
	 * 获取：
	 */
	public String getHold2() {
		return hold2;
	}
	/**
	 * 设置：
	 */
	public void setHold3(String hold3) {
		this.hold3 = hold3;
	}
	/**
	 * 获取：
	 */
	public String getHold3() {
		return hold3;
	}
	/**
	 * 设置：
	 */
	public void setHold4(String hold4) {
		this.hold4 = hold4;
	}
	/**
	 * 获取：
	 */
	public String getHold4() {
		return hold4;
	}
}
