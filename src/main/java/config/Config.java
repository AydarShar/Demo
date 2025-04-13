package config;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Config {

    public static void setup() throws IOException {
        RestAssured.defaultParser = Parser.JSON;
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        RestAssured.requestSpecification = given()
                .baseUri(System.getProperty("api"))
                .contentType("application/json");
    }
}
