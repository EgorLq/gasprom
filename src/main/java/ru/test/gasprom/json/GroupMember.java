package ru.test.gasprom.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GroupMember {

    @JsonProperty("member")
    private Integer member;
    @JsonProperty("can_invite")
    private Integer canInvite;

}
