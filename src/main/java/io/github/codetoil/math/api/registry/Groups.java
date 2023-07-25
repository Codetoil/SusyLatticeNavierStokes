package io.github.codetoil.math.api.registry;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.github.codetoil.math.api.specific.GeneralLinear;

import java.util.concurrent.ExecutionException;

public class Groups {
    private static final Cache<Integer, GeneralLinear> GENERAL_LINEAR_GROUPS = CacheBuilder.newBuilder().build();
    private static final Cache<Integer, GeneralLinear> COMPLEX_GENERAL_LINEAR_GROUPS
            = CacheBuilder.newBuilder().build();

    public static GeneralLinear getGeneralLinearGroup(int dimension) {
        try {
            return GENERAL_LINEAR_GROUPS.get(dimension, () -> new GeneralLinear(dimension, false));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static GeneralLinear getComplexGeneralLinearGroup(int dimension) {
        try {
            return COMPLEX_GENERAL_LINEAR_GROUPS.get(dimension, () -> new GeneralLinear(dimension, true));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static GeneralLinear getGeneralLinearGroup(int dimension, boolean isComplex) {
        return isComplex ? getComplexGeneralLinearGroup(dimension) : getGeneralLinearGroup(dimension);
    }
}
