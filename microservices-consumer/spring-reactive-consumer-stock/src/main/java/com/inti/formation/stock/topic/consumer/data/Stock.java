package com.inti.formation.stock.topic.consumer.data;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
// @Document(indexName = "", type = "")
public class Stock {
    private String key;
    private Long idStock;
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private Date date;
}
