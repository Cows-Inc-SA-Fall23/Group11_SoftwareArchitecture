package madasi.sensorManager.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Sensor {
	@Id
	private String mac;

	@OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorData> sensorData;

    @ManyToOne
    @JoinColumn(name = "livestock_id")
    private Livestock livestock;
	
	public String getMac() {
		return mac;
	}

	public void setMac(String name) {
		this.mac = name;
	}

	public Livestock getLivestock() {
		return livestock;
	}

	public void setLivestock(Livestock livestock) {
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
