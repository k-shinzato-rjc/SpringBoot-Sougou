package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PlaceDto;
import com.example.demo.form.PlaceForm;
import com.example.demo.repository.PlaceRepository;

/**
 * 事業所データ取得用 Serviceクラス
 * @author koki_shinzato
 */
@Service
public class PlaceService {
	
	@Autowired
	private PlaceRepository placeRepository;
	
	/**
	 * DBから事業所リストを取得し、Entity→ Dtoに変換して返す
	 * @return
	 */
	public List<PlaceDto> getAll(){
		
		List<PlaceDto> dtoList = new ArrayList<PlaceDto>();
		
		// ラムダ式で1データずつDtoに変換し、Dtoリストに格納
		placeRepository.findAll().stream().forEach(entity -> {
			dtoList.add(entity.FromEntityToDto());
		});
		
		return dtoList;
	}
	
	/**
	 * Dtoリスト → Formリスト変換
	 * @author koki_shinzato
	 * 
	 * @param dtoList
	 * @return Form型 事業所データ
	 */
	public List<PlaceForm> convertForm(List<PlaceDto> dtoList){
		List<PlaceForm> formList = new ArrayList<PlaceForm>();
		
		dtoList.stream().forEach(dto -> {
			formList.add(dto.FromDtoToForm());
		});
		
		return formList;
	}
}
