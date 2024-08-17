package com.madadipouya.springkafkatest.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hidden")
public class HiddenController {

  @GetMapping("/test")
  @ResponseStatus(HttpStatus.OK)
  // Hide the entire endpoint
  @Hidden
  // Just to hide the response object
  @ApiResponses(value = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(hidden = true))) })
  public ResponseEntity<TestResponse> getTestResponse() {
    return ResponseEntity.ok(new TestResponse(Map.of("key1", "value1")));
  }

  public record TestResponse(Map<String, String> data) {

  }
}
