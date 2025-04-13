package steps;

import config.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.login.LoginRequest;
import models.login.LoginResponse;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends Config {

    private LoginRequest loginRequest;
    private Response response;

    @BeforeAll
    public static void setup() throws IOException {
        Config.setup();
    }

    @Step("создаем тело запроса с email {email} и password {password}")
    @Given("^создаем тело запроса с email \"([^\"]*)\" и password \"([^\"]*)\"$")
    public LoginRequest createLoginRequest(String email, String password) {
        loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
        return loginRequest;
    }

    @Step("отправляем POST-запрос на {path} для авторизации")
    @When("^отправляем POST-запрос на \"([^\"]*)\" для авторизации$")
    public Response login(String path) {
        response = given()
                .body(loginRequest)
                .post(path);
        return response;
    }

    @Step("проверяем статус код {status}")
    @Then("^проверяем статус код (\\d+)$")
    public void validateResponseStatus(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @Step("проверяем что токен равен {token}")
    @Then("^проверяем что токен равен \"([^\"]*)\"$")
    public void validateToken(String token) {
        assertThat(response.as(LoginResponse.class).getToken()).isEqualTo(token);
    }
}
