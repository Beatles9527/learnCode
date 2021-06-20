package cn.redblood.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author charlie
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {
}
