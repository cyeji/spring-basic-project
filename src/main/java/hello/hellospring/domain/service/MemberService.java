package hello.hellospring.domain.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService ( MemberRepository memberRepository ) {this.memberRepository = memberRepository;}

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join ( Member member ) {
        // 같은 이름이 있는 중복 회원은 안됨
        validateDuplicateMember( member );
        memberRepository.save( member );
        return member.getId();
    }

    private void validateDuplicateMember ( Member member ) {
        memberRepository.findByName( member.getName() ).ifPresent( m -> {
            throw new IllegalStateException( "이미 존재하는 회원입니다." );
        } );
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers () {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne ( Long memberId ) {
        return memberRepository.findById( memberId );
    }


}
