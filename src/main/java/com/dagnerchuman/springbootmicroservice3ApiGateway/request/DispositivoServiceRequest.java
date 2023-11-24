package com.dagnerchuman.springbootmicroservice3ApiGateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value="device-service",
        //path="api/dispositivo",
        url="${device.service.url}",
        configuration = FeignConfiguration.class
)

public interface DispositivoServiceRequest {

    @PostMapping("/saveDevice")
    Object saveDevice(@RequestBody Object dispositivo);

    @PostMapping("/sendNotification/{deviceId}")
    Object sendNotification(@PathVariable("deviceId") int deviceId, @RequestBody Object notification);
}