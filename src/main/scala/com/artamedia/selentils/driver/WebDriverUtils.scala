package com.artamedia.selentils.driver

import java.util

import com.artamedia.selentils.util.{MathUtils, StringUtils}
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, WebDriver, WebElement}

import scala.util.{Success, Try}
import scala.collection.JavaConverters._
import scala.collection.mutable

trait WebDriverUtils {

  implicit class WebElementEnricher(el: WebElement) {

    def parent: WebElement = el.findElement(By.xpath(".."))

    def tags(tagName: String): List[WebElement] =
      el.findElements(By.tagName(tagName)).asScala.toList

    def tag(tagName: String): WebElement =
      el.findElement(By.tagName(tagName))

    def child(tagName: String, attr: String, value: String): WebElement =
      el.findElement(By.xpath(".//"+tagName+"[@"+attr+"=\""+value+"\"]"))

    def toDouble: Double = el.getText.filter(MathUtils.numericOnly).toDouble
    def toInt: Int = el.toDouble.toInt
    def toDblOpt: Option[Double] = if (StringUtils.isEmpty(el.getText)) {
                                      None
                                    } else {
                                      Some(toDouble)
                                    }

    def toDoubleWithBrackets: Double = el.getText.replaceAll("\\(", "-").filter(MathUtils.numericOnly).toDouble
  }

  def tag(tag: String)(implicit driver: WebDriver): WebElement =
    driver.findElement(By.tagName(tag))

  def tags(tag: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    driver.findElements(By.tagName(tag)).asScala

  def findParaByClass(clss: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("p", "class", clss)

  def findTagByAttr(tag: String, attr: String, value: String)(implicit driver: WebDriver): WebElement =
    driver.findElement(By.xpath("//"+tag+"[@"+attr+"=\""+value+"\"]"))

  def findTagsByAttr(tag: String, attr: String, value: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    driver.findElements(By.xpath("//"+tag+"[@"+attr+"=\""+value+"\"]")).asScala

  def findTableByClass(value: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("table", "class", value)

  def findTableById(value: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("table", "id", value)

  def findTablesById(value: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findTagsByAttr("table", "id", value)

  def findTagByAttrAndText(tag: String, attr: String, value: String, text: String)(implicit driver: WebDriver): WebElement =
    driver.findElement(By.xpath("//"+tag+"[@"+attr+"=\""+value+"\" and text()=\"" + text + "\"]"))

  def findTagsContainText(tag: String, text: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    driver.findElements(By.xpath("//" + tag + "[contains(text(), \"" + text + "\")]")).asScala

  def findTagWithText(tag: String, text: String)(implicit driver: WebDriver): WebElement =
    findTagsContainText(tag, text).head

  def findTagById(tag: String, id: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr(tag, "id", id)

  def findTagByClass(tag: String, clss: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr(tag, "class", clss)

  def findTdsByClass(clss: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findTagsByAttr("td", "class", clss)

  def findTagsByClass(tag: String, clss: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findTagsByAttr(tag, "class", clss)

  def findTagsById(tag: String, id: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findTagsByAttr(tag, "id", id)

  def findDivWithText(text: String)(implicit driver: WebDriver): WebElement =
    findTagWithText("div", text)

  def findLinksByClass(cls: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findTagsByAttr("a", "class", cls)

  def findLinkById(id: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("a", "id", id)

  def findLinksById(id: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findTagsByAttr("a", "id", id)

  def findFrameByName(name: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("frame", "name", name)

  def findSpansWithText(text: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findTagsContainText("span", text)

  def findSpanWithText(text: String)(implicit driver: WebDriver): WebElement =
    findTagWithText("span", text)

  def findSpanWithClass(clss: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("span", "class", clss)

  def findSpanByClassWithText(clss: String, text: String)(implicit driver: WebDriver): WebElement =
    findTagByAttrAndText("span", "class", clss, text)

  def findSpanByClass(clss: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("span", "class", clss)

  def findSpanById(id: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("span", "id", id)

  def findSpanByTitle(title: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("span", "title", title)

  def findListItemsWithText(text: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findTagsContainText("li", text)

  def findLabelFor(text: String)(implicit driver: WebDriver): WebElement =
    driver.findElement(By.xpath(s"//label[@for='$text']"))

  def findLabelsFor(text: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    driver.findElements(By.xpath(s"//label[@for='$text']")).asScala

  def findButtonById(id: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("button", "id", id)

  def findDivByClass(cl: String)(implicit driver: WebDriver): WebElement =
    driver.findElement(By.xpath(s"//div[@class='$cl']"))

  def findDivsById(id: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findTagsByAttr("div", "id", id)

  def findDivsByClass(clss: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findTagsByAttr("div", "class", clss)

  def findDivById(id: String)(implicit driver: WebDriver): WebElement =
    findTagByAttr("div", "id", id)

  def findInputsByAttrFragment(attr: String, fragment: String)(implicit driver: WebDriver): util.List[WebElement] =
    driver.findElements(By.xpath(s"//input[contains(@$attr, '$fragment')]"))

  def findInputByAttr(attr: String, value: String)(implicit driver: WebDriver): WebElement =
    driver.findElement(By.xpath(s"//input[@$attr='$value']"))

  def findInputsByAttr(attr: String, value: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    driver.findElements(By.xpath(s"//input[@$attr='$value']")).asScala

  def findInputByValue(valueStr: String)(implicit driver: WebDriver): WebElement =
    findInputByAttr("value", valueStr)

  def findInputsByValue(valueStr: String)(implicit driver: WebDriver): mutable.Buffer[WebElement] =
    findInputsByAttr("value", valueStr)

  def findInputById(id: String)(implicit driver: WebDriver): WebElement =
    findInputByAttr("id", id)

  def findInputByName(nm: String)(implicit driver: WebDriver): WebElement =
    findInputByAttr("name", nm)

  def findSelectById(id: String)(implicit driver: WebDriver): WebElement =
    findTagById("select", id)

  def typeInputWithId(id: String, text: String)(implicit driver: WebDriver): Unit =
    typeInput(findInputById(id), text)

  def typeInputWithValue(value: String, text: String)(implicit driver: WebDriver): Unit =
    typeInput(findInputByValue(value), text)

  def typeInputWithName(name: String, text: String)(implicit driver: WebDriver): Unit =
    typeInput(findInputByName(name), text)

  def typeInputWithId(id: String, ch: Char)(implicit driver: WebDriver): Unit =
    typeInput(findInputById(id), ch.toString)

  def typeInput(el: WebElement, text: String): Unit = {
    el.clear()
    el.sendKeys(text)
  }

  def getInputTextById(id: String)(implicit driver: WebDriver): String =
    findInputById(id).getAttribute("value")

  def existsDivWithClass(clss: String)(implicit driver: WebDriver): Boolean =
    findDivsByClass(clss).nonEmpty

  def existsDivWithId(id: String)(implicit driver: WebDriver): Boolean =
    findDivsById(id).nonEmpty

  def existsLinkWithClass(cls: String)(implicit driver: WebDriver): Boolean =
    findLinksByClass(cls).nonEmpty

  def existsLinkWithId(id: String)(implicit driver: WebDriver): Boolean =
    findLinksById(id).nonEmpty

  def existsTableWithId(id: String)(implicit driver: WebDriver): Boolean =
    findTablesById(id).nonEmpty

  def existsTag(tag: String)(implicit driver: WebDriver): Boolean =
    tags(tag).nonEmpty

  def existsTagWithId(tag: String, id: String)(implicit driver: WebDriver): Boolean =
    findTagsByAttr(tag, "id", id).nonEmpty

  def existsTagWithText(tag: String, text: String)(implicit driver: WebDriver): Boolean =
    findTagsContainText(tag, text).nonEmpty

  def existsInputWithId(id: String)(implicit driver: WebDriver): Boolean =
    findTagsByAttr("input", "id", id).nonEmpty

  def existsInputWithTitle(title: String)(implicit driver: WebDriver): Boolean =
    findInputsByAttr("title", title).nonEmpty

  def existsInputWithValue(value: String)(implicit driver: WebDriver): Boolean =
    findInputsByAttr("value", value).nonEmpty

  def existsButtonWithClass(cls: String)(implicit driver: WebDriver): Boolean =
    findTagsByClass("a", cls).nonEmpty

  def existsButtonWithId(id: String)(implicit driver: WebDriver): Boolean =
    findTagsById("button", id).nonEmpty

  def existsButtonWithText(text: String)(implicit driver: WebDriver): Boolean =
    findTagsContainText("button", text).nonEmpty

  def existsSpanWithClass(clss: String)(implicit driver: WebDriver): Boolean =
    findTagsByAttr("span", "class", clss).nonEmpty

  def existsSpanWithTitle(title: String)(implicit driver: WebDriver): Boolean =
    findTagsByAttr("span", "title", title).nonEmpty

  def existsFrame(name: String)(implicit driver: WebDriver): Boolean =
    findTagsByAttr("frame", "name", name).nonEmpty

  def notExistsFrame(name: String)(implicit driver: WebDriver): Boolean =
    findTagsByAttr("frame", "name", name).isEmpty

  def clickInputWithAttr(attr: String, value: String)(implicit driver: WebDriver): Unit =
    findInputByAttr(attr, value).click()

  def clickInputWithId(value: String)(implicit driver: WebDriver): Unit =
    findInputByAttr("id", value).click()

  def clickInputWithName(name: String)(implicit driver: WebDriver): Unit =
    findInputByAttr("name", name).click()

  def clickInputWithTitle(title: String)(implicit driver: WebDriver): Unit =
    findInputByAttr("title", title).click()

  def clickInputWithValue(value: String)(implicit driver: WebDriver): Unit =
    findInputByAttr("value", value).click()

  def clickLinkWithText(text: String)(implicit driver: WebDriver): Unit =
    driver.findElement(By.linkText(text)).click()

  def clickLinkWithClass(clss: String)(implicit driver: WebDriver): Unit =
    findTagByAttr("a", "class" , clss).click()

  def clickLinkWithId(id: String)(implicit driver: WebDriver): Unit =
    findTagByAttr("a", "id" , id).click()

  def clickLinkWithTitle(title: String)(implicit driver: WebDriver): Unit =
    findTagByAttr("a", "title" , title).click()

  def clickDivWithId(id: String)(implicit driver: WebDriver): Unit =
    findDivById(id).click()

  def clickDivWithClass(clss: String)(implicit driver: WebDriver): Unit =
    findDivByClass(clss).click()

  def clickDivWithText(text: String)(implicit driver: WebDriver): Unit =
    findDivWithText(text).click()

  def clickLabelFor(forTxt: String)(implicit driver: WebDriver): Unit =
    findLabelFor(forTxt).click()

  def clickSpanWithClass(clss: String)(implicit driver: WebDriver): Unit =
    findSpanWithClass(clss).click()

  def clickSpanWithText(text: String)(implicit driver: WebDriver): Unit =
    findSpanWithText(text).click()

  def clickSpanWithTitle(title: String)(implicit driver: WebDriver): Unit =
    findSpanByTitle(title).click()

  def clickButtonWithId(id: String)(implicit driver: WebDriver): Unit =
    findTagById("button", id).click()

  def clickButtonWithClass(clss: String)(implicit driver: WebDriver): Unit =
    findTagByClass("button", clss).click()

  def clickButtonWithText(text: String)(implicit driver: WebDriver): Unit =
    findTagWithText("button", text).click()

  def clickButtonWithTitle(title: String)(implicit driver: WebDriver): Unit =
    findTagByAttr("button", "title", title).click()

  def clickButtonWithType(typ: String)(implicit driver: WebDriver): Unit =
    findTagByAttr("button", "type", typ).click()

  def numericFromTagWithClass(tag: String, clss: String)(implicit driver: WebDriver): Double =
    findTagByClass(tag, clss).getText.filter(MathUtils.numericOnly).toDouble

  def switchToFrame(frm: String)(implicit driver: WebDriver): WebDriver = driver.switchTo().frame(frm)

  def waitFor(e: WebElement)(implicit driver: WebDriver): Option[WebElement] =
    try {
      Some(new WebDriverWait(driver, 30L).until(ExpectedConditions.elementToBeClickable(e)))
    } catch {
      case e: Exception => None
    }

  def waitForTagWithClass(tag: String, clss: String)(implicit driver: WebDriver): Option[WebElement] =
    waitFor(findTagByClass(tag, clss))

  def waitForInputWithId(id: String)(implicit driver: WebDriver): Option[WebElement] =
    waitFor(findInputById(id))

  def waitForParaWithClass(clss: String)(implicit driver: WebDriver): Option[WebElement] =
    waitFor(findParaByClass(clss))

  def waitForLinkWithText(text: String)(implicit driver: WebDriver): Option[WebElement] =
    waitFor(driver.findElement(By.linkText(text)))

  def waitForLinkWithId(id: String)(implicit driver: WebDriver): Option[WebElement] =
    waitFor(driver.findElement(By.id(id)))

  def linkClick(linkText: String)(implicit driver: WebDriver): Boolean = {
    val link: util.List[WebElement] = driver.findElements(By.linkText("view all"))
    if (link.isEmpty) {
      false
    } else {
      link.get(0).click()
      true
    }
  }


  def switchToOtherWindow()(implicit driver: WebDriver): Unit = {
    if (driver.getWindowHandles.size() > 1) {
      val mainHandle = Try(driver.getWindowHandle)
      mainHandle match {
        case Success(win) =>
                            println("Switching from " + driver.getTitle)
                            val handles = driver.getWindowHandles
                            val newHandle = handles.asScala.filterNot(_ == win).head
                            driver.switchTo().window(newHandle)
        case _ => // Do nothing
      }
    } else {
      driver.switchTo().window(driver.getWindowHandles.asScala.head)
    }
    println("Switched to window " + driver.getTitle)
  }

  def switchToWindowWithFrameAndTagWithId(frame: String, tag: String, id: String)(implicit driver: WebDriver): Unit = {
    val goodHandles = driver.getWindowHandles.asScala.filter(handle => {
                            driver.switchTo().window(handle)
                            existsTag(tag)
                          })
    if (goodHandles.nonEmpty) {
      driver.switchTo().window(goodHandles.head)
      driver.switchTo().frame(frame)
    }
  }


  def get(url: String)(implicit driver: WebDriver): Unit = {
    Try{
      driver.get(url)
      return
    }.getOrElse(
      Try(switchToOtherWindow())
    )
    driver.get(url)
  }

}
