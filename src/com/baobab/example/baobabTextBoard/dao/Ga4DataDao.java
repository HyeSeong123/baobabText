package com.baobab.example.baobabTextBoard.dao;

import java.util.Map;

import com.baobab.example.baobabTextBoard.dto.Article;
import com.baobab.example.baobabTextBoard.dto.Member;
import com.baobab.example.mysqlUtil.MysqlUtil;
import com.baobab.example.mysqlUtil.SecSql;

public class Ga4DataDao {

	public int deletePagePath(String pagePath) {
		SecSql sql = new SecSql();
		sql.append("DELETE");
		sql.append("FROM ga4DataPagePath");
		sql.append("WHERE pagePath = ?", pagePath);
		
		return MysqlUtil.delete(sql);
	}
	
	public int savaPagePath(String pagePath, int hit) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO ga4DataPagePath");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", pagePath = ?", pagePath);
		sql.append(", hit = ?", hit);
		
		return MysqlUtil.insert(sql);
	}
}
