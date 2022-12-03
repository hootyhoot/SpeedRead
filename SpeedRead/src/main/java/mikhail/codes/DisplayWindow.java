package mikhail.codes;

import java.awt.*;

public class DisplayWindow extends Canvas {
    public String textToDisplay;
    public void paint(Graphics g) {
        Dimension d = this.getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));

        drawCenteredString(textToDisplay, d.width, d.height, g);
        g.drawRect(0, 0, d.width - 1, d.height - 1);
    }
    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }
}
