package com.example.exportexcell.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CellConfig {
    private int columnIndex;

    private String fieldName;
}
