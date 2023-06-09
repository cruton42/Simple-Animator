//README
Main Method - Excellence
  - We maintained Excellence as the main method, as it required no changes to work properly with the
    edit view.

Changes to the Model
  - New Class called ShapeDetailsTick that extends the ShapeDetails class but also includes a tick
    parameter
    - Used to represent keyframes

Changes to the Controller
  - Slight changes to SimpleAnimatorControllerImpl to allow for new editing view
  - New command interface, abstracted command class, and concrete class for Edit View Command
    - EditCommand handles the brunt of the mathematics, keyframe creation, and changes to view
      through its inputs from the model indirectly through the controller
      and from the view class itself.
      - Handles checking for all possible situations from view, including restarting, looping,
        adding shapes or keyframes, editing keyframes, etc.

Edit View Itself
  - New interface EditInterface that extends IView, concrete class EditView that serves as the view
    frame for the editing.
    - EditView holds all the buttons, text inputs, etc. that send information back to the EditCommand
      and allow for changes to be made/actions to be taken while the view is running.
      - Displays possible text inputs for each button
    - (We sacrificed some GUI design to maintain the size of the view to be as requested by
      the model. As a result, occasionally the view requires slight resizing on the client's end in
      order to properly display change options on top).

How to Use GUI
  - TO ADD SHAPE: select create shape at top left, make name, choose type of shape, select ENTER.
  - TO DELETE SHAPE: select delete shape, choose name of shape, select ENTER.
  - TO ADD KEYFRAME: select add kf, choose shape, choose tick, select ENTER.
  - TO DELETE KEYFRAME: select delete kf, choose shape, choose valid tick of existing keyframe, select ENTER.
  - TO EDIT KEYFRAME: select edit kf, choose shape, choose valid tick for an existing keyframe,
                      choose any or all of rest of text choices
                      (each number separated by spaces) to represent desired new values, select ENTER.


