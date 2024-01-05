package school.pachecos.infra.commons.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import school.pachecos.infra.commons.interfaces.EntityInterface;
import school.pachecos.infra.commons.interfaces.ReturnDTOInterface;


public abstract class BaseService<
	Entity extends EntityInterface<UpdateEntityDTO>, 
	CreateIdDTO,
	CreateEntityDTO,
	UpdateIdDTO, 
	UpdateEntityDTO,
	ReturnDTO extends ReturnDTOInterface,
	Repository extends JpaRepository<Entity, Long>
>{

	@Autowired
	Repository repository;

	Page<ReturnDTO> listPerPage(Pageable pageable){
		Page<Entity> entitites = repository.findAll(pageable);
		return entitites.map(this::convertToReturnDTO);
	}

  ReturnDTO getById(Long id){
		Entity entity = repository.getReferenceById(id);
		return convertToReturnDTO(entity);
	}

  public Entity getEntityById(Long id){
		return repository.getReferenceById(id);
	}

  protected ReturnDTO create(CreateIdDTO dto){
		CreateEntityDTO entity_dto = convertToCreateDTO(dto);
		Entity entity = convertToEntity(entity_dto);
		repository.save(entity);
		return convertToReturnDTO(entity);
	}

  ReturnDTO update(Long id, UpdateIdDTO dto) {
		UpdateEntityDTO entity_dto = convertToUpdateDTO(dto);
		Entity entity = getEntityById(id);
		entity.update(entity_dto);
		return convertToReturnDTO(entity);
	}

  void delete(Long id) {
		Entity entity = getEntityById(id);
		repository.delete(entity);
	}

  protected abstract CreateEntityDTO convertToCreateDTO(CreateIdDTO dto);

	protected abstract UpdateEntityDTO convertToUpdateDTO(UpdateIdDTO dto);

	protected abstract ReturnDTO convertToReturnDTO(Entity entity);
	
	protected abstract Entity convertToEntity(CreateEntityDTO dto);

}
