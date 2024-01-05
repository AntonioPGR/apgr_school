package school.pachecos.infra.commons.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import school.pachecos.infra.commons.interfaces.ReturnDTOInterface;
import school.pachecos.infra.uri.URIService;
import java.net.URI;

public abstract class BaseController<
	CreateDTO,
	UpdateDTO,
	ReturnDTO extends ReturnDTOInterface, 
	Service extends BaseService
>{

	@Autowired
	Service service;
	@Autowired
	URIService uri_service;

	@GetMapping
	public ResponseEntity<Page<ReturnDTO>> listAll(Pageable pageable) {
		Page<ReturnDTO> page = service.listPerPage(pageable);
		return ResponseEntity.ok().body(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReturnDTO> getUnique(@PathVariable("id") Long id) {
		ReturnDTO dto = (ReturnDTO) service.getById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ReturnDTO> create(@RequestBody @Valid CreateDTO dto) {
		ReturnDTO return_dto = (ReturnDTO) service.create(dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath() + return_dto.id());
		return ResponseEntity.created(entity_uri).body(return_dto);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReturnDTO> edit(@PathVariable("id") Long id, @RequestBody @Valid UpdateDTO dto) {
		ReturnDTO entity = (ReturnDTO) service.update(id, dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath() + id);
		return ResponseEntity.created(entity_uri).body(entity);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	public abstract String getBaseUriPath();
}
