package cn.kuoyio.common.domain.result;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ResponseTest {
    @Test
    void should_return_success_response_without_data() {
        Response<Object> response = Response.success();

        assertThat(response).isNotNull();
        assertThat(response.getData()).isNull();
        assertThat(response.getMessage()).isNull();
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
    }

    @Test
    void should_return_success_response_with_data() {
        String data = "test";
        Response<String> response = Response.success(data);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getMessage()).isNull();
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
    }

    @Test
    void should_return_success_response_with_data_and_message() {
        String data = "test";
        String message = "Operation completed successfully";
        Response<String> response = Response.success(data, message);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getMessage()).isEqualTo(message);
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
    }

    @Test
    void should_return_error_response_with_code_and_message() {
        String errorCode = "E001";
        String errorMessage = "Invalid input";
        Response<Object> response = Response.error(errorCode, errorMessage);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isNull();
        assertThat(response.getMessage()).isEqualTo(errorMessage);
        assertThat(response.getCode()).isEqualTo(errorCode);
    }

    @Test
    void should_return_error_response_with_code_message_and_data() {
        String errorCode = "E002";
        String errorMessage = "Validation failed";
        String errorData = "Field 'name' cannot be empty";
        Response<String> response = Response.error(errorCode, errorMessage, errorData);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isEqualTo(errorData);
        assertThat(response.getMessage()).isEqualTo(errorMessage);
        assertThat(response.getCode()).isEqualTo(errorCode);
    }

    @Test
    void should_handle_null_values_properly() {
        Response<Object> response = Response.error(null, null);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isNull();
        assertThat(response.getMessage()).isNull();
        assertThat(response.getCode()).isNull();
    }
}