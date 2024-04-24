package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;

class MemberRepositoryTest {
    MemoryMemberRepository repo = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repo.clear();
    }

    @Test
    void save() {
        Member mem1 = new Member();
        mem1.setName("spring1");
        repo.save(mem1);

        Member result = repo.findById(mem1.getId()).get();
        Assertions.assertThat(result).isEqualTo(mem1);
    }

    @Test
    void findByName() {
        Member mem1 = new Member();
        mem1.setName("s1");
        String s2Name="s2";
        Member mem2 = new Member();
        mem2.setName(s2Name);

        repo.save(mem1);
        repo.save(mem2);

        Member result = repo.findByName(s2Name).get();
        Assertions.assertThat(result).isEqualTo(mem2);
    }

    @Test
    void findAll() {
        Member m1 = new Member();
        Member m2 = new Member();
        repo.save(m1);
        repo.save(m2);

        List<Member> result = repo.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
