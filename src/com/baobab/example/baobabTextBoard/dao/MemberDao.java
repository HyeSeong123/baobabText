package com.baobab.example.baobabTextBoard.dao;

import java.util.Map;

import com.baobab.example.baobabTextBoard.dto.Article;
import com.baobab.example.baobabTextBoard.dto.Member;
import com.baobab.example.mysqlUtil.MysqlUtil;
import com.baobab.example.mysqlUtil.SecSql;

public class MemberDao {

	public int memberAdd(String name, String loginId, String loginPw) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("name = ?,", name);
		sql.append("loginId = ?,", loginId);
		sql.append("loginPw = ?", loginPw);

		return MysqlUtil.insert(sql);
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);

		if (memberMap.isEmpty()) {
			return null;
		}

		return new Member(memberMap);
	}

}
