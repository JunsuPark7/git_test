package hello.itemservice.member.repository;

import hello.itemservice.member.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//사용x
//@Repository
public class MemoryMemberRepository{

    private static final Map<Long, Member> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long memberId, Member updateParam) {
        Member findMember = findById(memberId);
        findMember.setMemberName(updateParam.getMemberName());
        findMember.setGroup(updateParam.getGroup());
        findMember.setGrade(updateParam.getGrade());
    }

    public void delete(long memberId){
        store.remove(memberId);
    }

    public void clearStore() {
        store.clear();
    }

}
