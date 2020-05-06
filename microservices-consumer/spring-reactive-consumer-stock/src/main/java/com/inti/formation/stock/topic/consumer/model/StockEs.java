package com.inti.formation.stock.topic.consumer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Document(indexName = "shop", type = "_doc", createIndex = false)
public class StockEs {

    @Id
    private Long idStock;
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private Date creationDate;
    private Date deleteDate;
}