package com.github.uuidcode.core.model;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Project {
    private Long id;
    private Date CreateAt;
}
