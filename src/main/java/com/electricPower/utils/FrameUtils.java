package com.electricPower.utils;

import com.electricPower.project.entity.MeterData;
import com.electricPower.project.service.IMeterDataService;
import com.electricPower.project.service.impl.MeterDataServiceImpl;
import lombok.extern.log4j.Log4j2;

import java.text.ParseException;

@Log4j2
public class FrameUtils {

    public static MeterData analysisLien(String message) {
        MeterData meterData = new MeterData();
        String[] strings = message.split(" ");

        //电压
        meterData.setVoltageA(BCDUtils.stringBCDToFloat(strings[9] + strings[10], 1, false));
        meterData.setVoltageB(BCDUtils.stringBCDToFloat(strings[11] + strings[12], 1, false));
        meterData.setVoltageC(BCDUtils.stringBCDToFloat(strings[13] + strings[14], 1, false));

        //电流
        meterData.setCurrentA(BCDUtils.stringBCDToFloat(strings[15] + strings[16] + strings[17] + strings[18], 3, false));
        meterData.setCurrentB(BCDUtils.stringBCDToFloat(strings[19] + strings[20] + strings[21] + strings[22], 3, false));
        meterData.setCurrentC(BCDUtils.stringBCDToFloat(strings[23] + strings[24] + strings[25] + strings[26], 3, false));
        meterData.setCurrentZeroLine(BCDUtils.stringBCDToFloat(strings[27] + strings[28] + strings[29] + strings[30], 3, false));
        meterData.setCurrentRemain(BCDUtils.stringBCDToFloat(strings[31] + strings[32] + strings[33] + strings[34], 3, false));

        //有功功率
        meterData.setActivePowerA(BCDUtils.stringBCDToFloat(strings[35] + strings[36] + strings[37] + strings[38], 3, true));
        meterData.setActivePowerB(BCDUtils.stringBCDToFloat(strings[39] + strings[40] + strings[41] + strings[42], 3, true));
        meterData.setActivePowerC(BCDUtils.stringBCDToFloat(strings[43] + strings[44] + strings[45] + strings[46], 3, true));
        meterData.setActivePowerTotal(BCDUtils.stringBCDToFloat(strings[47] + strings[48] + strings[49] + strings[50], 3, true));

        //无功功率
        meterData.setReactivePowerA(BCDUtils.stringBCDToFloat(strings[51] + strings[52] + strings[53] + strings[54], 3, true));
        meterData.setReactivePowerB(BCDUtils.stringBCDToFloat(strings[55] + strings[56] + strings[57] + strings[58], 3, true));
        meterData.setReactivePowerC(BCDUtils.stringBCDToFloat(strings[59] + strings[60] + strings[61] + strings[62], 3, true));
        meterData.setReactivePowerTotal(BCDUtils.stringBCDToFloat(strings[63] + strings[64] + strings[65] + strings[66], 3, true));

        //功率因素
        meterData.setPowerFactorA(BCDUtils.stringBCDToFloat(strings[67] + strings[68], 3, true));
        meterData.setPowerFactorB(BCDUtils.stringBCDToFloat(strings[69] + strings[70], 3, true));
        meterData.setPowerFactorC(BCDUtils.stringBCDToFloat(strings[71] + strings[72], 3, true));

        //温度
        meterData.setTemperature((int)BCDUtils.stringBCDToFloat(strings[73] + strings[74], 0, true));

        //湿度
        if (strings[75].equals("FF") && strings[76].equals("FF"))
            log.info("该字段无效");
        else
            meterData.setHumidity((int)BCDUtils.stringBCDToFloat(strings[75] + strings[76], 0, true));

        //时间
        meterData.setSaveTime();
        try {
            meterData.setAcquisitionTime(DateTime.dateToLocalDateTime(BCDUtils.stringBCDToDate(strings[77], strings[78], strings[79], strings[80], strings[81], strings[82])));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return meterData;
    }


    public static void svarTest(MeterDataServiceImpl meterDataService){
        String message =
                "43 55 01 " + //前校验
                        "11 11 11 11 11 11 " +    //终端地址
                        "22 12 22 34 22 56 " +  //电压 ，小数1，非负，2
                        "00 00 12 34 00 00 56 78 00 01 90 12 00 00 01 23 00 00 00 45 " + //电流，小数3，非负，4
                        "80 00 78 90 00 01 23 45 80 00 56 78 00 23 45 67 00 00 78 90 80 01 23 45 00 00 56 78 80 23 45 67 " + //有功无功功率,小数3，可负
                        "10 00 09 98 85 00 " +  //功率因素，小数3，可负，
                        "80 25 00 65 20 08 06 11 15 00 " +  //温湿度 + 时间戳
                        "D1 16";//后校验

        MeterData meterData = FrameUtils.analysisLien(message);
        meterData.setMeterSn("test");

        meterDataService.save(meterData);
    }

    public static void main(String[] args) {
        String message =
                "43 55 01 " + //前校验
                        "11 11 11 11 11 11 " +    //终端地址
                        "22 12 22 34 22 56 " +  //电压 ，小数1，非负，2
                        "00 00 12 34 00 00 56 78 00 01 90 12 00 00 01 23 00 00 00 45 " + //电流，小数3，非负，4
                        "80 00 78 90 00 01 23 45 80 00 56 78 00 23 45 67 00 00 78 90 80 01 23 45 00 00 56 78 80 23 45 67 " + //有功无功功率,小数3，可负
                        "10 00 09 98 85 00 " +  //功率因素，小数3，可负，
                        "80 25 00 65 20 08 06 11 15 00 " +  //温湿度 + 时间戳
                        "D1 16";//后校验

        MeterDataServiceImpl meterDataService = new MeterDataServiceImpl();

        MeterData meterData = analysisLien(message);
        meterData.setMeterSn("test");

        meterDataService.save(meterData);
    }

//    int count = 9;
//        log.info("电压");
//        for (int i = 0; i < 6; i += 2) {
//        BCDUtils.stringBCDToFloat(strings[count + i] + strings[count + i + 1], 1, false);
//    }
//
//    count += 6;
//        log.info("电流");
//        for (int i = 0; i < 20; i += 4) {
//        BCDUtils.stringBCDToFloat(strings[count + i] + strings[count + i + 1] + strings[count + i + 2] + strings[count + i + 3], 3, false);
//    }
//
//    count += 20;
//        log.info("有功无功功率");
//        for (int i = 0; i < 32; i += 4) {
//        BCDUtils.stringBCDToFloat(strings[count + i] + strings[count + i + 1] + strings[count + i + 2] + strings[count + i + 3], 3, true);
//    }
//
//    count += 32;
//        log.info("功率因素");
//        for (int i = 0; i < 6; i += 2) {
//        BCDUtils.stringBCDToFloat(strings[count + i] + strings[count + i + 1], 3, true);
//    }
//
//    count += 6;
//        log.info("温度");
//        BCDUtils.stringBCDToFloat(strings[count] + strings[count + 1], 0, true);
//
//    count += 2;
//        log.info("湿度");
//        if (strings[count].equals("FF") && strings[count + 1].equals("FF"))
//            log.info("该字段无效");
//        else
//                BCDUtils.stringBCDToFloat(strings[count] + strings[count + 1], 0, true);
//
//    count += 2;
//        log.info("时间");
//        try {
//        BCDUtils.stringBCDToDate(strings[count], strings[count + 1], strings[count + 2] ,strings[count + 3],strings[count + 4] ,strings[count + 5]);
//    } catch (ParseException e) {
//        e.printStackTrace();
//    }

}
