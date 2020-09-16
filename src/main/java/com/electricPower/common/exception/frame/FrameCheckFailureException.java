package com.electricPower.common.exception.frame;

public class FrameCheckFailureException extends FrameException{

    public FrameCheckFailureException(String message){ super("frame.check.failure",null,message);}
}
