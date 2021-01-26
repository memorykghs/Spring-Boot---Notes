package com.springboot.rest.sql;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unchecked")
public class SqlAction {
	@Autowired
	private EntityManager entityManager;

	/**
	 * 簡單查詢
	 * @param <T>
	 * @return
	 */
	public <T> List<T> queryForList() {
		Query query = entityManager.createNativeQuery("select * from STUDENT.TB_EMP");
		return query.getResultList();
	}
	
	
	/**
	 * 查詢by SQL 檔
	 * @param <T>
	 * @param sql
	 * @param paramsMap
	 * @return
	 */
	public <T> List<T> queryMagicianBySql(String sql, Map<String, Object> paramsMap) {
		Query query = entityManager.createNativeQuery(sql);
		String magicianName = (String)paramsMap.get("magicianName");
		query.setParameter("MAGICIAN_NAME", magicianName);
		return query.getResultList();
	}
	
	/**
	 * <pre>
	 * 查詢by SQL 檔
	 * 定義好DTO格式
	 * </pre>
	 * @param <T>
	 * @param sql
	 * @param paramsMap
	 * @return
	 */
	public <T> List<T> queryMagicianBySql2(String sql, Map<String, Object> paramsMap) {
		Query query = entityManager.createNativeQuery(sql);
		String magicianName = (String)paramsMap.get("magicianName");
		query.setParameter("MAGICIAN_NAME", magicianName);
		return query.getResultList();
	}
}
