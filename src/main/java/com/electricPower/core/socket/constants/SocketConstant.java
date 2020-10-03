package com.electricPower.core.socket.constants;


public class SocketConstant {

	/**
	 * 心跳频率为10s
	 */
	//final关键字不允许修改或替换其原始值或定义。
	public static final int HEART_RATE = 10*1000;

	/**
	 * 最多开2000个socket线程，超过的直接拒绝
	 */
	public static final int MAX_SOCKET_THREAD_NUM = 2000;

	/**
	 * 重试次数：3
	 */
	public static final int RETRY_COUNT = 3;
}
