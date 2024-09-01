package cn.jwd.elasticsearch;

import cn.jwd.elasticsearch.entity.Product;
import cn.jwd.elasticsearch.service.CrudOfElasticsearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEsCrud {

    @Autowired
    private CrudOfElasticsearch crudOfElasticsearch;

    @Test
    public void testSave() {
        Product product = new Product();
        product.setId(1L);
        product.setName("华为手机");
        product.setPrice(3999.0);
        product.setBrand("华为");
        product.setDescription("小米手机，性价比真高");
        product.setProductionDate(System.currentTimeMillis());
        crudOfElasticsearch.save(product);
    }

    @Test
    public void testFindAll() {
        crudOfElasticsearch.findAll();
    }

    @Test
    public void testFindById() {
        crudOfElasticsearch.findById("1");
    }

    @Test
    public void testDelete() {
        crudOfElasticsearch.delete("1");
    }

    /*
    布尔查询
     */
    @Test
    public void testBoolQuery() {
        crudOfElasticsearch.booleanQuery();
    }
}
