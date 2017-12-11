class Publication(val title: String)
class Book(title: String, val author: String) extends Publication(title)

object Library {
  val books: Set[Book] = Set(
    new Book("Programming in Scala", "Odersky"),
    new Book("Walden", "Nick")
  )

  def printBookList(info: Book => AnyRef): Unit = {
    for (book <- books) println(info(book))
  }

  def getTitle(b: Publication): String = b.title
}

Library.printBookList(Library.getTitle)