package com.baobab.example.baobabTextBoard.service;

import java.util.List;

import com.baobab.example.baobabTextBoard.Container;
import com.baobab.example.baobabTextBoard.dto.Article;
import com.baobab.example.baobabTextBoard.dto.Board;
import com.baobab.example.baobabTextBoard.util.Util;

public class BuildService {
	
	private ArticleService articleService;
	private MemberService memberService;
	
	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void buildSite() {
		Util.rmdir("site");
		Util.mkdirs("site");
		Util.copy("template/main.css", "site/main.css");
		buildIndexPage();
		buildArticleListPage();
		buildArticlesDetailPage();
	}
	
	private void buildArticleListPage() {
		List<Board> boards = articleService.getBoards();
		
		String bodyTemplate = Util.getFileContents("template/article_list.html");
		String foot = Util.getFileContents("template/foot.html");
		
		for (Board board : boards) {
			if(board.menu_depth >= 2) {
				StringBuilder sb = new StringBuilder();
				
				sb.append(getHeadHtml("article_list_" + board.code));
				
				String fileName = "article_list_" + board.code + "_1.html";
				List<Article> articles = articleService.getForPrintArticlesByBoardNum(board.num);
				
				StringBuilder mainContent = new StringBuilder();
				for(Article article : articles) {
					String link = "article_detail_" + article.num + ".html";
					
					mainContent.append("<div>");
						mainContent.append("<div>" + article.num + "</div>");
						mainContent.append("<div>" + article.title + "</div>");
					mainContent.append("</div>");
				}
				
				String body = bodyTemplate.replace("${article-list__main-content}", mainContent.toString());
				
				sb.append(body);
				sb.append(foot);
				
				String filePath = "site/" + fileName;
				
				Util.writeFile(filePath, sb.toString());
				System.out.println(filePath + "생성");
			}
		}
	}

	private void buildArticlesDetailPage() {
		List<Article> articles = articleService.getArticles();

		String head = getHeadHtml("article_detail");
		String foot = Util.getFileContents("template/foot.html");

		// 게시물 상세피이지 생성
		for (Article article : articles) {
			StringBuilder sb = new StringBuilder();

			sb.append(head);

			sb.append("번호 : " + article.num + "<br>");
			sb.append("작성날짜 : " + article.regDate + "<br>");
			sb.append("갱신날짜 : " + article.updateDate + "<br>");
			sb.append("제목 : " + article.title + "<br>");
			sb.append("내용 : " + article.body + "<br>");
			sb.append("<a href=\"article_detail_" + (article.num - 1) + ".html\">이전글</a><br>");
			sb.append("<a href=\"article_detail_" + (article.num + 1) + ".html\">다음글</a><br>");

			sb.append("</div>");

			sb.append(foot);

			String fileName = "article_detail_" + article.num + ".html";

			String filePath = "site/" + fileName;

			Util.writeFile(filePath, sb.toString());
			System.out.println(filePath + " 생성");
		}
	}

	private String getHeadHtml(String pageName) {
		String head = Util.getFileContents("template/head.html");
		
		StringBuilder mainBannerContentHtml = new StringBuilder();
		StringBuilder boardMenu1ContentHtml = new StringBuilder();
		StringBuilder boardMenu1ContentHtmlMobile = new StringBuilder();
		StringBuilder boardMenu2ContentHtml = new StringBuilder();
		
		List<Board> boards1 = articleService.getBoardsByDepth(1);
		List<Board> boards2 = articleService.getBoardsByDepth(2);
		
		if(pageName.equals("홈")) {
			mainBannerContentHtml.append("<div class=\"main__banner\">");
			mainBannerContentHtml.append("<div class=\"height-100p flex flex-ai-c\">");
			mainBannerContentHtml.append("<strong>");
			mainBannerContentHtml.append("<p>slow hea rt beat. slow be--</p>");
			mainBannerContentHtml.append("<p>at. I've arrived</p>");
			mainBannerContentHtml.append("<p>at your home Ay. The door to</p>");
			mainBannerContentHtml.append("<p>get off is on the left</p>");
			mainBannerContentHtml.append("</strong>");
			mainBannerContentHtml.append("</div>");
			mainBannerContentHtml.append("</div>");
			
			head = head.replace("${main__banner-replace}", mainBannerContentHtml.toString());
		}
		else if (pageName.contains("article_list_")) {
			mainBannerContentHtml.append("<div class=\"list__banner\">");
			mainBannerContentHtml.append("<div class=\"height-100p flex flex-ai-c\">");
			mainBannerContentHtml.append("<strong>");
			mainBannerContentHtml.append("<p>${boardName__content}</p>");
			mainBannerContentHtml.append("</strong>");
			mainBannerContentHtml.append("</div>");
			mainBannerContentHtml.append("</div>");
			
			String title = pageName.split("_")[2];
			Board board = articleService.getBoardsByCode(title);
			
			head = head.replace("${main__banner-replace}", mainBannerContentHtml.toString());
			head = head.replace("${boardName__content}", board.name);
		}
		else {
			mainBannerContentHtml.append("");
			
			head = head.replace("${main__banner-replace}", mainBannerContentHtml.toString());
		}
		for (Board board : boards1) {
			boardMenu1ContentHtml.append("<li>");
			
				boardMenu1ContentHtml.append("<a href=\"" + board.menu_url + "\">");
					boardMenu1ContentHtml.append(board.name);
				boardMenu1ContentHtml.append("</a>");
				/* 2차 메뉴 치환용 텍스트 생성 */
				boardMenu1ContentHtml.append("<ul>");
					for ( Board board2 : boards2) {
						if(board2.parent_code.equals(board.code)) {
							boardMenu1ContentHtml.append("<li>");
							boardMenu1ContentHtml.append("<a href=\"" + board2.menu_url + "\">");
								boardMenu1ContentHtml.append(board2.name);
							boardMenu1ContentHtml.append("</a>");
							boardMenu1ContentHtml.append("</li>");
						}
					}
				boardMenu1ContentHtml.append("</ul>");
			boardMenu1ContentHtml.append("</li>");
		}
		head = head.replace("${menu-bar__menu-1__board-menu-content-pc}", boardMenu1ContentHtml.toString());
		/* 모바일 환경 */
		for (Board board : boards1) {
			boardMenu1ContentHtmlMobile.append("<li>");
			
			boardMenu1ContentHtmlMobile.append("<a style=\"cursor:pointer;\">");
			boardMenu1ContentHtmlMobile.append("<span>" + board.name + "</span>");	
			boardMenu1ContentHtmlMobile.append("<span class=\"arrow-down\"></span>");
					boardMenu1ContentHtmlMobile.append("</a>");
				/* 2차 메뉴 치환용 텍스트 생성 */
					boardMenu1ContentHtmlMobile.append("<ul>");
					for ( Board board2 : boards2) {
						if(board2.parent_code.equals(board.code)) {
							boardMenu1ContentHtmlMobile.append("<li>");
							boardMenu1ContentHtmlMobile.append("<a href=\"" + board2.menu_url + "\">");
								boardMenu1ContentHtmlMobile.append(board2.name);
							boardMenu1ContentHtmlMobile.append("</a>");
							boardMenu1ContentHtmlMobile.append("</li>");

						}
					}
					boardMenu1ContentHtmlMobile.append("</ul>");
					boardMenu1ContentHtmlMobile.append("</li>");
		}
		head = head.replace("${menu-bar__menu-1__board-menu-content-mobile}", boardMenu1ContentHtmlMobile.toString());
		
		head = head.replace("${title-bar__content}", pageName);
		
		return head;
	}

	private void buildIndexPage() {
		StringBuilder sb = new StringBuilder();

		String head = getHeadHtml("홈");
		String foot = Util.getFileContents("template/foot.html");

		String mainHtml = Util.getFileContents("template/index.html");

		sb.append(head);
		sb.append(mainHtml);
		sb.append(foot);

		String filePath = "site/index.html";
		Util.writeFile(filePath, sb.toString());
		System.out.println(filePath + " 생성");
	}
}
