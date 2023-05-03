package hello.hellospring.domain.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.service.MemberService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/members" )
public class MemberController {

    private final MemberService memberService;

    public MemberController ( MemberService memberService ) {
        this.memberService = memberService;
    }

    @GetMapping( "/new" )
    public String createForm () {
        return "members/createMemberForm";
    }

    @PostMapping( "/new" )
    public String create ( MemberCreateForm form ) {
        Member member = new Member();
        member.setName( form.getName() );
        memberService.join( member );

        return "redirect:/";
    }

    @GetMapping( "/members" )
    public String list ( Model model ) {
        List<Member> members = memberService.findMembers();
        model.addAttribute( "members", members );
        return "members/memberList";
    }
}
