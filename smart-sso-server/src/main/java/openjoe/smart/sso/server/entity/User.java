package openjoe.smart.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import openjoe.smart.sso.server.stage.mybatisplus.entity.BaseEntity;

import java.util.Date;

@TableName("sys_user")
public class User extends BaseEntity {
	
	private Long officeId;
	private String name;
	private String account;
	private String password;
	private Integer loginCount;
	private Date lastLoginTime;
	private Boolean isEnable;
	
	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
}
