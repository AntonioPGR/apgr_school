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
import java.util.UUID;

public abstract class BaseApiController<Entity extends BaseApiEntity, CreateDTO, UpdateDTO extends BaseUpdateDTO, ReturnDTO extends BaseReturnDTO, Service extends BaseApiService<Entity, CreateDTO, UpdateDTO, ReturnDTO>> {

	@Autowired
	Service service;
	@Autowired
	URIService uri_service;

	public ResponseEntity<Page<ReturnDTO>> listAll(Pageable pageable) {
		Page<ReturnDTO> page = service.listPerPage(pageable);
		return ResponseEntity.ok().body(page);
	}

	public ResponseEntity<ReturnDTO> getUnique(UUID id) {
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
	public ResponseEntity<URI> edit(UpdateDTO dto) {
		ReturnDTO entity = service.update(dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath() + entity.id());
		return ResponseEntity.created(entity_uri).build();
	}

	@Transactional
	public ResponseEntity delete(UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	protected abstract String getBaseUriPath();
}
