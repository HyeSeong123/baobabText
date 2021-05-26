package com.baobab.example.baobabTextBoard.dto;

import java.util.Map;

public class Member {
	public int num;
	public String regDate;
	public String updateDate;
	public String name;
	public String loginId;
	public String loginPw;

	public Member(int num, String regDate, String updateDate, String name, String loginId, String loginPw) {
		this.num = num;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.name = name;
		this.loginId = loginId;
		this.loginPw = loginPw;

	}

	public Member(Map<String, Object> memberMap) {
		this.num = (int) memberMap.get("num");
		this.regDate = (String) memberMap.get("regDate");
		this.updateDate = (String) memberMap.get("updateDate");
		this.name = (String) memberMap.get("name");
		this.loginId = (String) memberMap.get("loginId");
		this.loginPw = (String) memberMap.get("loginPw");
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", regDate=" + regDate + ", updateDate=" + updateDate + ", name=" + name
				+ ", loginId=" + loginId + ", loginPw=" + loginPw + "]";
	}

}
