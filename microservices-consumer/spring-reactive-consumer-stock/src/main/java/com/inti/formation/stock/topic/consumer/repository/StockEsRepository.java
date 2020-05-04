package com.inti.formation.stock.topic.consumer.repository;

import com.inti.formation.stock.topic.consumer.model.StockEs;
import com.inti.formation.stock.topic.consumer.model.StockTopic;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StockEsRepository {
    public static StockEs cloneStockTopic(StockTopic stock) throws ParseException {
        StockEs res = new StockEs();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ");
        res.setIdStock(stock.getIdStock());
        res.setQuantite(stock.getQuantite());
        res.setMagasin(stock.getMagasin());
        res.setActive(stock.getActive());
        res.setIdProduit(stock.getIdProduit());
        res.setCreationDate(format.parse(stock.getCreationDate()));
        res.setDeleteDate(format.parse(stock.getDeleteDate()));
        return res;
    }

}
