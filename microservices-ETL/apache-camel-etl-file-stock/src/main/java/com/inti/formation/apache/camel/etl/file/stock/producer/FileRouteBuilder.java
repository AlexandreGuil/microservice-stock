package com.inti.formation.apache.camel.etl.file.stock.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inti.formation.apache.camel.etl.file.stock.Converter.IStockConverter;
import com.inti.formation.apache.camel.etl.file.stock.model.file.StockeInputFile;
import com.inti.formation.apache.camel.etl.file.stock.model.mongodb.StockMongodbType;
import com.inti.formation.apache.camel.etl.file.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FileRouteBuilder extends RouteBuilder {

    @Autowired
    private IStockConverter stockConverter;

    @Autowired
    private IStockService serv;

    @Override
    public void configure() throws Exception {
        from("file://{{in.directory}}" +
                "?include={{in.file.fileFilter}} " +
                "&preMove=.tmp/ " +
                "&move=.done/" +
                "${date:now:yyy-MM-dd}" +
                "${file:name}")
                .routeId("process_file")
                .setHeader("uuid")
                .constant(UUID.randomUUID().toString())
                .process(pro -> {
                    log.info("file : " + pro.getIn().getHeader("uuid", String.class) + " from " + pro.getIn().getHeader("CamelFileName") + "will pushed in mongodb");
                })
                .process(file -> {
                    final String fileName = file.getIn().getHeader("CamelFileNameOnly", String.class);
                })
                .from("direct:init_file")
                .routeId("init_file")
                .removeHeaders("CamelFile*", "CamelFileNameOnly")
                .split()
                .jsonpath("$[*]")
                .streaming()
                .to("direct:process_file_processing");

        from("direct:process_file_processing")
                .routeId("process_file_to_mongo")
                .marshal()
                .json(JsonLibrary.Jackson)
                .convertBodyTo(String.class)
                .process( data -> {
                    final String OBJECT_BODY = data.getIn().getBody(String.class);
                    StockeInputFile stockFile = new ObjectMapper().readValue(OBJECT_BODY, StockeInputFile.class);
                    StockMongodbType stock = serv.save(stockConverter.converter(stockFile));
                    log.info(" Stock : " + stock.toString());
                }).end();
    }
}
