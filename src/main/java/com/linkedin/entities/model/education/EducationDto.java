package com.linkedin.entities.model.education;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(description = "education response object")
public class EducationDto {


    @ApiModelProperty(value = "education id", example = "1234", position = 1)
    private Long id;

    @ApiModelProperty(value = "university degree", example = "Bachelor", position = 2)
    private String universityDegree;

    @ApiModelProperty(value = "university name",example = "Kapodistriako", position = 3)
    private String universityName;


    @ApiModelProperty(value = "starting date", example = "2018-01-22", position = 5)
    private Date startingDate;

    @ApiModelProperty(value = "ending date", example = "2018-01-22", position = 6)
    private Date endingDate;
}








