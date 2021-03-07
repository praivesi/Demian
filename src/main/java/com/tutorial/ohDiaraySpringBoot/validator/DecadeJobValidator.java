package com.tutorial.ohDiaraySpringBoot.validator;

import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DecadeJobValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) { return DecadeJob.class.equals(clazz); }

    @Override
    public void validate(Object obj, Errors errors) {
        DecadeJob decadeJob = (DecadeJob)obj;

        if(!StringUtils.hasText(decadeJob.getTitle()))
        {
            errors.rejectValue("title", "key", "제목을 입력하세요");
        }

        if(!StringUtils.hasText(decadeJob.getContent()))
        {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }
    }
}
