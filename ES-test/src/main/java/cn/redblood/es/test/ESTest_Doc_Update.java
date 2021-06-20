package cn.redblood.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author charlie
 */
public class ESTest_Doc_Update {
    public static void main(String[] args) throws IOException {

        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.0.112", 9200, "http")));

        // 修改数据
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");
        request.doc(XContentType.JSON,"sex","女");

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());

        esClient.close();
    }
}
