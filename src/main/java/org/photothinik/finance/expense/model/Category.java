package org.photothinik.finance.expense.model;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(generator = "stock_order_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "stock_order_seq",
            sequenceName = "stock_order_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
