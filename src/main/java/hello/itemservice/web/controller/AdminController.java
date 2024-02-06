package hello.itemservice.web.controller;


import hello.itemservice.member.domain.Member;
import hello.itemservice.member.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/members")
public class AdminController {

    private final MemberRepository memberRepository;

    public AdminController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "admin/members";
    }

    @GetMapping("/{memberId}")
    public String member(@PathVariable long memberId, Model model) {
        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);
        return "admin/member";
    }

    @GetMapping("/add")
    public String addForm() {
        return "admin/addForm";
    }


    @PostMapping("/add")
    public String addMemberV6(Member member, RedirectAttributes redirectAttributes) {
        Member savedMember = memberRepository.save(member);
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/admin/members/{memberId}";
    }



    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model) {
        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);
        return "admin/editForm";
    }

    @PostMapping("/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @ModelAttribute Member member) {
        memberRepository.update(memberId, member);
        return "redirect:/admin/members/{memberId}";
    }
    @PostMapping("/{memberId}/delete")
    public String deleteMember(@PathVariable long memberId, RedirectAttributes redirectAttributes){
        memberRepository.delete(memberId);
        return "redirect:/admin/members";
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

