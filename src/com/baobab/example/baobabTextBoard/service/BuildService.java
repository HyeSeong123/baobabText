package com.baobab.example.baobabTextBoard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baobab.example.baobabTextBoard.Container;
import com.baobab.example.baobabTextBoard.dto.Article;
import com.baobab.example.baobabTextBoard.dto.Board;
import com.baobab.example.baobabTextBoard.util.Util;
import com.google.api.client.http.HttpRequest;

public class BuildService {

	private ArticleService articleService;
	private MemberService memberService;
	private DisqusApiService disqusApiService;

	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
		disqusApiService = Container.disqusApiService;
	}

	public void buildSite() {
		Util.mkdirs("site");
		Util.mkdirs("site/img");
		Util.mkdirs("site/fonts");
		Util.copy("template/main.css", "site/main.css");
		Util.copy("template/app.js", "site/app.js");
		Util.copyFolder("img", "site/img");
		Util.copyFolder("fonts", "site/fonts");

		loadDisqusData();
		loadDataFromGa4Data();

		buildIndexPage();
		buildArticleListPages();
		buildArticlesDetailPage();
	}

	private void loadDataFromGa4Data() {
		Container.googleAnalyticsApiService.updatePageHits();
	}

	private void loadDisqusData() {
		List<Article> articles = articleService.getForPrintArticlesByBoardNum();

		for (Article article : articles) {
			Map<String, Object> disqusArticleData = disqusApiService.getArticleData(article);

			if (disqusArticleData != null) {
				int likes = (int) disqusArticleData.get("likes");
				int replyCount = (int) disqusArticleData.get("posts");

				Map<String, Object> modifyArgs = new HashMap<>();
				modifyArgs.put("num", article.num);
				modifyArgs.put("like", likes);
				modifyArgs.put("replyCount", replyCount);

				articleService.doModify(modifyArgs);
			}
		}

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
			if (k == 10) {
				k = 0;
			}
			k++;
			if (articles.size() <= 0) {
				mainContent.append("<div>등록된 게시물이 없습니다.</div>");
			} else if (articles.size() > 0) {
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
				mainContent.append("<span class=\"article-title\">" + article.title + " [" + article.replyCount + "]</span>");
				mainContent.append("<span class=\"article-writer\">" + article.extra__writer + "</span>");
				mainContent.append("</div>");
				mainContent.append("<div class=\"article-inform flex-column\">");
				mainContent.append("<span>" + article.fRegDate + "</span> ");
				mainContent.append("<span class=\"article-like\"> Likes : " + article.like + "</span> ");
				mainContent.append("<span class=\"article-view\"> Views : " + article.hitsCount + "</span> ");
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

		if (pageBoxEndPage > totalPage) {
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

			if (i == page) {
				selectedClass = "article-page-menu__link--selected";
			}

			pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, i) + "\" class=\"flex flex-ai-c "
					+ selectedClass + "\">" + i + "</a></li>");
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

			if (articles.size() <= 0 && board.menu_depth >= 2) {
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
			int maxListNum = article.orderNum + 3;
			int minListNum = article.orderNum - 3;
			if(minListNum < 0) {
				minListNum = 0;
			}
			
			List<Article> articles1 = articleService.getForPrintArticlesByBoardNumAndNum(article.boardNum, maxListNum, minListNum);
			StringBuilder sb = new StringBuilder();
			String head = getHeadHtml("article_detail", article);
			sb.append(head);

			String articleBodyForPrint = article.body;
			/* 보기 좋게하기위해 날짜 형식 수정 */
			String newArticleRegDate = article.fRegDate.substring(2, article.fRegDate.length());
			newArticleRegDate = newArticleRegDate.replaceFirst("-", "년");
			newArticleRegDate = newArticleRegDate.replace("-", "월");
			articleBodyForPrint = articleBodyForPrint.replaceAll("<script type=\"text/javascript\">", "t-script");
			String articleNum = Integer.toString(article.num);
			String body = bodyTemplate.replace("${article-detail__num}", articleNum);
			body = body.replace("${article-detail__title}", article.title);
			body = body.replace("${article-detail__regDate}", newArticleRegDate);
			body = body.replace("${article-detail__writer}", article.extra__writer);
			body = body.replace("${article-detail__content}", articleBodyForPrint);
			body = body.replace("${site-domain}", "blog.baobab612.com");
			body = body.replace("${file-name}", "article_detail_" + articleNum + ".html");

			StringBuilder listContent = new StringBuilder();
			int i = article.num;
			int emoge = 0;
			int articleMax = 0;
			String newEmoge = null;
			for (int k=articles1.size()-1; k >= 0; k--) {
				Article article1 = articles1.get(k);
				if(articleMax == 6) {
					break;
				}
				articleMax ++;
				if (emoge == 7) {
					emoge = 0;
				}
				emoge++;
				switch (emoge) {
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
				if (article.num == article1.num) {
					listContent.append("<tr class=\"selected\" onClick=location.href=\"article_detail_"
							+ article1.num + ".html\">");
				} else {
					listContent.append("<tr onClick=location.href=\"article_detail_" + article1.num + ".html\">");
				}
				listContent.append("<td class=\"articleList_num\"" + ">" + newEmoge + "</td>");
				listContent.append("<td class=\"articleList_title\"" + "><span>" + article1.title + "</span></td>");
				listContent.append(
						"<td class=\"articleList_writer\"" + "><span>" + article1.extra__writer + "</span></td>");
				listContent.append(
						"<td class=\"articleList_regDate\"" + "><span>" + article1.fRegDate + "일</span></td>");
				listContent.append("</tr>");
			
			}
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
		return getHeadHtml(pageName, null);
	}

	private String getHeadHtml(String pageName, Object relObj) {
		String head = Util.getFileContents("template/head.html");

		StringBuilder mainBannerContentHtml = new StringBuilder();
		StringBuilder boardMenu1ContentHtml = new StringBuilder();
		StringBuilder boardMenu1ContentHtmlMobile = new StringBuilder();
		StringBuilder boardMenu2ContentHtml = new StringBuilder();

		List<Board> boards1 = articleService.getBoardsByDepth(1);
		List<Board> boards2 = articleService.getBoardsByDepth(2);

		if (pageName.equals("바오밥 블로그")) {
			mainBannerContentHtml.append("<div class=\"main__banner\">");
			mainBannerContentHtml.append("<div class=\"height-100p flex flex-ai-c\">");
			mainBannerContentHtml.append("<strong>");
			mainBannerContentHtml.append("<p>기술 블로그 입니다.</p>");
			mainBannerContentHtml.append("<p>여러가지 웹 개발 프레임워크와 DB</p>");
			mainBannerContentHtml.append("<p>서버, 일상 관련된 게시글이</p>");
			mainBannerContentHtml.append("<p>올라올 예정이오니 자주 방문 부탁 드립니다.</p>");
			mainBannerContentHtml.append("<p>아직 공부중이라 틀린 부분이 많을 수 있습니다.</p>");
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

		String siteName = "바오밥 블로그";
		String siteSubject = "바오밥의 웹 기술 블로그";
		String siteDescription = "웹 프로그램 일지 입니다.";
		String siteKeywords = "HTML, CSS, JS, JavaScript, Server, DataBase, Mysql, Java, CentOS";
		String siteDomain = "blog.baobab612.com";
		String siteMainUrl = "https://" + siteDomain;
		String currentDate = Util.getNowDateStr().replace(" ", "T");

		if (relObj instanceof Article) {
			Article article = (Article) relObj;
			siteName = article.title;
			siteSubject = article.title;
			siteDescription = article.title;
			pageName = siteName + "-바오밥 블로그";
			siteDescription = siteDescription.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", "");
		}
		head = head.replace("${title-bar__content}", pageName);
		head = head.replace("${site-name}", siteName);
		head = head.replace("${site-subject}", siteSubject);
		head = head.replace("${site-description}", siteDescription);
		head = head.replace("${site-domain}", siteDomain);
		head = head.replace("${site-domain}", siteDomain);
		head = head.replace("${current-date}", currentDate);
		head = head.replace("${site-main-url}", siteMainUrl);
		head = head.replace("${site-keywords}", siteKeywords);

		return head;
	}

	public String getArticleDetailFileName(int num) {
		return "article_detail_" + num + ".html";
	}

	private void buildIndexPage() {
		StringBuilder sb = new StringBuilder();

		String head = getHeadHtml("바오밥 블로그");
		String foot = Util.getFileContents("template/foot.html");
		String mainHtml = Util.getFileContents("template/index.html");
		StringBuilder webPrograms = new StringBuilder();

		sb.append(head);

		List<Article> articles = articleService.getArticles();
		for (int i = 0; i < 8; i++) {
			Article article = articles.get(i);
			webPrograms.append("<div class=\"list_slide list_slide" + (i + 1) + "\">");
			webPrograms.append("<a href=\"article_detail_" + article.num + ".html\">");
			webPrograms.append("<div class=\"list__regDate\">" + article.fRegDate + "</div>");
			webPrograms.append("<div class=\"list__title\">" + article.title + "</div>");
			webPrograms.append("</a>");
			webPrograms.append("</div>");
		}
		mainHtml = mainHtml.replace("${article_list_webPrograms}", webPrograms.toString());
		sb.append(mainHtml);

		sb.append(foot);

		String filePath = "site/index.html";
		Util.writeFile(filePath, sb.toString());
		System.out.println(filePath + " 생성");
	}
}
