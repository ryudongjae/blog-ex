package com.blog.blogex.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseConstants {
    public static final ResponseEntity OK = ResponseEntity.ok().build();

    public static final ResponseEntity<Void> CREATE =
            ResponseEntity.status(HttpStatus.CREATED).build();
}
