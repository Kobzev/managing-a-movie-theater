package ua.kobzev.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.domain.UserAccount;
import ua.kobzev.theatre.service.UserAccountService;
import ua.kobzev.theatre.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Created by kkobziev on 3/19/16.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(method = RequestMethod.GET)
    public String testUser(Model model){
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "user/createUser";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUserAction(Model model, User user){
        userService.register(user);
        return "users";
    }

    @RequestMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Integer id){
        userService.remove(userService.getById(id));
        return "users";
    }

    @RequestMapping(value = "/choosename", method = RequestMethod.GET)
    public String findByName(Model model){
        User user = new User();
        user.setName("Pick name");
        model.addAttribute("user", user);
        return "user/findbyname";
    }

    @RequestMapping(value = "/findbyname", method = RequestMethod.GET)
    public String findByNameAction(Model model, @RequestParam("name") String name){
        model.addAttribute("users", userService.getUsersByName(name));
        return "users";
    }

    @RequestMapping(value = "/chooseid", method = RequestMethod.GET)
    public String findById(Model model){
        User user = new User();
        user.setId(0);
        model.addAttribute("user", user);
        return "user/findbyid";
    }

    @RequestMapping(value = "/chooseemail", method = RequestMethod.GET)
    public String findByEmail(Model model){
        User user = new User();
        user.setEmail("Pick email");
        model.addAttribute("user", user);
        return "user/findbyemail";
    }

    @RequestMapping(value = "/findbyemail", method = RequestMethod.GET)
    public String findByEmailAction(Model model, @RequestParam("email") String email){
        List<User> users = new ArrayList<>();
        User usr = userService.getUserByEmail(email);
        if (nonNull(usr)) users.add(usr);

        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/findbyid", method = RequestMethod.GET)
    public String findByIDAction(Model model, @RequestParam("id") Integer id){
        List<User> users = new ArrayList<>();
        User usr = userService.getById(id);
        if (nonNull(usr)) users.add(usr);

        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getUserAccount(Model model, @RequestParam("id") Integer id){
        UserAccount userAccount = userAccountService.findByUserId(id);

        model.addAttribute("account", userAccount);
        return "user/account";

    }

    @RequestMapping(value = "/account/put", method = RequestMethod.GET)
    public String putMoneyPage(Model model, @RequestParam("id") Integer id){
        model.addAttribute("account", new UserAccount(id, new User(), 0.0d));
        return "user/putmoney";
    }

    @RequestMapping(value = "/account/put", method = RequestMethod.POST)
    public String putMoney(Model model, UserAccount userAccount){
        userAccountService.addMoney(userAccount.getId(), userAccount.getPrepaidMoney());
        model.addAttribute("account", userAccountService.findById(userAccount.getId()));
        return "user/account";
    }
}
