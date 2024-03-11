package DAO;

import DTO.CustomerDto;
import DTO.LoginDto;

public interface LoginDAO {
    CustomerDto  login(LoginDto d);
    boolean createAccount(CustomerDto c);
}
