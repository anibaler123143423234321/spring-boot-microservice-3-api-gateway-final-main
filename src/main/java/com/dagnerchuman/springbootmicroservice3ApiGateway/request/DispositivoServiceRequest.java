package com.dagnerchuman.springbootmicroservice3ApiGateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "device-service",
        url = "${device.service.url}",
        configuration = FeignConfiguration.class)
public interface DispositivoServiceRequest {

    @PostMapping("/saveDevice")
    Object saveDevice(@RequestBody Object dispositivo);

    @PostMapping("/sendNotification/{deviceId}")
    void sendNotification(@PathVariable("deviceId") int deviceId, @RequestBody Object notification);

    @GetMapping("/getAllDevices")
    List<Object> getAllDevices();

    @PostMapping("/sendNotification")
    void sendNotificationToAll(@RequestBody Object notification);

    // Nuevo método para enviar notificación a dispositivos asociados a un negocio específico
    @PostMapping("/sendNotificationToBusiness/{negocioId}")
    void sendNotificationToBusiness(@RequestBody Object notification, @PathVariable Long negocioId);
}
