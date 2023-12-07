package dokcerdevlink.profileservice.controller;

import java.util.Map;

public class ProfileEditController implements Controller {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "edit-profile";
    }
}
