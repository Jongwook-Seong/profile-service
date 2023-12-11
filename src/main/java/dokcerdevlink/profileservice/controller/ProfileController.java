package dokcerdevlink.profileservice.controller;

import dokcerdevlink.profileservice.dto.MyProfileDto;
import dokcerdevlink.profileservice.dto.ProfileDto;
import dokcerdevlink.profileservice.entity.Profile;
import dokcerdevlink.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
//    @GetMapping("/api/profile")
//    public MyProfileDto showMyProfilePage() { //Model model
////        List<ProfileDto> profileDtos = profileService.showProfile();
////        model.addAttribute("profileDtos", profileDtos);
//
//        Long profileId = 1L;
//        MyProfileDto myProfileDto = profileService.showMyPageProfile(profileId);
//        return myProfileDto;
//    }

    @PostMapping("/api/profile")
    public MyProfileDto editMyProfile(@RequestBody HashMap<String, Object> jsonObject) {
        MyProfileDto myProfileDto = convertJsonToMyProfileDto(jsonObject);
        profileService.saveMyProfile(myProfileDto);
        return myProfileDto;
    }

    @GetMapping("/api/profile")
    public ProfileDto viewProfilePage(@RequestParam("profileUuid") String profileUuid, @RequestHeader("userUuid") String userUuid) {
//        ProfileDto profileDto = profileService.getProfileByProfileUuid(profileUuid);
        ProfileDto profileDto = profileService.getProfileByProfileAndUserUuid(profileUuid, userUuid);
        return profileDto;
    }

    @GetMapping("/api/profile/list")
    public List<ProfileDto> viewProfilePage(@RequestParam("keyword") String listKeyword) {
        List<ProfileDto> profileListByKeyword = profileService.getProfileListByKeyword(listKeyword);
        return profileListByKeyword;
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
