package ca.ucalgary.main;

import java.awt.Graphics;
import java.awt.Color;

//public interface Board {
//    public void draw (String symbol, int x, int y);
//
//}

public class Board {
    
//public class GooeyBoard implements Board {

    private Graphics graphics;
    
    public Board(Graphics graphics) {
        this.graphics = graphics;
      
    }

    public void draw (String symbol, int x, int y) {
        Color color = Color.BLACK;
        if (symbol == "|") {
            color = Color.GREEN;
        }  else if (symbol == "V") {
            color = Color.RED;
        } else if (symbol == "$") {
            color = Color.YELLOW;
        }  else if (symbol == "A") {
            color = Color.BLUE;
        } else if ("*".equals(symbol)) {
            color = Color.ORANGE;
        }

        graphics.setColor(color);
        graphics.fillRect(x * 50, y * 50, 50, 50);
    }
}
