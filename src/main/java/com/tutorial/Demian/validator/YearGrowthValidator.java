package com.tutorial.Demian.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tutorial.Demian.dto.YearGrowthDTO;
import com.tutorial.Demian.model.YearGrowth;

@Component
public class YearGrowthValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return YearGrowth.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        YearGrowthDTO dto = (YearGrowthDTO) obj;

        if (!StringUtils.hasText(dto.getTitle())) {
            errors.rejectValue("title", "key", "제목을 입력하세요");
        }

        if (!StringUtils.hasText(dto.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }

        if (dto.getYearNumber() == 0) {
            errors.rejectValue("yearNumber", "key", "연도수를 입력해야 합니다.");
        }
    }
}
