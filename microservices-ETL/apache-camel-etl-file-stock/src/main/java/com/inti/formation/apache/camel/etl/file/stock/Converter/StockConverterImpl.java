package com.inti.formation.apache.camel.etl.file.stock.Converter;

import com.inti.formation.apache.camel.etl.file.stock.model.file.StockeInputFile;
import com.inti.formation.apache.camel.etl.file.stock.model.mongodb.StockMongodbType;

public class StockConverterImpl implements IStockConverter{
    @Override
    public StockMongodbType converter(StockeInputFile inputStock) {
        StockMongodbType outputStock = new StockMongodbType();
        outputStock.setIdStock(inputStock.getIdStock());
        outputStock.setQuantite(inputStock.getQuantite());
        outputStock.setMagasin(inputStock.getMagasin());
        outputStock.setActive(inputStock.getActive());
        outputStock.setIdProduit(inputStock.getIdProduit());
        outputStock.setCreationDate(inputStock.getCreationDate());
        return outputStock;
    }
}
