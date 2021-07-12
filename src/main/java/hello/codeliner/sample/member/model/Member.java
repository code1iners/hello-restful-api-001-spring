package hello.codeliner.sample.member.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"password"})
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    
    @Size(min = 2, message = "name은 2글자 이상 입력해주세요.")
    private String name;

    @Past
    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    // @JsonIgnore
    private String password;


    public static Member create(Long id, String name, String password) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setPassword(password);
        return member;
    }
}
