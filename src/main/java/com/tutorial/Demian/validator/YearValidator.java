package com.tutorial.Demian.validator;

import com.tutorial.Demian.dto.YearDTO;
import com.tutorial.Demian.model.Year;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class YearValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Year.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        YearDTO dto = (YearDTO) obj;

        if (!StringUtils.hasText(dto.getTitle())) {
            errors.rejectValue("title", "key", "제목을 입력하세요");
        }

        if (!StringUtils.hasText(dto.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }

        if (dto.getFromTime() == null) {
            errors.rejectValue("fromTime", "key", "시작 시간을 지정해야 합니다.");
        } else if (dto.getToTime() == null) {
            errors.rejectValue("toTime", "key", "마감 시간을 지정해야 합니다.");
        } else if (dto.getToTime().compareTo(dto.getFromTime()) < 0) {
            errors.rejectValue("fromTime", "key", "마감 시간이 시작 시간보다 빠릅니다.");
            errors.rejectValue("toTime", "key", "마감 시간이 시작 시간보다 빠릅니다.");
        }
    }
}
