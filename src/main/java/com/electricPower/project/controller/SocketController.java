package com.electricPower.project.controller;

import com.electricPower.common.result.CommonResult;
import com.electricPower.core.socket.server.SocketServer;
import com.electricPower.project.service.IMeterDataService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "socket 管理")
@RequestMapping("socket")
@Log4j2
public class SocketController {

    @Value("${task.socket.port}")
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
}
