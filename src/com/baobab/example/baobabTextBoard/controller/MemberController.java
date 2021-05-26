package com.baobab.example.baobabTextBoard.controller;

import java.util.Scanner;

import com.baobab.example.baobabTextBoard.Container;
import com.baobab.example.baobabTextBoard.dto.Member;
import com.baobab.example.baobabTextBoard.service.MemberService;

public class MemberController extends Controller {

	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.startsWith("member join")) {
			doJoin(cmd);
		} else if (cmd.startsWith("member login")) {
			doLogin(cmd);
		}
	}

	private void doLogin(String cmd) {
		System.out.println("== 로그인 ==");
		Scanner sc = Container.scanner;

		Member member = null;
		int i = 0;

		while (true) {
			System.out.printf("아이디: ");
			String loginId = sc.nextLine();
			member = memberService.getMemberByLoginId(loginId);
			if (i >= 2) {
				System.out.println("3회 틀렸습니다. 잠시 후 이용해 주십시오.");
				break;
			}
			
			if (member == null) {
				System.out.println("존재하지 않는 아이디 입니다.");
				i++;
				continue;
			}

			System.out.printf("패스워드: ");
			String loginPw = sc.nextLine();
			if (member.loginPw.equals(loginPw)) {
				System.out.println(member.loginId + "님의 로그인을 환영합니다.");
				break;
			} else if (member.loginPw.equals(loginPw) == false) {
				System.out.println("패스워드가 일치하지 않습니다.");
				i++;
				continue;
			}
		}
	}

	private void doJoin(String cmd) {
		System.out.println("== 회원 가입 ==");
		Scanner sc = Container.scanner;

		System.out.printf("이름: ");
		String name = sc.nextLine();

		System.out.printf("아이디: ");
		String loginId = sc.nextLine();

		Member member = memberService.getMemberByLoginId(loginId.trim());

		if (member != null) {
			System.out.println("이미 존재하는 아이디 입니다.");
			return;
		}

		System.out.println("패스워드: ");
		String loginPw = sc.nextLine();

		int num = memberService.memberAdd(name, loginId, loginPw);

		System.out.println(num + "번 회원님의 가입을 축하드립니다.");
	}

}
