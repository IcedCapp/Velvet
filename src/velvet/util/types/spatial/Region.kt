package velvet.util.types.spatial

import kotlin.math.max
import kotlin.math.min

class Region private constructor(val topLeft: Position, val size: Size) {

    companion object{
        fun fromStartOfSize(position: Position, size: Size) = Region(position, size)
        fun fromStartToEnd(start: Position, end: Position) = Region(start, (end - start).toSize())
    }

    val bounds get() = Bounds.fromStartOfSize(topLeft.vector, size.toArea())

    val bottomRight: Position get() = topLeft + Position(size.width, size.height)

    operator fun contains(position: Position) = position-topLeft in size

    fun positions() = size.positions().map { it + topLeft }
    fun randomWithin() = size.randomWithin() + topLeft

    override fun toString(): String {
        return "Region(topLeft=$topLeft, size=$size)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Region) return false

        if (topLeft != other.topLeft) return false
        if (size != other.size) return false

        return true
    }

    override fun hashCode(): Int {
        var result = topLeft.hashCode()
        result = 31 * result + size.hashCode()
        return result
    }
}

fun Collection<Position>.toRegion() = Region.fromStartToEnd(
        reduce { acc, p -> acc.fold(p, ::min) },
        reduce { acc, p -> acc.fold(p, ::max) })