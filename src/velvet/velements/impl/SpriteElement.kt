package velvet.velements.impl

import velvet.main.VGraphics
import velvet.main.game.graphics.Sprite
import velvet.structs.Vector
import velvet.velements.VElement

class SpriteElement (var sprite: Sprite? = null) : VElement {

    override val size: Vector?
        get() = sprite?.size?.vector

    override fun render(g: VGraphics, targetSize: Vector) {
        sprite?.let {
            g.save()
            g.scale(targetSize / it.size.vector)
            g.drawSprite(it)
            g.reset()
        }
    }
}