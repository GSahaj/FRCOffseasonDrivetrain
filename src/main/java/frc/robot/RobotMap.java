package frc.robot;

//Java FRC imports
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;


public final class RobotMap {
  //static class that is mainly for Constants of subsystems
  public static class OperatorConstants {
    public static final int FRONT_LEFT_CHANNEL = 0;
    public static final int REAR_LEFT_CHANNEL = 1;
    public static final int FRONT_RIGHT_CHANNEL = 2;
    public static final int REAR_RIGHT_CHANNEL = 3;

    public static final double SLEW_RATE_LIMITER = 3.0;

    public static final int JOYSTICK_PORT = 0;

    public static final int FIELD_ORIENTED_BUTTON = 4;
    public static final int RESET_FIELD_BUTTON = 5;

  }

  //static class that is mainly for Constants of subsystem 
  public static class OperatorVariables {
    //Variable declaration for Motorcontroller and drivesystem
    public PWMSparkMax frontLeft;
    public PWMSparkMax rearLeft;
    public PWMSparkMax frontRight;
    public PWMSparkMax rearRight;
    public MecanumDrive robotDrive;

    //Variable declaration for Gyro-related functions
    public AHRS gyro;
    public Rotation2d gyroAngle;
    public boolean fieldOriented;

    //Variable declaration for SlewRateLimiter of each mecanum drive parameter
    //Aims to smooth input singals coming caying less drift and wheel spin 
    public final SlewRateLimiter xSpeedLimiter;
    public final SlewRateLimiter ySpeedLimiter;
    public final SlewRateLimiter zRotationLimiter;

    public OperatorVariables(){
      //Creating instances for Motorcontroller and drivesystem
      frontLeft = new PWMSparkMax(OperatorConstants.FRONT_LEFT_CHANNEL);
      rearLeft = new PWMSparkMax(OperatorConstants.REAR_LEFT_CHANNEL);
      frontRight = new PWMSparkMax(OperatorConstants.FRONT_RIGHT_CHANNEL);
      rearRight = new PWMSparkMax(OperatorConstants.REAR_RIGHT_CHANNEL);
      robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

      //Creating instances for Gyro-realetd Objects
      gyro = new AHRS(SPI.Port.kMXP);
      gyroAngle = Rotation2d.fromDegrees(-gyro.getAngle());
      fieldOriented = false;

      //Creating instances for SLewRateLimiter
      xSpeedLimiter = new SlewRateLimiter(RobotMap.OperatorConstants.SLEW_RATE_LIMITER);
      ySpeedLimiter = new SlewRateLimiter(RobotMap.OperatorConstants.SLEW_RATE_LIMITER);
      zRotationLimiter = new SlewRateLimiter(RobotMap.OperatorConstants.SLEW_RATE_LIMITER);

    }
  }

  //Creating the OpertorVariable as an object such that in can be accessed in other classes
  public static final OperatorVariables variables = new OperatorVariables();
}
