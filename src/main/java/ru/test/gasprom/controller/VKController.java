package ru.test.gasprom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.test.gasprom.dto.request.VKGroupRequest;
import ru.test.gasprom.dto.response.VKGroupResponse;
import ru.test.gasprom.service.VkService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class VKController {

    private final VkService vkService;

    @PostMapping(path = "/vk")
    @Operation(summary = "Входит ли пользователь в запрошенную группу")
    @Parameters({
            @Parameter(name = "user_id", description = "Идентификатор пользователя в VK", required = true),
            @Parameter(name = "group_id", description = "Идентификатор группы в VK", required = true)
    }) //http://localhost:8080/swagger-ui/index.html swagger
    public VKGroupResponse getVKIsMemberGroup(@RequestHeader("vk_service_token") String token,
                                              @Valid @RequestBody VKGroupRequest request) {

        return vkService.getVKClientIsInGroup(token, request);

    }
}
