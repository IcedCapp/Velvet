package core.main.ui.elements;

import java.awt.Color;

public interface ITextable extends IElement{

    public void setText(String t);
    public void setTextColor(Color c);
    public void setTextSmoothColor(Color c);
    
    public String getText();
    public Color getTextColor();
}