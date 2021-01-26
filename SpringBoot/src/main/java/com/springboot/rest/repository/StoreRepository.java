package com.springboot.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.entity.StoreEntity;


@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {

	/**
	 * <pre>
	 * like 查詢
	 * 其他查詢方法必須先寫下規則
	 * </pre>
	 */
	List<StoreEntity> findByStorenameLike(String storeName);
}
