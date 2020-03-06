package sceneswitcher;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class View
{
    private Scene m_scene;
    private Pane m_pane;

    public View(Pane a_pane, int a_width, int a_height)
    {
        m_scene = new Scene(a_pane, a_width, a_height);
        m_pane = a_pane;
    }

    // Get the scene of this view
    public Scene getScene()
    {
        return m_scene;
    }

    // Get the pane of this view
    public Pane getPane()
    {
        return m_pane;
    }

    /*
        Call a specific event on this pane.
        A pane must implement IEventPane, if it doesn't. The event is not called
        The a_eventID is equal to the function name.
        a_params is the extra paremeters / data that has to be passed to the view
    */
    public void callEvent(String a_eventID, Object... a_params)
    {
        switch (a_eventID)
        {
            case "onFocusGained":
                if(m_pane != null)
                    if(m_pane instanceof IEventPane)
                        ((IEventPane)m_pane).onFocusGained(a_params);
                break;
            case "onFocusLost":
                if(m_pane != null)
                    if(m_pane instanceof IEventPane)
                        ((IEventPane)m_pane).onFocusLost();
                break;
        }
    }
}
