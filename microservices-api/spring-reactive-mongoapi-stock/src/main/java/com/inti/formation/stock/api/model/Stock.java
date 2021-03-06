package com.inti.formation.stock.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection ="stock")
public class Stock implements Serializable {

    @Id
    private Long idStock;
    @Indexed(unique = false)
    private Long quantite;
    private String magasin;
    @Indexed(unique = false)
    private Boolean active;
    private Long idProduit;
    private String creationDate;
}
