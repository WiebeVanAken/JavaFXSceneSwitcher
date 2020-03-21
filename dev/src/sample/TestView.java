package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sceneswitcher.IEventPane;

public class TestView extends GridPane implements IEventPane {
    Button btnTest;



    public TestView()
    {
        btnTest = new Button("Test!");

        add(btnTest, 0, 0);
    }

    @Override
    public void onFocusGained(Object... a_data)
    {

    }

    @Override
    public void onFocusLost() {

    }

    @Override
    public void onStop() {
        System.out.println("Stop");
    }
}
