package dokcerdevlink.profileservice.repository;

import dokcerdevlink.profileservice.dto.MyProfileDto;
import dokcerdevlink.profileservice.dto.ProfileDto;
import dokcerdevlink.profileservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    public Profile findByNickname(String nickname);

//    public void save(String nickname);


//    @Override
//    <S extends Profile> S save(S entity);

//    @Override
//    List<Profile> findAll();

//    @Query("select p from Profile p where p.nickname = :nickname")
//    List<Profile> findProfile(@Param("nickname") String nickname);

    @Query("select new dokcerdevlink.profileservice.dto.ProfileDto(p.id, p.nickname) from Profile p")
    ProfileDto findProfileDtoById(Long id);

    @Query("select new dokcerdevlink.profileservice.dto.MyProfileDto(p.id, p.imageUrl, p.name, p.nickname, p.introduction, p.stacks, p.address) from Profile p")
    MyProfileDto findMyProfileDtoById(Long id);

    @Query("select new dokcerdevlink.profileservice.dto.ProfileDto(p.id, p.nickname) from Profile p")
    List<ProfileDto> findProfileDto();
}
