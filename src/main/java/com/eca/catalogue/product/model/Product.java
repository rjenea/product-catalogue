package com.eca.catalogue.product.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Product {

    @Id
    private final String id;

    @NotNull
    private final String name;

    @NotNull
    private final String category;

    @Nullable
    private final String locationId;

    public Product(String id, String name, String category, @Nullable String locationId) {
        checkNotNull(category,"Category is mandatory");
        checkNotNull(name,"Name is mandatory");
        this.id = id;
        this.name = name;
        this.category = category;
        this.locationId = locationId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Nullable
    public String getLocationId() {
        return locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product that = (Product) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.name, that.name) &&
                Objects.equal(this.category, that.category) &&
                Objects.equal(this.locationId, that.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, category, locationId);
    }

    public static final class Builder {

        private String name;
        private String category;
        private String locationId;

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setLocationId(String locationId) {
            this.locationId = locationId;
            return this;
        }

        public Product build() {
            return new Product(null, name, category, locationId);
        }
    }

}
