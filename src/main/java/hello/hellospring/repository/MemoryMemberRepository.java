package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> db = new HashMap<>();
    private static long seq = 0;
    @Override
    public Member save(Member member) {
        member.setId(++seq);
        db.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return db.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public void clear() {
        db.clear();
    }
}
