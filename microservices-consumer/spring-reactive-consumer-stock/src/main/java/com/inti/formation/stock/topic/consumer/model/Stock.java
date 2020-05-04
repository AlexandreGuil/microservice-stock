package com.inti.formation.stock.topic.consumer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "shop", type = "stock")
public class Stock {

    @Id
    private Long idStock;
    private String key;
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private String creationDate;
    private String deleteDate;
}