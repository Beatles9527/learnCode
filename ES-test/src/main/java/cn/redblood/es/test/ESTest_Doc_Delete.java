package cn.redblood.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author charlie
 */
public class ESTest_Doc_Delete {
    public static void main(String[] args) throws IOException {

        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.0.112", 9200, "http")));

        // 查询数据
        DeleteRequest request = new DeleteRequest();
        request.index("user").id("1001");
        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());
        esClient.close();
    }
}
