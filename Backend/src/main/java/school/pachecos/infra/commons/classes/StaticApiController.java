package school.pachecos.infra.commons.classes;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.infra.commons.dtos.IdDTO;
import school.pachecos.infra.commons.interfaces.BaseReturnDTO;
import school.pachecos.infra.commons.interfaces.BaseUpdateDTO;
import school.pachecos.infra.uri.URIService;

import java.net.URI;
import java.util.UUID;

public abstract class StaticApiController<Entity extends BaseApiEntity, CreateDTO, UpdateDTO extends BaseUpdateDTO, ReturnDTO extends BaseReturnDTO, Service extends BaseApiService<Entity, CreateDTO, UpdateDTO, ReturnDTO>>
    extends BaseApiController<Entity, CreateDTO, UpdateDTO, ReturnDTO, Service> {

  @GetMapping
  @Override
  public ResponseEntity<Page<ReturnDTO>> listAll(@PageableDefault(size = 20) Pageable pageable) {
    return super.listAll(pageable);
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<ReturnDTO> getUnique(@PathVariable("id") UUID id) {
    return super.getUnique(id);
  }

  // POST
  @PostMapping
  @Transactional
  @Override
  public ResponseEntity<ReturnDTO> create(@RequestBody @Valid CreateDTO dto) {
    return super.create(dto);
  }

  // PUT
  @PutMapping
  @Transactional
  @Override
  public ResponseEntity<URI> edit(@RequestBody @Valid UpdateDTO dto) {
    return super.edit(dto);
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity delete(@PathVariable("id") UUID id) {
    return super.delete(id);
  }

  protected abstract String getBaseUriPath();
}
