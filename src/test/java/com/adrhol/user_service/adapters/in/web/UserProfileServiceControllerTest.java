package com.adrhol.user_service.adapters.in.web;

import com.adrhol.user_service.MvcTestConfig;
import com.adrhol.user_service.application.domain.entity.ProfileOwner;
import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.ports.in.*;
import com.adrhol.user_service.utils.TestFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserProfileServiceController.class)
class UserProfileServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RegisterUserProfileUseCase registerUserProfileUseCase;
    @MockBean
    private DeactivateUserProfileUseCase deactivateUserUseCase;
    @MockBean
    private RetrieveActiveProfilesUseCase retrieveActiveProfilesUseCase;
    @MockBean
    private FindUserProfileUseCase findUserProfileUseCase;
    @MockBean
    private ProfileDetailsEditionUseCase profileDetailsEditionUseCase;
    private final String API_VERSION = "/v1";
    private final String ALL_ENDPOINT = API_VERSION + "/all";
    private final String PROFILE_ENDPOINT = API_VERSION + "/profile";
    private final String CREATE_ENDPOINT = API_VERSION + "/create";

    @Test
    void retrievesAllProfilesWhenGetAllActiveUsers() throws Exception {
//        given
        List<UserProfile> profiles = TestFactory.generateUserList(5);
        given(retrieveActiveProfilesUseCase.getActiveUsers()).willReturn(profiles);
//        when/then
        mockMvc.perform(MockMvcRequestBuilders.get(ALL_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void retrievesCorrectProfileWhenGetAllActiveUsers() throws Exception {
//        given
        List<UserProfile> profiles = TestFactory.generateUserList(2);
        given(retrieveActiveProfilesUseCase.getActiveUsers()).willReturn(profiles);
//        when/then
        mockMvc.perform(MockMvcRequestBuilders.get(ALL_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(profiles.get(0).getId()))
                .andExpect(jsonPath("$[0].accountId").value(profiles.get(0).getAccountId()))
                .andExpect(jsonPath("$[0].profileOwner.firstName").value(profiles.get(0).getProfileOwner().firstName()))
                .andExpect(jsonPath("$[0].profileOwner.lastName").value(profiles.get(0).getProfileOwner().lastName()))
                .andExpect(jsonPath("$[0].profileType").value(profiles.get(0).getProfileType().toString()));
    }
    @Test
    void retrievesEmptyListWhenGetAllActiveUsers() throws Exception {
//        given
        List<UserProfile> profiles = TestFactory.generateUserList(0);
        given(retrieveActiveProfilesUseCase.getActiveUsers()).willReturn(profiles);
//        when
        mockMvc.perform(MockMvcRequestBuilders.get(ALL_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
    @Test
    void findsUserWithExpectedIdWhenGetUserProfile() throws Exception {
//        given
        UserProfile profile = TestFactory.generateRegularUserProfile();
        given(findUserProfileUseCase.findProfileById(profile.getId())).willReturn(profile);
        String requestPath = PROFILE_ENDPOINT + "/" + profile.getId();
//        when/then
        mockMvc.perform(MockMvcRequestBuilders.get(requestPath))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(profile.getId()))
                .andExpect(jsonPath("$.accountId").value(profile.getAccountId()))
                .andExpect(jsonPath("$.profileOwner.firstName").value(profile.getProfileOwner().firstName()))
                .andExpect(jsonPath("$.profileOwner.lastName").value(profile.getProfileOwner().lastName()))
                .andExpect(jsonPath("$.profileType").value(profile.getProfileType().toString()));
    }
    @Test
    void resultsInEmptyResponseIfWrongIdWhenGetUserProfile() throws Exception {
//        given
        UserProfile profile = TestFactory.generateRegularUserProfile();
        given(findUserProfileUseCase.findProfileById(profile.getId())).willReturn(profile);
        String incorrectId = "/testID";
        String requestPath = PROFILE_ENDPOINT + incorrectId;
//        when/then
        mockMvc.perform(MockMvcRequestBuilders.get(requestPath))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void returnsUserProfileWithCorrectIdWhenCreateUser() throws Exception {
//        given
        CreateUserCommand createUserCommand = new CreateUserCommand(TestFactory.generateRandomString(4),
                                                                    TestFactory.generateRandomString(5),
                                                                    TestFactory.generateRandomString(6));
        UserProfile createdProfile = TestFactory.createProfileFromCommand(createUserCommand);
        given(registerUserProfileUseCase.registerUser(createUserCommand)).willReturn(createdProfile);
//        when/then
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserCommand)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdProfile.getId()))
                .andExpect(jsonPath("$.accountId").value(createUserCommand.accountId()))
                .andExpect(jsonPath("$.profileOwner.firstName").value(createUserCommand.firstName()))
                .andExpect(jsonPath("$.profileOwner.lastName").value(createUserCommand.lastName()));
    }
    @Test
    void returns400WhenCommandHasNullsWhenCreateUser() throws Exception {
//        given
        CreateUserCommand createUserCommand = new CreateUserCommand(null,
                null,
                TestFactory.generateRandomString(6));
        UserProfile createdProfile = TestFactory.createProfileFromCommand(createUserCommand);
        given(registerUserProfileUseCase.registerUser(createUserCommand)).willReturn(createdProfile);
//        when/then
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserCommand)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findProfileByCriteria() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void promoteUserProfile() {
    }
}