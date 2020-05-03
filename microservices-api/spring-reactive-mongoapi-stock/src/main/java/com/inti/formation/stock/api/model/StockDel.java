package com.inti.formation.stock.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockDel implements Serializable {
    private String key;
    private Long idStock;
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private String creationDate;
    private String deleteDate;
}
