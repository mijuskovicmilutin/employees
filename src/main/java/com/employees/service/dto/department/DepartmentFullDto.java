package com.employees.service.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Full department details including member names and team lead.")
public class DepartmentFullDto {

    private Long id;

    private String name;

    private Long teamLeadId;

    private String teamLeadName;

    private List<MemberDto> members;

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;

    private Long version;

    @Getter
    @Setter
    public class MemberDto {

        private Long memberId;

        private String memberName;
    }
}
