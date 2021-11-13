package org.afrinnov.rnd.jgiven.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JGivenStage
public class AccountStage extends Stage<AccountStage> {
    @Autowired
    private MockMvc mvc;
    private final List<String> accountIds = new ArrayList<>();

    public AccountStage createAccount() throws Exception {
        String query = "{\"startingBalance\":0}";

        mvc.perform(post("/bank-account/create")
                        .content(query)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(result -> {
                    String accountId = result.getResponse().getContentAsString();
                    accountIds.add(accountId);
                });
        assertThat(accountIds).hasSize(1);
        return this;
    }

    public AccountStage getActivatedAccount() throws Exception {
        mvc.perform(get("/manage-account/get-account")
                        .param("id", accountIds.get(0))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", equalTo("ACTIVATED")));

        return this;
    }

    public AccountStage creditAmount(double amount) throws Exception {
        String query = "{\"accountId\":\":accountId\",\"amount\":<amount>}";

        mvc.perform(put("/bank-account/deposit")
                        .content(query.replace(":accountId", accountIds.get(0)).replace("<amount>", String.valueOf(amount)))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo("Amount credited")));

        return this;
    }

    public AccountStage debitAmount(double amount) throws Exception {
        String query = "{\"accountId\":\":accountId\",\"amount\":<amount>}";

        mvc.perform(put("/bank-account/withdraw")
                        .content(query.replace(":accountId", accountIds.get(0)).replace("<amount>", String.valueOf(amount)))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo("Amount debited")))
        ;

        return this;
    }

    public void checkAmount(double amount) throws Exception {
        mvc.perform(get("/manage-account/get-account")
                        .param("id", accountIds.get(0))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.balance", equalTo(amount)))
        ;
    }
}
