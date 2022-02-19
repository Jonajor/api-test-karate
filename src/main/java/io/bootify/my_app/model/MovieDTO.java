package io.bootify.my_app.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovieDTO {

    private Long id;

    @Size(max = 255)
    private String description;

}
