package com.example.mindvalleychallenge.data.remote;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ApiResponseTest {

    @Test
    public void exception() {
        Exception exception = new Exception("");
        ApiResponse<String> apiResponse = new ApiResponse<>();
        assertThat(apiResponse.body, nullValue());
        assertEquals(apiResponse.errorMessage, null);

    }
}
