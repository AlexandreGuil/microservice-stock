package com.inti.formation.etl.file.stock.model.file;

import lombok.Data;

@Data
public class StockeInputFile {
    private Long id;
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private String creationDate;
}
