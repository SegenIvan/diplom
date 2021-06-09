package com.example.sweater.controller;

import com.example.sweater.domain.Order;

import com.example.sweater.domain.User;
import com.example.sweater.repos.OrderRepo;

import com.example.sweater.repos.UserRepo;
import com.example.sweater.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserSevice userSevice;

    private User user;
    public MainController() {
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Order> orders;

        if (filter != null && !filter.isEmpty()) {
            orders = orderRepo.findByTag(filter);
        } else {
            orders = orderRepo.findAll();
        }

        model.addAttribute("orders", orders);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add
            (
            @AuthenticationPrincipal User user,
            @Valid Order order,
            BindingResult bindingResult,
            Model model
            )
    {
        if (bindingResult.hasErrors())
        {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("order", order);
        }
        else
            {
            orderRepo.save(order);
        }
        Iterable<Order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);
        return "main";
    }

    @RequestMapping(value="/user-orders-add/{id}", method=RequestMethod.GET)
    public String addOrderUser
            (
                    @AuthenticationPrincipal User currentUser,
                    @PathVariable Long id,
                    Model model
            ) {
        Iterable<Order> orders = orderRepo.findAll();

        for (Order order1 : orders) {
            if (order1.getId().equals(id)) {
                if(order1.getUser() != null)
                {
                    return "redirect:/main";
                }
                else
                {
                    order1.setUser(userRepo.findByUsername(currentUser.getUsername()));

                    Integer k = Integer.parseInt(userRepo.findByUsername(currentUser.getUsername()).getUsr_should());
                    Integer z = Integer.parseInt(order1.getPrice());
                    Integer result = k + z;

                    userRepo.findByUsername(currentUser.getUsername()).setUsr_should(String.valueOf(result));
                }
            }
        }
        userRepo.save(userRepo.findByUsername(currentUser.getUsername()));
            return "redirect:/user-orders/" + currentUser.getId();
}


    @GetMapping("/user-orders/{user}")
    public String userOrders(Model model,
                             @AuthenticationPrincipal User currentUser,
                             @PathVariable User user,
                             @RequestParam(required = false) Order order
                             )
    {
        Iterable<Order> orders = user.getOrder();

        model.addAttribute("orders", orders);

        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "userOrders";
    }

    @GetMapping("/confirm-order/")
    public String validePurchase(Model model,
                             @AuthenticationPrincipal User currentUser
    )
    {
        userSevice.sendConfirm(userRepo.findByUsername(currentUser.getUsername()));
        return "redirect:/main";
    }


   @GetMapping("/del-user-order/{id}")
    public String deleteUserOrder(
            @PathVariable ("id") Long id,
            @AuthenticationPrincipal User currentUser,
            Model model
    ) throws IOException
   {
       Iterable<Order> orders = orderRepo.findAll();

       for (Order order1 : orders) {
           if (order1.getId().equals(id)) {
               int k = Integer.parseInt(userRepo.findByUsername(currentUser.getUsername()).getUsr_should());
               int z = Integer.parseInt(order1.getPrice());
               int result = k - z;
               userRepo.findByUsername(currentUser.getUsername()).setUsr_should(String.valueOf(result));
               order1.setUser(null);
               userRepo.save(userRepo.findByUsername(currentUser.getUsername()));
           }
       }

       model.addAttribute("orders",orders);

       return "redirect:/user-orders/" + currentUser.getId();
   }

    @GetMapping("/del-order/{id}")
    public String deleteOrder(
            @PathVariable("id") Long id,
            Model model
    ) throws IOException {

        Iterable<Order> orders = orderRepo.findAll();

        for (Order order1 : orders) {
            if (order1.getId().equals(id)) {

                orderRepo.delete(order1);
            }
        }
        model.addAttribute("orders",orders);

        return "redirect:/main";
    }
}