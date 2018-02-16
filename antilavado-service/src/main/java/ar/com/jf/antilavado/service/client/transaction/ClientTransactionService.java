package ar.com.jf.antilavado.service.client.transaction;

/**
 * ClientTransactionService.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 16/03/2016.
 */
public interface ClientTransactionService {

    void importTransactions() throws Exception;

    void jobTransactions();
}
