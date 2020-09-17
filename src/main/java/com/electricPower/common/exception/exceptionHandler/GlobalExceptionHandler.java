package com.electricPower.common.exception.exceptionHandler;

import com.electricPower.common.result.CommonResult;
import com.electricPower.common.result.IErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Log4j2
@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler {

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public CommonResult runtimeExceptionHandler(RuntimeException e) {
        return resultFormat(1, e);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public CommonResult nullPointerExceptionHandler(NullPointerException e) {
        System.err.println("NullPointerException:");
        return resultFormat(2, e);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public CommonResult classCastExceptionHandler(ClassCastException e) {
        return resultFormat(3, e);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public CommonResult iOExceptionHandler(IOException e) {
        return resultFormat(4, e);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public CommonResult noSuchMethodExceptionHandler(NoSuchMethodException e) {
        return resultFormat(5, e);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public CommonResult indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        return resultFormat(6, e);
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public CommonResult requestNotReadable(HttpMessageNotReadableException e) {
        System.out.println("400…requestNotReadable");
        return resultFormat(7, e);
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public CommonResult requestTypeMismatch(TypeMismatchException e) {
        System.out.println("400…TypeMismatchException");
        return resultFormat(8, e);
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public CommonResult requestMissingServletRequest(MissingServletRequestParameterException e) {
        System.out.println("400…MissingServletRequest");
        return resultFormat(9, e);
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public CommonResult request405(HttpRequestMethodNotSupportedException e) {
        return resultFormat(10, e);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public CommonResult request406(HttpMediaTypeNotAcceptableException e) {
        System.out.println("406…");
        return resultFormat(11, e);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public CommonResult server500(RuntimeException e) {
        return resultFormat(12, e);
    }
    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public CommonResult requestStackOverflow(StackOverflowError e) {
        return resultFormat(13, e);
    }

    //除数不能为0
    @ExceptionHandler({ArithmeticException.class})
    public CommonResult arithmeticException(ArithmeticException e) {
        return resultFormat(13, e);
    }

    //其他错误
    @ExceptionHandler({Exception.class})
    public CommonResult exception(Exception e) {
        return resultFormat(14, e);
    }

    private CommonResult resultFormat(Integer code, Throwable e){
        log.error( "code:" + code + "; msg:" + e.getMessage());
        return CommonResult.failed(new IErrorCode() {
            @Override
            public int getCode() {
                return code;
            }
            @Override
            public String getMessage() {
                return e.getMessage();
            }
        });
    }
}
