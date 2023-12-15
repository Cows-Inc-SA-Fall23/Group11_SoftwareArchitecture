package madasi.labSystem.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Silo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private Integer capacity_kg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCapacity_kg() {
		return capacity_kg;
	}

	public void setCapacity_kg(Integer capacity_kg) {
		this.capacity_kg = capacity_kg;
	}

	@Override
	public int hashCode() {
		return Objects.hash(capacity_kg, id, name);
	}
	
	/**
	 * Right now just returns to string, but could be customized a bit. To be called from jsp, methods need the "get" prefix, then you just call "silo.html" for this example
	 * @return
	 */
	public String getHtml() {
		return toString();
	}
	
	

	@Override
	public String toString() {
		return "Silo [id=" + id + ", name=" + name + ", capacity_kg=" + capacity_kg + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Silo other = (Silo) obj;
		return Objects.equals(capacity_kg, other.capacity_kg) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

}
