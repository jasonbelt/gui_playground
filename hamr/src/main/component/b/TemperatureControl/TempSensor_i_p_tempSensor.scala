// #Sireum

package b.TemperatureControl

import org.sireum._
import b._

// This file will not be overwritten so is safe to edit
object TempSensor_i_p_tempSensor {

  var lastTemp: Temperature_i = TempSensorNative.currentTempGet()

  def initialise(api: TempSensor_i_Initialization_Api): Unit = {
    // initialize outgoing data port
    lastTemp = TempSensorNative.currentTempGet()
    api.setcurrentTemp(lastTemp)
  }

  def timeTriggered(api: TempSensor_i_Operational_Api): Unit = {
    val temp = TempSensorNative.currentTempGet()
    if(temp != lastTemp) {
      lastTemp = temp
      api.setcurrentTemp(temp)
      api.sendtempChanged()

      val degree = Util.toFahrenheit(temp).degrees
      api.logInfo(s"Sensed temperature: $degree F")
    }
  }

  def activate(api: TempSensor_i_Operational_Api): Unit = { }

  def deactivate(api: TempSensor_i_Operational_Api): Unit = { }

  def finalise(api: TempSensor_i_Operational_Api): Unit = { }

  def recover(api: TempSensor_i_Operational_Api): Unit = { }
}

import exts._
@ext object TempSensorNative {
  def currentTempGet(): Temperature_i = $
}
