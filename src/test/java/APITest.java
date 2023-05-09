import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITest {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void deveRotornarTarefas(){
        RestAssured.given()
                .when()
                    .get("/todo")
                .then()
                    .statusCode(200);
    }

    @Test
    public void deveAdcionarTarefaComSucesso(){
        RestAssured.given()
                    .body("{\"task\": \"Test via API\", \"dueDate\": \"2030-12-30\"}")
                .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .statusCode(201);
    }

    @Test
    public void naoDeveAdcionarTarefaComSucesso(){
        RestAssured.given()
                    .body("{\"task\": \"Test via API\", \"dueDate\": \"2020-12-20\"}")
                .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .log().all()
                    .statusCode(400);
//                    .body("message", CoreMatchers.is("Due date must not be in past"));
    }


}


