package com.baobab.example.baobabTextBoard.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baobab.example.baobabTextBoard.dto.Article;
import com.baobab.example.mysqlUtil.MysqlUtil;
import com.baobab.example.mysqlUtil.SecSql;

public class ArticleDao {

	public List<Article> getArticles() {
		List<Article> articles = new ArrayList<>();
		
		SecSql sql = new SecSql();
		sql.append("SELECT A.*,");
		sql.append("M.loginId AS extra__writer");
		sql.append("FROM article AS A");
		sql.append("LEFT JOIN `member` AS M");
		sql.append("ON A.memberNum = M.num");
		
		sql.append("ORDER BY A.num DESC");
		
		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);
		
		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}
		
		return articles;
	}

	public Article getArticleByNum(int num) {
		SecSql sql = new SecSql();
		sql.append("SELECT A.*,");
		sql.append("M.loginId AS extra__writer");
		sql.append("FROM article AS A");
		sql.append("LEFT JOIN `member` AS M");
		sql.append("ON A.memberNum = M.num");
		
		sql.append("WHERE A.num = ?", num);
		
		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);
		
		if(articleMap.isEmpty()) {
			return null;
		}
		
		return new Article(articleMap);
	}

	public int articleAdd(int boardNum, int memberNum, String title, String body) {
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO article");
		sql.append("SET regDate= NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("memberNum = ?,", memberNum);
		sql.append("boardNum = ?,", boardNum);
		sql.append("title = ?,", title);
		sql.append("body = ?", body);
		
		return MysqlUtil.insert(sql);
	}

	public int articleDelete(int inputNum) {
		SecSql sql = new SecSql();
		
		sql.append("DELETE FROM article");
		sql.append("WHERE num = ?", inputNum);
		
		return MysqlUtil.delete(sql);
	}

	public void articleModify(int num, String title, String body) {
		SecSql sql = new SecSql();
		
		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("body = ?", body);
		sql.append("WHERE num = ?", num);
		
		MysqlUtil.update(sql);
	}
}
