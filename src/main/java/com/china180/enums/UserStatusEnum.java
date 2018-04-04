package com.china180.enums;

/**
 * 用户状态
 * 
 * @author shaowei.liu
 *
 */
public enum UserStatusEnum {
	/**
	 * 正常
	 */
	Normal("1"),
	/**
	 * 非法
	 */
	Invalid("2");

	private String text;

	private UserStatusEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
