package com.example.keycloakadminwrapperapi.command;

import com.example.keycloakadminwrapperapi.acl.KeycloakClient;
import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/realms")
public class RealmCommands {

    private final KeycloakClient keycloakClient;

    public RealmCommands(KeycloakClient keycloakClient) {
        this.keycloakClient = keycloakClient;
    }

    @PostMapping("/create")
    void createAccount(@RequestBody CreateRealmDto createRealmDto) {
        RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(createRealmDto.name);
        realmRepresentation.setDisplayName(createRealmDto.displayName);
        realmRepresentation.setEnabled(Boolean.TRUE);
        realmRepresentation.setBruteForceProtected(Boolean.TRUE);

        keycloakClient.get().realms().create(realmRepresentation);
    }

    private record CreateRealmDto(String name, String displayName) {
    }
}
