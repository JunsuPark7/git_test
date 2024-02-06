package hello.itemservice.member.repository;

import hello.itemservice.member.domain.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member item);
    Member findById(Long id);

    List<Member> findByName(String name);

    List<Member> findAll();

    void update(Long itemId, Member updateParam);

    void delete(long itemId);
}
