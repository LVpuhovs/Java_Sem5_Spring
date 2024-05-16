package lv.venta.model;



import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "AuthorityTable")

@Entity
public class MyAuthority {

	@Column(name =  "Ida")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ida;
	
	@NotNull
	@Pattern(regexp = "[A-Z]{4,5}", message = "Only letters and numbers")
	@Column(name = "Title")
	private String title;
	
	@ManyToMany
	@JoinTable(name = "UserAuthorityTable",
	joinColumns = @JoinColumn(name = "Ida"),
	inverseJoinColumns = @JoinColumn(name = "Idu"))
	@ToString.Exclude
	private Collection<MyUser> users = new ArrayList<MyUser>();
	
	
	public void addAuthority(MyUser user) {
		if(!users.contains(user))
			users.add(user);
	}
	
	public void remove (MyUser user) {
		if(users.contains(user))
			users.remove(user);
	}
	
	public MyAuthority(String title)
	{
		setTitle(title);
	}
	
	

}
