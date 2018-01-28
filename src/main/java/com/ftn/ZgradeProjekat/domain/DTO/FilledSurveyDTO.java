package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.FilledSurvey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EmulatE on 27-Jan-18.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilledSurveyDTO {

    private Long id;
    private Long surveyID;
    private Long tenantID;


    public FilledSurveyDTO(FilledSurvey fs)
    {
        this.id = fs.getId();
        this.surveyID = fs.getSurveyID();
        this.tenantID = fs.getTenantID();
    }
}
