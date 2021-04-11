package com.tutorial.Demian.validator;

import com.tutorial.Demian.dto.DecadeJobDTO;
import com.tutorial.Demian.dto.YearJobDTO;
import com.tutorial.Demian.model.DecadeJob;
import com.tutorial.Demian.model.YearJob;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class YearJobValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return YearJob.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        YearJobDTO dto = (YearJobDTO) obj;

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
