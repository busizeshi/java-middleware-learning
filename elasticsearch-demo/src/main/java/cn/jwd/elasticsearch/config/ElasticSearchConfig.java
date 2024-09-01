package cn.jwd.elasticsearch.config;

import cn.jwd.elasticsearch.entity.Product;
import lombok.Data;
import org.apache.lucene.index.IndexOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
 * 配置ElasticSearch索引名称
 */
@Data
@Component
public class ElasticSearchConfig {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    private static final String indexName = "product";

    @PostConstruct
    public void init() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));
        boolean exists = indexOperations.exists();

        if (!exists) {
            System.out.println(indexName + "不存在");
            indexOperations.create();
            indexOperations.createMapping(Product.class);
        }
    }
}
