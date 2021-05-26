package com.baobab.example.baobabTextBoard.service;

import java.util.List;

import com.baobab.example.baobabTextBoard.dao.ArticleDao;
import com.baobab.example.baobabTextBoard.dto.Article;

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
		articleDao.articleModify(inputNum,title,body);
	}


}
