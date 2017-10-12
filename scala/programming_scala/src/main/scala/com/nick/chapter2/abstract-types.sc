import java.io._

abstract class BulkReader[In] {
  val source: In
  def read: String
}

class StringBulkReader(val source: String) extends BulkReader[String] {
//  type In = String
  def read: String = source.toUpperCase

  def main(args: Array[String]) = {
    println("")
  }
}

class FileBulkReader(val source: File) extends BulkReader[File] {
  def read: String = {
    val in = new BufferedInputStream(new FileInputStream(source))
    val numBytes = in.available
    val bytes = new Array[Byte](numBytes)
    in.read(bytes, 0, numBytes)
    new String(bytes)
  }
}

println(new StringBulkReader("hello scala").read)

val s: Int = 1
println(1.+(2))