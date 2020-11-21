package com.electricPower.core.Dataframe;

import lombok.Data;
import lombok.Getter;

@Getter
public enum DownLinkSign {

    CALLTERMINAL("召测终端","01"),
    CALLTMASTER("召测主站","02");
    String msg;
    String sign;

    DownLinkSign(String msg, String sign){
        this.sign = sign;
        this.msg = msg;
    }
}
