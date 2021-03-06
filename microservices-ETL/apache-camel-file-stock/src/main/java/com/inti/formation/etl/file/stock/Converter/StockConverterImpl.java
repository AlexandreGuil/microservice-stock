package com.inti.formation.etl.file.stock.Converter;


import com.inti.formation.etl.file.stock.model.file.StockeInputFile;
import com.inti.formation.etl.file.stock.model.mongodb.StockMongodbType;
import org.springframework.stereotype.Component;

@Component
public class StockConverterImpl implements IStockConverter{
    @Override
    public StockMongodbType converter(StockeInputFile inputStock) {
        StockMongodbType outputStock = new StockMongodbType();
        outputStock.setIdStock(inputStock.getId());
        outputStock.setQuantite(inputStock.getQuantite());
        outputStock.setMagasin(inputStock.getMagasin());
        outputStock.setActive(inputStock.getActive());
        outputStock.setIdProduit(inputStock.getIdProduit());
        outputStock.setCreationDate(inputStock.getCreationDate());
        return outputStock;
    }
}
