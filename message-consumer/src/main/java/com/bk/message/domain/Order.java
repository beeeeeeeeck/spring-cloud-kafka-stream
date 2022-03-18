package com.bk.message.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Order implements Serializable {

    private String id;
    private Long amount;
    private BigDecimal price;
    @Builder.Default
    private Boolean active = Boolean.TRUE;

}
