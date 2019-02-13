package com.digital.awayday.model;

import com.digital.awayday.service.ActivityBlockServiceImpl;

/**
 * Class for a group of tasks with a delimited size and extra time
 * @author Shubham Agarwal
 *
 */
public class ExtraTimeBlock extends ActivityBlockServiceImpl {

	private Integer extraTime;

	public ExtraTimeBlock(Integer size, Integer extraTime) {
		super(size);
		this.extraTime = extraTime;
	}
	
	public Integer getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(Integer extraTime) {
		this.extraTime = extraTime;
	}

}
