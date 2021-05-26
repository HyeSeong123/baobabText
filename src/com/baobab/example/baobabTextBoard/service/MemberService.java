package com.baobab.example.baobabTextBoard.service;

import com.baobab.example.baobabTextBoard.dao.MemberDao;
import com.baobab.example.baobabTextBoard.dto.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao();
	
	}
	
	public int memberAdd(String name, String loginId, String loginPw) {
		return memberDao.memberAdd(name,loginId,loginPw);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

}
