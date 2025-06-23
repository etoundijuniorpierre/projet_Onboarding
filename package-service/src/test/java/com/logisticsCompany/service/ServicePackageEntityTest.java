package com.logisticsCompany.service;

import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.repository.PackageEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static com.logisticsCompany.entities.enums.Status.DELIVERED;
import static com.logisticsCompany.entities.enums.Status.NOT_DELIVERED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // call mockito in JUnit 5
class ServicePackageEntityTest {

    @Mock //mocks the repository
    private PackageEntityRepository packageEntityRepository;

    @Mock //moks the mapper
    private PackageMapper packageMapper;

    @InjectMocks //Inject the mocked repository and mapper in service
    private ServicePackageEntity servicePackageEntity;


    //TEST CREATE
    @Test
    void shouldCreateNewPackageEntity() {
        // 1- given

        //initialisation d'une new entity reponse and request dto
        PackageRequestDto requestDto = new PackageRequestDto();
        requestDto.setDescription("une description");
        requestDto.setWeight(10);
        requestDto.setFragile(true);
        requestDto.setStatus(DELIVERED);

        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setId(1L);
        packageEntity.setDescription("une description");
        packageEntity.setWeight(10);
        packageEntity.setFragile(true);
        packageEntity.setStatus(DELIVERED);

        PackageReponseDto reponseDto = new PackageReponseDto();
        reponseDto.setId(1L);
        reponseDto.setDescription("une description");
        reponseDto.setWeight(10);
        reponseDto.setFragile(true);
        reponseDto.setStatus(DELIVERED);

        // 2- when

        // condition + mapping
        when(packageMapper.toEntity(requestDto)).thenReturn(packageEntity);
        when(packageEntityRepository.save(packageEntity)).thenReturn(packageEntity);
        when(packageMapper.toDto(packageEntity)).thenReturn(reponseDto);
        PackageReponseDto result = servicePackageEntity.createPackage(requestDto);

        // 3- then

        // vérification appel des méthodes
        assertNotNull(result);
        verify(packageMapper).toEntity(requestDto);
        verify(packageEntityRepository).save(packageEntity);
        verify(packageMapper).toDto(packageEntity);
    }



    //test GETall
    @Test
    void shouldGetListOfAllPackages() {
            // 1- given

        // initialise l'entity 1
            PackageEntity package1 = new PackageEntity();
            package1.setId(1L);
            package1.setDescription("Une description 1");
            package1.setWeight(5);
            package1.setFragile(true);
            package1.setStatus(DELIVERED);

        // initialise l'entity 2
            PackageEntity package2 = new PackageEntity();
            package2.setId(2L);
            package2.setDescription("Une description 2");
            package2.setWeight(8);
            package2.setFragile(false);
            package2.setStatus(NOT_DELIVERED);

        // mettre dans une entityList
            List<PackageEntity> entities = List.of(package1, package2);

        // initialise reponseDto 1
            PackageReponseDto dto1 = new PackageReponseDto();
            dto1.setId(1L);
            dto1.setDescription("Une description 1");
            dto1.setWeight(5);
            dto1.setFragile(true);
            dto1.setStatus(DELIVERED);

        // initialise reponseDto 2
            PackageReponseDto dto2 = new PackageReponseDto();
            dto2.setId(2L);
            dto2.setDescription("Une description 2");
            dto2.setWeight(8);
            dto2.setFragile(false);
            dto2.setStatus(NOT_DELIVERED);

            //mettre dans une reponseDtoList
            List<PackageReponseDto> expectedDtos = List.of(dto1, dto2);

            //2- when
            when(packageEntityRepository.findAll()).thenReturn(entities);
            when(packageMapper.toDtoList(entities)).thenReturn(expectedDtos);


            List<PackageReponseDto> result = servicePackageEntity.getAllPackages();

            //3- then

        // condition à respecter
            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Une description 1", result.get(0).getDescription());
            assertEquals("Une description 2", result.get(1).getDescription());
    }



//TEST GETBYid
    @Test
    void shouldGetPackageById() {
        // 1- given

        //param à chercher
        Long id = 1L;

        // initialise l'entity
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setId(id);

        // initialise le reponseDto
        PackageReponseDto reponseDto = new PackageReponseDto();
        reponseDto.setId(id);

        //2- when
        when(packageEntityRepository.findById(id)).thenReturn(Optional.of(packageEntity));
        when(packageMapper.toDto(packageEntity)).thenReturn(reponseDto);

        PackageReponseDto result = servicePackageEntity.getPackageById(id);

        //3- then

        // condition à respecter
        assertEquals(id, result.getId());
    }



    @Test
    void shouldUpdatePackageEntity() {
        // 1- given
        //param à chercher
        Long id = 1L;

        // initialise le new requesDto
        PackageRequestDto requestoDto = new PackageRequestDto();
        requestoDto.setDescription("new description");
        requestoDto.setWeight(11);
        requestoDto.setFragile(true);
        requestoDto.setStatus(NOT_DELIVERED);

        // initialise actual entity
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setId(1L);
        packageEntity.setDescription("une description");
        packageEntity.setWeight(10);
        packageEntity.setFragile(true);
        packageEntity.setStatus(DELIVERED);

        // initialise new update entity
        PackageEntity UpdatePackageEntity = new PackageEntity();
        packageEntity.setId(1L);
        requestoDto.setDescription("new description");
        requestoDto.setWeight(11);
        requestoDto.setFragile(true);
        requestoDto.setStatus(NOT_DELIVERED);

        // initialise le update reponseDto
        PackageReponseDto UpdateReponseDto = new PackageReponseDto();
        UpdateReponseDto.setId(1L);
        UpdateReponseDto.setDescription("new description");
        UpdateReponseDto.setWeight(11);
        UpdateReponseDto.setFragile(true);
        UpdateReponseDto.setStatus(NOT_DELIVERED);

        //2- when
        when(packageEntityRepository.findById(id)).thenReturn(Optional.of(packageEntity));
        when(packageEntityRepository.save(packageEntity)).thenReturn(UpdatePackageEntity);
        when(packageMapper.toDto(UpdatePackageEntity)).thenReturn(UpdateReponseDto);

        PackageReponseDto result = servicePackageEntity.updatePackage(id, requestoDto);

        //3- then
        assertEquals(UpdateReponseDto, result);

    }

    @Test
    void shouldDeletePackageEntity() {
        // 1- given
        Long id = 1L;

        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setId(id);

        // 2- when

        // Configuration du mock pour FINDById
        when(packageEntityRepository.findById(id)).thenReturn(Optional.of(packageEntity));

        servicePackageEntity.deletePackage(id);

        // 3- then

        // vérification appel du FINDBiId
        verify(packageEntityRepository).findById(id);
        // vérification appel du delete
        verify(packageEntityRepository).delete(packageEntity);
    }
}