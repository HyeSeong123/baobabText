package com.baobab.example.baobabTextBoard;

import java.util.List;
import java.util.Map;

import com.baobab.example.baobabTextBoard.util.Util;
import com.baobab.example.mysqlUtil.MysqlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
	public void run() {
		MysqlUtil.setDBInfo("127.0.0.1", "codingsepo", "coding123414", "baobabTextBoard");
		testUpdatPageHitsByGa4Api();
	}

	private void testUpdatPageHitsByGa4Api() {
		Container.googleAnalyticsApiService.updatePageHits();
	}

	private static void testAPIGoogleCredentials() {
		String keyFilePath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

		System.out.println(keyFilePath);
	}

	private static void testUpdateGoogleAnalyticsApi() {
		Container.googleAnalyticsApiService.updatePageHits();
	}

	private static void testAPIReturnMap() {
		String url = "https://disqus.com/api/3.0/forums/listThreads.json";
		Map<String, Object> rs = Util.callApiResponseToMap(url,
				"api_key=0ObNxr04unsg6qHoZDgRASFtlMWUjob5igy0TUEZFmA7DstFp0JPPrpYPMkOMc4l", "forum=baobab612",
				"thread:ident=article_detail_2.html");
		List<Map<String, Object>> response = (List<Map<String, Object>>) rs.get("response");
		Map<String, Object> thread = response.get(0);
		System.out.println((int) thread.get("likes"));
		System.out.println(rs);
	}

	private static void testAPI() {
		String url = "https://disqus.com/api/3.0/forums/listThreads.json";
		String rs = Util.callApi(url, "api_key=0ObNxr04unsg6qHoZDgRASFtlMWUjob5igy0TUEZFmA7DstFp0JPPrpYPMkOMc4l",
				"forum=baobab612", "thread:ident=article_detail_2.html");
		System.out.println(rs);
	}

	private static void textJackson() {
		String jsonString = "{\"age\":22, \"name\":\"홍길동\"}";

		ObjectMapper ob = new ObjectMapper();
		Map rs = null;
		try {
			rs = ob.readValue(jsonString, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}
		System.out.println(rs);
	}
}
