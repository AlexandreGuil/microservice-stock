package com.inti.formation.stock.api.rest.exception;

public class StockParamException extends Throwable {
    public StockParamException(final String msg){super(msg);}
    public StockParamException(final String msg, final Throwable cause)
    {
        super(msg, cause);
    }
}
