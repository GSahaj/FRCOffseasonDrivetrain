package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  //Creating instances and objects of main subsystem and controller
  private final Joystick controller = new Joystick(RobotMap.OperatorConstants.JOYSTICK_PORT);
  private final Drivetrain drivetrain = new Drivetrain();

  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  //Setting subsystem to defualt commands 
  private void configureBindings() {
    drivetrain.setDefaultCommand(new DriveCommand(drivetrain, controller));
  }
}
