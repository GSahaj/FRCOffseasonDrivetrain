package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drivetrain;

public class DriveCommand extends Command{
    //Initalize all Objects
    private final Drivetrain drivetrain;
    private final Joystick controller;
    
    //Contructor for all Variables
    public DriveCommand(Drivetrain drivetrain, Joystick controller){
        this.drivetrain = drivetrain;
        this.controller = controller;

        addRequirements(drivetrain);
    }

    @Override
    public void execute(){
        //Setting all Drivetrain Parameters to Joystick commands
        double xSpeed = controller.getRawAxis(0); 
        double ySpeed = -controller.getRawAxis(1);
        double zRotation = controller.getRawAxis(4);

        //logic statements for Field oriented operations
        if(controller.getRawButton(RobotMap.OperatorConstants.FIELD_ORIENTED_BUTTON)){
            drivetrain.toggleFieldOriented();
        }

        if(controller.getRawButton(RobotMap.OperatorConstants.RESET_FIELD_BUTTON)){
            drivetrain.resetFieldOrientation();
        }

        //base case for non-field oriented -- Default Settings
        drivetrain.drive(xSpeed, ySpeed, zRotation);
    }

    @Override
    public void end(boolean interrupt){
        //Ending command by stopping motors
        drivetrain.stop();
    }

    @Override
    public boolean isFinished(){
        //Required isFinished Method
        return false;
    }

    
}
