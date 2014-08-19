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
    public int updateAddress(Address address);
    public void deleteAddress(Address address) ;
}
