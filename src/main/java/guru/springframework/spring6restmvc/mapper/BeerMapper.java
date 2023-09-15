package guru.springframework.spring6restmvc.mapper;

import guru.springframework.spring6restmvc.dto.BeerDto;
import guru.springframework.spring6restmvc.entity.Beer;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto beerDto);

    BeerDto beerToBeerDto(Beer beer);
}
