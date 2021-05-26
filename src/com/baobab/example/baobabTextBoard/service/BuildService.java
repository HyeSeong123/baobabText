package com.baobab.example.baobabTextBoard.service;

import java.util.List;

import com.baobab.example.baobabTextBoard.Container;
import com.baobab.example.baobabTextBoard.dto.Article;
import com.baobab.example.baobabTextBoard.util.Util;

public class BuildService {
	
	private ArticleService articleService;
	private MemberService memberService;
	
	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void buildSite() {
		Util.mkdirs("site/article");
		
		List<Article> articles = articleService.getArticles();
		
		
		for ( Article article : articles) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<!DOCTYPE html>");
			sb.append("<html lang=\"ko\">");
			
			sb.append("<head>");
			sb.append("<meta charset=\"UTF-8\">");
			sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\">");
			sb.append("<title>게시물 상세페이지: " + article.title  + "</title>");
			sb.append("</head>");
			
			sb.append("<body>");
			
				sb.append("<h1>게시물 상세페이지</h1>");
				sb.append("<div>");
					
					sb.append("번호: " + article.num + "<br>");
					sb.append("작성일: " + article.regDate + "<br>");
					sb.append("작성자: " + article.extra__writer + "<br>");
					sb.append("제목: " + article.title + "<br>");
					sb.append("내용: " + article.body + "<br>");
				
				sb.append("</div>");
			
			sb.append("</body>");
			
			sb.append("</html>");
			
			String fileName = article.num + ".html";
			
			Util.writeFile("site/article/" + fileName, sb.toString());
		}
	}
}
