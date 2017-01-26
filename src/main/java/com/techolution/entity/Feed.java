package com.techolution.entity;

import java.io.Serializable;

public class Feed implements Serializable, Comparable<Feed> {

	/**
	 *
	 */
	private static final long serialVersionUID = 8260966910968432177L;

	private Long satisfaction;

	private Long time;

	public Feed() {
	}

	public Feed(Long satisfaction, Long time) {
		super();
		this.satisfaction = satisfaction;
		this.time = time;
	}

	public Long getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Long satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Feed [" + (satisfaction != null ? "satisfaction=" + satisfaction + ", " : "")
				+ (time != null ? "time=" + time : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((satisfaction == null) ? 0 : satisfaction.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Feed other = (Feed) obj;
		if (satisfaction == null) {
			if (other.satisfaction != null) {
				return false;
			}
		} else if (!satisfaction.equals(other.satisfaction)) {
			return false;
		}
		if (time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!time.equals(other.time)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Feed o) {
		if ((this.satisfaction / this.time) > (o.satisfaction / o.time)) {
			return -1;
		} else if ((this.satisfaction / this.time) < (o.satisfaction / o.time)) {
			return 1;
		}
		return 0;
	}
}
