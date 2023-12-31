package school.pachecos.infra.commons.classes;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import school.pachecos.infra.commons.interfaces.BaseReturnDTO;
import school.pachecos.infra.uri.URIService;

import java.net.URI;

public abstract class BaseApiController<Entity extends BaseApiEntity, CreateDTO, UpdateDTO, ReturnDTO extends BaseReturnDTO, Service extends BaseApiService<Entity, CreateDTO, UpdateDTO, ReturnDTO>> {

	@Autowired
	Service service;
	@Autowired
	URIService uri_service;

	public ResponseEntity<Page<ReturnDTO>> listAll(Pageable pageable) {
		Page<ReturnDTO> page = service.listPerPage(pageable);
		return ResponseEntity.ok().body(page);
	}

	public ResponseEntity<ReturnDTO> getUnique(Long id) {
		ReturnDTO dto = (ReturnDTO) service.getById(id);
		return ResponseEntity.ok().body(dto);
	}

	// POST
	@Transactional
	public ResponseEntity<ReturnDTO> create(CreateDTO dto) {
		ReturnDTO return_dto = service.create(dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath() + return_dto.id());
		return ResponseEntity.created(entity_uri).body(return_dto);
	}

	// PUT
	@Transactional
	public ResponseEntity<ReturnDTO> edit(Long id, UpdateDTO dto) {
		ReturnDTO entity = service.update(id, dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath() + id);
		return ResponseEntity.created(entity_uri).body(entity);
	}

	@Transactional
	public ResponseEntity delete(Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	protected abstract String getBaseUriPath();
}
