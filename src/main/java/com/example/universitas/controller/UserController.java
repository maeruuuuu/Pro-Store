package com.example.universitas.controller;

import com.example.universitas.model.dto.UserDto;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    //@Value("${keycloak.auth-server-url}")
    private String authServerUrl = "http://localhost:8090/auth";
    //@Value("${keycloak.realm)")
    private String realm = "demo";
    //@Value("${keycloak.resource}")
    private String clientId = "demo-app";
    //@Value("${keycloak.credentials.secret}")
    private String clientSecret = "b7ed008c-866f-422a-9fcd-2acf3dc6a50a";
    private String role = "user";

    @PostMapping(path = "/signup")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {

        Keycloak keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl)
                .grantType(OAuth2Constants.PASSWORD).realm("master").clientId("admin-cli")
                .username("admin").password("admin")
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();

        keycloak.tokenManager().getAccessToken();

        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userDto.getEmail());
        user.setFirstName(userDto.getFirstname());
        user.setLastName(user.getLastName());
        user.setEmail(userDto.getEmail());

        // Get Realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        Response response = usersResource.create(user);

        userDto.setStatusCode(response.getStatus());
        userDto.setStatus(response.getStatusInfo().toString());

        if (response.getStatus() == 201) {

            String userId = CreatedResponseUtil.getCreatedId(response);

            log.info("Created userId {}", userId);

            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(userDto.getPassword());

            UserResource userResource = usersResource.get(userId);

            // set password credential (yang problem)
            userResource.resetPassword(passwordCred);

            RoleRepresentation realmRoleUser = realmResource.roles().get(role).toRepresentation();

            userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser));
        }
        return ResponseEntity.ok(userDto);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<?> signin(@RequestBody UserDto userDto) {
        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", clientSecret);
        clientCredentials.put("grant_type","password");

        Configuration configuration =
                new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);

        AccessTokenResponse response =
                authzClient.obtainAccessToken(userDto.getEmail(), userDto.getPassword());

        return ResponseEntity.ok(response);
    }
}
