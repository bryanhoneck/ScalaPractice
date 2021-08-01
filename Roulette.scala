import java.util.Scanner

object Roulette extends App {

  //Create a new game with default cash.
  val g = new Game()

  //Run the intro screen
  g.gameIntro()

  while (true) {
    //Add the menu with options
    g.gameMenu()
  }
}
