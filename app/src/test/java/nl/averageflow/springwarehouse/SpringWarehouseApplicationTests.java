package nl.averageflow.springwarehouse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestConfigForMail.class)
class SpringWarehouseApplicationTests {

    @Test
    void contextLoads() {
    }

}
