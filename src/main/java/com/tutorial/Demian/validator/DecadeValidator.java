package com.tutorial.Demian.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tutorial.Demian.dto.DecadeGrowthDTO;
import com.tutorial.Demian.model.DecadeGrowth;

@Component
public class DecadeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DecadeGrowth.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        DecadeGrowthDTO dto = (DecadeGrowthDTO) obj;

        if (!StringUtils.hasText(dto.getTitle())) {
            errors.rejectValue("title", "key", "제목을 입력하세요");
        }

        if (!StringUtils.hasText(dto.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }

        if (dto.getDecadeNumber() == 0) {
            errors.rejectValue("decadeNumber", "key", "연도수를 입력해야 합니다.");
        }
    }
}
