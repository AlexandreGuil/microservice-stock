package com.inti.formation.apache.camel.etl.file.stock.model.file;

import lombok.Data;

@Data
public class StockeInputFile {
    private Long idStock;
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private String creationDate;
}
