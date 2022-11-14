package ru.test.gasprom.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VKGroupResponse {

    private String lastName;
    private String firstName;
    private Boolean member;

    public VKGroupResponse setMember(Integer member) { //вывод  true или false тк как вк api возвращает 1 или 0
        if(member > 0) {
            this.member = Boolean.TRUE;
        } else {
            this.member = Boolean.FALSE;
        }
        return this;
    }
}
