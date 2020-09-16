package com.electricPower.socket.constants;

/**
 * @author 丁许
 * @date 2019-01-24 20:29
 */
public class SocketConstant {

	/**
	 * 心跳频率为10s
	 */
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
