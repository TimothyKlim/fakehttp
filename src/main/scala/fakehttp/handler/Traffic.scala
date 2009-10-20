package fakehttp.handler

import java.util.concurrent._
import java.util.concurrent.atomic._

object Traffic {
  private val map = new ConcurrentHashMap[String, AtomicInteger]()

  def record(uri: String): Unit = {
    var i = map.putIfAbsent(uri, new AtomicInteger)
    if (i == null) i = map.get(uri)
    i.incrementAndGet
  }

  def hits = scala.collection.jcl.Set(map.entrySet)
}