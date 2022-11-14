package ru.test.gasprom.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserInformationList {

    @JsonProperty("response")
    List<UserInformation> userInformationList;
}
