package velvet.vcontainer.premade

import velvet.structs.Vector
import velvet.vcontainer.velement.SquareElement
import velvet.vcontainer.velement.TextElement
import velvet.vcontainer.interact.TextController
import velvet.vcontainer.interact.TrackedVContainer
import velvet.vcontainer.interact.UINode
import velvet.vcontainer.interact.UINodeImpl
import java.awt.Color

class InputFieldNode(promptText: String,
                     defaultInput: String = ""):
        UINode by UINodeImpl() {

    val outlineElement = SquareElement(outlineThickness = 4.0, rounding = 20.0)
    val promptElement = TextElement(promptText, _color = Color(200, 200, 200))
    val inputTextElement = TextElement(defaultInput, fontResolution = 40)

    val textController = TextController(inputTextElement)

    init {
        containers.add(TrackedVContainer(outlineElement){ bounds })
        containers.add(TrackedVContainer(promptElement, true) {
            bounds.scale(Vector(0.3, 1.0), Vector(0))
                    .scale(Vector(0.8), Vector(0.5))
        })
        containers.add(TrackedVContainer(inputTextElement) {
            bounds.scale(Vector(0.7, 1.0), Vector(1))
                    .scale(Vector(0.9), Vector(0.5))
                    .fixRatioElement(it, Vector(0.0, 0.5))
        })

        uiEventListener.onCharTyped = textController::onCharTyped
        uiEventListener.onKeyPressed = textController::onKeyPressed

        var focused = false
        uiEventListener.onFocusStart = {
            focused = true
            outlineElement.outlineColor = Color(150, 200, 200)
        }
        uiEventListener.onFocusEnd = {
            focused = false
            outlineElement.outlineColor = Color.BLACK
        }

        uiEventListener.onHoverStart = {
            if(!focused) {
                outlineElement.outlineColor = Color(150, 150, 150)
            }
        }
        uiEventListener.onHoverEnd = {
            if(!focused) {
                outlineElement.outlineColor = Color.BLACK
            }
        }
    }
}