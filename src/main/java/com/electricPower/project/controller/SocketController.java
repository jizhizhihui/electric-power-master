package com.electricPower.project.controller;

import com.electricPower.common.result.CommonResult;
import com.electricPower.core.Dataframe.DownLinkSign;
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
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

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
        socketServer = new SocketServer(port);
        socketServer.start();
        return CommonResult.success("Socket Start");
    }

    @GetMapping("close")
    public CommonResult socketClose(){
        socketServer.close();
        return  CommonResult.success("Socket Close");
    }

    /**
     * 召测数据帧
     * @param terminalNum 终端地址
     * @param sing 1 表示户表，2 表示总表
     * @return CommonResult
     */
    @GetMapping("call-terminal")
    @ApiOperation("召测终端，1 表示户表，2 表示总表")
    public CommonResult callTerminal(@RequestParam String terminalNum, int sing){
        if (!socketServer.getExistSocketMap().containsKey(terminalNum)) {
//            log.error(socketServer.getExistSocketMap());
            return CommonResult.failed("TerminalNum Not Online : " + terminalNum);
        }
        if(sing > 2 || sing < 1)
            return CommonResult.failed("Sign Error");
        try{
            Connection connection = socketServer.getExistSocketMap().get(terminalNum);
            FrameCmd frameCmd = new FrameCmd(DownLinkSign.CALLTERMINAL.getSign(),terminalNum);
            if (sing == 2)
                frameCmd.setCtrl(DownLinkSign.CALLTMASTER.getSign());
            log.error(frameCmd.toString());
            connection.println(HexUtils.hexToBytes(frameCmd.toString()));
        }catch (Exception e){
            log.error("Data sending failed : " + e);
            return CommonResult.failed("Data Sending Failed");
        }
        return CommonResult.success("Sending Success");
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
