package polyglot.a05b

import util.Sequences.Sequence
import polyglot.a05b.Logics
import util.Sequences.Sequence.Cons

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a05b/sol2/ */
class LogicsImpl(private val size: Int) extends Logics:
  
  private val seed: Int = 42
  private val minInnerBorder: Int = 1
  private val maxInnerBorder: Int = size - 1
  
  private var offset: Int = 1
  
  private val random = scala.util.Random(seed)
  private val initCell: Position = (random.between(minInnerBorder, maxInnerBorder), random.between(minInnerBorder, maxInnerBorder))
  private var cells: Sequence[Position] = Sequence(initCell)
  
  opaque type Position = (Int, Int)
  
  override def tick(): Unit =
    for
      dirX <- -1 to 1
      dirY <- -1 to 1
      if !(dirX == 0 && dirY == 0)
    do cells = Cons((initCell._1 + offset * dirX, initCell._2 + offset * dirY), cells)
    offset += 1

  override def isOver: Boolean = !cells.find((x, y) => x < 0 || y < 0).isEmpty
  
  override def hasElement(x: Int, y: Int): Boolean = cells.contains((x, y))
