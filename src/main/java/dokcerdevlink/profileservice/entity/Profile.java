package dokcerdevlink.profileservice.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {})
public class Profile {

    @Id @GeneratedValue
    @Column(name = "profile_id")
    private Long id;
    @Column(name = "user_uuid")
    private String userUuid;
    private String name;
    private String nickname;
    @Column(name = "profile_type")
    private Enum<ProfileType> profileType;
    private String introduction;
    private int career; // int?
    private String address;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
