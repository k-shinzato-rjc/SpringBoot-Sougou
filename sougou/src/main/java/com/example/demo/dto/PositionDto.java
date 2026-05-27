package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.form.PositionForm;

public class PositionDto {
	
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
	
	public PositionForm fromDtoToForm() {
		PositionForm positionForm = new PositionForm();
		
		positionForm.setPositionId(positionId);
		positionForm.setPositionName(positionName);
		positionForm.setAccessAuthority(accessAuthority);
		positionForm.setRegistDate(registDate);
		positionForm.setUpdateDate(updateDate);
		
		return positionForm;
	}
}
