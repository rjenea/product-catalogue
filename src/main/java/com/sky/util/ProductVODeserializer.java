package com.sky.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.sky.catalogue.product.vo.ProductVO;

import java.io.IOException;

public final class ProductVODeserializer extends JsonDeserializer<ProductVO> {

    @SuppressWarnings("DuplicateThrows")
    @Override
    public ProductVO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode node = objectCodec.readTree(jsonParser);
        return ProductVO.builder()
                .setCategory(node.get("category").asText())
                .setName(node.get("name").asText())
                .setLocationId(deserializeLocationId(node))
                .build();
    }

    private String deserializeLocationId(JsonNode node) {
        String locationId = node.get("locationId").asText();
        return "null".equals(locationId) ? null : locationId;
    }
}
