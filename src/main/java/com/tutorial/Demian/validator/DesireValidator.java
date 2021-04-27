package com.tutorial.Demian.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tutorial.Demian.dto.DesireDTO;

@Component
public class DesireValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DesireDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DesireDTO dto = (DesireDTO)target;

        if (!StringUtils.hasText(dto.getTitle())) {
            errors.rejectValue("title", "key", "제목을 입력하세요.");
        }

        if (!StringUtils.hasText(dto.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요.");
        }
    }
}
