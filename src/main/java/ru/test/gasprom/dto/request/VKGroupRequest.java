package ru.test.gasprom.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VKGroupRequest {

    /**Идентификатор пользователя в VK*/
    @NotNull(message = "userId не должно быть пустым")
    @Min(value = 1, message = "userId не может быть отрицательным")
    private Integer userId;

    /**Идентификатор группы в VK*/
    @NotNull(message = "groupId не должно быть пустым")
    @Min(value = 1, message = "groupId не может быть отрицательным")
    private Integer groupId;

}
