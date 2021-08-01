import java.util.{InputMismatchException, Scanner}

class Game (){

  val BANK_INIT = 500
  var bank = 500
  val formatter = java.text.NumberFormat.getCurrencyInstance
  //Scanner to receive input throughout the game.
  val sc = new Scanner(System.in)


  def gameIntro():Unit = {
    println("             /-----------------\\                     ")
    println("            /  /-------------\\  \\                    ")
    println("           /  /               \\  \\                   ")
    println("          /  /   WELCOME TO    \\  \\                  ")
    println("         /  /     ROULETTE      \\  \\                 ")
    println("        /  /---------------------\\  \\                ")
    println("       |  |      CREATED BY       |  |               ")
    println("        \\  \\                     /  /                ")
    println("         \\  \\   BRYAN HONECK    /  /                 ")
    println("          \\  \\-----------------/  /                  ")
    println("           \\  \\   7/31/2021   /  /                   ")
    println("            \\  \\-------------/  /                    ")
    println("             \\-----------------/                     ")
  }

  def viewBoard() = {
    println("Roulette by Bryan Honeck, in Scala!")
    println()
    println("|--------|--------|                    ")
    println("|    0   |   00   |                    ")
    println("|________|________|---------|          ")
    println("|  1R |  2B |  3R | 1st12   |---------|")
    println("|  4B |  5R |  6B |---------|  EVEN   |")
    println("|  7R |  8B |  9R | 1to18   |---------|")
    println("| 10B | 11B | 12R |---------|  RED    |")
    println("| 13B | 14R | 15B | 2nd12   |---------|")
    println("| 16R | 17B | 18R |---------|  BLACK  |")
    println("| 19R | 20B | 21R | 19to36  |---------|")
    println("| 22B | 23R | 24B |---------|  ODD    |")
    println("| 25R | 26B | 27R | 3rd12   |---------|")
    println("| 28B | 29B | 30R |---------|          ")
    println("| 31B | 32R | 33B |                    ")
    println("| 34R | 35B | 36R |                    ")
    println("|-----|-----|-----|                    ")
    println("|2to1a|2to1b|2to1c|                    ")
    println("|-----|-----|-----|                    ")
    println()
  }


  def gameMenu() = {
    println("What would you like to do?")
    println("(1) Place bet")
    println("(2) View Board")
    println("(3) Quit")
    println()

    try {
      val operation = sc.nextInt()

      operation match {
        case 1 => runGame()
        case 2 => viewBoard()
        case 3 => {
          if(BANK_INIT < bank){
            println("                 _______________")
            println("Your net profit: |  +" + (formatter.format(bank - BANK_INIT)) + "  |")
            println("                 |_____________|")
          } else {
            println("               _____________")
            println("Your net loss: | -" + (formatter.format(BANK_INIT - bank)) + "  | ... Mission failed, we'll get 'em next time.")
            println("               |____________|")
          }
          System.exit(0)
        }
        case _ => println("You entered an incorrect command.")
      }
    } catch {
      case e: InputMismatchException => println("Fatal error. You did not input an integer.")
    }

  }

  def runGame() = {

    println("|--------|--------|                    ")
    println("|   0    |   00   |                    ")
    println("|________|________|_________|          ")
    println("|  1R |  2B |  3R | 1st12   |---------|")
    println("|  4B |  5R |  6B |---------|  EVEN   |")
    println("|  7R |  8B |  9R | 1to18   |---------|")
    println("| 10B | 11B | 12R |---------|  RED    |")
    println("| 13B | 14R | 15B | 2nd12   |---------|")
    println("| 16R | 17B | 18R |---------|  BLACK  |")
    println("| 19R | 20B | 21R | 19to36  |---------|")
    println("| 22B | 23R | 24B |---------|  ODD    |")
    println("| 25R | 26B | 27R | 3rd12   |---------|")
    println("| 28B | 29B | 30R |---------|          ")
    println("| 31B | 32R | 33B |                    ")
    println("| 34R | 35B | 36R |                    ")
    println("|_____|_____|_____|   _________________  ")
    println(s"|2to1a|2to1b|2to1c|   | BANK: ${formatter.format(bank)} |   ")
    println("|_____|_____|_____|   |_______________|  ")
    println()
    println("How much are you willing to bet?")
    print("$")
    val betAmount = sc.nextInt()
    if(betAmount > bank){
      println("You must bet a value less than or equal to the value of your bank.")
      System.exit(0)
    }
    println("For what space are you betting on?")
    val spaceSelected = sc.next()
    println(s"You are betting ${formatter.format(betAmount)} on $spaceSelected. Good luck!")
    dropBall(spaceSelected, betAmount)
  }


  def dropBall(spaceSelected:String, betAmount: Int) = {
    val random:Int = ((Math.random()*38).toInt)
    val rouletteWheel = Map(
      0 -> "0", 1 -> "00", 2 -> "1R", 3 -> "2B", 4 -> "3R", 5 -> "4B", 6 -> "5R", 7 -> "6B", 8 -> "7R", 9 -> "8B",  10 -> "9R",
      11 -> "10B", 12 -> "11B", 13 -> "12R", 14 -> "13B", 15 -> "14R", 16 -> "15B", 17 -> "16R", 18 -> "17B", 19 -> "18R", 20 -> "19R",
      21 -> "20B", 22 -> "21R", 23 -> "22B", 24 -> "23R", 25 -> "24B", 26 -> "25R", 27 -> "26B", 28 -> "27R", 29 -> "28B", 30 -> "29B",
      31 -> "30R", 32 -> "31B", 33 -> "32R", 34 -> "33B", 35 -> "34R", 36 -> "35B", 37 -> "36R"
    )
    val ballResult = rouletteWheel(random)
    val win = betResult(spaceSelected, ballResult, random)
    println("                       |-----|")
    println("The ball landed in:    | " + ballResult + " |")
    println("                       |-----|")


    if(win){this.bank += betAmount}
    else {this.bank -= betAmount}
    println("|-------------------------------|")
    println("| Remaining Balance: " + formatter.format(this.bank) + "     |")
    println("|-------------------------------|")
  }

  def betResult(spaceSelected:String, ballResult: String, random: Int):Boolean = {
    var odd = false
    var even = false
    var zero = false
    var doubleZero = false
    var red = false
    var black = false
    var first12 = false
    var second12 = false
    var third12 = false
    var oneTo18 = false
    var nineteenTo36 = false


    if (random > 1 && (random % 2 == 0)) {odd = true}
    else if (random > 1) {even = true}
    else if (random == 0) {zero = true}
    else {doubleZero = true}

    if((random > 1 && random <= 10 && (random % 2) == 0) ||
      (random > 12 && random <= 19 && (random % 2) == 1) ||
      (random > 20 && random <= 28 && (random % 2) == 0) ||
      (random > 30 && random <= 37 && (random % 2) == 1)) {red = true}
    else if(random > 1) {black = true}

    if (random > 1 && random <= 13) {first12 = true}
    else if (random > 13 && random <= 25) {second12 = true}
    else if (random > 25 && random <= 37) {third12 = true}

    if (random > 1 && random <= 19) {oneTo18 = true}
    else if (random > 19 && random <= 37) {nineteenTo36 = true}

    // Compare spaceSelected to bools
    if ((spaceSelected == "EVEN") && even) {return true}
    else if ((spaceSelected == "ODD") && odd) {return true}

    if ((spaceSelected == "BLACK") && black) {return true}
    else if((spaceSelected == "RED") && red) {return true}


    false
  }



}
