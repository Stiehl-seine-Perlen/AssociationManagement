package de.thi.association.connector;

import de.benevolo.entities.finance.FinancialAccount;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@ApplicationScoped
@RegisterRestClient(configKey = "finance", baseUri = "http://localhost:8082")
public interface FinanceRestClient {

    @POST
    @Path("/account/")
    public void createFinancialAccount(FinancialAccount financialAccount);

}
