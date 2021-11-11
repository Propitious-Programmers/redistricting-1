import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.GridLayout;

public class GraphicDrawTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        int numCols = 5;
        int numRows = 5;
        int NUM_CELLS = numCols * numRows;
        final int CELL_EDGE = 20; //20 pix cell edge size
        final int BORDER_WIDTH = 1;
        int imgWidth = CELL_EDGE * numCols;
        int imgHeight = CELL_EDGE * numRows;
        GridLayout region = new GridLayout(numRows, numCols, BORDER_WIDTH, BORDER_WIDTH);
        BufferedImage img = createImage(CELL_EDGE, CELL_EDGE, Color.black);
        frame.setLayout(region);
        frame.getContentPane().setLayout(new FlowLayout());
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numCols; y++) {
                if (y % 2 == 1 && x % 2 != 1) {
                    JLabel image = new JLabel(new ImageIcon(createImage(CELL_EDGE, CELL_EDGE, Color.black)));
                    frame.add(image, x, y);
                } else if (y % 2 == 0 && x % 2 == 1) {
                    JLabel image = new JLabel(new ImageIcon(createImage(CELL_EDGE, CELL_EDGE, Color.black)));
                    frame.add(image, x, y);
                } else {
                    JLabel image = new JLabel(new ImageIcon(createImage(CELL_EDGE, CELL_EDGE, Color.white)));
                    frame.add(image, x, y);
                }
            }
        }
        frame.pack();
        frame.setVisible(true);
    }

    private static BufferedImage createImage(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return image;
    }
}
