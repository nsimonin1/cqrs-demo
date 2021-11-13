package org.afrinnov.rnd.query.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Account {
    @Id
    private String id;
    private BigDecimal balance;
    private String status;
}
