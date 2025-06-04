package springProject.Expense_Tracker.Service.UserServicesTest;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArguments implements ArgumentsProvider{

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
//                Arguments.of("mohit"),
                Arguments.of("sunita")
//                Arguments.of("another")
        );
    }
}
