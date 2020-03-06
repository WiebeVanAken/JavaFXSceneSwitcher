# JavaFXSceneSwitcher

A JavaFX scene / pane UI switcher
Made for school.
For the lonely soul who'd ever want to use this code, do whatever you want with this code.

WHAT IS IT
This is a JavaFX pane / scene switcher.
It provides a global system for switching panes in the application. 

HOW TO USE
Example main function
```java
@Override
public void start(Stage primaryStage) throws Exception
{
	ViewController.setStage(primaryStage);
	ViewController.addView(new View(new ExampleView(), 100, 100), "ExampleView");
	ViewController.show("ExampleView");
}
```

What happens here is the following.
1. The ViewController gets a Stage to work with.
2. The ViewController gets all the views that it needs to work with.
	Every view has a Pane, width & height and a key to index this view
3. A view gets shown by ViewController.show("key")

Example pane
```java
public class ExampleView extends GridPane implements IEventPane
{
    Label lbl_title;
    TextField txt_name;

    public ExampleView()
    {
	lbl_title = new Label("Example view");
	txt_name  = new TextField("Your name here");

	this.add(lbl_title, 0, 0);
	this.add(txt_name, 0, 0);
    }

    @Override
    public void onFocusGained(Object... a_data)
    {
	txt_name.setText("Perensoep109");
    }

    @Override
    public void onFocusLost()
    {
		
    }
}
```

What happens here is the following:
* A simple basic JavaFX pane gets made.
* This pane also implements IEventPane. IEventPane is an interface which holds pane events.
* These events get called when a pane gains or loses focus.
* A pane does not have to implement IEventPane. If it doesn't, it doesn't work with the event system that I've made. It's entirely optional

About passing data to another view
Passing data around to another view is done by adding additional parameters to ViewController.show, this data can be accessed in the onFocusGained function in a pane.
