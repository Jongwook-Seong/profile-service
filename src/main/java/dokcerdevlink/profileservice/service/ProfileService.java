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

        String imageUrl = "http://hello";
        String name = "kim";
        String nickname = "kkk";
        String introduction = "hello kim";
        String stacks = "#java #spring";
        String address = "incheon gyeyang";

        Profile profile = new Profile(imageUrl, name, nickname, introduction, stacks, address);
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

    public List<ProfileDto> showProfile() {
        List<ProfileDto> profileDtos = profileRepository.findProfileDto();
        return profileDtos;
    }

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
