package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.form.MemberForm;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	/**
	 * DBから取得した全メンバーリストをDto型で返す
	 * @author koki_shinzato
	 * @return Dtoメンバーリスト
	 */
	public List<MemberDto> findAll(){
		
		List<MemberDto> dtoList = new ArrayList<MemberDto>();
		
		// DBから取得したEntityリストを全てDtoに変換
		List<MemberEntity> entityList = memberRepository.findAll();
		
		entityList.stream().forEach(data -> {
			dtoList.add(data.fromEntitytoDto());
		});
		
		return dtoList;
	}
	
	
	/**
	 * IDを指定してメンバーを取得し、Dto型で返す
	 * @param memberId
	 * @return MemberDto
	 * @throws NoSuchElementException
	 */
	public MemberDto findById(String memberId) {
		
		// IDに該当するメンバーデータ(Optional<Entity>) → Optional<Dto> → Dto
		return memberRepository.findById(memberId).map(opEntity -> opEntity.fromEntitytoDto()).orElse(null);
	}
	
	
	/**
	 * Dto型データを全てForm型に変換し、配列として返す
	 * @param memberDto
	 * @return formList
	 */
	public List<MemberForm> convertToForm(List<MemberDto> memberDto){
		
		List<MemberForm> formList = new ArrayList<MemberForm>();
		
		memberDto.stream().forEach(data -> {
			formList.add(data.fromDtoToForm());
		});
		
		return formList;
	}
	
	/**
	 * 引数に与えられたDtoをFormに変換し、DB登録
	 * @author koki_shinzato
	 * 
	 * @param memberDto
	 */
	public void save(MemberDto memberDto) {
		memberRepository.save(memberDto.toEntity());
	}
	
	/**
	 * IDで指定したメンバーデータを削除する
	 * @author koki_shinzato
	 * 
	 * @param memberId メンバーID
	 */
	public void deleteById(String memberId) {
		memberRepository.deleteById(memberId);
	}
	
}
