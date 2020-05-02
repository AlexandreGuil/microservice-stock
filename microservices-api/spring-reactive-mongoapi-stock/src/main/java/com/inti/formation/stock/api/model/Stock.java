package com.inti.formation.stock.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection ="stock")
@CompoundIndexes(
        @CompoundIndex(name = "stock",
                def = "{active: 1, date: 1}",
                unique = false )
)
public class Stock implements Serializable {

    @Id
    private long idStock; // private ObjectId idStock; // _id : mongodb // BigInteger
    @Indexed(unique = false)
    private Long quantite;
    private String magasin;
    private Boolean active;
    private Long idProduit;
    private Date creationDate; // format yyyy-MM-ddTHH:ss.SSSZZZZ
}
