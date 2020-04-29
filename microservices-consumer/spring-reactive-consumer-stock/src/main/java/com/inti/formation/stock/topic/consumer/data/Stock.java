package com.inti.formation.stock.topic.consumer.data;

import lombok.Data;

import java.util.Date;

@Data
public class Stock {
    private String key;
    private Long idStock;
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private Date date;
}
