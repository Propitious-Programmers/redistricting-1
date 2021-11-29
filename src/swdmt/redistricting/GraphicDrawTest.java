package swdmt.redistricting;
import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Set;
import java.awt.GridLayout;
import java.util.Map;
import java.util.TreeMap;

/**
 *  Displays the voter map.
 * @author Henry Callin
 * @version 11/28/2021
 */
public class GraphicDrawTest {
    /**
     * Display the voter map with JFrame window.
     * @param region
     * @param district
     */
    public static void main(Region region, Set<District> district) {
        //Finds the number of rows and columns(in case they change in the future).
        Collection<Location> locs = region.locations();
        Location locWithMinimumX = locs
                .stream()
                .min((loc1, loc2) -> Integer.compare(loc1.xCoordinate(),
                        loc2.xCoordinate()))
                .orElse(null);
        Location locWithMinimumY = locs
                .stream()
                .min((loc1, loc2) -> Integer.compare(loc1.yCoordinate(),
                        loc2.yCoordinate()))
                .orElse(null);
        Location locWithMaximumX = locs
                .stream()
                .max((loc1, loc2) -> Integer.compare(loc1.xCoordinate(),
                        loc2.xCoordinate()))
                .orElse(null);
        Location locWithMaximumY = locs
                .stream()
                .max((loc1, loc2) -> Integer.compare(loc1.yCoordinate(),
                        loc2.yCoordinate()))
                .orElse(null);

        // Construct location:voter mapping.
        Map<Location, Voter> voterMap = new TreeMap<>();
        for (Voter vot : region.voters()) {
            voterMap.put(vot.location(), vot);
        }
        //Assigns number of rows and columns.
        int numRows = 1 + locWithMaximumY.yCoordinate()
                - locWithMinimumY.yCoordinate();
        int numCols = 1 + locWithMaximumX.xCoordinate()
                - locWithMinimumX.xCoordinate();
        //Initializes JFrame display and squares.
        JFrame frame = new JFrame();
        int NUM_CELLS = region.size();
        final int CELL_EDGE = 20; //20 pix cell edge size
        final int BORDER_WIDTH = 1;
        GridLayout display = new GridLayout(numRows, numCols);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(display);
        int i = NUM_CELLS;
        //Assigns colors to squares depending on voter affiliation.
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                Location currentLoc = new Location(c, r);
                if (locs.contains(currentLoc)) {
                    if (voterMap.keySet().contains(currentLoc)) {
                        if (voterMap.get(currentLoc).affiliation().id() == '1') {
                            JButton b = new JButton("");
                            b.setBackground(Color.RED);
                            b.setOpaque(true);
                            frame.add(b);
                            //b.setBorder(BorderFactory.createLineBorder
                            //(Color.black, 8));
                            i--;
                        } else {
                            JButton b = new JButton("");
                            b.setBackground(Color.BLUE);
                            b.setOpaque(true);
                            frame.add(b);
                            i--;
                        }
                    }
                }
            }
        }
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

}
