package br.com.extratora.twelvekingdoms.aggregator;

import br.com.extratora.twelvekingdoms.dto.request.SignupRequest;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class SignupRequestAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(
            ArgumentsAccessor argumentsAccessor,
            ParameterContext parameterContext
    ) throws ArgumentsAggregationException {
        return SignupRequest.builder()
                .username(argumentsAccessor.getString(1))
                .password(argumentsAccessor.getString(2))
                .firstName(argumentsAccessor.getString(3))
                .lastName(argumentsAccessor.getString(4))
                .email(argumentsAccessor.getString(5))
                .build();
    }
}
