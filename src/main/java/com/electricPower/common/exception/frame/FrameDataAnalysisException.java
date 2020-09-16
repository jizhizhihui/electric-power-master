package com.electricPower.common.exception.frame;

public class FrameDataAnalysisException extends FrameException {

    public FrameDataAnalysisException() {
        super("frame.data.analysis", null, "数据帧解析异常");
    }
}
