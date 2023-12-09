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

import java.util.List;

@RestController
public class ProfileController {

    private ProfileService profileService;


    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /** 마이페이지 **/
    @GetMapping("/api/profile")
    public MyProfileDto showProfilePage() { //Model model
//        List<ProfileDto> profileDtos = profileService.showProfile();
//        model.addAttribute("profileDtos", profileDtos);

        Long profileId = 1L;
        MyProfileDto myProfileDto = profileService.showMyPageProfile(profileId);
        return myProfileDto;
    }

//    @PostMapping("/api/profile")
//    public MyProfileDto editMyProfile(@ModelAttribute MyProfileDto myProfileDto) {
//
//        Model model;
//        model.getAttribute()
//        MyProfileDto dto = new MyProfileDto(10L, "a", "a", "a", "a", "a", "a");
//        //profileService.saveMyProfile(myProfileDto);
//        profileService.saveMyProfile(dto);
//        return dto;
//    }

    @PostMapping("/api/profile")
    public ResponseEntity<Profile> editMyProfile(@RequestBody Profile profile) {

        profileService.save(profile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }
}
