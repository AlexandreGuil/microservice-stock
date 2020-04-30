package com.inti.formation.stock.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StockDel implements Serializable {
    private long idStock;
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private String creationDate;
    private String deleteDate;
}
