package hello.itemservice.web.basic;


import hello.itemservice.member.domain.Member;
import hello.itemservice.member.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/members")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "basic/members";
    }

    @GetMapping("/{memberId}")
    public String member(@PathVariable long memberId, Model model) {
        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);
        return "basic/member";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/{memberId}/delete")
    public String deleteMember(@PathVariable long memberId, RedirectAttributes redirectAttributes){
        memberRepository.delete(memberId);
        System.out.println("hhhhhhhh");
        return "redirect:/basic/members";
    }

    @PostMapping("/add")
    public String addMemberV6(Member member, RedirectAttributes redirectAttributes) {
        Member savedMember = memberRepository.save(member);
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/members/{memberId}";
    }



    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model) {
        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);
        return "basic/editForm";
    }

    @PostMapping("/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @ModelAttribute Member member) {
        memberRepository.update(memberId, member);
        return "redirect:/basic/members/{memberId}";
    }



    /**
     * 테스트용 데이터 추가
     */
//    @PostConstruct
//    public void init() {
//        memberRepository.save(new Member("박준수", "전산팀", "사원"));
//        memberRepository.save(new Member("홍길동", "경영팀", "팀장"));
//    }

}

