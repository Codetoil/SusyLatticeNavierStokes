package io.github.codetoil.math.api.registry;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.github.codetoil.math.api.general.GroupRepresentation;
import io.github.codetoil.math.api.specific.GeneralLinear;

import java.util.concurrent.ExecutionException;

public class GroupRepresentations {
    private static final Cache<GeneralLinear, GroupRepresentation<GeneralLinear>> GENERAL_LINEAR_REPRESENTATIONS
            = CacheBuilder.newBuilder().build();

    public static GroupRepresentation<GeneralLinear> getGeneralLinearRepresentation(GeneralLinear group) {
        try {
            return GENERAL_LINEAR_REPRESENTATIONS.get(group, () ->
                    group.new GeneralLinearRepresentation());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
