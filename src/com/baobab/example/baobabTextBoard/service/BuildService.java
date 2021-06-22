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
		Util.mkdirs("site/img");
		Util.copy("template/main.css", "site/main.css");
		Util.copyFolder("img", "site/img");
		buildIndexPage();
		buildArticleListPages();
		buildArticlesDetailPage();
	}

	private void buildArticleListPage(Board board, int itemsInAPage, int pageBoxSize, List<Article> articles,
			int page) {

	StringBuilder sb = new StringBuilder();

	sb.append(getHeadHtml("article_list_" + board.code));
	String bodyTemplate = Util.getFileContents("template/article_list.html");

	StringBuilder mainContent = new StringBuilder();

	int articlesCount = articles.size();
	int start = (page - 1) * itemsInAPage;
	int end = start + itemsInAPage - 1;
	String roman = null;
	if (end >= articlesCount) {
		end = articlesCount - 1;
	}
	int k = 0;
	for (int i = start; i <= end; i++) {
		if(k==10) {
			k = 0;
		}
		k++;
		if(articles.size() <= 0) {
			mainContent.append("<div>등록된 게시물이 없습니다.</div>");
		}
		else if(articles.size() > 0) {
		Article article = articles.get(i);

		String link = "article_detail_" + article.num + ".html";

		switch (k) {
		case 1:
			roman = "I";
			break;

		case 2:
			roman = "II";
			break;

		case 3:
			roman = "III";
			break;

		case 4:
			roman = "IV";
			break;

		case 5:
			roman = "V";
			break;

		case 6:
			roman = "VI";
			break;

		case 7:
			roman = "VII";
			break;

		case 8:
			roman = "VIII";
			break;

		case 9:
			roman = "XI";
			break;

		case 10:
			roman = "X";
			break;
		}

		mainContent.append("<a href=\"article_detail_" + article.num + ".html\"class=\"article-list flex\">");
		mainContent.append("<div class=\"article-num\">");
		mainContent.append("<span class=\"article-" + (k) + "\">" + roman + "</span>");
		mainContent.append("</div>");
		mainContent.append("<div class=\"article-writerAndTitle\">");
		mainContent.append("<span class=\"article-title\">" + article.title + "</span>");
		mainContent.append("<span class=\"article-writer\">" + article.extra__writer + "</span>");
		mainContent.append("</div>");
		mainContent.append("<div class=\"article-regDate\">");
		mainContent.append("<span>" + article.regDate + "</span> ");
		mainContent.append("</div>");
		mainContent.append("</a>");
		}
	}

	StringBuilder pageMenuContent = new StringBuilder();

	// 토탈 페이지 계산
	int totalPage = (int) Math.ceil((double) articlesCount / itemsInAPage);

	// 현재 페이지 계산
	if (page < 1) {
		page = 1;
	}

	if (page > totalPage) {
		page = totalPage;
	}

	// 현재 페이지 박스 시작, 끝 계산
	int previousPageBoxesCount = (page - 1) / pageBoxSize;
	int pageBoxStartPage = pageBoxSize * previousPageBoxesCount + 1;
	int pageBoxEndPage = pageBoxStartPage + pageBoxSize - 1;
	
	if ( pageBoxEndPage > totalPage ) {
		pageBoxEndPage = totalPage;
	}

	// 이전버튼 페이지 계산
	int pageBoxStartBeforePage = pageBoxStartPage - 1;
	if (pageBoxStartBeforePage < 1) {
		pageBoxStartBeforePage = 1;
	}

	// 다음버튼 페이지 계산
	int pageBoxEndAfterPage = pageBoxEndPage + 1;

	if (pageBoxEndAfterPage > totalPage) {
		pageBoxEndAfterPage = totalPage;
	}

	// 이전버튼 노출여부 계산
	boolean pageBoxStartBeforeBtnNeedToShow = pageBoxStartBeforePage != pageBoxStartPage;
	// 다음버튼 노출여부 계산
	boolean pageBoxEndAfterBtnNeedToShow = pageBoxEndAfterPage != pageBoxEndPage;

	if (pageBoxStartBeforeBtnNeedToShow) {
		pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, pageBoxStartBeforePage)
				+ "\" class=\"flex flex-ai-c\">&lt; 이전</a></li>");
	}

	for (int i = pageBoxStartPage; i <= pageBoxEndPage; i++) {
		String selectedClass = "";
		
		if ( i == page ) {
			selectedClass = "article-page-menu__link--selected";
		}
		
		pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, i)
				+ "\" class=\"flex flex-ai-c " + selectedClass + "\">" + i + "</a></li>");
	}

	if (pageBoxEndAfterBtnNeedToShow) {
		pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, pageBoxEndAfterPage)
				+ "\" class=\"flex flex-ai-c\">다음 &gt;</a></li>");
	}
	
	String foot = Util.getFileContents("template/foot.html");

	String body = bodyTemplate.replace("${article-list__main-content}", mainContent.toString());
	body = body.replace("${list__page}", pageMenuContent.toString());
	
	sb.append(body);
	sb.append(foot);
	
	String fileName = getArticleListFileName(board, page);
	String filePath = "site/" + fileName;

	Util.writeFile(filePath, sb.toString());
	System.out.println(filePath + "생성");
	}

	private String getArticleListFileName(Board board, int page) {
		return "article_list_" + board.code + "_" + page + ".html";
	}

	private void buildArticleListPages() {
		List<Board> boards = articleService.getBoards();

		int itemsInAPage = 10;
		int pageBoxMenuSize = 10;

		for (Board board : boards) {
			List<Article> articles = articleService.getForPrintArticlesByBoardNum(board.num);
			int articlesCount = articles.size();
			int totalPage = (int) Math.ceil((double) articlesCount / itemsInAPage);
			
			if(articles.size() <= 0 && board.menu_depth >= 2) {
				StringBuilder mainContent = new StringBuilder();
				
				StringBuilder sb = new StringBuilder();
				
				mainContent.append("<div class=\"article-none flex flex-jc-c\">아직 등록된 게시글이 없습니다.</div>");
				sb.append(getHeadHtml("article_list_" + board.code));
				String bodyTemplate = Util.getFileContents("template/article_list.html");
				
				String body = bodyTemplate.replace("${article-list__main-content}", mainContent.toString());
				body = body.replace("${list__page}", "");
				
				String foot = Util.getFileContents("template/foot.html");
				
				sb.append(body);
				sb.append(foot);
				
				String fileName = "article_list_" + board.code + "_1.html";
				String filePath = "site/" + fileName;

				Util.writeFile(filePath, sb.toString());
				System.out.println(filePath + "생성");
			}
			
			for (int i = 1; i <= totalPage; i++) {
				buildArticleListPage(board, itemsInAPage, pageBoxMenuSize, articles, i);
			}
		}
	}

	private void buildArticlesDetailPage() {
		List<Article> articles = articleService.getArticles();

		String bodyTemplate = Util.getFileContents("template/article_detail.html");
		String foot = Util.getFileContents("template/foot.html");

		for (Article article : articles) {
			StringBuilder sb = new StringBuilder();
			article.fRegDate = article.fRegDate.substring(2,article.fRegDate.length());
			article.fRegDate = article.fRegDate.replaceFirst("-", "년 ");
			article.fRegDate = article.fRegDate.replace("-", "월 ");
			sb.append(getHeadHtml("article_detail_" + article.num));

			StringBuilder mainContetnt = new StringBuilder();
			mainContetnt.append("<section class=\"numAndTitle flex\">");
			mainContetnt.append("<div class=\"detail__article-num\">" + article.num + ". </div>");
			mainContetnt.append("<div class=\"detail__article-title\">" + article.title);
			mainContetnt.append("<span class=\"star star1\">⛧</span>");
			mainContetnt.append("<span class=\"star star2\">⛧</span>");
			mainContetnt.append("<span class=\"star star3\">⛧</span>");
			mainContetnt.append("</div>");
			mainContetnt.append("</section>");
			mainContetnt.append("<div class=\"detail__article-regDate\">" + article.fRegDate + "일</div>");
			mainContetnt.append("<div class=\"detail__article-content\">" + article.body + "</div>");
			
			StringBuilder listContent = new StringBuilder();
			int i = article.num;
			int emoge = 0;
			String newEmoge = null;
			for (Article article1 : articles) {
				if(emoge == 7) {
					emoge = 0;
				}
				emoge++;
				switch(emoge) {
					case 1:
						newEmoge = "🗒";
						break;
					case 2:
						newEmoge = "📖";
						break;
					case 3:
						newEmoge = "📱";
						break;
					case 4:
						newEmoge = "💻";
						break;
					case 5:
						newEmoge = "✏";
						break;
					case 6:
						newEmoge = "🖋";
						break;
					case 7:
						newEmoge = "📕";
						break;
				}
				article1.fRegDate = article1.fRegDate.replaceFirst("-", ". ");
				article1.fRegDate = article1.fRegDate.replace("-", ".");
				if ( i >= (article1.num - 3) && i <= (article1.num + 3)) {
					if(article.num == article1.num) {
						listContent.append("<tr class=\"selected\" onClick=location.href=\"article_detail_" + article1.num + ".html\">");
					} else {
						listContent.append("<tr onClick=location.href=\"article_detail_" + article1.num + ".html\">");
					}
						listContent.append("<td class=\"articleList_num\"" + ">" + newEmoge +  "</td>");
						listContent.append("<td class=\"articleList_title\"" + ">" + article1.title + "</td>");
						listContent.append("<td class=\"articleList_writer\"" + ">" + article1.extra__writer + "</td>");
						listContent.append("<td class=\"articleList_regDate\"" + ">" + article1.fRegDate + ".</td>");
					listContent.append("</tr>");
				}
			}
			String body = bodyTemplate.replace("${article_detail_replace}", mainContetnt.toString());
			body = body.replace("${article_list_replace}", listContent.toString());
			
			sb.append(body);
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

		if (pageName.equals("홈")) {
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
		} else if (pageName.contains("article_list_")) {
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
		} else {
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
			for (Board board2 : boards2) {
				if (board2.parent_code.equals(board.code)) {
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
			for (Board board2 : boards2) {
				if (board2.parent_code.equals(board.code)) {
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
