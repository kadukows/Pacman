package com.company.fieldmatrixfactory;

import com.company.Board;
import com.company.Matrix;
import com.company.field.AbstractField;

/**
 * Interface for factories of AbstractField Matrices
 */
public interface IFieldMatrixFactory {

    /**
     * Returns current filename of JSON.
     *
     * @return filename
     */
    String getFilename();

    /**
     * Creates Matrix of AbstractFields.
     *
     * @param board Board to which assign fields.
     * @return Matrix of AbstractFields
     */
    Matrix<AbstractField> create(Board board);
}
