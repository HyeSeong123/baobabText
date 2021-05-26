package com.baobab.example.baobabTextBoard.controller;

import java.util.List;
import java.util.Scanner;

import com.baobab.example.baobabTextBoard.Container;
import com.baobab.example.baobabTextBoard.dto.Article;
import com.baobab.example.baobabTextBoard.service.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public void doCommand(String cmd) {
		if (cmd.startsWith("article list")) {
			showList();
		} else if (cmd.startsWith("article detail ")) {
			showDetail(cmd);
		} else if (cmd.startsWith("article add")) {
			doAdd(cmd);
		} else if( cmd.startsWith("article delete ")) {
			doDelete(cmd);
		} else if (cmd.startsWith("article modify ")) {
			doModify(cmd);
		}

	}

	private void doModify(String cmd) {
		System.out.println("== 게시물 수정 ==");
		int inputNum = Integer.parseInt(cmd.split(" ")[2]);
		
		Article article = articleService.getArticleByNum(inputNum);
		
		if(article == null) {
			System.out.println("존재하지 않는 게시물 입니다.");
			return ;
		}
		
		Scanner sc = Container.scanner;
		
		System.out.println("수정할 제목: ");
		String title = sc.nextLine();
		
		System.out.println("수정할 내용: ");
		String body = sc.nextLine();
		
		articleService.doModify(inputNum, title, body);
		
		System.out.println(inputNum + "번 게시물의 수정이 완료되었습니다.");
	}

	private void doDelete(String cmd) {
		System.out.println("== 게시물 삭제 ==");
		int inputNum = Integer.parseInt(cmd.split(" ")[2]);
		
		Article article = articleService.getArticleByNum(inputNum);
		
		if(article == null) {
			System.out.println("존재하지 않는 게시물 입니다.");
			return ;
		}
		
		int num = articleService.doDelete(inputNum);
		System.out.println(article.num + "번 게시물이 삭제되었습니다.");
	}

	private void doAdd(String cmd) {
		System.out.println("게시물 작성");

		Scanner sc = Container.scanner;

		System.out.printf("제목: ");
		String title = sc.nextLine();

		System.out.println("내용: ");
		String body = sc.nextLine();

		int memberNum = 1;
		int boardNum = 1;

		int addNum = articleService.articleAdd(boardNum, memberNum, title, body);

		System.out.println(addNum + "번 게시물이 추가되었습니다.");
	}

	private void showDetail(String cmd) {
		System.out.println("== 게시물 상세페이지 ==");

		int inputNum = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleByNum(inputNum);

		if (article == null) {
			System.out.println("해당 게시물은 존재하지 않습니다.");
			return;
		}

		System.out.println("게시물 번호:  " + article.num);
		System.out.println("작성자:  " + article.extra__writer);
		System.out.println("게시물 제목:  " + article.title);
		System.out.println("게시물 내용:  " + article.body);
	}

	public void showList() {
		System.out.println("== 게시물 목록 ==");
		List<Article> articles = articleService.getArticles();

		for (Article article : articles) {
			System.out.println("게시물 번호: " + article.num);
			System.out.println("작성일: " + article.regDate);
			System.out.println("작성자:  " + article.extra__writer);
			System.out.println("제목: " + article.title);
			System.out.println("내용: " + article.body);
			System.out.println();
		}
	}
}
