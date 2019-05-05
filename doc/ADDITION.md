## Before
I'm going to add the view that shows all the turtles' images and allows the user to change a single turtle's image.

I think this will take around 25 minutes to do, mainly because I'll need to think of how to create the JavaFX aspects of the feature.

I don't think what I'm trying to make could subclass the drop down, text window, or slider abstract Feature classes we made, so 
I think I'll only to extend the abstract Feature class. I think all the states I'll need for images and their corresponding turtles are in the State Manager, 
so I'll really only need to make this feature class. 
Then I'll have to include the feature's display name in one of the properties files so that the display can add the feature to its customization screen.



## After
It took me an hour to complete this new feature, a little longer than I expected to figure out the JavaFX stuff. 
As I guessed though, I only had to make one class, which I refactored to have a helper class (TurtleIcon which had a VBox of a Label, ImageView, and ComboBox).
I also moved the list of image file names from a TurtleImageSelector we made in sprint three (modified all turtle images at once) to the TurtleManager class. 

I got it completely right on my first try, minus a typo in the features.properties file.

It was easy to remember that I needed to add a Feature, and I remembered the properties file before I started coding or looking at the documentation.
Even if I had forgotten, the documentation in the DESIGN.md explains how to do the process.

This was honestly really fun to add because it was just adding a new whistle to the project by simply making a Feature. 
This design is incredibly extendable. The only area I could see the design improving would be eliminating the need to even have a properties file 
by using the ClassGrabber I made in vooga to get all Feature subclasses (the ones that aren't abstract) and converting their simple names into labels, and conversely creating the class from the label. 
This would take a good amount of time refactoring for barely any reward though, since that would require bringing in the ClassGrabber and creating an equivalent of ResourceBundleHelper that uses the grabber. 

If I wasn't familiar with my code, I think it would have taken a little time to realize that I should get the turtle's image property for each icon and get/set/add a change listener to it. 
Still, I think if another programmer understood JavaFX bindings and JavaFX frontend basics, it would have taken them under an hour and a half to do the same task.
