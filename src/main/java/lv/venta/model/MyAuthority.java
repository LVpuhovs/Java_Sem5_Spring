package lv.venta.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	

}
