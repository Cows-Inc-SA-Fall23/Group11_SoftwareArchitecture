package madasi.sensorManager.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Timestamp timestamp;

    private String valueName;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_mac")
    private Sensor sensor;
    
    @Transient
    private String sensor_mac_tmp;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the valueName
	 */
	public String getValueName() {
		return valueName;
	}

	/**
	 * @param valueName the valueName to set
	 */
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the sensor
	 */
	public Sensor getSensor() {
		return sensor;
	}

	/**
	 * @param sensor the sensor to set
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public String getSensor_mac_tmp() {
		return sensor_mac_tmp;
	}

	public void setSensor_mac_tmp(String sensor_mac_tmp) {
		this.sensor_mac_tmp = sensor_mac_tmp;
	}

}
