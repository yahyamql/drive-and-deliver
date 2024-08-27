package com.carrefour.kata.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Setter
@Getter
@ToString
@Table
public class Customer implements Persistable<Long> {

    @Id
    private Long id;
    private String name;

    @Transient
    private List<Delivery> deliveries;

    @Transient
    @JsonIgnore
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
