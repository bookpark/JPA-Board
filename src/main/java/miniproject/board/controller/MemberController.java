package miniproject.board.controller;

import miniproject.board.domain.Member;
import miniproject.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService service;

    // @Autowired
    // 1) Field
    // 2) Constructor: 한 번만 실행 -- 선호
    // 3) Setter: 메서드가 정의 될 때마다 실행
    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/signup")
    public String join() {
        return "signup";
    }

    @PostMapping("/signup")
    public String joinProcess(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setPhone(form.getPhone());
        member.setEmail(form.getEmail());
        member.setAddress(form.getAddress());
        service.memberJoin(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = service.findAllMembers();
        model.addAttribute("members", members);
        return "members";
    }

    @GetMapping("/member/{id}")
    public String getMemberById(@PathVariable("id") Long id, Model model) {
        Member member = service.findOne(id).get();
        model.addAttribute("member", member);
        return "member-update";
    }

    @PostMapping("/member/{id}")
    public String updateMemberById(@PathVariable("id") Long id, MemberForm form) {
        Member member = service.findOne(id).get();
        if (Objects.equals(form.getName(), "")) {
            member.setName(member.getName());
        } else member.setName(form.getName());

        if (Objects.equals(form.getPhone(), "")) {
            member.setPhone(member.getPhone());
        } else member.setPhone(form.getPhone());

        if (Objects.equals(form.getEmail(), "")) {
            member.setEmail(member.getEmail());
        } else member.setEmail(form.getEmail());

        if (Objects.equals(form.getAddress(), "")) {
            member.setAddress(member.getAddress());
        } else member.setAddress(form.getAddress());

        service.memberJoin(member);
        return "redirect:/members";
    }

}

