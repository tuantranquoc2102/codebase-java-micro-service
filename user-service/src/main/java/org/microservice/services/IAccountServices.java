package org.microservice.services;

import org.microservice.dto.CreateNewAccountDto;
import org.microservice.dto.LoginDto;
import org.microservice.dto.ResponseDto;
import org.microservice.entity.Account;
import org.springframework.stereotype.Service;


public interface IAccountServices {
    ResponseDto<Account> login(LoginDto loginDto);

    void createNewUser(CreateNewAccountDto loginDto);
}
