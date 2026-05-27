package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

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
		List<MemberEntity> memberList = memberRepository.findAll();
		
		memberList.stream().forEach(data -> {
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
		
		/*
		// IDに該当するメンバーデータを取得
		Optional<MemberEntity> op = memberRepository.findById(memberId);
		
		// Optional型データの中身がnullだった場合、例外を投げる
		if(!op.isPresent()) {
			throw new NoSuchElementException("メンバーが見つかりませんでした。");
		}
		
		// データが存在する場合、Optionalからデータを取り出してDtoに変換する
		MemberEntity member = op.get();
		MemberDto memberDto = member.fromEntitytoDto();
		
		// Dtoデータを返す
		return memberDto;
		*/
		
		
		// ↓登録画面作成の際に修正予定↓
		
		// DBからIDに該当するメンバーをオプショナル型で取得
		Optional<MemberEntity> op = memberRepository.findById(memberId);
		
		// opがnullであればnullを格納、nullでなければ値を取り出す
		MemberEntity memberEntity = op.orElse(null);
		
		// EntityがNullの場合、nullを返す
		if(Objects.isNull(memberEntity)) {
			return null;
		}
		
		// Nullでない場合、EntityからDtoに変換して返す
		return memberEntity.fromEntitytoDto();
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
}
