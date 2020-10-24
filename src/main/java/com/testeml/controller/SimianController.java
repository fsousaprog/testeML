package com.testeml.controller;

import com.testeml.model.Simian;
import com.testeml.service.SimianService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/simian")
public class SimianController {

    @Autowired
    private SimianService simianService;

    @PostMapping()
    public ResponseEntity<String> simian(@RequestBody JSONObject simian) {
        try {
            this.simianService.isJSONValid(simian);
            Simian simianCreated = this.simianService.create(simian);
            if (this.simianService.isSimian(simianCreated.getDna())) {
                this.simianService.add(simianCreated);
                return ResponseEntity.status(OK).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(FORBIDDEN).body(e.getMessage());
        }

        return ResponseEntity.status(FORBIDDEN).body(null);
    }

}
