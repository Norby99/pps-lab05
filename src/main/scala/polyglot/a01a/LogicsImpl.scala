package polyglot.a01a

import util.Sequences.Sequence
import polyglot.a01a.Logics
import polyglot.a01a.Logics.Result

import javax.swing.text.Position

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a01a/sol2/ */
class LogicsImpl(private val size: Int, private val boatLength: Int) extends Logics:
  
  private class Boat(private val boardHeight: Int, private val boardLength: Int, private val boatLength: Int):
    private val seed: Int = 42
    private val random = scala.util.Random(seed)
    private val height: Int = random.between(0, boardHeight)
    
    private val horizontalOffset: Int = random.between(0, boardLength - boatLength)
    
    val positions: Sequence[Position] =
      var tPos: Sequence[Position] = Sequence()
      0 until boatLength foreach { n => tPos = tPos.concat(Sequence((height, n + horizontalOffset)))}
      tPos
  
    println(positions)
  
  private val boat: Boat = Boat(size, size, boatLength)
  private val maxMisses: Int = 5
  
  opaque type Position = (Int, Int)
  
  private var hitCounter: Int = 0
  private var missCounter: Int = 0
  
  def hit(row: Int, col: Int) =
    if boat.positions.contains((row, col)) then
      hitCounter += 1
      if hitCounter >= boatLength then
        Result.WON
      else
        Result.HIT
    else
      missCounter += 1
      if missCounter >= maxMisses then
        Result.LOST
      else
        Result.MISS
