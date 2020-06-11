package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.TheaterPlayDto;
import net.avalith.city_pass.models.TheaterPlay;
import net.avalith.city_pass.repositories.TheaterPlayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TheaterPlayService {
    private final TheaterPlayRepository theaterPlayRepository;

    public List<TheaterPlayDto> getAll() {
        return this.theaterPlayRepository.findAllByisActive(Boolean.TRUE)
                .stream()
                .map(TheaterPlayDto::new)
                .collect(Collectors.toList());
    }
}
