package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.MyUser;



public interface IUserRepo extends CrudRepository<MyUser, Integer>{

	//SELECT * FROM MY_USER_TABLE WHERE USERNAME=<username>
	MyUser findByUsername(String username);

}
