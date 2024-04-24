package hello.hellospring;

import hello.hellospring.repository.JPAMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;
    @Bean
    MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    MemberRepository memberRepository() {
        return new JPAMemberRepository(em);
    }
}
