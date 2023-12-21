package school.pachecos.commons.classes;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.commons.dtos.IdDTO;
import school.pachecos.commons.interfaces.BaseUpdateDTO;


@RestController
public abstract class BaseApiController<Entity extends BaseApiEntity, CreateDTO, UpdateDTO extends BaseUpdateDTO, ReturnDTO, Service extends BaseApiService<Entity, CreateDTO, UpdateDTO, ReturnDTO>> {

	@Autowired
	Service service;

	@GetMapping
	public ResponseEntity<Page<ReturnDTO>> listAll(@PageableDefault(size = 20) Pageable pageable){
		Page<ReturnDTO> page = service.listPerPage(pageable);
		return ResponseEntity.ok().body(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReturnDTO> getUnique(@PathVariable("id") long id){
		ReturnDTO dto = (ReturnDTO) service.getById(id);
		return ResponseEntity.ok().body(dto);
	}

	// POST
	@PostMapping
	@Transactional
	public ResponseEntity create(@RequestBody @Valid CreateDTO dto) {
		service.create(dto);
		return ResponseEntity.ok().build();
	}

	// PUT
	@PutMapping
	@Transactional
	public ResponseEntity edit(@RequestBody @Valid UpdateDTO dto){
		service.update(dto.getId(), dto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping()
	@Transactional
	public ResponseEntity delete(@RequestBody @Valid IdDTO dto){
		service.delete(dto.id());
		return ResponseEntity.noContent().build();
	}

}
