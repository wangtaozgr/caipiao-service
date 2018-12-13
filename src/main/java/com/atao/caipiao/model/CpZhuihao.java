package com.atao.caipiao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.atao.base.model.BaseEntity;

/**
 * 
 *
 * @author twang
 */
public class CpZhuihao extends BaseEntity {

	/**
	* 
	*/
	@Id
	@Column(name = "zh_id")
	private Integer zhId;

	/**
	 * 期数
	 */
	@Column(name = "code")
	private Integer code;
	/**
	 * 位数 1:个位2:十位;3:百位;4:千位;5:万位
	 */
	@Column(name = "w")
	private Integer w;
	/**
	 * 类型 1:大;2:小;3:偶数;4:奇数
	 */
	@Column(name = "type")
	private Integer type;
	/**
	 * 
	 */
	@Column(name = "buyNum")
	private Integer buynum;
	/**
	 * 0:可以追号的;1:追号中;2:追号成功;3:追号失败
	 */
	@Column(name = "status")
	private Integer status;
	/**
	 * 
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 
	 */
	@Column(name = "content")
	private String content;
	
	@Transient
	private List<CpRecord> records;

	public Integer getZhId() {
		return zhId;
	}

	public void setZhId(Integer zhId) {
		this.zhId = zhId;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getBuynum() {
		return buynum;
	}

	public void setBuynum(Integer buynum) {
		this.buynum = buynum;
	}

	public Integer getStatus() {
		return status;
	}

	@Transient
	public String getStatusStr() {
		// 0:可以追号的;1:追号中;2:追号成功;3:追号失败
		String statusStr = "未开始";
		switch (status) {
		case 0:
			statusStr = "未开始";
			break;
		case 1:
			statusStr = "追号中";
			break;
		case 2:
			statusStr = "追号成功";
			break;
		case 3:
			statusStr = "追号失败";
			break;
		default:
			break;
		}
		return statusStr;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Transient
	public String getWStr() {
		String s = "";
		switch (w) {
		case 1:
			s = "个位";
			break;
		case 2:
			s = "十位";
			break;
		case 3:
			s = "百位";
			break;
		case 4:
			s = "千位";
			break;
		case 5:
			s = "万位";
			break;
		default:
			break;
		}
		return s;
	}

	@Transient
	public String getTypeStr() {
		String s = "";
		switch (type) {
		case 1:
			s = "大";
			break;
		case 2:
			s = "小";
			break;
		case 3:
			s = "偶数";
			break;
		case 4:
			s = "奇数";
			break;
		default:
			break;
		}
		return s;
	}

	public static class TF {

		public static String TABLE_NAME = "CP_ZHUIHAO"; // 表名

		// public static String TABLE_SCHEMA = ConfigUtils.getValue(""); // 库名

		public static String zhId = "zh_id"; //
		public static String code = "code"; // 期数
		public static String w = "w"; // 位数
		public static String type = "type"; // 类型
		public static String buynum = "buyNum"; //
		public static String status = "status"; // 0:追号中;1:追号成功;2:追号失败
		public static String createTime = "create_time"; //
		public static String content = "content"; //

	}

	public List<CpRecord> getRecords() {
		return records;
	}

	public void setRecords(List<CpRecord> records) {
		this.records = records;
	}
}
