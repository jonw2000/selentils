package com.artamedia.selentils

import com.artamedia.selentils.driver.WebDriverUtils
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

object Tests extends App with WebDriverUtils {

  igIndexTest()

  def igIndexTest(): Unit = {
    // Download gecko - https://github.com/mozilla/geckodriver/releases
    val wd: WebDriver = new FirefoxDriver
    wd.get("https://www.ig.com/uk")
  }

}
