package com.company.fieldmatrixfactory;

import com.company.Board;
import com.company.Matrix;
import com.company.field.AbstractField;

public interface IFieldMatrixFactory {
    Matrix<AbstractField> create(Board board);
}
