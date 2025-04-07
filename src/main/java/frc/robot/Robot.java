package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.controls.RobotContainer;
public class Robot extends TimedRobot {
    //Calling RobotContainer 
    private RobotContainer robotContainer;

    @Override
    public void robotInit(){
        //Initalizing robotContainer
        robotContainer = new RobotContainer();
    }

    @Override
    public void teleopPeriodic(){
        //Command Scheduler for each subsystem instance
        CommandScheduler.getInstance().run();
    }
}
