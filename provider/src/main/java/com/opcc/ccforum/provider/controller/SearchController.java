package com.opcc.ccforum.provider.controller;

import com.opcc.ccforum.provider.bean.response.CommonResponse;
import com.opcc.ccforum.provider.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ------------------------------------------------
 * Class SearchController
 *
 * @author Glan
 * @description 描述内容
 * @date 2022-04-06
 * ------------------------------------------------
 */
@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    public CommonResponse createIndex() {
        searchService.createIndex();

        return CommonResponse.success();
    }

}
