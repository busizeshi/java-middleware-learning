package cn.jwd.elasticsearch.service;

import cn.jwd.elasticsearch.entity.Product;

public interface CrudOfElasticsearch {

    void save(Product product);

    void delete(String id);

    void findAll();

    void findById(String id);

    void booleanQuery();
}
