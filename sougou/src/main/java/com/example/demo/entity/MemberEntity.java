package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.example.demo.dto.MemberDto;

/**
 * Beanクラス
 * @author  RJC Human Resources
 */
@Entity
@Table(name = "tbl_user")
public class MemberEntity{
	
	/** ID */
	@Id
	@Column(name="user_id")
	private String memberId;
	
	/** 名前 */
	@Column(name="user_name")
	private String name;
	
	/** 年齢 */
	@Column(name="age")
	private String age;
	
	/** 住所 */
	@Column(name="address")
	private String address;
	
	/** 性別 */
	@Column(name="sex")
	private String sex;
	
	/** mail */
	@Column(name="mail")
	private String mail;
	
	/** 電話番号 */
	@Column(name="tel")
	private String tel;
	
	/** 役職id */
	@Column(name="position_id")
	private String positionId;
	
	/** 役職名 */
	@Column(name="position_name")
	private String positionName;
	
	/** 事業所id */
	@Column(name="place_id")
	private String placeId;
	
	/** 事業所名 */
	@Column(name="place_name")
	private String placeName;
	
	/** 登録日 */
	@Column(name="regist")
	private String regist;

	/**
	 * IDを取得します。
	 * @return ID
	 */
	public String getMemberId() {
	    return memberId;
	}
	/**
	 * IDを設定します。
	 * @param memberId ID
	 */
	public void setMemberId(String memberId) {
	    this.memberId = memberId;
	}
	/**
	 * 名前を取得します。
	 * @return 名前
	 */
	public String getName() {
	    return name;
	}
	/**
	 * 名前を設定します。
	 * @param name 名前
	 */
	public void setName(String name) {
	    this.name = name;
	}
	/**
	 * 年齢を取得します。
	 * @return 年齢
	 */
	public String getAge() {
	    return age;
	}
	/**
	 * 年齢を設定します。
	 * @param age 年齢
	 */
	public void setAge(String age) {
	    this.age = age;
	}
	/**
	 * 住所を取得します。
	 * @return 住所
	 */
	public String getAddress() {
	    return address;
	}
	/**
	 * 住所を設定します。
	 * @param address 住所
	 */
	public void setAddress(String address) {
	    this.address = address;
	}
	/**
	 * 性別を取得します。
	 * @return 性別
	 */
	public String getSex() {
	    return sex;
	}
	/**
	 * 性別を設定します。
	 * @param sex 性別
	 */
	public void setSex(String sex) {
	    this.sex = sex;
	}
	/**
	 * mailを取得します。
	 * @return mail
	 */
	public String getMail() {
	    return mail;
	}
	/**
	 * mailを設定します。
	 * @param mail mail
	 */
	public void setMail(String mail) {
	    this.mail = mail;
	}
	/**
	 * 電話番号を取得します。
	 * @return 電話番号
	 */
	public String getTel() {
	    return tel;
	}
	/**
	 * 電話番号を設定します。
	 * @param tel 電話番号
	 */
	public void setTel(String tel) {
	    this.tel = tel;
	}
	/**
	 * 役職idを取得します。
	 * @return 役職id
	 */
	public String getPositionId() {
	    return positionId;
	}
	/**
	 * 役職idを設定します。
	 * @param positionId 役職id
	 */
	public void setPositionId(String positionId) {
	    this.positionId = positionId;
	}
	/**
	 * 役職名を取得します。
	 * @return 役職名
	 */
	public String getPositionName() {
	    return positionName;
	}
	/**
	 * 役職名を設定します。
	 * @param position 役職名
	 */
	public void setPositionName(String positionName) {
	    this.positionName = positionName;
	}
	/**
	 * 事業所idを取得します。
	 * @return 事業所id
	 */
	public String getPlaceId() {
	    return placeId;
	}
	/**
	 * 事業所idを設定します。
	 * @param placeId 事業所id
	 */
	public void setPlaceId(String placeId) {
	    this.placeId = placeId;
	}
	/**
	 * 事業所名を取得します。
	 * @return 事業所名
	 */
	public String getPlaceName() {
	    return placeName;
	}
	/**
	 * 事業所名を設定します。
	 * @param place 事業所名
	 */
	public void setPlaceName(String placeName) {
	    this.placeName = placeName;
	}
	/**
	 * 登録日を取得します。
	 * @return 登録日
	 */
	public String getRegist() {
	    return regist;
	}
	/**
	 * 登録日を設定します。
	 * @param regist 登録日
	 */
	public void setRegist(String regist) {
	    this.regist = regist;
	}
	
	/**
	 * DBから取得したデータをEntityからDtoに変換する
	 * @author koki_shinzato
	 * @param memberEntity DBから取得したEntity型データ
	 * @return 変換されたDtoデータ
	 */
	public MemberDto fromEntitytoDto() {
		MemberDto memberDto = new MemberDto();

		memberDto.setMemberId(memberId);
		memberDto.setName(name);
		memberDto.setAge(age);
		memberDto.setSex(sex);
		memberDto.setAddress(address);
		memberDto.setTel(tel);
		memberDto.setMail(mail);
		memberDto.setPositionId(positionId);
		memberDto.setPositionName(positionName);
		memberDto.setPlaceId(placeId);
		memberDto.setPlaceName(placeName);
		memberDto.setRegist(regist);
		
		return memberDto;
	}

}
