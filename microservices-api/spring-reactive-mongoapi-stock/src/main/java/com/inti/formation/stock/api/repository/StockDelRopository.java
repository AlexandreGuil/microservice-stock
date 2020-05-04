package com.inti.formation.stock.api.repository;

import com.inti.formation.stock.api.model.Stock;
import com.inti.formation.stock.api.model.StockDel;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Repository
public class StockDelRopository {

    public StockDel getStockDelByStock(Stock stock) {
        StockDel res = new StockDel();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ");

        res.setKey(UUID.randomUUID().toString());
        res.setIdStock(stock.getIdStock());
        res.setQuantite(stock.getQuantite());
        res.setMagasin(stock.getMagasin());
        res.setActive(stock.getActive());
        res.setIdProduit(stock.getIdProduit());
        res.setCreationDate(stock.getCreationDate().toString());
        res.setDeleteDate(format.format(new Date()));

        return res;

    }

}