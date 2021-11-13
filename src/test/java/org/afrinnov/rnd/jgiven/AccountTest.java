package org.afrinnov.rnd.jgiven;

import com.tngtech.jgiven.integration.spring.junit5.SimpleSpringScenarioTest;
import com.tngtech.jgiven.junit5.JGivenExtension;
import org.afrinnov.rnd.jgiven.stage.AccountStage;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(JGivenExtension.class)
public class AccountTest extends SimpleSpringScenarioTest<AccountStage> {

    @Test
    void createAccount() throws Exception {
        int creditAccount = RandomUtils.nextInt(10, 250);
        int withdrawAccount = RandomUtils.nextInt(10, creditAccount);
        given().createAccount()
                .then()
                .getActivatedAccount()
                .when()
                .creditAmount(creditAccount)
                .and()
                .when()
                .debitAmount(withdrawAccount)
                .then()
                .checkAmount(withdrawAccount - creditAccount);
    }
}
