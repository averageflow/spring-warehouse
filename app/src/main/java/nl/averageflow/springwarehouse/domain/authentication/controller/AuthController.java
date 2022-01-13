package nl.averageflow.springwarehouse.domain.authentication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nl.averageflow.springwarehouse.domain.authentication.dto.LoginRequest;
import nl.averageflow.springwarehouse.domain.authentication.dto.RegisterRequest;
import nl.averageflow.springwarehouse.domain.authentication.service.AuthService;
import nl.averageflow.springwarehouse.domain.authentication.service.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication", description = "Authentication API")
public final class AuthController {

    private final AuthService authService;

    public AuthController(final AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/api/auth/authenticate")
    @Operation(summary = "Authenticate user",
            description = "The service permits authenticating a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<String> authenticateUser(@RequestBody final LoginRequest loginRequest) {
        return this.authService.authenticateUser(loginRequest.email(), loginRequest.password());
    }

    @PostMapping("/api/auth/register")
    @Operation(summary = "Register user",
            description = "The service permits registering a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<String> registerUser(@RequestBody final RegisterRequest registerRequest) {
        return this.authService.registerUser(registerRequest);
    }
}