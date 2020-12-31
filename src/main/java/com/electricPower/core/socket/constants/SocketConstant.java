package com.electricPower.core.socket.constants;


public class SocketConstant {

	/**
	 * 心跳频率为 4 分钟
	 */
	public static final int HEART_RATE = 4*60*1000;

	/**
	 * 最多开2000个socket线程，超过的直接拒绝
	 */
	public static final int MAX_SOCKET_THREAD_NUM = 2000;

	/**
	 * 重试次数：3
	 */
	public static final int RETRY_COUNT = 3;

	/**
	 * 定时间隔：200 毫秒
	 */
	public static  final  int TIMING = 200;
}
