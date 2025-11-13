package openjoe.smart.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import openjoe.smart.sso.server.stage.mybatisplus.entity.BaseEntity;


@TableName("sys_role")
public class Role extends BaseEntity {

	private String name;
	private Integer sort;
	private String description;
	private Boolean isEnable;

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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
}
