package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.example.demo.dto.PositionDto;

@Entity
@Table(name="mst_position")
public class PositionEntity {
	
	@Id
	@Column(name="position_id")
	private String positionId;
	
	@Column(name="position_name")
	private String positionName;
	
	@Column(name="access_authority")
	private int accessAuthority;
	
	@Column(name="regist_date")
	private LocalDateTime registDate;
	
	@Column(name="update_date")
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
	
	public PositionDto fromEntityToDto() {
		
		PositionDto positionDto = new PositionDto();
		
		positionDto.setPositionId(positionId);
		positionDto.setPositionName(positionName);
		positionDto.setAccessAuthority(accessAuthority);
		positionDto.setRegistDate(registDate);
		positionDto.setUpdateDate(updateDate);
		
		return positionDto;
	}
}
