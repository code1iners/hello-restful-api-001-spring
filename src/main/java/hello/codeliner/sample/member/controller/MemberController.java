package hello.codeliner.sample.member.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hello.codeliner.sample.member.MemberNotFoundException;
import hello.codeliner.sample.member.model.Member;
import hello.codeliner.sample.member.service.MemberDaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/members")
@Slf4j
public class MemberController {
    
    private final MemberDaoService memberDaoService;

    @GetMapping(path = "")
    public List<Member> list() {
        return memberDaoService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Member retrieve(@PathVariable Long id) {
        Member foundMember = memberDaoService.findOne(id);
        if (foundMember == null) {
            throw new MemberNotFoundException();
        }
        
        Member retrieve = WebMvcLinkBuilder.methodOn(MemberController.class).retrieve(id);
        Link self = WebMvcLinkBuilder.linkTo(retrieve).withSelfRel();

        Member delete = WebMvcLinkBuilder.methodOn(MemberController.class).delete(id);
        Link withSelfRel = WebMvcLinkBuilder.linkTo(delete).withRel("Self or Delete");

        foundMember.add(self);
        foundMember.add(withSelfRel);

        return foundMember;
    }

    @PostMapping(path = "")
    public ResponseEntity<Member> create(@Valid @RequestBody Member member) {
        Member savedMember = memberDaoService.save(member);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedMember.getId())
            .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public Member delete(@PathVariable Long id) {
        Member deletedMember = memberDaoService.deleteById(id);
        if (deletedMember == null) {
            throw new MemberNotFoundException();
        }
        return deletedMember;
    }
}
