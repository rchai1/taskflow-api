package sg.edu.ntu.taskflow_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//automatically generates getters, setters, constructors, and other utility methods
@Data
//automatically generates an all-arguments constructor
//automatically generates a no-arguments constructor
@NoArgsConstructor
@AllArgsConstructor

public class Task {
private Long id;
private String title;
private String description;
private boolean completed;
}
