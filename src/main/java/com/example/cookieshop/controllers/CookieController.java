package com.example.cookieshop.controllers;

import com.example.cookieshop.models.Basket;
import com.example.cookieshop.repositories.CookieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class CookieController
{
    private final CookieRepository repo = new CookieRepository();

    // Land on start page
    // Create a new empty basket and save it in the session
    @GetMapping("/")
    public String session(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Basket mySessionBasket = new Basket(new ArrayList<>());
        session.setAttribute("sessionBasket", mySessionBasket);
        return "index";
    }

    // Home redirects here to avaoid landing on start endpoint again
    @GetMapping("/index")
    public String index()
    {
        return "index";
    }

    @GetMapping("/basket")
    public String basket(Model model, HttpSession session)
    {
        Basket b = (Basket) session.getAttribute("sessionBasket");
        model.addAttribute("sessionBasket", b.getCookieList());
        model.addAttribute("total", b.getTotal());
        return "basket";
    }

    @GetMapping("/shop")
    public String shop(Model cookieModel)
    {
        cookieModel.addAttribute("cookies", repo.getAllCookies());
        return "shop";
    }

    @GetMapping("/addToBasket")
    public String add(@RequestParam int id, HttpSession session)
    {
        Basket b = (Basket) session.getAttribute("sessionBasket");
        b.addCookieToBasket(repo.getCookieById(id));
        session.setAttribute("sessionBasket", b);
        return "redirect:basket";
    }
}
