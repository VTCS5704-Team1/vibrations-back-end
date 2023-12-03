package com.vibrations.vibrationsapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vibrations.vibrationsapi.dto.SignInRequestDto;
import com.vibrations.vibrationsapi.dto.SignUpRequestDto;
import com.vibrations.vibrationsapi.dto.SignUpResponseDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This Test file has the bulk of the Unit Test (because most functionality is handled
 * by the User Controller)
 * Creates a Unit Test account in Cognito, whose credentials will be used throughout the remaining Unit Tests
 * Tests must be run in the specified order
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String baseUrl = "/api/users";
    private final String email = "unit@test.com";
    private final String password = "TestWorld123!";

    /**
     * Tests registering a fake User to Cognito.
     * This test will register a user account to Cognito,
     * which will be used by the other tests (delete will run last)
     */
    /**@Test
    @Order(1)
    void testRegister() throws Exception {
        // Create fake request data
        SignUpRequestDto signUpDto = new SignUpRequestDto();
        signUpDto.setEmail(email);
        signUpDto.setPassword(password);
        signUpDto.setBirthdate("2000-04-17");
        signUpDto.setGender("Male");
        signUpDto.setFirstName("Unit");
        signUpDto.setLastName("Test");
        signUpDto.setPhoneNumber("+1234567890");

        String json = new ObjectMapper().writeValueAsString(signUpDto);

        RequestBuilder request = MockMvcRequestBuilders.post(baseUrl + "/register")
                .contentType("application/json")
                .accept("application/json")
                .content(json);

        SignUpResponseDto responseDto = new SignUpResponseDto();
        responseDto.setStatusCode(200);
        responseDto.setStatusMessage("Successfully created user account.");

        this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("{\"statusCode\":" + responseDto.getStatusCode() +
                        ",\"statusMessage\":\"" + responseDto.getStatusMessage() + "\"}"));
    } */

    /**
     * Unit test for testing logging in via Cognito
     * This one will only test for the status code, because
     * there's a lot of pre-generated AWS JSON content that really can't be hardcoded
     */
    @Test
    @Order(2)
    void testLogin() throws Exception {
        SignInRequestDto requestDto = new SignInRequestDto();
        requestDto.setEmail(email);
        requestDto.setPassword(password);

        String jsonRequest = new ObjectMapper().writeValueAsString(requestDto);

        RequestBuilder request = MockMvcRequestBuilders.post(baseUrl + "/login")
                .contentType("application/json")
                .accept("application/json")
                .content(jsonRequest);

        this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk());
    }


    /**
     * Helper function for generating an access token for endpoints that require them.
     * @return An AWS-generated access token
     */
    private String generateAccessToken() {
        return null;
    }
}
