package com.electricPower.common.exception.frame;

import com.electricPower.common.exception.base.BaseException;

public class FrameException extends BaseException {

    public FrameException(String code, Object[] args,String message) {
        super("Frame", code, args, message);
    }
}
