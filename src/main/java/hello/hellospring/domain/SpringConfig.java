package hello.hellospring.domain;

import hello.hellospring.domain.repository.JdbcMemberRepository;
import hello.hellospring.domain.repository.MemberRepository;
import hello.hellospring.domain.service.MemberService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig ( DataSource dataSource ) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService () {
        return new MemberService( memberRepository() );
    }

    @Bean
    public MemberRepository memberRepository () {
        return new JdbcMemberRepository( dataSource );
    }
}
