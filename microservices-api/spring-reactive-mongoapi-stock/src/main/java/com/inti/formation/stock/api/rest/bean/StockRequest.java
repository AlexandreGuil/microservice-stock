package com.inti.formation.stock.api.rest.bean;

import com.inti.formation.stock.api.model.Stock;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class StockRequest extends Stock {
}