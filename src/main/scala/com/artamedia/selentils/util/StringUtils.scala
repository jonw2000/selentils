package com.artamedia.selentils.util

object StringUtils {

  def isEmpty(x: String) = x == null || x.trim.isEmpty
}
