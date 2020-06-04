package com.qa.cineverse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Transactional
public class ScreeningsDTO {

    private Long screeningsId;
    @NonNull
    private LocalDateTime movieDateTime;
    @NonNull
    private String screenType;
    @NonNull
    private Long screenNumber;
    @NonNull
    private String movieName;

    private List<CustomersDTO> customers = new ArrayList<> ();
    public ScreeningsDTO(LocalDateTime movieDateTime, Long screenNumber, String screenType, String movieName) {
        super();
        this.movieDateTime = movieDateTime;
        this.screenNumber = screenNumber;
        this.screenType = screenType;
        this.movieName = movieName;
    }
}
