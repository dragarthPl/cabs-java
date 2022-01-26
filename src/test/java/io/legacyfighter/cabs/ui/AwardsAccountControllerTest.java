package io.legacyfighter.cabs.ui;

import io.legacyfighter.cabs.dto.AwardsAccountDTO;
import io.legacyfighter.cabs.entity.Client;
import io.legacyfighter.cabs.repository.ClientRepository;
import io.legacyfighter.cabs.service.AwardsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AwardsAccountControllerTest {
    @Autowired
    private AwardsService awardsService;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void registerClient() {
        //given
        Client client = new Client();
        clientRepository.save(client);

        //when
        awardsService.registerToProgram(client.getId());

        //then
        AwardsAccountDTO account = awardsService.findBy(client.getId());
        assertNotNull(account);
        assertEquals(client.getId(), account.getClient().getId());
        assertFalse(account.isActive());
    }

    @Test
    void activateClient() {
        //given
        Client client = new Client();
        clientRepository.save(client);
        awardsService.registerToProgram(client.getId());

        //when
        awardsService.activateAccount(client.getId());

        //then
        AwardsAccountDTO account = awardsService.findBy(client.getId());
        assertNotNull(account);
        assertEquals(client.getId(), account.getClient().getId());
        assertTrue(account.isActive());
    }

    @Test
    void deactivateClient() {
        //given
        Client client = new Client();
        clientRepository.save(client);
        awardsService.registerToProgram(client.getId());
        awardsService.activateAccount(client.getId());

        //when
        awardsService.deactivateAccount(client.getId());

        //then
        AwardsAccountDTO account = awardsService.findBy(client.getId());
        assertNotNull(account);
        assertEquals(client.getId(), account.getClient().getId());
        assertFalse(account.isActive());
    }
}