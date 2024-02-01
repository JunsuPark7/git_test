package hello.itemservice.member.repository;

import hello.itemservice.member.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DBMemberRepository implements MemberRepository {

    private final EntityManager em;
//    private static long sequence = 0L; //static

    public DBMemberRepository(EntityManager em) {
        this.em = em;
    }
    public Member save(Member member) {
        em.persist(member);
        Member savedMember = em.find(Member.class, member.getId());
        return savedMember;
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }




    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public void update(Long memberId, Member updateParam) {

        Member findMember = em.find(Member.class, memberId);
        findMember.setMemberName(updateParam.getMemberName());
        findMember.setGroup(findMember.getGroup());
        findMember.setGrade(findMember.getGrade());

    }

    public void delete(long memberId){
        Member findMember = em.find(Member.class, memberId);

        em.remove(findMember);
    }


}
