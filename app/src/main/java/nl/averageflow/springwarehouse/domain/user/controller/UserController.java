package nl.averageflow.springwarehouse.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.user.dto.UpdateUserRequest;
import nl.averageflow.springwarehouse.domain.user.dto.UserResponseItem;
import nl.averageflow.springwarehouse.domain.user.service.UserService;
import nl.averageflow.springwarehouse.domain.user.service.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;

    public UserController(final UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    @Operation(summary = "Returns users",
            description = "Returns the requested page of users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public Page<UserResponseItem> getUsers(final Pageable pageable) {
        return this.userService.getUsers(pageable);
    }

    @GetMapping("/api/users/{uid}")
    @Operation(summary = "Returns a single user",
            description = "Returns a single user by uuid")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = CategoryResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public UserResponseItem getUser(@PathVariable final UUID uid) {
        return this.userService.getUserByUid(uid);
    }

    @PatchMapping("/api/users/update-role")
    @Operation(summary = "Update a user's role",
            description = "Update a user's role. The requester has to have admin privileges.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<String> updateRole(@RequestBody final UpdateUserRequest request) {
        return this.userService.updateUserRole(request);
    }

    @DeleteMapping("/api/users/{uid}")
    @Operation(summary = "Deletes a single user",
            description = "Deletes a single user by uuid")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = CategoryResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<String> deleteUser(@PathVariable @NotNull final UUID uid) {
        return this.userService.deleteUserByUid(uid);
    }
}
