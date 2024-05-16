package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.MyAuthority;
import lv.venta.model.MyUser;

public interface IMyAuthorityRepo extends CrudRepository<MyAuthority, Integer> {

}
