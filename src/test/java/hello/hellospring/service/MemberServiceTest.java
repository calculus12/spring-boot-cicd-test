package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberRepository repository;
    MemberService service;

    @BeforeEach
    void DI() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }
    @AfterEach
    void clear() {
        repository.clear();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("test");
        // when
        Long id = service.join(member);
        // then
        Member found = service.findOne(id).get();
        assertThat(found.getName()).isEqualTo(member.getName());
    }


    @Test
    void 중복_회원_예외() {
        // given
        final String name = "test";
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName(name);
        member2.setName(name);
        // when
        service.join(member1);
        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));
        assertThat(e.getMessage()).isEqualTo("duplicate member");
    }

}