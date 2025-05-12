package com.employees.service.controller;

import com.employees.service.dto.EmployeeSearchCriteria;
import com.employees.service.dto.employee.EmployeeCreateDto;
import com.employees.service.dto.employee.EmployeeFullDto;
import com.employees.service.dto.employee.EmployeeShortDto;
import com.employees.service.dto.employee.EmployeeUpdateDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Employee API")
@RequestMapping("employees")
public interface EmployeeApi {

    @Operation(summary = "List all employees", description = "Retrieves a complete list of all employees with basic information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resources", content = @Content( array = @ArraySchema( schema = @Schema(implementation = EmployeeShortDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @GetMapping("")
    ResponseEntity<List<EmployeeShortDto>> findAll();

    @Operation(summary = "Get employee details", description = "Retrieves full details of a specific employee including department and team lead info.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resources", content = @Content( schema = @Schema(implementation = EmployeeFullDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<EmployeeFullDto> findById(
            @Parameter(in = ParameterIn.PATH, description = "Unique value of employee", required = true) @PathVariable("id") Long id
    );

    @Operation(summary = "Create new employee", description = "Adds a new employee to the system with provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resources", content = @Content( schema = @Schema(implementation = EmployeeFullDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @PostMapping("")
    ResponseEntity<EmployeeFullDto> create(
            @Valid @Parameter(description = "Employee creation data", required = true) @RequestBody EmployeeCreateDto createDto
    );

    @Operation(summary = "Update employee", description = "Modifies existing employee information with provided data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resources", content = @Content( schema = @Schema(implementation = EmployeeFullDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @PutMapping("/{id}")
    ResponseEntity<EmployeeFullDto> update(
            @Parameter(in = ParameterIn.PATH, description = "Unique value of employee", required = true) @PathVariable("id") Long id,
            @Valid @Parameter(description = "Employee update data", required = true) @RequestBody EmployeeUpdateDto updateDto
    );

    @Operation(summary = "Delete employee", description = "Removes a specific employee from the system permanently")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resources", content = @Content( schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(
            @Parameter(in = ParameterIn.PATH, description = "Unique value of employee", required = true) @PathVariable("id") Long id
    );

    @Operation(summary = "Search employees", description = "Search employees with filtering, sorting and pagination.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employee results"),
            @ApiResponse(responseCode = "400", description = "Invalid search parameters", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(schema = @Schema(implementation = RestError.class))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = RestError.class)))
    })
    @GetMapping("/search")
    ResponseEntity<Page<EmployeeFullDto>> searchEmployees(
            @Parameter(description = "Employee search criteria") @Valid EmployeeSearchCriteria criteria,
            @Parameter(description = "Pagination and sorting parameters") Pageable pageable
    );
}
