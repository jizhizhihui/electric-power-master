package com.electricPower.common.exception.frame;

/**
 * 控制字节无效
 */
public class FrameInvalidCtrlException extends  FrameException{

    public FrameInvalidCtrlException() {
        super("frame.invalid.ctrl", null, "控制字节无效");
    }
}
