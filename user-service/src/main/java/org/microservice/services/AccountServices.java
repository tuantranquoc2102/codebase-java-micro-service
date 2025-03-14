package org.microservice.services;

import org.microservice.dto.CreateNewAccountDto;
import org.microservice.dto.LoginDto;
import org.microservice.dto.ResponseDto;
import org.microservice.entity.Account;
import org.microservice.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServices implements IAccountServices {

    @Autowired()
    private IAccountRepository accountRepository;


    @Override
    public ResponseDto<Account> login(LoginDto loginDto) {
        ResponseDto<Account> response = new ResponseDto<>();
        Account account = accountRepository.findByAccountName((loginDto.getAccountName()));

        if (account == null) {
            response.setSuccess(false);
            response.setMessage("Account Is Not Exist");
            return response;
        }

        if (!account.getPassword().equals(loginDto.getPassword())) {
            response.setSuccess(false);
            response.setMessage("Password Is Invalid");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Login is success");
        response.setData(account);
        return response;
    }

    @Override
    public void createNewUser(CreateNewAccountDto loginDto) {
        Account account = new Account();
        account.setAccountName(loginDto.getAccountName());
        account.setPassword(loginDto.getPassword());
        account.setEmail(loginDto.getEmail());

        try {
            accountRepository.save(account);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
