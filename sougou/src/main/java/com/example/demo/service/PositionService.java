package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PositionDto;
import com.example.demo.form.PositionForm;
import com.example.demo.repository.PositionRepository;

@Service
public class PositionService {
	
	@Autowired
	private PositionRepository positionRepository;
	
	public List<PositionDto> findAll(){
		
		ArrayList<PositionDto> list = new ArrayList<PositionDto>();
		
		positionRepository.findAll().stream().forEach(entity -> {
			list.add(entity.fromEntityToDto());
		});
		
		return list;
	}
	
	public List<PositionForm> convertToForm(List<PositionDto> dtoList){
		
		List<PositionForm> formList = new ArrayList<PositionForm>();
		dtoList.stream().forEach(dto -> {
			formList.add(dto.fromDtoToForm());
		});
		
		return formList;
	}
}
