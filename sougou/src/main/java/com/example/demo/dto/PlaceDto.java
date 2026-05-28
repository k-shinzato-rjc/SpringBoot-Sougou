package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.form.PlaceForm;

/**
 * 事業所データ Dtoクラス
 */
public class PlaceDto {
	
	// 事業所ID
	private String placeId;
	
	// 事業所名
	private String placeName;
	
	// 事業所住所
	private String address;
	
	// 事業所TEL
	private String telephone;
	
	// 事業所メール
	private String mail;
	
	// 登録日
	private LocalDateTime registDate;
	
	// 更新日
	private LocalDateTime updateDate;
	
	public String getPlaceId() {
		return placeId;
	}
	
	public String getPlaceName() {
		return placeName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public String getMail() {
		return mail;
	}
	
	public LocalDateTime getRegiDateTime() {
		return registDate;
	}
	
	public LocalDateTime getUpDateTime() {
		return updateDate;
	}
	
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}
	
	public void setUpdateDate(LocalDateTime updaDateTime) {
		this.updateDate = updaDateTime;
	}
	
	/**
	 * 事業所データ Dto → Form
	 * @return Form型 事業所データ
	 */
	public PlaceForm FromDtoToForm() {
		
		PlaceForm placeForm = new PlaceForm();
		
		placeForm.setPlaceId(placeId);
		placeForm.setPlaceName(placeName);
		placeForm.setAddress(address);
		placeForm.setTelephone(telephone);
		placeForm.setMail(mail);
		placeForm.setRegistDate(registDate);
		placeForm.setUpdateDate(registDate);
		
		return placeForm;
	}
}
