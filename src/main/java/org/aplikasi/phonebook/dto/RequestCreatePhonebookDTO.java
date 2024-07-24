package org.aplikasi.phonebook.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RequestCreatePhonebookDTO {

    @NotNull(message = "Field 'surname' is required")
    protected String surname;

    protected String middlename;

    @NotNull(message = "Field 'phone' is required")
    @Pattern(regexp = "08[0-9]{5,17}", message = "Field 'phone' invalid or limit characters is 18")
    protected String phone;

    @Pattern(regexp = "[0-9]{5,17}", message = "Field 'workPhone' invalid or limit characters is 18")
    protected String workPhone;

}

