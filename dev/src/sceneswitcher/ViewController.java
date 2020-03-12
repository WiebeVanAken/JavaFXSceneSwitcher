package sceneswitcher;

import javafx.stage.Stage;

import java.util.HashMap;

public class ViewController
{
    private static ViewController m_con;

    private Stage m_stage;
    private String m_curView;
    private HashMap<String, View> m_views;

    static
    {
        m_con = new ViewController();
    }

    private ViewController()
    {
        m_views = new HashMap<>();
    }

    /*
        Add a new view with a specific ID to the usable views
        A view cannot be added if either the ID or view is invalid
    */
    public static void addView(View a_view, String a_viewID)
    {
        if(!m_con.m_views.containsKey(a_viewID))
        {
            if(a_view == null)
            {
                System.out.println(String.format("ERROR::SCENE::ADD Could not add view %s, view is null", a_viewID));
                return;
            }

            m_con.m_views.put(a_viewID, a_view);
        }
        else
            System.out.println(String.format("ERROR::SCENE::ADD Could not add view %s, view already exists", a_viewID));
    }

    /*
        Remove a view with a specific ID from the usable views
        A view cannot be removed if the view is the current view
     */
    public static void removeView(String a_viewID)
    {
        if(a_viewID == m_con.m_curView)
        {
            System.out.println(String.format("ERROR::SCENE::REMOVE Could not remove view %s, cannot remove the current view", a_viewID));
            return;
        }

        m_con.m_views.remove(a_viewID);
    }

    /*
        Show a specific view with a specific view ID
        Events don't fire if the new view cannot be shown
        a_viewKey is the view to be shown
        a_params is the extra data that has to be passed to the view
    */
    public static void show(String a_viewKey, Object... a_params)
    {
        // Check for errors
        if(m_con.m_stage == null)
        {
            System.out.println(String.format("ERROR::SCENE::SHOW Could not switch to view %s, stage is null", a_viewKey));
            return;
        }

        if(!m_con.m_views.containsKey(a_viewKey))
        {
            System.out.println(String.format("ERROR::SCENE::SHOW Could not switch to view %s, key does not exist", a_viewKey));
            return;
        }

        // Get all required views
        View prevView = m_con.m_views.get(m_con.m_curView);
        View newView = m_con.m_views.get(a_viewKey);

        // Check for errors
        if(newView == null)
        {
            System.out.println(String.format("ERROR::SCENE::SHOW Could not switch to view %s, value does not exist", a_viewKey));
            return;
        }

        // Get the last view, call focus lost event
        if(prevView != null)
            prevView.callEvent("onFocusLost");

        // Set new scene
        newView.callEvent("onFocusGained", a_params);

        m_con.m_curView = a_viewKey;
        m_con.m_stage.setScene(newView.getScene());
        m_con.m_stage.show();
    }

    /*
        Stop the scene switcher
        This fires the onStop event in all the panes in the app
    */
    public static void stop()
    {
        for (View view : m_con.m_views.values())
        {
            view.callEvent("onStop");
        }
    }

    // Set the view stage for views to be hosted in
    public static void setStage(Stage a_stage)
    {
        // Set value
        m_con.m_stage = a_stage;

        // Set exit event (for stopping the application)
        m_con.m_stage.setOnCloseRequest(windowEvent -> stop());
    }

    // Get the view stage in which views are hosted
    public static Stage getStage()
    {
        return m_con.m_stage;
    }

    // Get the current view in focus
    public static View getCurView()
    {
        return m_con.m_views.get(m_con.m_curView);
    }

    // Get the view ID of the current view in focus
    public static String getCurViewKey()
    {
        return m_con.m_curView;
    }
}