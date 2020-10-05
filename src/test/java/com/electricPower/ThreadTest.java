//package com.electricPower;
//
//import com.electricPower.project.entity.MeterData;
//import com.electricPower.project.service.impl.MeterDataServiceImpl;
//import com.electricPower.utils.FrameUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
////由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//@WebAppConfiguration
//public class ThreadTest {
//
//    @Autowired
//    MeterDataServiceImpl meterDataService;
//    @Test
//    public void ThreadTest(){
//        String message =
//                "43 55 01 " + //前校验
//                        "11 11 11 11 11 11 " +    //终端地址
//                        "22 12 22 34 22 56 " +  //电压 ，小数1，非负，2
//                        "00 00 12 34 00 00 56 78 00 01 90 12 00 00 01 23 00 00 00 45 " + //电流，小数3，非负，4
//                        "80 00 78 90 00 01 23 45 80 00 56 78 00 23 45 67 00 00 78 90 80 01 23 45 00 00 56 78 80 23 45 67 " + //有功无功功率,小数3，可负
//                        "10 00 09 98 85 00 " +  //功率因素，小数3，可负，
//                        "80 25 00 65 20 08 06 11 15 00 " +  //温湿度 + 时间戳
//                        "D1 16";//后校验
//
////        MeterData meterData = FrameUtils.analysisLien(message,true);
////        meterData.setMeterSn("test");
//
////        meterDataService.save(meterData);
//    }
//
//}
