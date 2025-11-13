package openjoe.smart.sso.server.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import openjoe.smart.sso.server.stage.mybatisplus.entity.BaseEntity;

import java.beans.Transient;

@TableName("sys_office")
public class Office extends BaseEntity {
	
	private Long parentId;
	private String name;
	private Integer sort;
	private Boolean isEnable;

	public Long getParentId() {
		return this.parentId;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Boolean getIsEnable() {
		return this.isEnable;
	}
	
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	@Transient
	public Long getPId() {
		return this.parentId;
	}
}
