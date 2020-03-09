package sample;

import javafx.scene.layout.GridPane;
import sceneswitcher.IEventPane;

public class TestView extends GridPane implements IEventPane {
    @Override
    public void onFocusGained(Object... a_data) {

    }

    @Override
    public void onFocusLost() {

    }

    @Override
    public void onStop() {
        System.out.println("Stop");
    }
}
