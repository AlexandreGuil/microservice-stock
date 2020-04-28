package com.inti.formation.stock.api.rest.exception;

public class StockModelNotFindInApiStockMongoDbCollectionException extends Throwable {
    public StockModelNotFindInApiStockMongoDbCollectionException(final String msg) {super(msg);}
    public StockModelNotFindInApiStockMongoDbCollectionException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
