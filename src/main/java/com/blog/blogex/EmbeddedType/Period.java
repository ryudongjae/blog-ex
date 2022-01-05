package com.blog.blogex.EmbeddedType;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;


@Embeddable
@Getter
public class Period {

    private LocalDateTime startDate;

    public Period() {
        startDate = LocalDateTime.now();
    }
}
