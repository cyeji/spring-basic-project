package hello.hellospring.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hello.hellospring.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach () {
        repository.clearStore();
    }

    @Test
    void save () {
        Member member = new Member();
        member.setName( "spring" );

        repository.save( member );
        Member result = repository.findById( member.getId() ).get();
        System.out.println( "result = " + ( result == member ) );
        assertEquals( member, result );
    }

    @Test
    void findByName () {
        Member member = new Member();
        member.setName( "spring1" );
        repository.save( member );

        Member member2 = new Member();
        member2.setName( "spring2" );
        repository.save( member2 );

        Member result = repository.findByName( "spring1" ).get();

        assertEquals( member, result );

    }

    @Test
    void findAll () {
        Member member = new Member();
        member.setName( "spring1" );
        repository.save( member );

        Member member2 = new Member();
        member2.setName( "spring2" );
        repository.save( member2 );

        List<Member> result = repository.findAll();

        assertEquals( 2, result.size() );

    }

}