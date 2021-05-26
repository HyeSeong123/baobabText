package com.baobab.example.baobabTextBoard.dto;

import java.util.Map;

public class Board {
	public int num;
	public String regDate;
	public String updateDate;
	public int menu_depth;
	public String name;
	public String code;
	public String parent_code;
	public String menu_url;
	
	public String extra__writer;
	
	public Board(int num, String regDate, String updateDate, int menu_depth, String name, String code, String parent_code, String menu_url) {
		this.num = num;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.menu_depth = menu_depth;
		this.name = name;
		this.code = code;
		this.parent_code = parent_code;
		this.menu_url = menu_url;
	}
	
	public Board(Map<String, Object> articleMap) {
		this.num = (int) articleMap.get("num");
		this.regDate = (String) articleMap.get("regDate");
		this.updateDate = (String) articleMap.get("updateDate");
		this.menu_depth = (int) articleMap.get("menu_depth");
		this.name = (String) articleMap.get("name");
		this.code = (String) articleMap.get("code"); 
		this.parent_code = (String) articleMap.get("parent_code");
		this.menu_url = (String) articleMap.get("menu_url"); 
	}

	@Override
	public String toString() {
		return "Board [num=" + num + ", regDate=" + regDate + ", updateDate=" + updateDate + ", menu_depth="
				+ menu_depth + ", name=" + name + ", code=" + code + ", parent_code=" + parent_code + ", menu_url="
				+ menu_url + ", extra__writer=" + extra__writer + "]";
	}


}
