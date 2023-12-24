package school.pachecos.infra.commons.classes;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.assignments.dtos.AssignmentReturnDTO;
import school.pachecos.infra.commons.dtos.IdDTO;
import school.pachecos.infra.commons.interfaces.BaseReturnDTO;
import school.pachecos.infra.commons.interfaces.BaseUpdateDTO;
import school.pachecos.infra.uri.URIService;

import java.net.URI;


@RestController
public abstract class BaseApiController<
		Entity extends BaseApiEntity,
		CreateDTO,
		UpdateDTO extends BaseUpdateDTO,
		ReturnDTO extends BaseReturnDTO,
		Service extends BaseApiService<Entity, CreateDTO, UpdateDTO, ReturnDTO>
	> {

	@Autowired
	Service service;
	@Autowired
	URIService uri_service;

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
	public ResponseEntity<URI> create(@RequestBody @Valid CreateDTO dto) {
		ReturnDTO entity = service.create(dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath()+entity.id());
		return ResponseEntity.created(entity_uri).build();
	}

	// PUT
	@PutMapping
	@Transactional
	public ResponseEntity<URI> edit(@RequestBody @Valid UpdateDTO dto){
		ReturnDTO entity = service.update(dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath()+entity.id());
		return ResponseEntity.created(entity_uri).build();
	}

	@DeleteMapping()
	@Transactional
	public ResponseEntity delete(@RequestBody @Valid IdDTO dto){
		service.delete(dto.id());
		return ResponseEntity.noContent().build();
	}

	protected abstract String getBaseUriPath();
}
