package com.example.cookieshop.models;

public class CookieOrder
{
    private Cookie cookie;
    private int quantity;

    public CookieOrder(Cookie cookie, int quantity)
    {
        this.cookie = cookie;
        this.quantity = quantity;
    }
}
