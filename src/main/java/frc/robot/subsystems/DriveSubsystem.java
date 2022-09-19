// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

  CANSparkMax m_lMaster, m_lSlave;
  CANSparkMax m_rMaster, m_rSlave;

  DifferentialDrive m_drivetrain;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    // Instantiate motor controllers
    m_lMaster = new CANSparkMax(Constants.LEFT_FRONT_DRIVE_MOTOR_PORT, MotorType.kBrushless);
    m_lSlave = new CANSparkMax(Constants.LEFT_REAR_DRIVE_MOTOR_PORT, MotorType.kBrushless);
    m_rMaster = new CANSparkMax(Constants.RIGHT_FRONT_DRIVE_MOTOR_PORT, MotorType.kBrushless);
    m_rSlave = new CANSparkMax(Constants.RIGHT_REAR_DRIVE_MOTOR_PORT, MotorType.kBrushless);

    m_rMaster.setInverted(true);
    m_lMaster.setInverted(true);

    // Make slaves follow master
    m_lSlave.follow(m_lMaster);
    m_rSlave.follow(m_rMaster);

    // Create differential drive object
    m_drivetrain = new DifferentialDrive(m_lMaster, m_rMaster);
  }

  public void teleop(double speed, double turn) {
    m_drivetrain.arcadeDrive(speed, turn);
  }

  public void teleopAdvanced(double speed, double turn) {
    m_lMaster.set(speed + turn);
    m_rMaster.set(speed - turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
