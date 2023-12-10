package madasi.sensorManager.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Setting {
	
	@Id
	private Integer id;

	//can use this to have text in the project change only by modifying the db and not having to publish a new version. Currently only used for project title, but others variables can be added to this object.
	private String projectName;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
