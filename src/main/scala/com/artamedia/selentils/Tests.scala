package com.artamedia.selentils

import com.artamedia.selentils.driver.WebDriverUtils
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

object Tests extends App with WebDriverUtils {

  firefoxTest()

  def igIndexTest(): Unit = {
    // Download gecko - https://github.com/mozilla/geckodriver/releases
    val wd: WebDriver = new FirefoxDriver
    wd.get("https://www.ig.com/uk")
    println(wd.getPageSource)
    }

  def firefoxTest(): Unit = {
    // Download gecko - https://github.com/mozilla/geckodriver/releases
    System.setProperty("webdriver.gecko.driver", "C:\\Dev\\firefox\\geckodriver.exe")
    val wd: WebDriver = new FirefoxDriver
    wd.get("https://www.bbc.co.uk/")
    println(wd.getPageSource)
  }

  def chromeTest(): Unit = {
    // Download gecko - https://github.com/mozilla/geckodriver/releases
    System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe")
    val wd: WebDriver = new ChromeDriver
    wd.get("https://www.bbc.co.uk/")
    println(wd.getPageSource)
  }
}
