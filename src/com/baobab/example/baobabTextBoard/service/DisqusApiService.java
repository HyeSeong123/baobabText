package com.baobab.example.baobabTextBoard.service;

import java.util.HashMap;
import java.util.Map;

import com.baobab.example.baobabTextBoard.Container;
import com.baobab.example.baobabTextBoard.DisqusApiDataListThread;
import com.baobab.example.baobabTextBoard.dto.Article;
import com.baobab.example.baobabTextBoard.util.Util;

public class DisqusApiService {

	public Map<String, Object> getArticleData(Article article) {
		String fileName = Container.buildService.getArticleDetailFileName(article.num);
		String url = "https://disqus.com/api/3.0/forums/listThreads.json";
		DisqusApiDataListThread disqusApiDataListThread = (DisqusApiDataListThread) Util.callApiResponseTo(
				DisqusApiDataListThread.class, url, "api_key=" + Container.config.getDisqusApiKey(),
				"forum=" + Container.config.getDisqusForumName(), "thread:ident=" + fileName);
		
		if (disqusApiDataListThread == null) {
			return null;
		}
		
		Map<String, Object> rs = new HashMap<>();
		rs.put("likes", disqusApiDataListThread.response.get(0).likes);
		rs.put("posts", disqusApiDataListThread.response.get(0).posts);
		
		return rs;
	}

}
