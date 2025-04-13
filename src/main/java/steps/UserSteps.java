package steps;

import config.Config;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.user.UserRequest;
import models.user.UserResponse;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UserSteps {

    private UserRequest userRequest;
    private Response response;

    @BeforeAll
    public static void setup() throws IOException {
        Config.setup();
    }

    @Step("cоздаем тело запроса для пользователя с именем {name} и должностью {job}")
    @Given("^cоздаем тело запроса для пользователя с именем \"([^\"]*)\" и должностью \"([^\"]*)\"$")
    public UserRequest createUserBody(String name, String job) {
        userRequest = UserRequest.builder()
                .name(name)
                .job(job)
                .build();
        return userRequest;
    }

    @Step("отправляем POST-запрос на {path} для создания пользователя")
    @When("^отправляем POST-запрос на \"([^\"]*)\" для создания пользователя$")
    public Response createUser(String path) {
        response = given()
                .body(userRequest)
                .post(path);
        return response;
    }

    @Step("проверяем статус ответа {status}")
    @Then("^проверяем статус ответа (\\d+)$")
    public void checkResponseStatus(int status) {
        assertThat(response.then().extract().statusCode()).isEqualTo(status);
    }

    @Step("сравниваем имя в ответе c именем {name}")
    @Then("^сравниваем имя в ответе c именем \"([^\"]*)\"$")
    public void checkName(String name) {
        assertThat(response.as(UserResponse.class).getName()).isEqualTo(name);
    }

    @Step("отправляем DELETE-запрос на {path} для удаление пользователя")
    @When("^отправляем DELETE-запрос на \"([^\"]*)\" для удаление пользователя$")
    public Response deleteUser(String path) {
        response = given()
                .when()
                .delete(path + response.as(UserResponse.class).getId());
        return response;
    }

    @Step("отправляем GET-запрос на {path} с id {userId} для получения несуществующего пользователя")
    @When("^отправляем GET-запрос на \"([^\"]*)\" с id (\\d+) для получения несуществующего пользователя$")
    public Response getUserNotFound(String path, int userId) {
        response = given()
                .when()
                .get(path + userId);
        return response;
    }
}
