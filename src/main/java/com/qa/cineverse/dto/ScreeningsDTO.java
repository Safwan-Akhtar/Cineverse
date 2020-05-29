package com.qa.cineverse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Transactional
public class ScreeningsDTO {

    @Getter @Setter @NonNull
    private Long screeningsId;
    @Getter @Setter @NonNull
    private LocalDateTime movieDateTime;
    @Getter @Setter @NonNull
    private String screenType;
    @Getter @Setter @NonNull
    private Long screenNumber;
    @Getter @Setter @NonNull
    private String movieName;
    @Getter @Setter
    private List<CustomersDTO> customers = new ArrayList<> ();

    public ScreeningsDTO(LocalDateTime movieDateTime, Long screenNumber, String screenType, String movieName) {
        super();
        this.movieDateTime = movieDateTime;
        this.screenNumber = screenNumber;
        this.screenType = screenType;
        this.movieName = movieName;
    }
}
