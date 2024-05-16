package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "UserTable")
@Entity
public class MyUser {
	@NotNull
	@Pattern(regexp = "[A-Za-z.]+", message = "Only letters and dot")
	@Size(min = 4, max = 200)
	@Column(name = "Username")
	private String username;
	
	@NotNull
	@Size(min = 6, max = 200)
	@Column(name = "Password")
	private String password;
	
	public MyUser(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
}
