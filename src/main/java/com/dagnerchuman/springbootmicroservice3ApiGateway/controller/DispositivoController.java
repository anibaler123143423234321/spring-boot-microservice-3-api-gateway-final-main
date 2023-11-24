package com.dagnerchuman.springbootmicroservice3ApiGateway.controller;

import com.dagnerchuman.springbootmicroservice3ApiGateway.request.DispositivoServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/dispositivo")
@CrossOrigin(origins = "http://api-gateway:5200")
public class DispositivoController {

    @Autowired
    private DispositivoServiceRequest dispositivoServiceRequest;

    @PostMapping("/saveDevice")
    public ResponseEntity<?> saveDevice(@RequestBody Object dispositivo) {
        return new ResponseEntity<>(dispositivoServiceRequest.saveDevice(dispositivo), HttpStatus.CREATED);
    }

    @PostMapping("/sendNotification/{deviceId}")
    public ResponseEntity<?> sendNotification(
            @PathVariable("deviceId") int deviceId,
            @RequestBody Object notification) {
        try {
            Object response = dispositivoServiceRequest.sendNotification(deviceId, notification);
            // Puedes agregar lógica adicional aquí para manejar el GenericResponse si es necesario
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // Manejar la excepción apropiada, dependiendo de la lógica de negocio en tu servicio
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
