package repository;

import com.athome.foosh.foosh.Address;

import java.util.List;

/**
 * Created by Bob on 2014/08/19.
 */
public interface DatasourceDAO {


    public void addAddress(Address address);
    public Address getAddress(int id);
    public List<Address> getAllAddress();
    public int getAddressCount() ;
    public int updateAddress(int id, Address address);
    public void deleteAddress(String id) ;
}
