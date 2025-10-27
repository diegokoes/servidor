package es.daw.productosrestcontroller.mapper;

import es.daw.productosrestcontroller.dto.ProductoDTO;
import es.daw.productosrestcontroller.entities.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    /*
  Nombres iguales de propiedades DTO<->ENTIDAD no hace falta hacer nada
  Si tuvieran nombres distintos :
  @Mapping(source="nombrePropiedadENTIDAD", target="nombrePropiedadDTO")
  si es de DTO a ENTIDAD -> se invierten
      @InheritInverseConfiguration lo hace solo!! 
   */
    ProductoDTO toProductoDTO(Producto producto);
    Producto toEntity(ProductoDTO productoDTO);

    List<ProductoDTO> toProductoDTOList(List<Producto> productoList);

    List<Producto> toEntityList(List<ProductoDTO> productoDTOList);
}
