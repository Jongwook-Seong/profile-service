package dokcerdevlink.profileservice.controller;

import dokcerdevlink.profileservice.dto.MyProfileDto;
import dokcerdevlink.profileservice.dto.ProfileDto;
import dokcerdevlink.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ProfileController {

    private ProfileService profileService;


    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /** 마이페이지 **/
    @GetMapping("/api/myprofile")
    public MyProfileDto viewMyProfilePage() {

        Long profileId = 1L; // 테스트용
        MyProfileDto myProfileDto = profileService.getMyPageProfile(profileId);
        return myProfileDto;
    }

    @PostMapping("/api/myprofile")
    public MyProfileDto editMyProfile(@RequestBody HashMap<String, Object> jsonObject, @RequestHeader("userUuid") String userUuid) {
        MyProfileDto myProfileDto = convertJsonToMyProfileDto(jsonObject);
        profileService.saveMyProfile(myProfileDto);
        return myProfileDto;
    }

    @GetMapping("/api/profile")
    public ProfileDto viewProfilePage(@RequestParam("profileUuid") String profileUuid, @RequestHeader("userUuid") String userUuid) {
        ProfileDto profileDto = profileService.getProfileByProfileAndUserUuid(profileUuid, userUuid);
        return profileDto;
    }

    @GetMapping("/api/profile/list")
    public List<ProfileDto> viewProfilePage(@RequestParam("keyword") String listKeyword) {
        List<ProfileDto> profileListByKeyword = profileService.getProfileListByKeyword(listKeyword);
        return profileListByKeyword;
    }

    @DeleteMapping("/api/myprofile")
    public String deleteMyProfile(@RequestParam("profileUuid") String profileUuid, @RequestHeader("userUuid") String userUuid) {
//        Long profileId = 1L;
        profileService.deleteProfileByProfileAndUserUuid(profileUuid, userUuid);
        return "Delete success.";
    }

    private static MyProfileDto convertJsonToMyProfileDto(HashMap<String, Object> jsonObject) {
        String imageUrl = jsonObject.get("imageUrl").toString();
        String name = jsonObject.get("name").toString();
        String nickname = jsonObject.get("nickname").toString();
        String introduction = jsonObject.get("introduction").toString();
        String stacks = jsonObject.get("stacks").toString();
        String address = jsonObject.get("address").toString();

        return new MyProfileDto(10L, imageUrl, name, nickname, introduction, stacks, address);
    }
}
