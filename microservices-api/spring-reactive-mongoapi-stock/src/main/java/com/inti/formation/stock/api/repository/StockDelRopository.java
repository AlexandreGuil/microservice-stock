package com.inti.formation.stock.api.repository;

import com.inti.formation.stock.api.model.Stock;
import com.inti.formation.stock.api.model.StockDel;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class StockDelRopository {

    public StockDel getStockDelByStock(Stock stock) throws ParseException {
        StockDel res = new StockDel();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

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