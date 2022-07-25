package com.opcc.ccforum.provider.bean.response;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ------------------------------------------------
 * Class CommonResponse
 *
 * @author Glan
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

}
