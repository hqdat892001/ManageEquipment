package com.example.manageequipment.controller;

import com.example.manageequipment.dto.EquipmentDto;
import com.example.manageequipment.dto.UserDto;
import com.example.manageequipment.model.Equipment;
import com.example.manageequipment.service.EquipmentService;
import com.example.manageequipment.type.IntegerArrayRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<EquipmentDto> createEquipment(@ModelAttribute Equipment equipment, @ModelAttribute MultipartFile image) throws IOException {
        System.out.println(equipment);
        return new ResponseEntity<>(equipmentService.createEquipment(equipment, image), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/equipments")
    public ResponseEntity<List<EquipmentDto>> getEquipments(
            @RequestParam(value = "name", defaultValue = "", required = false) String name
    ) {
        return new ResponseEntity<>(equipmentService.getAllEquipment(name), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') or #ownerId == authentication.credentials")
    @GetMapping("/equipments-by-owner-id")
    public ResponseEntity<List<EquipmentDto>> getEquipmentByOwnerId(
            @RequestParam(value = "ownerId", defaultValue = "", required = false) int ownerId
    ) {
        return new ResponseEntity<>(equipmentService.getEquipmentByOwnerId(ownerId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/update/{equipmentId}")
    public ResponseEntity<EquipmentDto> updateEquipment(@PathVariable Long equipmentId, @ModelAttribute EquipmentDto equipmentDto, @ModelAttribute MultipartFile image) throws IOException {
        return new ResponseEntity<>(equipmentService.updateEquipment(equipmentId, equipmentDto, image), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public  ResponseEntity<String> deleteEquipment(@RequestBody IntegerArrayRequest equipmentIds) {
        equipmentService.deleteEquipment(equipmentIds.getIds());
        return new ResponseEntity<>("Delete equipment success!!", HttpStatus.OK);
    }

    @PostMapping("/transfer/{userId}")
    public ResponseEntity<UserDto> transferEquipment(@RequestBody IntegerArrayRequest equipmentIds, @PathVariable Long userId) {
        return new ResponseEntity<>(equipmentService.transferEquipment(equipmentIds.getIds(), userId), HttpStatus.OK);
    }
}

