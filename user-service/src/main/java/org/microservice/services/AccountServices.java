package org.microservice.services;

import org.microservice.dto.CreateNewAccountDto;
import org.microservice.dto.LoginDto;
import org.microservice.dto.ResponseDto;
import org.microservice.entity.Account;
import org.microservice.exceptions.BusinessException;
import org.microservice.repositories.IAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServices implements IAccountServices {

    @Autowired()
    private IAccountRepository accountRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseDto<Account> login(LoginDto loginDto) {
        ResponseDto<Account> response = new ResponseDto<>();
        Account account = accountRepository.findByAccountName((loginDto.getAccountName()));

        logger.debug("=====Login Start=======");
        logger.debug("Login Info User Name : [ " + loginDto.getAccountName() + "] ");
        logger.debug("Login Info Password : [ " + loginDto.getPassword() + "] ");

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

        logger.debug("=====End=======");
        return response;
    }

    @Override
    public ResponseDto<CreateNewAccountDto> createNewUser(CreateNewAccountDto loginDto) {
        Account account = new Account();
        account.setAccountName(loginDto.getAccountName());
        account.setPassword(loginDto.getPassword());
        account.setEmail(loginDto.getEmail());
        account.setRoleId(loginDto.getRoleId());

        int countAccountName = this.accountRepository.countByAccountName(account.getAccountName()).orElse(0);

        // check account name
        if (countAccountName != 0) {
            return new ResponseDto<>(false, "Account is already exists", new CreateNewAccountDto());
        }

        // check exist email
        int countEmailAlreadyExists = this.accountRepository.countByEmail(account.getEmail()).orElse(0);

        if (countEmailAlreadyExists != 0) {
            return new ResponseDto<>(false, "Email is already exists", new CreateNewAccountDto());
        }

        accountRepository.save(account);

        return  new ResponseDto<>(true, "An account is inserted successfull", new CreateNewAccountDto());
    }
}
