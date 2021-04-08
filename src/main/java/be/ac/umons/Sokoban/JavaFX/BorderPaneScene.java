package be.ac.umons.Sokoban.JavaFX;

import javafx.scene.Scene;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class BorderPaneScene {
    protected final static int COLUMNS = 16;
    protected final static int ROWS = 9;
    protected final static int SIZE = 64;
    protected final static int LEFT_MARGIN = 30;

    protected final static Paint lightOrangePaint = Color.valueOf("#FA8132");
    protected final static Paint darkOrangePaint = Color.valueOf("#E86A17");
    protected final static Paint lightBluePaint = Color.valueOf("#1EA7E1");
    protected final static Paint darkBluePaint = Color.valueOf("#1989B8");
    protected final static Paint grayPaint = Color.valueOf("#808080");
    protected final static Paint greenPaint = Color.valueOf("#77D04F");

    protected final static BackgroundFill bgFillLightBlue =
            new BackgroundFill(lightBluePaint, new CornerRadii(1), null);
    protected final static BackgroundFill bgFillDarkOrange =
            new BackgroundFill(darkOrangePaint, new CornerRadii(1), null);
    protected final static BackgroundFill bgFillGray =
            new BackgroundFill(grayPaint, new CornerRadii(1), null);
    protected final static BackgroundFill bgFillDarkBlue =
            new BackgroundFill(darkBluePaint, new CornerRadii(1), null);
    protected final static BackgroundFill bgFillGreen =
            new BackgroundFill(greenPaint, new CornerRadii(1), null);

    protected final static SpriteIcon iconGiver = new SpriteIcon("images/sheet_black1x.png", 50);
    protected final static SpriteGame cellGiver = new SpriteGame();

    // Non-static
    public final BorderPane root = new BorderPane();
    public final Scene rootScene = new Scene(root);

    private void centerGenesis(int row, int col){}
    private void centerGenesis(){}
    private void rightGenesis(){}
    private void bottomGenesis(){}
    private void leftGenesis(){}
    private void topGenesis(){}
}
