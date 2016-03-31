package com.eca.catalogue.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;
import com.eca.util.ProductVODeserializer;
import com.eca.util.ProductVOSerializer;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@AutoValue
@JsonDeserialize(builder = AutoValue_ProductVO.Builder.class,using = ProductVODeserializer.class)
@JsonSerialize(using = ProductVOSerializer.class)
public abstract class ProductVO {

    @NotNull
    @JsonProperty("name")
    public abstract String getName();

    @NotNull
    @JsonProperty("category")
    public abstract String getCategory();

    @Nullable
    @JsonProperty("locationId")
    @JsonDeserialize(contentAs = String.class)
    public abstract Optional<String> getLocationId();

    protected ProductVO() {
    }

    public static Builder builder() {
        return new AutoValue_ProductVO.Builder()
                .setLocationId(Optional.<String>absent());
    }

    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("name")
        public abstract Builder setName(String name);

        @JsonProperty("category")
        public abstract Builder setCategory(String category);

        @Nullable
        abstract Builder setLocationId(Optional<String> locationId);

        @Nullable
        @JsonProperty("locationId")
        public Builder setLocationId(String locationId) {
            return setLocationId(Optional.fromNullable(locationId));
        }

        public abstract ProductVO build();
    }
}
