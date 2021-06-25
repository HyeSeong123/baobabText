package com.baobab.example.baobabTextBoard.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baobab.example.baobabTextBoard.dto.Article;
import com.baobab.example.baobabTextBoard.dto.Board;
import com.baobab.example.mysqlUtil.MysqlUtil;
import com.baobab.example.mysqlUtil.SecSql;

public class ArticleDao {

	public List<Article> getArticles() {
		List<Article> articles = new ArrayList<>();
		
		SecSql sql = new SecSql();
		sql.append("SELECT A.*,");
		sql.append("DATE_FORMAT(A.regDate, '%Y-%m-%d') AS fRegDate,");
		sql.append("M.name AS extra__writer");
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
		sql.append("M.name AS extra__writer");
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

	public List<Board> getBoards() {
		List<Board> boards = new ArrayList<>();
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM board");
		
		List<Map<String, Object>> boardsMapList = MysqlUtil.selectRows(sql);
		
		for (Map<String, Object> boardMap : boardsMapList) {
			boards.add(new Board(boardMap));
		}
		
		return boards;
	}

	public List<Board> getBoardsByDepth(int depth) {
		List<Board> boards = new ArrayList<>();
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM board");
		sql.append("WHERE menu_depth = ?", depth);
		
		List<Map<String, Object>> boardsMapList = MysqlUtil.selectRows(sql);
		
		for (Map<String, Object> boardMap : boardsMapList) {
			boards.add(new Board(boardMap));
		}
		
		return boards;
		
	}

	public List<Article> getForPrintArticlesByBoardNum(int num) {
		SecSql sql = new SecSql();
		List<Article> articles = new ArrayList<>();
		
		sql.append("SELECT A.*,");
		sql.append("DATE_FORMAT(A.regDate, '%Y-%m-%d') AS fRegDate,");
		sql.append("M.name AS extra__writer ,B.name AS extra__board");
		sql.append("FROM article AS A");
		sql.append("LEFT JOIN member AS M");
		sql.append("ON A.memberNum = M.num");
		sql.append("LEFT JOIN member AS B");
		sql.append("ON A.boardNum = B.num");
		
		sql.append("WHERE boardNum = ?", num);
		
		sql.append("GROUP BY A.num");
		
		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);
		
		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}
		
		return articles;
	}

	public Board getBoardsByCode(String title) {
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM board");
		
		sql.append("WHERE code = ?", title);
		
		Map<String, Object> boardMap = MysqlUtil.selectRow(sql);
		
		if(boardMap.isEmpty()) {
			return null;
		}
		
		return new Board(boardMap);
	}
}
