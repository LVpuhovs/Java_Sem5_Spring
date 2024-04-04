package lv.venta.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
//@AllArgsConstructor
public class Product {
	private int id;
	
	private static int counter;
	
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z ]+")
	@Size(min = 3, max = 20)
	private String title;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z]+")
	@Size(min = 4, max = 200)
	private String description;
	
	@Min(0)
	@Max(10000)
	private float price;
	
	@Min(0)
	@Max(100)
	private int quantity;
	
	public void setId() {
		this.id = counter;
		counter++;
	}
	public Product(String title, String description, float price, int quantity) {
		setId();
		setTitle(title);
		setDescription(description);
		setPrice(price);
		setQuantity(quantity);
	}
}