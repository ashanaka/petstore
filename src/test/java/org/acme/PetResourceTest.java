package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;


@QuarkusTest
public class PetResourceTest {

    @Test
    public void testPetEndpoint() {
        given()
                .when().get("/v1/pets")
                .then()
                .statusCode(200);
//             .body(hasItem(
// 		            allOf(
//    		                hasEntry("pet_id", "1"),
//    		                hasEntry("pet_type", "Dog"),
//    		                hasEntry("pet_name", "Boola"),
//    		                hasEntry("pet_age", "3")
//    		            )
//    		      )
//    		 );
    }

    public void testPetsAddEndPointSuccess() {
        given()
                .header("Content-Type", "application/json")
                .body("{\r\n    \"petId\":\"3\",\n    \"petName\":\"Dane\",\r\n   \"petAge\":4,\r\n   \"petType\":\"Bird\"\r\n}")
                .when().post("v1/pets")
                .then()
                .assertThat()
                .statusCode(201)
                .body("petId", equalTo(3))
                .body("petAge", equalTo(4))
                .body("petName", equalTo("Dane"))
                .body("petType", notNullValue());
    }

    @Test
    public void testPetUpdateEndPoint() {
        given()
                .header("Content-Type", "application/json")
                .pathParam("petId", 3)
                .body("{\r\n    \"petId\":\"3\",\n    \"petName\":\"Woofy\",\r\n   \"petAge\":4,\r\n   \"petType\":\"Bird\"\r\n}")
                .when().patch("/v1/pets/{petId}")
                .then()
                .statusCode(201)
                .body("petId", equalTo(1))
                .body("petAge", notNullValue())
                .body("petName", equalTo("Woofy"))
                .body("petType", notNullValue());
    }

    @Test
    public void testPetDeleteEndPoint() {
        given()
                .header("Content-Type", "application/json")
                .pathParam("petId", 1)
                .when().delete("/v1/pets/{petId}")
                .then()
                .statusCode(204)
                .body("successful", equalTo(true));
    }


}