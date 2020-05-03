package com.inti.formation.stock.topic.consumer.data;

import lombok.Data;

@Data
// @Document(indexName = "", type = "")
public class Stock {
    private String key;
    private long idStock;
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private String creationDate;
    private String deleteDate;
}