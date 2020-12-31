package com.electricPower.project.controller;

import com.electricPower.common.result.CommonResult;
import com.electricPower.core.Dataframe.CtrlFrame;
import com.electricPower.core.Dataframe.downlink.FrameCmd;
import com.electricPower.core.socket.server.Connection;
import com.electricPower.core.socket.server.SocketServer;
import com.electricPower.project.service.IMeterDataService;
import com.electricPower.utils.FrameUtils;
import com.electricPower.utils.HexUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Api(tags = "socket 管理")
@RequestMapping("socket")
@Log4j2
public class SocketController {

    @Value("${task.socket.server.port}")
    private int port;

    @Autowired
    private IMeterDataService meterDataService;

    private SocketServer socketServer;

    @GetMapping("start")
    public CommonResult socketServer() {
        try {
            socketServer = new SocketServer(port);
            socketServer.start();
            return CommonResult.success("Socket Start");
        }catch (Exception e){
            log.error(e);
            return CommonResult.failed();
        }
    }

    @GetMapping("close")
    public CommonResult socketClose(){
        socketServer.close();
        return  CommonResult.success("Socket Close");
    }

    /**
     * 召测终端
     * @param terminalNum 终端地址
     * @param sign 01 表示户表，02 表示总表
     * @return CommonResult
     */
    @GetMapping("call-terminal")
    @ApiOperation("召测终端，01 表示户表，02 表示总表")
    public CommonResult callTerminal(@RequestParam String terminalNum, @RequestParam  String sign){
        if (!socketServer.getExistSocketMap().containsKey(terminalNum)) {
            return CommonResult.failed("TerminalNum Not Online : " + terminalNum);
        }
        if(!sign.equals(CtrlFrame.LINE.getVal()) && !sign.equals(CtrlFrame.MASTER.getVal()))
            return CommonResult.failed("Sign Error");
        try{
            Connection connection = socketServer.getExistSocketMap().get(terminalNum);
            FrameCmd frameCmd;
            if (sign.equals(CtrlFrame.LINE.getVal()))
                frameCmd= new FrameCmd(CtrlFrame.LINE.getVal(),FrameUtils.reverseTerminalNum(terminalNum));
            else
                frameCmd= new FrameCmd(CtrlFrame.MASTER.getVal(),FrameUtils.reverseTerminalNum(terminalNum));
            log.info("召测终端：" + frameCmd.toString());
            connection.getFrame().add(HexUtils.hexToBytes(frameCmd.toString()));
            connection.println();
            return CommonResult.success("Call Success");
        }catch (Exception e) {
            log.error("Data sending failed : " + e);
            return CommonResult.failed("Data Sending Failed");
        }
    }

    /**
     * 获取召测的数据帧
     * @param terminalNum 终端地址
     * @param sign 召测标识
     * @return CommonResult
     */
    @GetMapping("call-data")
    @ApiOperation("获取召测的数据帧，81 表示户表数据，82 表示总表数据")
    public CommonResult getCallData(@RequestParam String terminalNum, String sign){
        if (terminalNum == null)
            return CommonResult.failed("terminalNum null");
        if(!sign.equals(CtrlFrame.CALLLINE.getVal()) && !sign.equals(CtrlFrame.CALLMASTER.getVal()))
            return CommonResult.failed("Sign Error");
        try{
            if (sign.equals(CtrlFrame.CALLMASTER.getVal()))
                sign = CtrlFrame.CALLMASTER.getVal();
            log.info("召测终端数据" + "terminalNum:" + terminalNum + "; Sign: " + sign);
            return CommonResult.success(meterDataService.getOneBySign(terminalNum,sign));
        }catch (Exception e){
            log.error("getCallData : " + e);
            return CommonResult.failed("Data Get Failed");
        }

    }


    /**
     * 查看在线终端
     * @return map
     */
    @GetMapping("onLineTerminal")
    @ApiOperation("查看在线终端")
    public CommonResult onLineTerminal(){
        return CommonResult.success(socketServer.getExistSocketMap().keySet());
    }
}
