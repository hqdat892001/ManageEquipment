package com.example.manageequipment.controller;

import com.example.manageequipment.dto.RequestDto;
import com.example.manageequipment.service.RequestService;
import com.example.manageequipment.type.IntegerArrayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @PostMapping("/create-request")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<RequestDto> createRequestEquipment(@RequestBody RequestDto requestDto) {
        return new ResponseEntity<>(requestService.createRequestEquipment(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/get-request-by-user/{userId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<List<RequestDto>> getRequestEquipmentByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(requestService.getRequestEquipmentByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/get-all-request")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<RequestDto>> getAllRequestEquipment() {
        return new ResponseEntity<>(requestService.getAllRequestEquipment(), HttpStatus.OK);
    }

    @PostMapping("/reject-request/{requestId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> rejectRequestEquipment(@PathVariable Long requestId) {
        return new ResponseEntity<>(requestService.rejectRequestEquipment(requestId), HttpStatus.OK);
    }

    @PostMapping("/confirm-request/{requestId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> confirmRequestEquipment(@PathVariable Long requestId) {
        return new ResponseEntity<>(requestService.confirmRequestEquipment(requestId), HttpStatus.OK);
    }
}
