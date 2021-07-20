package com.baobab.example.baobabTextBoard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baobab.example.baobabTextBoard.dao.ArticleDao;
import com.baobab.example.baobabTextBoard.dto.Article;
import com.baobab.example.baobabTextBoard.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = new ArticleDao();
	}
	
	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public Article getArticleByNum(int inputNum) {
		return articleDao.getArticleByNum(inputNum);
	}

	public int articleAdd(int boardNum, int memberNum, String title, String body) {
		return articleDao.articleAdd(boardNum,memberNum,title,body);
	}

	public int doDelete(int inputNum) {
		return articleDao.articleDelete(inputNum);
	}

	public void doModify(int inputNum, String title, String body) {
		Map<String, Object> modifyArgs = new HashMap<>();
		modifyArgs.put("num", inputNum);
		modifyArgs.put("title", title);
		modifyArgs.put("body", body);
		
		doModify(modifyArgs);
	}

	public List<Board> getBoards() {
		return articleDao.getBoards();
	}

	public List<Board> getBoardsByDepth(int depth) {
		return articleDao.getBoardsByDepth(depth);
	}

	public List<Article> getForPrintArticlesByBoardNum(int num) {
		return articleDao.getForPrintArticlesByBoardNum(num);
	}

	public Board getBoardsByCode(String title) {
		return articleDao.getBoardsByCode(title);
	}

	public void doModify(Map<String, Object> modifyArgs) {
		articleDao.articleModify(modifyArgs);
	}

	public List<Article> getForPrintArticlesByBoardNum() {
		return articleDao.getForPrintArticlesByBoardNum(0);
	}


}
