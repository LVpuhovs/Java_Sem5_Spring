package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.MyUser;



public interface IUserRepo extends CrudRepository<MyUser, Integer>{

}
