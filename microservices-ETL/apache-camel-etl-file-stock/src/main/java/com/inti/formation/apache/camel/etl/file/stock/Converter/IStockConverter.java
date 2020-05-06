package com.inti.formation.apache.camel.etl.file.stock.Converter;

import com.inti.formation.apache.camel.etl.file.stock.model.file.StockeInputFile;
import com.inti.formation.apache.camel.etl.file.stock.model.mongodb.StockMongodbType;

public interface IStockConverter {
    public StockMongodbType converter(final StockeInputFile stock);
}
