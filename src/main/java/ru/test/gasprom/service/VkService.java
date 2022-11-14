package ru.test.gasprom.service;

import org.springframework.stereotype.Service;
import ru.test.gasprom.dto.request.VKGroupRequest;
import ru.test.gasprom.dto.response.VKGroupResponse;


public interface VkService {

    VKGroupResponse getVKClientIsInGroup(final String token, VKGroupRequest request);
}
