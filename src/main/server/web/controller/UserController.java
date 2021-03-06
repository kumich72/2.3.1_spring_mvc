package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import web.exception.DBException;
import web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import web.service.UserService;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService  userService;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printUsers() {
//        UserService userService = new UserService();
        List<User> users = userService.getAllUsers();
        ModelAndView result = new ModelAndView("users");
        result.addObject("users", users);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deleteUser(@RequestParam String id) {
        try {
            userService.deleteUser(Long.valueOf(id));
        }catch (DBException e){

        }
        List<User> users = userService.getAllUsers();
        ModelAndView result = new ModelAndView("/users");
        result.addObject("users", users);
        return result;
    }

    @RequestMapping(value = "/editing", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editingUser(@RequestParam String edit_id) {
        User user = userService.getUserById(Long.valueOf(edit_id));
        ModelAndView result = new ModelAndView("/editUser");
        result.addObject("user", user);
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editUser(@RequestParam String id, String name, String password, String email) {
        try {
            userService.editUser(Long.valueOf(id), name, password, email );
        }catch (DBException e){

        }
        List<User> users = userService.getAllUsers();
        ModelAndView result = new ModelAndView("/users");
        result.addObject("users", users);
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addUser(@RequestParam String name, String password, String email, String role) {
        ModelAndView result = new ModelAndView("/users");
        try {
            User user = new User(name,password, email, role);
            userService.addUser(user);
        }catch (DBException e){

        }
        List<User> users = userService.getAllUsers();

        result.addObject("users", users);
        return result;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public String printAdd(ModelMap model) {
        return "addUser";
    }
}