package com.sky.util;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.base.Optional;
import com.sky.catalogue.product.vo.ProductVO;

import java.io.IOException;

public final class ProductVOSerializer extends JsonSerializer<ProductVO> {

    @Override
    public void serialize(ProductVO productVO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", productVO.getName());
        jsonGenerator.writeStringField("category", productVO.getCategory());
        jsonGenerator.writeStringField("locationId", productVO.getLocationId().or("null"));
        jsonGenerator.writeEndObject();
    }
}
