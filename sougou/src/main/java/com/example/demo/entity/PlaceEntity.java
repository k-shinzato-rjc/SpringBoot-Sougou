package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.example.demo.dto.PlaceDto;

@Entity
@Table(name = "mst_place")
public class PlaceEntity {
	
	// 事業所ID
	@Id
	@Column(name="place_id")
	private String placeId;

	// 事業所名
	@Column(name="place_name")
	private String placeName;

	// 事業所住所
	@Column(name="address")
	private String address;

	// 事業所TEL
	@Column(name="telephone")
	private String telephone;

	// 事業所メール
	@Column(name="mail")
	private String mail;

	// 登録日
	@Column(name="regist_date")
	private LocalDateTime registDate;

	// 更新日
	@Column(name="update_date")
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
	 * Entity → Dto
	 * @return 事業所データ Dto型
	 */
	public PlaceDto FromEntityToDto() {

		PlaceDto placeDto = new PlaceDto();

		placeDto.setPlaceId(placeId);
		placeDto.setPlaceName(placeName);
		placeDto.setAddress(address);
		placeDto.setTelephone(telephone);
		placeDto.setMail(mail);
		placeDto.setRegistDate(registDate);
		placeDto.setUpdateDate(registDate);

		return placeDto;
	}
}
