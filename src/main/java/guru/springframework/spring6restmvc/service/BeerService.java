package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.dto.BeerDto;
import guru.springframework.spring6restmvc.entity.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Page<BeerDto> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto beerDto);

    Optional<BeerDto> updateBeerById(UUID beerId, BeerDto beerDto);

    Boolean deleteById(UUID beerId);

    Optional<BeerDto> patchBeerById(UUID beerId, BeerDto beerDto);
}
