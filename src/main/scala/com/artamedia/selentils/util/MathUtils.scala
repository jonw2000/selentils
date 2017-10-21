package com.artamedia.selentils.util

import scala.language.postfixOps

object MathUtils {

  def numericOnly(ch: Char) = "-1234567890."contains ch

  def parseCcy(s: String): Option[Double] = s match {
    case null => None
    case empty if StringUtils.isEmpty(empty) => None
    case _ => Some(s.filter(numericOnly).toDouble)
  }
}
