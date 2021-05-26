package com.baobab.example.baobabTextBoard;

import java.util.Scanner;

import com.baobab.example.baobabTextBoard.controller.ArticleController;
import com.baobab.example.baobabTextBoard.controller.BuildController;
import com.baobab.example.baobabTextBoard.controller.Controller;
import com.baobab.example.baobabTextBoard.controller.MemberController;
import com.baobab.example.baobabTextBoard.service.ArticleService;
import com.baobab.example.baobabTextBoard.service.BuildService;
import com.baobab.example.baobabTextBoard.service.MemberService;

public class Container {

	public static Scanner scanner;
	public static ArticleService articleService;
	public static MemberService memberService;
	public static BuildService buildService;
	
	public static Controller articleController;
	public static Controller memberController;
	public static Controller buildController;
	

	static {
		scanner = new Scanner(System.in);

		memberService = new MemberService();
		articleService = new ArticleService();
		buildService = new BuildService();

		memberController = new MemberController();
		articleController = new ArticleController();
		buildController = new BuildController();
	}
}
