package com.tutorial.Demian.validator;

import com.tutorial.Demian.model.MonthGrowth;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tutorial.Demian.dto.MonthGrowthDTO;

@Component
public class MonthGrowthValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MonthGrowth.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        MonthGrowthDTO dto = (MonthGrowthDTO) obj;

        if (!StringUtils.hasText(dto.getTitle())) {
            errors.rejectValue("title", "key", "제목을 입력하세요");
        }

        if (!StringUtils.hasText(dto.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }

        if (dto.getYearNumber() == 0) {
            errors.rejectValue("yearNumber", "key", "해당 년도를 지정해야 합니다.");
        }

        if (dto.getMonthNumber() == 0) {
            errors.rejectValue("monthNumber", "key", "해당 월 을 지정해야 합니다.");
        }

        dto.setMonthNumber(dto.getMonthNumber() - 1);
    }
}
