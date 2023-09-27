package com.epam.training.olha_haichenkova;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/");

    }
}