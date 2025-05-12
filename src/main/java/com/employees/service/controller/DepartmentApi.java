package com.employees.service.controller;

import com.employees.service.dto.department.DepartmentCreateDto;
import com.employees.service.dto.department.DepartmentFullDto;
import com.employees.service.dto.department.DepartmentShortDto;
import com.employees.service.dto.department.DepartmentUpdateDto;
import com.employees.service.exception.dto.RestError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Department API")
@RequestMapping("departments")
public interface DepartmentApi {

    @Operation(summary = "List all departments", description = "Retrieves a complete list of all departments with basic information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resources", content = @Content( array = @ArraySchema( schema = @Schema(implementation = DepartmentShortDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @GetMapping("")
    ResponseEntity<List<DepartmentShortDto>> findAll();

    @Operation(summary = "Get department details", description = "Retrieves full details of a specific department including team members.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resources", content = @Content( schema = @Schema(implementation = DepartmentFullDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<DepartmentFullDto> findById(
            @Parameter(in = ParameterIn.PATH, description = "Unique value of department", required = true) @PathVariable("id") Long id
    );

    @Operation(summary = "Create new department", description = "Adds a new department to the system with provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resources", content = @Content( schema = @Schema(implementation = DepartmentShortDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @PostMapping("")
    ResponseEntity<DepartmentShortDto> create(
            @Valid @Parameter(description = "Department creation data", required = true) @RequestBody DepartmentCreateDto createDto
    );

    @Operation(summary = "Update department", description = "Modifies existing department information with provided data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resources", content = @Content( schema = @Schema(implementation = DepartmentShortDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @PutMapping("/{id}")
    ResponseEntity<DepartmentShortDto> update(
            @Parameter(in = ParameterIn.PATH, description = "Unique value of department", required = true) @PathVariable("id") Long id,
            @Valid @Parameter(description = "Department update data", required = true) @RequestBody DepartmentUpdateDto updateDto
    );

    @Operation(summary = "Delete department", description = "Removes a specific department from the system permanently")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resources", content = @Content( schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(
            @Parameter(in = ParameterIn.PATH, description = "Unique value of department", required = true) @PathVariable("id") Long id
    );
}
