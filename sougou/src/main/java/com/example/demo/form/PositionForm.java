package com.example.demo.form;

import java.time.LocalDateTime;

public class PositionForm {

	private String positionId;
	
	private String positionName;
	
	private int accessAuthority;
	
	private LocalDateTime registDate;
	
	private LocalDateTime updateDate;
	
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	
	public void setPositionName(String positionName){
		this.positionName = positionName;
	}
	
	public void setAccessAuthority(int accessAuthority) {
		this.accessAuthority = accessAuthority;
	}
	
	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}
	
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getPositionId() {
		return positionId;
	}
	
	public String getPositionName() {
		return positionName;
	}
	
	public int getAccessAuthority() {
		return accessAuthority;
	}
	
	public LocalDateTime getRegistDate() {
		return registDate;
	}
	
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
}
