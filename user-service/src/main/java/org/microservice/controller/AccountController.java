package org.microservice.controller;

import org.microservice.dto.CreateNewAccountDto;
import org.microservice.dto.LoginDto;
import org.microservice.dto.ResponseDto;
import org.microservice.entity.Account;
import org.microservice.services.IAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AccountController {

    @Autowired()
    private IAccountServices accountServices;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Account>> checkLogin(@RequestBody()LoginDto loginDto) {
        ResponseDto<Account> responseDto = accountServices.login(loginDto);

        if (!responseDto.isSuccess()) {
            return  new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PostMapping("/loginSystem")
    public  ResponseDto<Account> login(@RequestBody()LoginDto loginDto) {
        return accountServices.login(loginDto);
    }

    @GetMapping(value = "/secure")
    public String getSecure() {
        return "Secure endpoint available";
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto<CreateNewAccountDto>> create(@RequestBody() CreateNewAccountDto dto) {
        ResponseDto<CreateNewAccountDto> response = new ResponseDto<>();
        try {
            response = accountServices.createNewUser(dto);
        } catch (Exception ex) {
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
