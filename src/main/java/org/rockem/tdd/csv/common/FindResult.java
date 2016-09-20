package org.rockem.tdd.csv.common;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class FindResult {

    private int row;
    private int column;
    private String value;

}
