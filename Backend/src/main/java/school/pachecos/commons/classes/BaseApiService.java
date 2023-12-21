package school.pachecos.commons.classes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public abstract class BaseApiService<Entity extends BaseApiEntity, CreateDTO, UpdateDTO, ReturnDTO> {

	@Autowired
	private JpaRepository<Entity, Long> repository;

	public Page<ReturnDTO> listPerPage(Pageable pageable){
		Page<Entity> list_user_entity = repository.findAll(pageable);
		return list_user_entity.map(this::convertToReturnDTO);
	}

	public ReturnDTO getById(Long user_id){
		Entity user_entity = repository.getReferenceById(user_id);
		return convertToReturnDTO(user_entity);
	}

	public ReturnDTO create(CreateDTO user_info){
		Entity user_entity = convertToEntity(user_info);
		repository.save(user_entity);
		return convertToReturnDTO(user_entity);
	}

	public ReturnDTO update(Long id, UpdateDTO update_info){
		Entity user_entity = repository.getReferenceById(id);
		user_entity.update(update_info);
		return convertToReturnDTO(user_entity);
	}

	public void delete(Long id){
		Entity user_entity = repository.getReferenceById(id);
		repository.delete(user_entity);
	}

	protected abstract ReturnDTO convertToReturnDTO(Entity entity);
	protected abstract Entity convertToEntity(CreateDTO create_dto);

}
