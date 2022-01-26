package io.legacyfighter.cabs.service;

import io.legacyfighter.cabs.config.AppProperties;
import io.legacyfighter.cabs.dto.CarTypeDTO;
import io.legacyfighter.cabs.entity.CarType;
import io.legacyfighter.cabs.repository.CarTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarTypeServiceTest {

    @Autowired
    private CarTypeService carTypeService;

    @Autowired
    private CarTypeRepository carTypeRepository;

    @Test
    void createCarType() {
        //given
        CarTypeDTO carTypeDTO = new CarTypeDTO(
            new CarType(CarType.CarClass.ECO,
            "Eco Car Type",
            1
            )
        );

        //when
        CarType resultCarType = carTypeService.create(carTypeDTO);

        //then
        CarType created = carTypeRepository.findByCarClass(CarType.CarClass.ECO);
        assertEquals(created.getStatus(), CarType.Status.INACTIVE);
        assertEquals(created.getCarClass(), CarType.CarClass.PREMIUM);
        assertEquals(created.getCarsCounter(), 0);

        assertEquals(resultCarType.getId(), created.getId());
    }
}