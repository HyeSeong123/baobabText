package com.baobab.example.baobabTextBoard;

import java.util.Scanner;

import com.baobab.example.baobabTextBoard.controller.ArticleController;
import com.baobab.example.baobabTextBoard.controller.BuildController;
import com.baobab.example.baobabTextBoard.controller.Controller;
import com.baobab.example.baobabTextBoard.controller.MemberController;
import com.baobab.example.baobabTextBoard.service.ArticleService;
import com.baobab.example.baobabTextBoard.service.BuildService;
import com.baobab.example.baobabTextBoard.service.DisqusApiService;
import com.baobab.example.baobabTextBoard.service.GoogleAnalyticsApiService;
import com.baobab.example.baobabTextBoard.service.MemberService;
import com.baobab.example.baobabTextBoard.session.Session;

public class Container {

	public static Scanner scanner;
	public static ArticleService articleService;
	public static MemberService memberService;
	public static BuildService buildService;
	public static DisqusApiService disqusApiService;
	public static GoogleAnalyticsApiService googleAnalyticsApiService;
	
	public static Controller articleController;
	public static Controller memberController;
	public static Controller buildController;
	
	public static Session session;
	
	public static AppConfig config;
	

	static {
		config = new AppConfig();
		
		scanner = new Scanner(System.in);
		
		session = new Session();
		
		googleAnalyticsApiService = new GoogleAnalyticsApiService();
		disqusApiService = new DisqusApiService();
		memberService = new MemberService();
		articleService = new ArticleService();
		buildService = new BuildService();
		
		memberController = new MemberController();
		articleController = new ArticleController();
		buildController = new BuildController();
	}
}
