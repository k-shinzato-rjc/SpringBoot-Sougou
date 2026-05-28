package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PlaceEntity;

/**
 * 事業所テーブル操作クラス
 * @author koki_shinzato
 */
public interface PlaceRepository extends JpaRepository<PlaceEntity, String>{
	
	public List<PlaceEntity> findAll();
}
