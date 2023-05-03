package hello.hellospring.domain.controller;

import hello.hellospring.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController ( MemberService memberService ) {
        this.memberService = memberService;
    }


}
