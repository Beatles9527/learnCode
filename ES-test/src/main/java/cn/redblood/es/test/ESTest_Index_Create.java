package cn.redblood.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author charlie
 */
public class ESTest_Index_Create {
    public static void main(String[] args) throws IOException {

        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.0.112", 9200, "http")));

        // 创建索引
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = esClient.indices().create(request, RequestOptions.DEFAULT);

        // 响应状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("索引操作：" + acknowledged);

        esClient.close();
    }
}
