package com.opcc.ccforum.provider.service;

import com.opcc.ccforum.provider.bean.constant.InjectConstant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------------------
 * Class SearchService
 *
 * @author Glan
 * @description 描述内容
 * @date 2022-04-06
 * ------------------------------------------------
 */
@Service
@Slf4j
public class SearchService {
    @Resource(name = InjectConstant.DEFAULT_REST_HIGH_LEVEL_CLIENT)
    private RestHighLevelClient restHighLevelClient;

    private final String INDEX_NAME = "spring-test-index";

    private final String INDEX_AIDER_NAME = "spring-test-index-aider";

    private String indexConfigYmlContent;

    @SneakyThrows
    public void createIndex() {
        // 创建新索引
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX_NAME);
        // 读取yml配置
        createIndexRequest.source(getIndexConfigYmlContent(), XContentType.YAML);
        // 设置别名
        createIndexRequest.alias(new Alias(INDEX_AIDER_NAME));
        createIndexRequest.setTimeout(TimeValue.timeValueSeconds(5));

        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        if (!createIndexResponse.isAcknowledged()) {
            log.error("创建索引失败，index: {}", INDEX_NAME);
        }

    }


    public List<Long> search() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);

        RequestOptions options = RequestOptions.DEFAULT;

        try {
            SearchRequest searchRequest = new SearchRequest("index-name");
            restHighLevelClient.search(searchRequest, options);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 获取索引配置
     *
     * @return
     */
    @SneakyThrows
    public final String getIndexConfigYmlContent() {
        if (!ObjectUtils.isEmpty(indexConfigYmlContent)) {
            return indexConfigYmlContent;
        }
        InputStream inputStream = this.getClass().getResourceAsStream("es/spring-test-index.yaml");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        String lineData;
        while ((lineData = br.readLine()) != null) {
            stringBuilder.append(lineData);
            stringBuilder.append(ls);
        }
        // 删除最后一个新行分隔符
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        inputStream.close();
        br.close();
        indexConfigYmlContent = stringBuilder.toString();

        return indexConfigYmlContent;
    }
}
