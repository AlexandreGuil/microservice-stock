package com.inti.formation.stock.topic.consumer.model;

import lombok.Data;

@Data
public class StockTopic {

    private String key;
    private Long idStock;
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private String creationDate;
    private String deleteDate;
}