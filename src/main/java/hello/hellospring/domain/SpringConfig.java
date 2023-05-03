package hello.hellospring.domain;

import hello.hellospring.domain.repository.JpaMemberRepository;
import hello.hellospring.domain.repository.MemberRepository;
import hello.hellospring.domain.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig ( EntityManager em ) {
        this.em = em;
    }

    @Bean
    public MemberService memberService () {
        return new MemberService( memberRepository() );
    }

    @Bean
    public MemberRepository memberRepository () {
        return new JpaMemberRepository( em );
    }
}
