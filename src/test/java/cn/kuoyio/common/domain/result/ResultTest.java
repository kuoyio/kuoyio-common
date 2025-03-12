package cn.kuoyio.common.domain.result;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    void return_success_result_when_no_data_provided() {
        Result<Object> result = Result.success();
        assertThat(result.getCode()).isEqualTo(ResultCode.SUCCESS.getCode());
        assertThat(result.getMessage()).isNull();
        assertThat(result.getData()).isNull();
    }

    @Test
    void return_success_result_with_data_when_data_provided() {
        String testData = "test data";
        Result<String> result = Result.success(testData);
        assertThat(result.getCode()).isEqualTo(ResultCode.SUCCESS.getCode());
        assertThat(result.getMessage()).isNull();
        assertThat(result.getData()).isEqualTo(testData);
    }

    @ParameterizedTest
    @MethodSource("provide_error_parameters")
    void return_error_result_when_code_and_message_provided(String code, String message) {
        Result<Object> result = Result.error(code, message);
        assertThat(result.getCode()).isEqualTo(code);
        assertThat(result.getMessage()).isEqualTo(message);
        assertThat(result.getData()).isNull();
    }

    private static Stream<Arguments> provide_error_parameters() {
        return Stream.of(
            Arguments.of("E001", "Error message"),
            Arguments.of("E002", "Another error message"),
            Arguments.of("E003", "")
        );
    }
}