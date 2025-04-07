package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drivetrain;

public class DriveCommand extends Command{
    private final Drivetrain drivetrain;
    private final Joystick controller;

    public DriveCommand(Drivetrain drivetrain, Joystick controller){
        this.drivetrain = drivetrain;
        this.controller = controller;

        addRequirements(drivetrain);
    }

    @Override
    public void execute(){
        double xSpeed = controller.getRawAxis(0); 
        double ySpeed = -controller.getRawAxis(1);
        double zRotation = controller.getRawAxis(4);

        if(controller.getRawButton(RobotMap.OperatorConstants.FIELD_ORIENTED_BUTTON)){
            drivetrain.toggleFieldOriented();
        }

        if(controller.getRawButton(RobotMap.OperatorConstants.RESET_FIELD_BUTTON)){
            drivetrain.resetFieldOrientation();
        }

        drivetrain.drive(xSpeed, ySpeed, zRotation);
    }

    @Override
    public void end(boolean interrupt){
        drivetrain.stop();
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    
}
