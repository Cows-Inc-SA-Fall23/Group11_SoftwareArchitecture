package madasi.sensorManager.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Sensor {
	@Id
	private String mac;

	@OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorData> sensorData;
	
	public Integer getDataEntriesNumber() {
		if (sensorData != null) {
			return sensorData.size();
		}else {
			return 0;
		}
	}

    private Integer livestock;
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getLivestock() {
		return livestock;
	}

	public void setLivestock(Integer livestock) {
		this.livestock = livestock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(livestock, mac, sensorData);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sensor other = (Sensor) obj;
		return Objects.equals(livestock, other.livestock) && Objects.equals(mac, other.mac)
				&& Objects.equals(sensorData, other.sensorData);
	}

	public List<SensorData> getSensorData() {
		return sensorData;
	}

	public void setSensorData(List<SensorData> sensorData) {
		this.sensorData = sensorData;
	}
	
}
