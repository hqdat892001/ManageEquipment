package com.example.manageequipment.service.impl;

import com.example.manageequipment.dto.RequestDto;
import com.example.manageequipment.model.Category;
import com.example.manageequipment.model.Equipment;
import com.example.manageequipment.model.Request;
import com.example.manageequipment.model.User;
import com.example.manageequipment.repository.CategoryRepository;
import com.example.manageequipment.repository.EquipmentRepository;
import com.example.manageequipment.repository.RequestRepository;
import com.example.manageequipment.repository.UserRepository;
import com.example.manageequipment.service.EquipmentService;
import com.example.manageequipment.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public RequestDto mapToDto(Request request) {
        RequestDto requestDto = new RequestDto();
        requestDto.setUserId(request.getUserOwner().getId());
        requestDto.setState(request.getState());
        requestDto.setId(request.getId());
        requestDto.setRequestEquipmentTypeId(request.getRequestEquipmentType().getId());
        requestDto.setDescription(request.getDescription());

        return requestDto;
    }

    @Override
    public RequestDto createRequestEquipment(RequestDto requestDto) {
        Request request = new Request();

        User user = userRepository.findById(requestDto.getUserId()).get();

        Category category = categoryRepository.findById(requestDto.getRequestEquipmentTypeId()).get();

        request.setDescription(requestDto.getDescription());
        request.setUserOwner(user);
        request.setState("PENDING");
        request.setRequestEquipmentType(category);

        requestRepository.save(request);

        return mapToDto(request);
    }

    @Override
    public List<RequestDto> getRequestEquipmentByUserId(Long userId) {
        User userOwner = userRepository.findById(userId).get();
        List<Request> listRequest = requestRepository.findByUserOwner(userOwner);
        List<RequestDto> listRequestDto = new ArrayList<>();

        listRequest.forEach(request -> {
            listRequestDto.add(mapToDto(request));
        });

        return listRequestDto;
    }

    @Override
    public List<RequestDto> getAllRequestEquipment() {
        List<Request> listRequest = requestRepository.findAll();
        List<RequestDto> listRequestDto = new ArrayList<>();

        listRequest.forEach(request -> {
            listRequestDto.add(mapToDto(request));
        });

        return listRequestDto;
    }

    @Override
    public String rejectRequestEquipment(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid request id "+ requestId));

        if (request != null) {
            requestRepository.deleteById(requestId);
        }

        return "Reject equipment success!!";
    }

    @Override
    public String confirmRequestEquipment(Long requestId) {
//        Request request = requestRepository.findById(requestId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid request id "+ requestId));
//
//        List<Long> equipmentIds = new ArrayList<>();
//
//        request.getRequestEquipments().forEach((requestEquipment) -> {
//            equipmentIds.add(requestEquipment.getId());
//        });
//
//        equipmentService.transferEquipment(equipmentIds, request.getUserId());
//        requestRepository.deleteById(requestId);

        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid request id "+ requestId));

        if (request != null) {
            requestRepository.deleteById(requestId);
        }

        return "Confirm Request Success!!";
    }
}
