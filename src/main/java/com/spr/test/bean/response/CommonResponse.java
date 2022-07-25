package com.spr.test.bean.response;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * ------------------------------------------------
 * Class CommonResponse
 *
 * @author Glan <glanlv@can-dao.com>
 * @description 描述内容
 * @date 2022-03-28
 * ------------------------------------------------
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse implements Serializable {
    private static final long serialVersionUID = -4392854764123262199L;
    
    private String msg;
    
    private Integer code;
    
    private Object data;

    public static CommonResponse success() {
        return new CommonResponse("success", 200, Lists.newArrayList());
    }


    public static void main(String[] args) {
        BigDecimal formatOrderNum = new BigDecimal(1);
        BigDecimal formatTodayTotalOrderNum = new BigDecimal(3);

        //转成百分比
        BigDecimal numberToPercent = new BigDecimal(100);
        BigDecimal multiply = formatOrderNum.divide(formatTodayTotalOrderNum, 6, BigDecimal.ROUND_HALF_UP).multiply(numberToPercent);

        System.out.println(multiply);

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(df.format(multiply));
        
    }
    
}
