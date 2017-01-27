package com.techolution.beans;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.techolution.TecholutionApplication;
import com.techolution.entity.Feed;

/**
 *
 * @author bhargav.m
 *
 * Feed Bean is used to load feed related data and to calculate
 * the specification result achieved in given time.
 */
@Component
public class FeedBean {

	/**
	 * the amount of time given for eating food.
	 */
	private Long timeForFeed;

	/**
	 * starting point for application to run.
	 */
	@PostConstruct
	public void init() {
		TreeSet<Feed> data = loadData();
		Long satisfactionCount = getSatisfactionCount(data);
		System.out.println();
		System.out.println("====================================================");
		System.out.println("The Satisfaction achieved is = " + satisfactionCount);
		System.out.println("====================================================");
		System.out.println();
	}

	/**
	 * load the data from file and set time given to eat food.
	 * data.txt is loaded.
	 * @return TreeSet<Feed> the sorted tree set of feed.
	 */
	public TreeSet<Feed> loadData() {
		try {
			List<String> lines = IOUtils.readLines(TecholutionApplication.class.getClassLoader().getResourceAsStream("data.txt"), Charset.defaultCharset());
			String input = lines.get(0);
			String[] split = input.split(" ");
			setTimeForFeed(Long.parseLong(split[0]));
			lines.remove(0);
			TreeSet<Feed> collect = lines.stream().map(line -> convertToFeed(line)).collect(Collectors.toCollection(() -> new TreeSet<Feed>()));
			return collect;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * convert the line of file into feed object.
	 * @param line the string
	 * @return The Feed object.
	 */
	private Feed convertToFeed(String line) {
		if (line != null && line.length() > 0) {
			String[] split = line.split(" ");
			if (split.length >= 2) {
				Long satisfaction = Long.parseLong(split[0]);
				Long time = Long.parseLong(split[1]);
				return new Feed(satisfaction, time);
			}
			return null;
		}
		return null;
	}

	/**
	 * get the highest satisfaction that can be achieved in given time.
	 * @param data the TreeSet<Feed> of feed
	 * @return Long the satisfaction achieved
	 */
	public Long getSatisfactionCount(TreeSet<Feed> data) {
		Long value = Long.valueOf(0);
		if (getTimeForFeed() != null && data != null) {
			Iterator<Feed> iterator = data.iterator();
			while (iterator.hasNext() && getTimeForFeed() > 0) {
				Feed feed = iterator.next();
				long timeStatus = timeForFeed - feed.getTime();
				if (timeStatus >= 0) {
					value += feed.getSatisfaction();
					setTimeForFeed(timeStatus);
				}
			}
		}
		return value;
	}

	public Long getTimeForFeed() {
		return timeForFeed;
	}

	public void setTimeForFeed(Long timeForFeed) {
		this.timeForFeed = timeForFeed;
	}
}
