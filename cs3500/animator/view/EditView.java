package cs3500.animator.view;

import cs3500.animator.model.Motion;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.OurShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import cs3500.animator.model.ShapeDetailsTick;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Represents a visual view that allows for editing, change of speed, pausing, looping, etc.
 */
public class EditView extends JFrame implements EditInterface, ActionListener {
  private VisualPanel visualPanel;
  private JPanel buttonPanel;

  private JPanel keyframePanel;
  private JComboBox keyFrameBox;

  private JTextField speedArea;
  private JTextField currentSpeed;
  private JTextField loopingOn;

  private int width;
  private int height;
  private int speed;
  private boolean isPaused;
  private boolean isRestarted;
  private boolean isLooping;

  private List<ShapeDetails> shapeNames;
  private JTextField permanentInputTick;
  private JTextField inputTick;
  private JTextField permanentInputXY;
  private JTextField inputXY;
  private JTextField permanentInputWidthHeight;
  private JTextField inputWidthHeight;
  private JTextField permanentInputColor;
  private JTextField inputColor;
  private JTextField permanentInputShapeName;
  private JTextField inputShapeName;
  private JComboBox shapeList;
  private JComboBox optionsBox;

  private ShapeDetailsTick inputFrame;
  private ShapeDetails inputShape;
  private OurDimensions editDimensions;
  private OurColor editColor;
  private Position2D editPosition;
  private boolean shapeAdded;
  private boolean shapeRemoved;
  private boolean frameAdded;
  private boolean frameRemoved;
  private boolean frameEdited;

  /**
   * Creates an EditView. Allows for the creation and editing of a view with shapes.
   */
  public EditView() {
    super();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.width = 0;
    this.height = 0;
    this.speed = 1;
    this.isPaused = false;
    this.isRestarted = false;
    this.isLooping = true;

    this.inputFrame = null;
    this.inputShape = null;
    this.shapeAdded = false;
    this.shapeRemoved = false;
    this.frameAdded = false;
    this.frameRemoved = false;

    this.editColor = null;
    this.editDimensions = null;
    this.editPosition = null;

    this.visualPanel = new VisualPanel();

    this.setKeyFramePanel();
    this.setButtons();

    //Makes scroll bar
    JScrollPane scroll = new JScrollPane(visualPanel);
    scroll.getVerticalScrollBar().setUnitIncrement(16);
    scroll.getHorizontalScrollBar().setUnitIncrement(16);

    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scroll, BorderLayout.EAST);
    this.add(keyframePanel, BorderLayout.NORTH);

    setVisible(true);
    pack();
  }

  private void setKeyFramePanel() {
    this.keyframePanel = new JPanel();
    this.keyframePanel.setLayout(new FlowLayout());
    this.keyframePanel.setPreferredSize(new Dimension(width, 60));

    keyFrameBox = new JComboBox();
    keyFrameBox.addActionListener(this);
    keyFrameBox.setEditable(false);

    this.permanentInputTick = new JTextField("Tick: ");
    this.permanentInputTick.setPreferredSize(new Dimension(50, 20));
    this.permanentInputTick.setEditable(false);
    this.permanentInputTick.setVisible(true);

    this.inputTick = new JTextField();
    this.inputTick.setPreferredSize(new Dimension(40, 20));
    this.inputTick.setEditable(true);
    this.inputTick.setVisible(true);


    this.permanentInputXY = new JTextField("Position (x y): ");
    this.permanentInputXY.setPreferredSize(new Dimension(100, 20));
    this.permanentInputXY.setEditable(false);
    this.permanentInputXY.setVisible(true);

    this.inputXY = new JTextField();
    this.inputXY.setPreferredSize(new Dimension(50, 20));
    this.inputXY.setEditable(true);
    this.inputXY.setVisible(true);


    this.permanentInputWidthHeight = new JTextField("Dimensions (w h): ");
    this.permanentInputWidthHeight.setPreferredSize(new Dimension(130, 20));
    this.permanentInputWidthHeight.setEditable(false);
    this.permanentInputWidthHeight.setVisible(true);

    this.inputWidthHeight = new JTextField();
    this.inputWidthHeight.setPreferredSize(new Dimension(50, 20));
    this.inputWidthHeight.setEditable(true);
    this.inputWidthHeight.setVisible(true);


    this.permanentInputColor = new JTextField("Color (r g b): ");
    this.permanentInputColor.setPreferredSize(new Dimension(95, 20));
    this.permanentInputColor.setEditable(false);
    this.permanentInputColor.setVisible(true);

    this.inputColor = new JTextField();
    this.inputColor.setPreferredSize(new Dimension(70, 20));
    this.inputColor.setEditable(true);
    this.inputColor.setVisible(true);

    this.permanentInputShapeName = new JTextField("Name: ");
    this.permanentInputShapeName.setPreferredSize(new Dimension(60, 20));
    this.permanentInputShapeName.setEditable(false);

    this.inputShapeName = new JTextField();
    this.inputShapeName.setPreferredSize(new Dimension(40, 20));
    this.inputShapeName.setEditable(true);

    String[] shapeStrings = { "RECTANGLE", "ELLIPSE" };

    this.shapeList = new JComboBox(shapeStrings);
    this.shapeList.addActionListener(this);
    this.shapeList.setEditable(false);


    String[] editRemove = {"Add KF", "Edit KF", "Remove KF", "Create Shape", "Remove Shape"};
    this.optionsBox = new JComboBox(editRemove);
    this.optionsBox.setSelectedIndex(1);
    this.optionsBox.setEditable(false);

    this.keyframePanel.add(optionsBox);
    this.keyframePanel.add(keyFrameBox);

    this.keyframePanel.add(permanentInputTick);
    this.keyframePanel.add(inputTick);

    this.keyframePanel.add(permanentInputXY);
    this.keyframePanel.add(inputXY);

    this.keyframePanel.add(permanentInputWidthHeight);
    this.keyframePanel.add(inputWidthHeight);

    this.keyframePanel.add(permanentInputColor);
    this.keyframePanel.add(inputColor);

    this.keyframePanel.add(permanentInputShapeName);
    this.keyframePanel.add(inputShapeName);
    this.keyframePanel.add(shapeList);

    this.permanentInputShapeName.setVisible(false);
    this.inputShapeName.setVisible(false);
    this.shapeList.setVisible(false);

    optionsBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String selectedItem = (String) optionsBox.getSelectedItem();
        switch (selectedItem) {
          case "Add KF":
          case "Remove KF":
            keyFrameBox.setVisible(true);
            permanentInputTick.setVisible(true);
            inputTick.setVisible(true);

            permanentInputXY.setVisible(false);
            inputXY.setVisible(false);
            permanentInputWidthHeight.setVisible(false);
            inputWidthHeight.setVisible(false);
            permanentInputColor.setVisible(false);
            inputColor.setVisible(false);

            permanentInputShapeName.setVisible(false);
            inputShapeName.setVisible(false);
            shapeList.setVisible(false);

            break;
          case "Edit KF":
            keyFrameBox.setVisible(true);
            permanentInputTick.setVisible(true);
            inputTick.setVisible(true);

            permanentInputXY.setVisible(true);
            inputXY.setVisible(true);
            permanentInputWidthHeight.setVisible(true);
            inputWidthHeight.setVisible(true);
            permanentInputColor.setVisible(true);
            inputColor.setVisible(true);

            permanentInputShapeName.setVisible(false);
            inputShapeName.setVisible(false);
            shapeList.setVisible(false);

            break;

          case "Create Shape":
            keyFrameBox.setVisible(false);
            permanentInputTick.setVisible(false);
            inputTick.setVisible(false);
            permanentInputXY.setVisible(false);
            inputXY.setVisible(false);
            permanentInputWidthHeight.setVisible(false);
            inputWidthHeight.setVisible(false);
            permanentInputColor.setVisible(false);
            inputColor.setVisible(false);

            permanentInputShapeName.setVisible(true);
            inputShapeName.setVisible(true);
            shapeList.setVisible(true);

            break;
          case "Remove Shape":
            keyFrameBox.setVisible(true);
            permanentInputTick.setVisible(false);
            inputTick.setVisible(false);
            permanentInputXY.setVisible(false);
            inputXY.setVisible(false);
            permanentInputWidthHeight.setVisible(false);
            inputWidthHeight.setVisible(false);
            permanentInputColor.setVisible(false);
            inputColor.setVisible(false);

            permanentInputShapeName.setVisible(false);
            inputShapeName.setVisible(false);
            shapeList.setVisible(false);
            break;
          default:
            //nothing should be done here
            break;
        }
        keyframePanel.revalidate();
        keyframePanel.repaint();
      }
    });

    JButton kfGoButton = new JButton("ENTER");
    kfGoButton.setActionCommand("ENTER");
    kfGoButton.addActionListener(this);
    keyframePanel.add(kfGoButton);

  }

  /**
   * Sets the buttons and text areas as required when the EditView is constructed.
   */
  private void setButtons() {
    JTextField speedWord = new JTextField("Set Speed:");
    JTextField currSpeed = new JTextField("Current Speed:");
    JTextField currLoop = new JTextField("Current Looping:");
    speedWord.setEditable(false);
    currSpeed.setEditable(false);
    currLoop.setEditable(false);

    this.buttonPanel = new JPanel();

    this.loopingOn = new JTextField("ON");
    this.loopingOn.setEditable(false);
    this.loopingOn.setPreferredSize(new Dimension(40, 20));

    JButton loopButton = new JButton("Looping");
    JButton startButton = new JButton("Start/Restart");
    JButton pauseButton = new JButton("Pause/Resume");

    loopButton.setActionCommand("Loop");
    startButton.setActionCommand("Start");
    pauseButton.setActionCommand("Pause");

    loopButton.addActionListener(this);
    startButton.addActionListener(this);
    pauseButton.addActionListener(this);

    this.currentSpeed = new JTextField("" + this.speed);
    this.currentSpeed.setPreferredSize(new Dimension(40, 20));
    this.currentSpeed.setEditable(false);

    this.speedArea = new JTextField();
    this.speedArea.setPreferredSize(new Dimension(40, 20));
    this.speedArea.setEditable(true);

    this.speedArea.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          setSpeed(Integer.parseInt(speedArea.getText()));
        }
        catch (NumberFormatException nfe) {
          // Does not require any action if no valid number input
        }

        currentSpeed.setText("" + speed);
        speedArea.setText("");
      }
    });

    this.buttonPanel.add(currLoop);
    this.buttonPanel.add(this.loopingOn);
    this.buttonPanel.add(loopButton);
    this.buttonPanel.add(startButton);
    this.buttonPanel.add(pauseButton);
    this.buttonPanel.add(currSpeed);
    this.buttonPanel.add(this.currentSpeed);
    this.buttonPanel.add(speedWord);
    this.buttonPanel.add(this.speedArea);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    // does not run in typical use, only for tests primarily
    if (width <= 0 || height <= 0) {
      return new Dimension(1000, 1000);
    }
    return new Dimension(this.width, this.height);
  }

  @Override
  public void setKeyFrames(List<ShapeDetails> shapeNames) {
    this.shapeNames = shapeNames;

    this.keyframePanel = new JPanel();

    List<String> shapeStrings = new ArrayList<String>();

    keyFrameBox.removeAllItems();
    for (int i = 0; i < shapeNames.size(); i++) {
      if (!shapeStrings.contains(shapeNames.get(i).getName())) {
        shapeStrings.add(shapeNames.get(i).getName());
        keyFrameBox.addItem(shapeNames.get(i).getName());
      }
    }

    keyFrameBox.addActionListener(this);
    keyFrameBox.setEditable(false);

    keyframePanel.revalidate();
    keyframePanel.repaint();
  }

  @Override
  public void resetInputBooleans() {
    this.inputFrame = null;
    this.inputShape = null;
    this.editDimensions = null;
    this.editPosition = null;
    this.editColor = null;
    this.shapeAdded = false;
    this.shapeRemoved = false;
    this.frameAdded = false;
    this.frameRemoved = false;
    this.frameEdited = false;
  }

  @Override
  public ShapeDetailsTick getInputFrame() {
    return this.inputFrame;
  }

  @Override
  public ShapeDetails getInputShape() {
    return this.inputShape;
  }

  @Override
  public Position2D getEditPos() {
    return this.editPosition;
  }

  @Override
  public OurDimensions getEditDimensions() {
    return this.editDimensions;
  }

  @Override
  public OurColor getEditColor() {
    return this.editColor;
  }

  @Override
  public boolean getShapeAdded() {
    return this.shapeAdded;
  }

  @Override
  public boolean getShapeRemoved() {
    return this.shapeRemoved;
  }

  @Override
  public boolean getFrameAdded() {
    return this.frameAdded;
  }

  @Override
  public boolean getFrameRemoved() {
    return this.frameRemoved;
  }

  @Override
  public boolean getFrameEdited() {
    return this.frameEdited;
  }

  @Override
  public void addShapes(List<ShapeDetails> shapes) {
    this.visualPanel.setShapes(shapes);
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.width = width;
    this.height = height;
    this.visualPanel.setCanvas(x, y, width, height);
  }

  @Override
  public void setMotions(List<Motion> motions) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Unsupported operation for EditView");
  }

  @Override
  public String getTextualRepresentation() {
    return null;
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
    this.currentSpeed.setText("" + speed);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Loop":
        if (this.isLooping) {
          this.loopingOn.setText("OFF");
        }
        else {
          this.loopingOn.setText("ON");
        }
        this.isLooping = !this.isLooping;
        break;
      case "Pause":
        this.isPaused = !this.isPaused;
        break;
      case "Start":
        this.isPaused = false;
        this.isRestarted = true;
        break;
      case "ENTER":
        this.kfPanelEntered();
        break;
      default:
        //nothing should be done here
        break;
    }
  }

  /**
   * Performs the operations involved with adding, removing, or editing keyframes,
   *                      or adding or removing shapes.
   */
  private void kfPanelEntered() {
    String selectedItem = (String) optionsBox.getSelectedItem();

    switch (selectedItem) {
      case "Add KF":
        this.addKeyFrame();
        break;
      case "Edit KF":
        this.editKeyFrame();
        break;
      case "Remove KF":
        this.removeKeyFrame();
        break;
      case "Create Shape":
        this.addShape();
        break;
      case "Remove Shape":
        this.removeShape();
        break;
      default:
        //nothing should be done here
        break;
    }
  }

  /**
   * Adds a shape to the given view if valid.
   */
  private void addShape() {
    String shapeName = null;
    OurShape shapeType = null;

    if (this.inputShapeName.getText() != null && this.shapeList.getSelectedItem() != null) {
      shapeName = (String) this.inputShapeName.getText();
      shapeType = OurShape.valueOf((String) this.shapeList.getSelectedItem());
      this.inputShape = new ShapeDetails(shapeName, shapeType,
          new Position2D(0,0), new OurDimensions(1,1), new OurColor(255,255,255));
      this.shapeAdded = true;
    }

    this.inputShapeName.setText("");
  }

  /**
   * Removes a shape from the view if valid.
   */
  private void removeShape() {
    for (ShapeDetails s : this.shapeNames) {
      if (s.getName().equals((String) this.keyFrameBox.getSelectedItem())) {
        this.inputShape = s;
        this.shapeRemoved = true;
      }
    }
  }

  /**
   * Edits a given keyframe if valid.
   */
  private void editKeyFrame() {
    String tempName = null;
    Position2D tempPos = null;
    OurColor tempColor = null;
    OurShape tempShape = null;
    OurDimensions tempDim = null;
    int tempInt = -1;
    String[] splitStr = null;
    ShapeDetails usableShape = null;

    try {
      tempInt = Integer.parseInt(this.inputTick.getText());
      this.inputTick.setText("");
    } catch (NumberFormatException e) {
      this.inputTick.setText("");
      return;
    }

    for (ShapeDetails s : this.shapeNames) {
      if (s.getName().equals((String) this.keyFrameBox.getSelectedItem())) {
        usableShape = s;
        tempName = s.getName();
        tempShape = OurShape.valueOf(s.getShapeString());
      }
    }

    splitStr = ((String)this.inputXY.getText()).split("\\s+");
    try {
      this.editPosition =
          new Position2D(Integer.parseInt(splitStr[0]), Integer.parseInt(splitStr[1]));
      this.inputXY.setText("");
    }
    catch (NumberFormatException e) {
      this.inputXY.setText("");
    }

    splitStr = ((String)this.inputWidthHeight.getText()).split("\\s+");
    try {
      this.editDimensions =
          new OurDimensions(Integer.parseInt(splitStr[0]), Integer.parseInt(splitStr[1]));
      this.inputWidthHeight.setText("");
    }
    catch (NumberFormatException e) {
      this.inputWidthHeight.setText("");
    }

    splitStr = ((String)this.inputColor.getText()).split("\\s+");
    try {
      this.editColor = new OurColor(Integer.parseInt(splitStr[0]), Integer.parseInt(splitStr[1]),
          Integer.parseInt(splitStr[2]));
      this.inputColor.setText("");
    }
    catch (NumberFormatException e) {
      this.inputColor.setText("");
    }

    if (tempName != null && tempShape != null) {
      this.inputFrame = new ShapeDetailsTick(new ShapeDetails(tempName, tempShape,
          new Position2D(1,1), new OurDimensions(1,1), new OurColor(1,1,1)),
          tempInt);

      this.frameEdited = true;
    }
  }

  /**
   * Removes a given keyframe if valid.
   */
  private void removeKeyFrame() {
    ShapeDetails temp = null;
    int tempInt = -1;
    try {
      tempInt = Integer.parseInt(this.inputTick.getText());
      this.inputTick.setText("");
    } catch (NumberFormatException e) {
      this.inputTick.setText("");
      return;
    }

    for (ShapeDetails s : this.shapeNames) {
      if (s.getName().equals((String) this.keyFrameBox.getSelectedItem())) {
        temp = s;
      }
    }

    if (temp != null && tempInt > 0) {
      this.inputFrame = new ShapeDetailsTick(temp, tempInt);
      this.frameRemoved = true;
    }
  }

  /**
   * Adds a given keyframe if valid.
   */
  private void addKeyFrame() {
    ShapeDetails temp = null;
    int tempInt = -1;
    try {
      tempInt = Integer.parseInt(this.inputTick.getText());
    } catch (NumberFormatException e) {
      this.inputTick.setText("");
      return;
    }

    for (ShapeDetails s : this.shapeNames) {
      if (s.getName().equals((String) this.keyFrameBox.getSelectedItem())) {
        temp = s;
      }
    }

    if (temp != null && tempInt > 0) {
      this.inputFrame = new ShapeDetailsTick(temp.updateShape(null, null,
          new OurColor(255, 255, 255)), tempInt);
      this.frameAdded = true;
    }
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public boolean isRestarted() {
    return isRestarted;
  }

  @Override
  public void restartRestart() {
    this.isRestarted = false;
  }

  @Override
  public boolean isPaused() {
    return isPaused;
  }


  @Override
  public boolean isLooping() {
    return this.isLooping;
  }

}
