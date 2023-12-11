package dokcerdevlink.profileservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dokcerdevlink.profileservice.dto.MyProfileDto;
import dokcerdevlink.profileservice.dto.ProfileDto;
import dokcerdevlink.profileservice.entity.Profile;
import dokcerdevlink.profileservice.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;

        String profileUuid = "123";
        String userUuid = "456";
        String imageUrl = "http://hello";
        String name = "kim";
        String nickname = "kkk";
        String introduction = "hello kim";
        String stacks = "#java #spring";
        String address = "incheon gyeyang";

        Profile profile = new Profile(profileUuid, userUuid, imageUrl, name, nickname, introduction, stacks, address);
        profileRepository.save(profile);
    }

//    public List<ProfileDto> getAllProfiles() {
//        return profileRepository.findAllById();
//    }

//    public Profile getAllProfiles(String nickname) {
//        Profile profile = new Profile();
//        profile.setNickname(nickname);
//        profileRepository.save(nickname);
//        return profileRepository.findByNickname(nickname);
//    }

    public ProfileDto getProfileByProfileUuid(String profileUuid) {
        ProfileDto byProfileUuid = profileRepository.findByProfileUuid(profileUuid);
        return byProfileUuid;
    }

    public ProfileDto getProfileByProfileAndUserUuid(String profileUuid, String userUuid) {
        ProfileDto byUuid = profileRepository.findByProfileAndUserUuid(profileUuid, userUuid);
        return byUuid;
    }

    public List<ProfileDto> getProfileListByKeyword(String keyword) {
        List<ProfileDto> profilesByStackKeyword = profileRepository.findProfilesByStackKeyword(keyword);
        return profilesByStackKeyword;
    }

//    public List<ProfileDto> showProfile() {
//        List<ProfileDto> profileDtos = profileRepository.findProfileDto();
//        return profileDtos;
//    }

    public MyProfileDto showMyPageProfile(Long id) {
        MyProfileDto myProfileDtoById = profileRepository.findMyProfileDtoById(id);
        return myProfileDtoById;
    }

    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    public void saveMyProfile(MyProfileDto myProfileDto) {

        Profile profile = myProfileDto.convertToEntity();
        profileRepository.save(profile);
    }
}
