package com.dimoybiyca.svitkeramicu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter @Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url", columnDefinition = "default 'empty.svg'")
    private String imageUrl;

    @Column(name = "root_id", nullable = false)
    private Long rootId;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
