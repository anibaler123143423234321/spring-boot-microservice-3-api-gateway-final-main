package com.dagnerchuman.springbootmicroservice3ApiGateway.controller;

import com.dagnerchuman.springbootmicroservice3ApiGateway.request.DispositivoServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gateway/dispositivo")
@CrossOrigin(origins = "http://api-gateway:5200")
public class DispositivoController {

    @Autowired
    private DispositivoServiceRequest dispositivoServiceRequest;

    // Guardar dispositivo
    @PostMapping("/saveDevice")
    public ResponseEntity<Object> saveDevice(@RequestBody Object dispositivo) {
        try {
            // Llama al servicio de dispositivos a través de FeignClient
            Object savedDispositivo = dispositivoServiceRequest.saveDevice(dispositivo);

            // Retorno de ejemplo (ajústalo según tus necesidades)
            return new ResponseEntity<>(savedDispositivo, HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo de excepciones, puedes personalizar según tus necesidades
            e.printStackTrace();
            return new ResponseEntity<>("Error al guardar el dispositivo.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Enviar notificación
    @PostMapping("/sendNotification/{deviceId}")
    public ResponseEntity<String> sendNotification(
            @PathVariable("deviceId") int deviceId,
            @RequestBody Object notification) {
        try {
            // Llama al servicio de dispositivos a través de FeignClient
            dispositivoServiceRequest.sendNotification(deviceId, notification);

            // Retorno de ejemplo (ajústalo según tus necesidades)
            return new ResponseEntity<>("Notificación enviada correctamente.", HttpStatus.OK);
        } catch (Exception e) {
            // Manejo de excepciones, puedes personalizar según tus necesidades
            e.printStackTrace();
            return new ResponseEntity<>("Error al enviar la notificación.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
