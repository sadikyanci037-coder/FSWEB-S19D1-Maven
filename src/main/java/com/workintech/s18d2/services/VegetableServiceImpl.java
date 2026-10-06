package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.repository.VegetableRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VegetableServiceImpl implements VegetableService {

    private final VegetableRepository vegetableRepository;

    public VegetableServiceImpl(VegetableRepository vegetableRepository) {
        this.vegetableRepository = vegetableRepository;
    }

    @Override
    public List<Vegetable> getByPriceAsc() {
        return vegetableRepository.getByPriceAsc();
    }

    @Override
    public List<Vegetable> getByPriceDesc() {
        return vegetableRepository.getByPriceDesc();
    }

    @Override
    public List<Vegetable> searchByName(String name) {
        return vegetableRepository.searchByName(name);
    }

    @Override
    public Vegetable getById(Long id) {
        if (id < 0) {
            throw new PlantException(
                    "Id değeri 0'dan küçük olamaz.",
                    HttpStatus.BAD_REQUEST
            );
        }

        return vegetableRepository.findById(id)
                .orElseThrow(() ->
                        new PlantException(
                                "Vegetable bulunamadı.",
                                HttpStatus.NOT_FOUND
                        )
                );
    }

    @Override
    public Vegetable save(Vegetable vegetable) {
        if (vegetable == null ||
                vegetable.getName() == null ||
                vegetable.getPrice() == null) {

            throw new PlantException(
                    "Vegetable bilgileri eksik olamaz.",
                    HttpStatus.BAD_REQUEST
            );
        }

        return vegetableRepository.save(vegetable);
    }

    @Override
    public Vegetable delete(Long id) {
        Vegetable vegetable = getById(id);
        vegetableRepository.delete(vegetable);
        return vegetable;
    }
}