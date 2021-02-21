package com.day.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/1/2 14:15
 */
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {

    @Autowired
    private SwaggerAutoProvider swaggerResources;

    @GetMapping
    public Mono<ResponseEntity> swaggerResources() {
        return Mono.just((new ResponseEntity<>(swaggerResources.get(), HttpStatus.OK)));
    }

}
