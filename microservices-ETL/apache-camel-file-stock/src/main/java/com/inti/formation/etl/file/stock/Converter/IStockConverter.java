package com.inti.formation.etl.file.stock.Converter;

import com.inti.formation.etl.file.stock.model.file.StockeInputFile;
import com.inti.formation.etl.file.stock.model.mongodb.StockMongodbType;

public interface IStockConverter {
    public StockMongodbType converter(final StockeInputFile stock);
}
