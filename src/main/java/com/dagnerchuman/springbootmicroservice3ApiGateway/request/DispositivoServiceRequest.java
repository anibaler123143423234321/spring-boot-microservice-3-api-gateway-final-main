package com.dagnerchuman.springbootmicroservice3ApiGateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "device-service",
        url = "${device.service.url}",
        configuration = FeignConfiguration.class)
public interface DispositivoServiceRequest {

    @PostMapping("/api/dispositivo/saveDevice")
    Object saveDevice(@RequestBody Object dispositivo);

    @PostMapping("/api/dispositivo/sendNotification/{deviceId}")
    void sendNotification(@PathVariable("deviceId") int deviceId, @RequestBody Object notification);
}
