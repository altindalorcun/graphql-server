package dev.altindal.graphql_server.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphqlExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment environment) {
        if (ex instanceof UserNotFoundException) {
            return toGraphqlError(ex);
        } else if (ex instanceof Exception) {
            return toGraphqlError(ex);
        }
        return super.resolveToSingleError(ex, environment);
    }

    private GraphQLError toGraphqlError(Throwable throwable) {
        return GraphqlErrorBuilder.newError()
                .message(throwable.getMessage())
                .errorType(ErrorType.DataFetchingException)
                .build();
    }

}
