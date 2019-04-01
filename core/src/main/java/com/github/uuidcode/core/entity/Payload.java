package com.github.uuidcode.core.entity;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Payload {
    private List<Book> bookList;
}
