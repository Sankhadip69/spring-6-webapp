package guru.springframework.spring6restmvc.service.impl;

import guru.springframework.spring6restmvc.dto.BeerDto;
import guru.springframework.spring6restmvc.entity.BeerStyle;
import guru.springframework.spring6restmvc.service.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

    private Map<UUID,BeerDto> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        BeerDto beerDto1 = BeerDto.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDto beerDto2 = BeerDto.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDto beerDto3 = BeerDto.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        beerMap.put(beerDto1.getId(), beerDto1);
        beerMap.put(beerDto2.getId(), beerDto2);
        beerMap.put(beerDto3.getId(), beerDto3);
    }

    @Override
    public Page<BeerDto> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize) {
        return new PageImpl<>(new ArrayList<>(beerMap.values()));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        BeerDto savedBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .version(beerDto.getVersion())
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .upc(beerDto.getUpc())
                .price(beerDto.getPrice())
                .quantityOnHand(beerDto.getQuantityOnHand())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        beerMap.put(savedBeer.getId(),savedBeer);
        return savedBeer;
    }

    @Override
    public Optional<BeerDto> updateBeerById(UUID beerId, BeerDto beerDto) {
        BeerDto existing = beerMap.get(beerId);
        existing.setBeerName(beerDto.getBeerName());
        existing.setPrice(beerDto.getPrice());
        existing.setUpc(beerDto.getUpc());
        existing.setQuantityOnHand(beerDto.getQuantityOnHand());
        return Optional.of(existing);
    }

    @Override
    public Boolean deleteById(UUID beerId) {

        beerMap.remove(beerId);

        return true;
    }

    @Override
    public Optional<BeerDto> patchBeerById(UUID beerId, BeerDto beerDto) {
        BeerDto existing = beerMap.get(beerId);

        if (StringUtils.hasText(beerDto.getBeerName())){
            existing.setBeerName(beerDto.getBeerName());
        }

        if (beerDto.getBeerStyle() != null) {
            existing.setBeerStyle(beerDto.getBeerStyle());
        }

        if (beerDto.getPrice() != null) {
            existing.setPrice(beerDto.getPrice());
        }

        if (beerDto.getQuantityOnHand() != null){
            existing.setQuantityOnHand(beerDto.getQuantityOnHand());
        }

        if (StringUtils.hasText(beerDto.getUpc())) {
            existing.setUpc(beerDto.getUpc());
        }

        return Optional.of(existing);
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {

        log.debug("Get Beer Id in Service is called");

        return Optional.of(beerMap.get(id));
    }

}
