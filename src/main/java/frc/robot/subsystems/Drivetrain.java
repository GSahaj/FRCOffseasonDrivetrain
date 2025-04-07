package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase{
    //Empty contructor as all variables and object have been intialized in RobotMap.java
    public Drivetrain(){

    }

    //Method for driving xSpeed -- Shraft, ySpeed -- Forward/Backwords, zRotation -- Left/Right
    public void drive(double xSpeed, double ySpeed, double zRotation){
        xSpeed = applyDeadBand(xSpeed);
        ySpeed = applyDeadBand(ySpeed);
        zRotation = applyDeadBand(zRotation);

        xSpeed = RobotMap.variables.xSpeedLimiter.calculate(xSpeed);
        ySpeed = RobotMap.variables.ySpeedLimiter.calculate(ySpeed);
        zRotation = RobotMap.variables.zRotationLimiter.calculate(zRotation);
        
        if(!RobotMap.variables.fieldOriented){
            RobotMap.variables.robotDrive.driveCartesian(xSpeed, ySpeed, zRotation, RobotMap.variables.gyroAngle);
        }
        else{
            RobotMap.variables.robotDrive.driveCartesian(xSpeed, ySpeed, zRotation);
        }
    }

    //method that applies a deadband to prevent drift. remove amonalies from the input signal that are close to 0 and ignore them
    private double applyDeadBand(double value){
        double deadband = 0.05;

        //If the value is less than threshold, the value is reset to 0 
        if(Math.abs(value) < deadband){
            return 0;
        }

        //Mathematical calcualtion 
        return Math.copySign((Math.abs(value) - deadband) / (1.0 - deadband), value);
    }

    public void stop(){
        RobotMap.variables.xSpeedLimiter.reset(0);
        RobotMap.variables.ySpeedLimiter.reset(0);
        RobotMap.variables.zRotationLimiter.reset(0);
        RobotMap.variables.robotDrive.stopMotor();
    }

    public void resetGyro(){
        RobotMap.variables.gyro.reset();
    }

    public double getGyroAngle(){
        return RobotMap.variables.gyro.getAngle();
    }
    
    public boolean isFieldOriented(){
        return RobotMap.variables.fieldOriented;
    }

    public void toggleFieldOriented(){
        RobotMap.variables.fieldOriented = !RobotMap.variables.fieldOriented;
        resetGyro();
    }

    public void resetFieldOrientation(){
        resetGyro();
    }
}
