package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PositionEntity;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, String> {
		
	public List<PositionEntity> findAll();
}