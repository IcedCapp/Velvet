package velvet.util.types.spatial

import java.awt.Dimension
import kotlin.math.max
import kotlin.random.Random

class Size private constructor(val width: Int, val height: Int) : TwoDimensionalInt{

    companion object{
        operator fun invoke() = Size(1, 1)
        operator fun invoke(width: Int, height: Int) = Size(max(width, 1), max(height, 1))
        operator fun invoke(size: Int) = invoke(size, size)
    }

    override val xComponent get() = width
    override val yComponent get() = height

    //properties
    val area get() = width*height
    val perimeter get() = (width+height)*2

    //utility
    operator fun contains(p: Position) = p.x>=0 && p.y>=0 && p.x<width && p.y<height
    operator fun contains(region: Region) = region.topLeft in this && region.bottomRight-1 in this

    fun toIndex(p: Position) = (p.x + p.y*width)
    fun toIndexOrNull(p: Position) = toIndex(p).takeIf { p in this }
    fun fromIndex(index: Int) = Position(index%width, index/width)
    fun fromIndexOrNull(index: Int) = Position(index%width, index/width).takeIf { index in 0 until area }

    fun positions() = (0 until area).asSequence().map(::fromIndex)

    fun randomWithin() = Position(Random.nextInt(width), Random.nextInt(height))

    operator fun plus(td: TwoDimensionalInt) = invoke(width+td.xComponent, height+td.yComponent)
    operator fun plus(td: Int) = invoke(width+td, height+td)

    operator fun minus(td: TwoDimensionalInt) = invoke(width-td.xComponent, height-td.yComponent)
    operator fun minus(td: Int) = invoke(width-td, height-td)

    operator fun times(td: TwoDimensionalInt) = invoke(width*td.xComponent, height*td.yComponent)
    operator fun times(td: Int) = invoke(width*td, height*td)

    operator fun div(td: TwoDimensionalInt) = invoke(width/td.xComponent, height/td.yComponent)
    operator fun div(td: Int) = invoke(width/td, height/td)

    override fun toString(): String {
        return "Size(width=$width, height=$height)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Size) return false

        if (width != other.width) return false
        if (height != other.height) return false

        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        return result
    }
}

fun Dimension.toSize() = Size(width, height)