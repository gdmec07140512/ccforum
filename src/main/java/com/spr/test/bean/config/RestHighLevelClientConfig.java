package com.spr.test.bean.config;

import com.spr.test.bean.constant.InjectConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 批批网
 * ```
 * ================================================
 * 版权所有: 广州市批来批往信息科技有限公司，并保留所有权利。
 * 网站地址: https://www.ppwang.com
 * ================================================
 * 创建者: 研发中心 <tech@ppwang.com>
 * ```
 * ------------------------------------------------
 * Class RestHighLevelClientConfig
 *
 * @author lizhuohuan <birdylee_cn@163.com>
 * @description 描述内容
 * @date 2021-03-22
 * ------------------------------------------------
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = {"es.service.default.ip", "es.service.default.scheme"})
public class RestHighLevelClientConfig {

    @Value("${es.service.default.ip}")
    private String[] ipDefault;

    @Value("${es.service.default.scheme}")
    private String schemeDefault;

    @Bean(name = InjectConstant.DEFAULT_REST_HIGH_LEVEL_CLIENT)
    @ConditionalOnProperty(value = "es.service.default.ip")
    public RestHighLevelClient restHighLevelClientDefault() {
        HttpHost[] httpHosts = Arrays.stream(ipDefault)
                .map(this::getHttpHost)
                .toArray(HttpHost[]::new);
        RestClientBuilder builder = RestClient.builder(httpHosts);
        return new RestHighLevelClient(builder);
    }

    private HttpHost getHttpHost(String s) {
        assert StringUtils.isNoneEmpty(s);
        String[] address = s.split(":");
        String ip = address[0];
        int port = 9200;
        if (address.length == 2) {
            port = Integer.parseInt(address[1]);
        }
        return new HttpHost(ip, port, schemeDefault);
    }
}
