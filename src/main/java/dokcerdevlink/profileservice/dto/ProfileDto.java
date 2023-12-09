package dokcerdevlink.profileservice.dto;

//import dokcerdevlink.profileservice.entity.ProfileType;
import lombok.Data;


@Data
public class ProfileDto {

    private Long id;
//    private String userUuid;
//    private String name;
    private String nickname;
//    private Enum<ProfileType> profileType;
//    private String introduction;
//    private int career; // int?
//    private String address;

    // constructor

//    public static class Request {
//
//    }
//
//    public static class Response {
//
//    }


    public ProfileDto(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
