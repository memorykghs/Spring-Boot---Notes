package com.springboot.rest.sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class SqlUtils {

	private static final String QUERY_FIELD = "queryField:queryField";

	private static final String ORDER_BY_FIELD = "queryField:queryField";

	/**
	 * 取得 查詢 SQL
	 * 
	 * @param sqlName
	 * @throws IOException
	 */
	public String getQuerySql(String sqlName) throws IOException {
		return getSQLStr(sqlName, null, null);
	}

	/**
	 * 由名稱讀取SQL檔案
	 * 
	 * @param sqlName
	 * @param queryFields
	 * @param orderBy
	 * @return
	 * @throws IOException
	 */
	private String getSQLStr(String sqlName, String[] queryFields, String[] orderBy) throws IOException {

		ClassPathResource resource = new ClassPathResource("sql/" + sqlName);
		InputStream sqlAsStream = resource.getInputStream();
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(sqlAsStream))) {
			while (br.ready()) {
				sb.append(br.readLine());
			}
		}

		String sql = sb.toString();
		System.out.println("SQL：" + sql);

		if (StringUtils.contains(sql, QUERY_FIELD) && queryFields != null && queryFields.length > 0) {
			sql = replaceField(sql, QUERY_FIELD, queryFields);
		}

		if ((StringUtils.contains(sql, "order by") || StringUtils.contains(sql, "ORDER BY")) && orderBy != null
				&& orderBy.length > 0) {
			sql = replaceField(sql, ORDER_BY_FIELD, orderBy);
		}

		return sql;
	}

	/**
	 * 組動態查詢條件SQL
	 * 
	 * @param sql
	 * @param sqlMap
	 * @return
	 */
	private String getQuerySql(String sql, Map<String, Object> sqlMap) {

		String[] str = StringUtils.substringsBetween(sql, "[", "]");
		if (str == null) {
			return sql;
		}

		Pattern pattern = Pattern.compile("\\:(\\S*)");

		List<String> strList = Arrays.asList(str);
		List<String> getStrList = new ArrayList<>();
		Set<String> keySet = sqlMap.keySet();
		for (String s : strList) {
			Matcher matcher = pattern.matcher(s);
			List<String> subStrList = new ArrayList<>();
			while (matcher.find()) {
				subStrList.add(matcher.group(0).replace(":", ""));
			}
			int subStrListSize = subStrList.size();
			if (subStrListSize == 0 || !keySet.containsAll(subStrList)) {
				continue;
			}

			getStrList.add(s);

		}
		strList = new ArrayList<>(strList);
		strList.removeAll(getStrList);

		for (String s : strList) {
			sql = StringUtils.replace(sql, s, "");
		}
		sql = sql.replace("[", "").replace("]", "");

		return sql;
	}

	/**
	 * 替換欄位
	 * 
	 * @param sql
	 * @return
	 */
	private String replaceField(String sql, String replaceField, String[] orderBy) {

		StringBuilder sb = new StringBuilder();
		for (String field : orderBy) {
			sb.append(field).append(',');
		}
		String orderByStr = StringUtils.removeEnd(sb.toString(), ",");

		return sql.replace(replaceField, orderByStr);
	}
}
