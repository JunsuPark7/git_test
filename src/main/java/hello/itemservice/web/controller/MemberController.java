package hello.itemservice.web.controller;

import hello.itemservice.member.domain.Member;
import hello.itemservice.member.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
