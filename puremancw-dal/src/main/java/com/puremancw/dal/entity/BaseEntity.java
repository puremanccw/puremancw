/**
 * 
 */
package com.puremancw.dal.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.puremancw.common.utils.StringUtil;



/**
 * <p>Tilte: BaseEntity</p>
 * @author puremancw
 * @date 2016年11月18日 下午4:23:43
 
 */
public class BaseEntity {
	
	/**
	 * 主键Id
	 */
	private String id = StringUtil.get32UUID();
	
	/**
	 * 版本号，默认为0
	 */
	private Integer version = 0;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 创建人
	 */
	private String creater;
	
	/**
	 * 创建时间
	 */
	private Date createTime = new Date();
	
	/**
	 * 修改人
	 */
	private String editor;
	
	/**
	 * 修改时间
	 */
	private Date editTime;
	
	/**
	 * 描述
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
