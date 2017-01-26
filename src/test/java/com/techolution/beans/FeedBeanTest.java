package com.techolution.beans;

import java.io.IOException;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.techolution.entity.Feed;

@RunWith(MockitoJUnitRunner.class)
public class FeedBeanTest {

	@Autowired
	@InjectMocks
	private FeedBean feedBean;

	@Before
	public void setUp() throws IOException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testComplete() {
		feedBean.loadData();
		Assert.assertEquals(Long.valueOf(10000), feedBean.getTimeForFeed());
	}

	@Test
	public void getSatisfactionCountSuccess() {
		feedBean.setTimeForFeed(Long.valueOf(10000));
		Long satisfactionCount = feedBean.getSatisfactionCount(dummyData());
		Assert.assertEquals(Long.valueOf(10), satisfactionCount);
	}

	@Test
	public void getSatisfactionCountSuccessNull() {
		feedBean.setTimeForFeed(Long.valueOf(10000));
		Long satisfactionCount = feedBean.getSatisfactionCount(null);
		Assert.assertEquals(Long.valueOf(0), satisfactionCount);
	}

	public static TreeSet<Feed> dummyData() {
		TreeSet<Feed> feedSet = new TreeSet<Feed>();
		Feed feed = new Feed(Long.valueOf(10), Long.valueOf(10000));
		feedSet.add(feed);
		return feedSet;
	}
}
