package hello.hellospring.domain.repository;

import hello.hellospring.domain.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository ( DataSource dataSource ) {
        jdbcTemplate = new JdbcTemplate( dataSource );
    }

    @Override
    public Member save ( Member member ) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert( jdbcTemplate );
        jdbcInsert.withTableName( "member" ).usingGeneratedKeyColumns( "id" );

        Map<String, Object> parameters = new HashMap<>();
        parameters.put( "name", member.getName() );

        Number key = jdbcInsert.executeAndReturnKey( new MapSqlParameterSource( parameters ) );
        member.setId( key.longValue() );
        return member;
    }

    @Override
    public Optional<Member> findById ( Long id ) {
        List<Member> memberList = jdbcTemplate.query( "select * from member where id = ?", memberRowMapper(), id );
        return memberList.stream().findAny();
    }

    private RowMapper<Member> memberRowMapper () {
        return ( rs, rowNum ) -> {
            Member member = new Member();
            member.setId( rs.getLong( "id" ) );
            member.setName( rs.getString( "name" ) );
            return member;
        };
    }

    @Override
    public Optional<Member> findByName ( String name ) {
        List<Member> memberList = jdbcTemplate.query( "select * from member where name = ?", memberRowMapper(), name );
        return memberList.stream().findAny();
    }

    @Override
    public List<Member> findAll () {
        return jdbcTemplate.query( "select * from member", memberRowMapper() );
    }


}
