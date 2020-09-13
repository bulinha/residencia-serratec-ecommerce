package br.org.serratec.backend.ecommerce.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.org.serratec.backend.ecommerce.enums.PedidoStatus;

@Converter(autoApply = true)
public class PedidoStatusConverter implements AttributeConverter<PedidoStatus, Integer>{

    @Override
    public Integer convertToDatabaseColumn(PedidoStatus pedidoStatus) {
        if (pedidoStatus==null) {
            return null;
        }
        return pedidoStatus.getCode();
    }

    @Override
    public PedidoStatus convertToEntityAttribute(Integer code) {
        if (code==null) {
            return null;
        }

        for(PedidoStatus status: PedidoStatus.values()) {
            if (status.getCode()==code.intValue()) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }


}