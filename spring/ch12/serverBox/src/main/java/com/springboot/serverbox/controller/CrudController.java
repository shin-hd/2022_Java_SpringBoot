package com.springboot.serverbox.controller;

import com.springboot.serverbox.dto.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

@RestController
@RequestMapping("/api/v1/crud-api")
public class CrudController {

    Logger LOGGER = LoggerFactory.getLogger(CrudController.class);

    @GetMapping
    public String getName() {
        return "Flature";
    }

    @GetMapping(value = "/{variable}")
    public String getVariable(@PathVariable String variable) {
        return variable;
    }

    @GetMapping("/param")
    public String getNameWithParam(@RequestParam String name) {
        return "Hello. " + name + "!";
    }

    @PostMapping
    public ResponseEntity<MemberDto> getMember(
            @RequestBody MemberDto request,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization
    ) {
        LOGGER.info(request.getName());
        LOGGER.info(request.getEmail());
        LOGGER.info(request.getOrganization());

        MemberDto memberDto = new MemberDto();
        memberDto.setName(name);
        memberDto.setEmail(email);
        memberDto.setOrganization(organization);

        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @PostMapping(value = "/add-header")
    public ResponseEntity<MemberDto> addHeader(@RequestHeader("my-header") String header, @RequestBody MemberDto memberDto) {
        LOGGER.info(header);

        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

}
