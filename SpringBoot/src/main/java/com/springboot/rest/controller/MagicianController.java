package com.springboot.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.entity.MagicianEntity;
import com.springboot.rest.service.MagicianService;
import com.springboot.rest.sql.SqlAction;
import com.springboot.rest.sql.SqlUtils;

@RestController
public class MagicianController {
	@Autowired
	private MagicianService magicianService;

	@Autowired
	private SqlUtils SqlUtils;
	
	@Autowired
	private SqlAction sqlAction;

	/**
	 * 取得魔法師資訊
	 * @return
	 */
	@RequestMapping(value = "/magician", method = RequestMethod.GET)
	public ResponseEntity<Object> getMagicianData() {
		System.out.println("-----> from MagicianController：" + magicianService.toString());
		return new ResponseEntity<>(magicianService.getMagician(), HttpStatus.OK);
	}

	/**
	 * <pre>
	 * Dynamic Query by SQL
	 * 依名字查詢魔法師
	 * </pre>
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/magicianBySql", method = RequestMethod.GET)
	public List<MagicianEntity> queryMagicianBySql(RequestEntity<Map<String, Object>> requestEntity) throws IOException{
		String sqlString = SqlUtils.getQuerySql("findByPK_001");
		return sqlAction.queryMagicianBySql(sqlString, requestEntity.getBody());
	}
}
