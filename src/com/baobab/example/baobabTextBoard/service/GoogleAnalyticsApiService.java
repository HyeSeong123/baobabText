package com.baobab.example.baobabTextBoard.service;

import java.io.IOException;

import com.baobab.example.baobabTextBoard.Container;
import com.baobab.example.baobabTextBoard.dao.Ga4DataDao;
import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.Row;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;


public class GoogleAnalyticsApiService {
	private Ga4DataDao ga4DataDao;
	
	public GoogleAnalyticsApiService(){
		ga4DataDao = new Ga4DataDao();
	}
	public boolean updateGa4DataPageHits() {
		String ga4PropertyId = Container.config.getGa4PropertyId();
		try (BetaAnalyticsDataClient  analyticsData = BetaAnalyticsDataClient.create()) {
			RunReportRequest request = RunReportRequest.newBuilder()
					.setProperty("properties/" + ga4PropertyId)
					.addDimensions(Dimension.newBuilder().setName("pagePath"))
					.addMetrics(Metric.newBuilder().setName("activeUsers"))
					.addDateRanges(DateRange.newBuilder().setStartDate("2021-06-22").setEndDate("today")).build();

			// Make the request
			RunReportResponse response = analyticsData.runReport(request);

			System.out.println("Report result:");
			for (Row row : response.getRowsList()) {
				String pagePath = row.getDimensionValues(0).getValue();
				int hit = Integer.parseInt(row.getMetricValues(0).getValue());
				System.out.printf("pagePath : %s, hit : %d\n", pagePath, hit);
				
				update(pagePath, hit);
			}
		} catch (IOException e) {
			System.out.println("실패");
			return false;
		}
		System.out.println("성공");
		return true;
	}
	
	private void update(String pagePath, int hit) {
		 ga4DataDao.deletePagePath(pagePath);
		 ga4DataDao.savaPagePath(pagePath, hit);
	}
	public void updatePageHits() {
		updateGa4DataPageHits();
		Container.articleService.updatePageHits();
	}
}
