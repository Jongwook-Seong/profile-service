package dokcerdevlink.profileservice.repository;

import dokcerdevlink.profileservice.dto.MyProfileDto;
import dokcerdevlink.profileservice.dto.ProfileDto;
import dokcerdevlink.profileservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("select new dokcerdevlink.profileservice.dto.ProfileDto(p.id, p.nickname) from Profile p")
    ProfileDto findProfileDtoById(Long id);

    @Query("select new dokcerdevlink.profileservice.dto.MyProfileDto(p.id, p.imageUrl, p.name, p.nickname, p.introduction, p.stacks, p.address) from Profile p where p.id = :id")
    MyProfileDto findMyProfileDtoById(@Param("id") Long id);

    @Query("select new dokcerdevlink.profileservice.dto.ProfileDto(p.id, p.profileUuid, p.userUuid, p.imageUrl, p.name, p.nickname, p.introduction, p.stacks, p.address) " +
            "from Profile p where p.profileUuid = :profileUuid")
    ProfileDto findByProfileUuid(@Param("profileUuid") String profileUuid);

    @Query("select new dokcerdevlink.profileservice.dto.ProfileDto(p.id, p.profileUuid, p.userUuid, p.imageUrl, p.name, p.nickname, p.introduction, p.stacks, p.address) " +
            "from Profile p where p.profileUuid = :profileUuid and p.userUuid = :userUuid")
    ProfileDto findByProfileAndUserUuid(@Param("profileUuid") String profileUuid, @Param("userUuid") String userUuid);

    @Query("select new dokcerdevlink.profileservice.dto.ProfileDto(p.id, p.profileUuid, p.imageUrl, p.nickname, p.stacks, p.address) " +
            "from Profile p where (p.stacks like %:keyword% or p.address like %:keyword%)")
    List<ProfileDto> findProfilesByStackKeyword(@Param("keyword") String keyword);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Profile p where (p.profileUuid = :profileUuid and p.userUuid = :userUuid)")
    void deleteByProfileAndUserUuid(@Param("profileUuid") String profileUuid, @Param("userUuid") String userUuid);
}
