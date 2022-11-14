package ru.test.gasprom.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.test.gasprom.dto.request.VKGroupRequest;
import ru.test.gasprom.dto.response.VKGroupResponse;
import ru.test.gasprom.json.GroupMember;
import ru.test.gasprom.json.UserInformationList;
import ru.test.gasprom.service.VkService;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class VkServiceImpl implements VkService {

    @Cacheable(cacheNames = "UserCache", key = "#request")  //кеширование
    public VKGroupResponse getVKClientIsInGroup(final String token, VKGroupRequest request) {

        log.info("VKGroupRequest = " + request.toString());

        try {

            TransportClient transportClient = new HttpTransportClient();
            VkApiClient vk = new VkApiClient(transportClient);
            UserActor userActor = new UserActor(request.getUserId(), token);

            UserInformationList userInformationList = getUserInformationList(token, request, vk);
            log.info("UserInformationList get from VK = " + userInformationList);

            GroupMember groupMember = getGroupMember(request, vk, userActor);
            log.info("GroupMember get from VK = " + groupMember);

            return toVKGroupResponse(userInformationList, groupMember);

        } catch (ClientException ex) {

            log.error("Vk error " + ex.getMessage());
            throw new RuntimeException(ex);

        }
    }
    // получение распаршенной информации
    private VKGroupResponse toVKGroupResponse(UserInformationList userInformationList, GroupMember groupMember) {

        Optional<VKGroupResponse> first = userInformationList.getUserInformationList()
                .stream()
                .map(userInformation -> new VKGroupResponse()
                        .setFirstName(userInformation.getFirstName())
                        .setLastName(userInformation.getLastName())
                        .setMember(groupMember.getMember()))
                .findFirst();

        return first.orElseGet(VKGroupResponse::new);
    }
    //получение ответа от vk api состоит ли пользователь в группе
    private GroupMember getGroupMember(VKGroupRequest request, VkApiClient vk, UserActor userActor) throws ClientException {
        String result = vk.groups().isMember(userActor, String.valueOf(request.getGroupId()))
                .userId(request.getUserId())
                .extended(true)
                .executeAsString();

        return parseJson(GroupMember.class, "response", result);
    }
    //получение информации от vk api о пользователе
    private UserInformationList getUserInformationList(String token, VKGroupRequest request, VkApiClient vk) throws ClientException {
        String result = vk.users().get(new UserActor(request.getUserId(), token))
                .userIds(String.valueOf(request.getUserId()))
                .executeAsString();

        return parseJson(UserInformationList.class, "", result);
    }

    @SneakyThrows
    private <T> T parseJson(Class<T> value, String root, String json) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.reader(value).withRootName(root);
            return reader.readValue(json);
    }
}
