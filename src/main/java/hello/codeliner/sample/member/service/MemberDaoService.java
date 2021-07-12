package hello.codeliner.sample.member.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hello.codeliner.sample.member.model.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDaoService {
    
    private static List<Member> members = new ArrayList<>();

    static {
        members.add(Member.create((long) members.size() + 1, "member1", "password1"));
        members.add(Member.create((long) members.size() + 1, "member2", "password2"));
        members.add(Member.create((long) members.size() + 1, "member3", "password3"));
    }

    public List<Member> findAll() {
        return members;
    }

    public Member findOne(Long id) {
        for (Member m: members) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public Member save(Member member) {
        if (member.getId() == null) {
            member.setId((long)members.size() + 1);
        }
        members.add(member);
        return member;
    }

    public Member deleteById(Long id) {
        Member deletedMember = findOne(id);
        List<Member> filteredMembers = members.stream().filter(m -> {
            return m.getId() != id;
        }).collect(Collectors.toList());
        members = filteredMembers;
        return deletedMember;
    }
}
