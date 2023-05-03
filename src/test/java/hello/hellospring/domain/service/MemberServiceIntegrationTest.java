package hello.hellospring.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 회원가입 () {
        // given
        Member member = new Member();
        member.setName( "hello" );
        // when
        Long saveId = memberService.join( member );
        // then
        Member findMember = memberService.findOne( saveId ).get();
        assertEquals( findMember.getId(), saveId );
    }

    @Test
    void 중복_회원_예외 () {
        // given
        Member member = new Member();
        member.setName( "spring" );
        Member member2 = new Member();
        member2.setName( "spring" );
        // when
        memberService.join( member );
        // then
        IllegalStateException e = assertThrows( IllegalStateException.class, () -> memberService.join( member2 ) );
        assertEquals( "이미 존재하는 회원입니다.", e.getMessage() );
    }

}
