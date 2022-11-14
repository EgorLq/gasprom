package ru.test.gasprom.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInformation {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("can_access_closed")
    private Boolean canAccessClosed;
    @JsonProperty("is_closed")
    private Boolean isClosed;

}
