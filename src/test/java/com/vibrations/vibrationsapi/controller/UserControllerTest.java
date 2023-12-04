package com.vibrations.vibrationsapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vibrations.vibrationsapi.dto.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This Test file has the bulk of the Unit Tests (because most functionality is handled
 * by the User Controller)
 * Creates a Unit Test account in Cognito, whose credentials will be used throughout the remaining Unit Tests
 * Tests must be run in the specified order
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
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
    @Test
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
    }

    /**
     * Unit test for testing logging in via Cognito
     * This test only checks the status code because it returns randomly generated
     * AWS tokens
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
     * Unit test for logging out via Cognito.
     * When logging out, the access token will no longer with Cognito requests
     * First logout request should return 200, next should contain 400
     * This endpoint returns an AWS JSON response, so only check for status codes
     */
    @Test
    @Order(3)
    void testLogout() throws Exception {
        String accessToken = "Bearer " + generateAccessToken();
        RequestBuilder request = MockMvcRequestBuilders.post(baseUrl + "/logout")
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", accessToken);

        // Should be 200
        this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk());
        // Should be 400
        this.mockMvc.perform(request).andDo(print()).andExpect(status().is4xxClientError());
    }

    /**
     * Tests changing a password in Cognito.
     * Changes the password, but will change back immediately
     * just for simplicity of testing.
     * Will only check status because the controller returns
     * an AWS Cognito JSON response
     */
    @Test
    @Order(4)
    void testChangePassword() throws Exception {
        String newPassword = "HelloWorld123!";
        String accessToken = "Bearer " + generateAccessToken();

        ChangePasswordDto requestDto = new ChangePasswordDto();
        requestDto.setEmail(email);
        requestDto.setOldPassword(password);
        requestDto.setNewPassword(newPassword);
        ObjectMapper om = new ObjectMapper();
        String jsonContent = om.writeValueAsString(requestDto);

        RequestBuilder request = MockMvcRequestBuilders.post(baseUrl + "/change/password")
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", accessToken)
                .content(jsonContent);

        this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk());

        // Now change it back:
        requestDto.setNewPassword(password);
        requestDto.setOldPassword(newPassword);
        jsonContent = om.writeValueAsString(requestDto);
        request = MockMvcRequestBuilders.post(baseUrl + "/change/password")
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", accessToken)
                .content(jsonContent);
        this.mockMvc.perform(request).andExpect(status().isOk());

    }

    /**
     * Tests the getALl Users endpoint
     * Only will check for status 200 in this case (since the users are variable)
     */
    @Test
    @Order(5)
    void testGetAllUsers() throws Exception {
        String accessToken = "Bearer " + generateAccessToken();

        this.mockMvc.perform(get(baseUrl + "/all")
                .header("Authorization", accessToken)
        ).andDo(print()).andExpect(status().isOk());
    }

    /**
     * Tests matchmaking algorithm call. It only checks status since matches are variable.
     */
    @Test
    @Order(6)
    void testMatch() throws Exception {
        String accessToken = "Bearer " + generateAccessToken();

        this.mockMvc.perform(get(baseUrl + "/match")
                .header("Authorization", accessToken)
                .param("email", email))
                .andDo(print()).andExpect(status().isOk());
    }


    /**
     * Tests deleting a User in cognito.
     * Only checks status because it returns AWS Json.
     * This should ALWAYS be the last test run
     */
    @Test
    @Order(7)
    void testDeleteUser() throws Exception {
        String accessToken = "Bearer " + generateAccessToken();
        DeleteAccountDto deleteDto = new DeleteAccountDto();
        deleteDto.setEmail(email);
        deleteDto.setPassword(password);

        String jsonContent = new ObjectMapper().writeValueAsString(deleteDto);

        RequestBuilder request = MockMvcRequestBuilders.post(baseUrl + "/delete")
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", accessToken)
                .content(jsonContent);

        this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk());

        // Ensure that this was deleted
        this.mockMvc.perform(request).andDo(print()).andExpect(status().is4xxClientError());

    }

    /**
     * Helper function for generating an access token for endpoints that require them.
     * @return An AWS-generated access token
     */
    private String generateAccessToken() throws Exception {
        SignInRequestDto requestDto = new SignInRequestDto();
        requestDto.setEmail(email);
        requestDto.setPassword(password);

        String jsonRequest = new ObjectMapper().writeValueAsString(requestDto);

        RequestBuilder request = MockMvcRequestBuilders.post(baseUrl + "/login")
                .contentType("application/json")
                .accept("application/json")
                .content(jsonRequest);

        MvcResult result = this.mockMvc.perform(request).andReturn();
        JsonNode json = new ObjectMapper().readTree(result.getResponse().getContentAsString());
        return json.get("accessToken").asText();
    }
}
