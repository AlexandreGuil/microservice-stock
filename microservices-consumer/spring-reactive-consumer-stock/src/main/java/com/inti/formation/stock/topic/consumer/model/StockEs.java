package com.inti.formation.stock.topic.consumer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "shop", type = "_doc", createIndex = false) //
public class StockEs {

    @Id
    private Long idStock;
    @Field(type = FieldType.Long)
    private Long quantite;
    @Field(type = FieldType.Keyword)
    private String magasin;
    @Field(type = FieldType.Boolean)
    private Boolean active;
    @Field(type = FieldType.Long)
    private Long idProduit;
    @Field(type = FieldType.Date)
    private Date creationDate;
    @Field(type = FieldType.Date)
    private Date deleteDate;
}