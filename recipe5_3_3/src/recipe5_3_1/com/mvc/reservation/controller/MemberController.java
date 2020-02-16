package recipe5_3_1.com.mvc.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member/*")
public class MemberController {
//
//	private MemberService memberService;
//	
//
//	public MemberController(MemberService memberService) {
//		super();
//		this.memberService = memberService;
//	}
//
//	@RequestMapping("add")
//	public String addMember(Model model) {
//		model.addAttribute("member", new Member());
//		model.addAttribute("guests", memberService.list());
//		return "memberList";
//	}
//	
//	@RequestMapping(value= {"remove", "delite"}, method=RequestMethod.Get)
//	public String removeMember(@RequestParam("memberName")String memberName) {
//		memberService.remove(memberName);
//		return "redirect:";
//	}
//	
//	@RequestMapping("display/{member}")
//	public String displayMember(@PathVariable("member") String member, Model model) {
//		model.addAttribute("member", memberService.find(member).orElse(null));
//		return "member";
//	}
//	
//	@RequestMapping
//	public void memberList{}
//	
//	public void memberLogic(String memberName) {}
	
}
