package hello.itemservice.web.controller;


import hello.itemservice.member.domain.Member;
import hello.itemservice.member.repository.MemberRepository;
import hello.itemservice.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/members")
@Slf4j
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
    public String addForm(Model model) {
        model.addAttribute("member", new Member());
        return "admin/addForm";
    }

    @PostMapping("/add")
    public String addMemberV6(@ModelAttribute Member member, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        log.info("member777={}",member);

//        null값 체크!
        if(!StringUtils.hasText(member.getMemberName())){
            bindingResult.addError(new FieldError("member", "memberName","직원 이름은 필수입니다."));
        }

        if(member.getGroup() == null || member.getGroup().length() >= 5){
            bindingResult.addError(new FieldError("member", "group", member.getGroup(), false, null,null, "부서 이름은 4글자 이하 여야 합니다."));
        }

        log.info("member grade={}",member.getGrade());
        if(member.getGrade() == null){
            bindingResult.addError(new FieldError("member","grade","직급은 필수입니다."));
            log.info("===========");
        }


        if(bindingResult.hasErrors()){
            log.info("error={}",bindingResult);
            return "admin/addForm";
        }



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

