package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Joystick controller = new Joystick(RobotMap.OperatorConstants.JOYSTICK_PORT);
  private final Drivetrain drivetrain = new Drivetrain();

  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    drivetrain.setDefaultCommand(new DriveCommand(drivetrain, controller));

  }
  
}
