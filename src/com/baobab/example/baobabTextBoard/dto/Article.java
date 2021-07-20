package com.baobab.example.baobabTextBoard.dto;

import java.util.Map;

public class Article {
	public int num;
	public String regDate;
	public String updateDate;
	public int memberNum;
	public int boardNum;
	public String title;
	public String body;
	public int hitsCount;
	public int replyCount;
	public int like;
	
	public String extra__writer;
	public String extra__board;
	public String fRegDate;
	public Article(int num, String regDate, String updateDate, int memberNum, int boardNum, String title, String body, int hitsCount, int replyCount, int like, String extra__writer, String extra__board) {
		this.num = num;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.memberNum = memberNum;
		this.boardNum = boardNum;
		this.title = title;
		this.body = body;
		this.hitsCount = hitsCount;
		this.replyCount = replyCount;
		this.like = like;
		this.extra__writer = extra__writer;
	}
	
	public Article(Map<String, Object> articleMap) {
		this.num = (int) articleMap.get("num");
		this.regDate = (String) articleMap.get("regDate");
		this.updateDate = (String) articleMap.get("updateDate");
		this.memberNum = (int) articleMap.get("memberNum");
		this.boardNum = (int) articleMap.get("boardNum");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body"); 
		this.hitsCount = (int) articleMap.get("hitsCount");
		this.replyCount = (int) articleMap.get("replyCount");
		this.like = (int) articleMap.get("like");
		if(articleMap.containsKey("extra__writer")) {
			this.extra__writer = (String) articleMap.get("extra__writer");
		}
		if(articleMap.containsKey("extra__board")) {
			this.extra__board = (String) articleMap.get("extra__board");
		}
		if(articleMap.containsKey("fRegDate")) {
			this.fRegDate = (String) articleMap.get("fRegDate");
		}
	}

	@Override
	public String toString() {
		return "Article [num=" + num + ", regDate=" + regDate + ", updateDate=" + updateDate + ", memberNum="
				+ memberNum + ", boardNum=" + boardNum + ", title=" + title + ", body=" + body + ", hitsCount="
				+ hitsCount + ", replyCount=" + replyCount + ", like=" + like + ", extra__writer=" + extra__writer
				+ ", extra__board=" + extra__board + ", fRegDate=" + fRegDate + "]";
	}



}
