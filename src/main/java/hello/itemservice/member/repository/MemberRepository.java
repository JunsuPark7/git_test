package hello.itemservice.member.repository;

import hello.itemservice.member.domain.Member;

import java.util.List;

public interface MemberRepository {
    public Member save(Member item);
    public Member findById(Long id);

    public List<Member> findAll();

    public void update(Long itemId, Member updateParam);

    public void delete(long itemId);
}
