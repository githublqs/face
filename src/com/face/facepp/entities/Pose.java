package com.face.facepp.entities;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

   
public class Pose {

   @JsonProperty("pitch_angle")
   private PitchAngle pitch_angle;
   @JsonProperty("roll_angle")
   private RollAngle roll_angle;
   @JsonProperty("yaw_angle")
   private YawAngle yaw_angle;
public PitchAngle getPitch_angle() {
	return pitch_angle;
}
public void setPitch_angle(PitchAngle pitch_angle) {
	this.pitch_angle = pitch_angle;
}
public RollAngle getRoll_angle() {
	return roll_angle;
}
public void setRoll_angle(RollAngle roll_angle) {
	this.roll_angle = roll_angle;
}
public YawAngle getYaw_angle() {
	return yaw_angle;
}
public void setYaw_angle(YawAngle yaw_angle) {
	this.yaw_angle = yaw_angle;
}


   
    
}