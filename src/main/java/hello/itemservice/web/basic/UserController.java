package hello.itemservice.web.basic;

import hello.itemservice.user.domain.User;
import hello.itemservice.user.repository.MemoryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final MemoryUserRepository memoryUserRepository;


    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user){
        return "users/addUserForm";
    }

    @PostMapping("/add")
    private String save(@Valid @ModelAttribute User user, BindingResult result){
        if(result.hasErrors()){
            return "users/addUserForm";
        }

        memoryUserRepository.save(user);
        return "redirect:/";
    }









}
