package com.dimoybiyca.svitkeramicu.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Map;

@Entity
@Table(name = "products")
@Getter @Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "units_in_stock", nullable = false)
    private double unitsInStock;

    @Column(name = "unit_type", nullable = false)
    private String unitType;

    @Min(value = 0)
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "image_url", columnDefinition = "default 'empty.svg'")
    private String imageUrl;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "country")
    private String country;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Map<String, String> properties;
}
