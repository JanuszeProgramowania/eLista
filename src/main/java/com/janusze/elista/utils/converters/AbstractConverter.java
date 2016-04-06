package com.janusze.elista.utils.converters;

import org.dozer.Mapper;

/**
 * Created by Tomasz Jodko on 2016-04-06.
 */

public abstract class AbstractConverter<OB, DTO> implements IConverter<OB, DTO> {
    private Mapper mapper;
    private java.lang.Class<OB> classOB;
    private java.lang.Class<DTO> classDTO;


    public AbstractConverter(Mapper mapper, Class<OB> classOB, Class<DTO> classDTO) {
        this.mapper = mapper;
        this.classOB = classOB;
        this.classDTO = classDTO;
    }

    @Override
    public OB mapDTOtoOB(DTO dto) {
        OB ob = null;
        if (dto != null) {
            ob = mapper.map(dto, classOB);
        }
        return ob;
    }

    @Override
    public DTO mapOBtoDTO(OB ob) {
        DTO dto = null;
        if (ob != null) {
            dto = mapper.map(ob, classDTO);
        }
        return dto;
    }
}
