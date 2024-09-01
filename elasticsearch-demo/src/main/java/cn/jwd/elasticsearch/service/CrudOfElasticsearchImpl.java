package cn.jwd.elasticsearch.service;

import cn.jwd.elasticsearch.entity.Product;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Service;

@Service
public class CrudOfElasticsearchImpl implements CrudOfElasticsearch {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /*
     * 新增单条数据
     */
    @Override
    public void save(Product product) {
        elasticsearchRestTemplate.save(product);
    }

    /*
     * 删除单条数据
     */

    @Override
    public void delete(String id) {
        elasticsearchRestTemplate.delete(id, Product.class);
    }

    /*
     * 查询所有数据
     */
    @Override
    public void findAll() {
        MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();
        SearchHits<Product> hits = elasticsearchRestTemplate.search(new NativeSearchQuery(matchAllQueryBuilder), Product.class);
        hits.forEach(System.out::println);
    }

    /*
     * 根据id查询单条数据
     */
    @Override
    public void findById(String id) {
        Product product = elasticsearchRestTemplate.get(id, Product.class);
        System.out.println(product);
    }

    /*
     * bool查询
     */
    @Override
    public void booleanQuery() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("name", "手机"))
                .must(QueryBuilders.matchQuery("brand", "华为"))
                .should(QueryBuilders.rangeQuery("price").gte(3000));
        SearchHits<Product> hits = elasticsearchRestTemplate.search(new NativeSearchQuery(boolQueryBuilder), Product.class);
        hits.forEach(System.out::println);
    }
}
