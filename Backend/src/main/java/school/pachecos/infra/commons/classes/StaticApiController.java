package school.pachecos.infra.commons.classes;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.infra.commons.interfaces.BaseReturnDTO;

import java.net.URI;

@SuppressWarnings("rawtypes")
public abstract class StaticApiController<Entity extends BaseApiEntity, CreateDTO, UpdateDTO, ReturnDTO extends BaseReturnDTO, Service extends BaseApiService<Entity, CreateDTO, UpdateDTO, ReturnDTO>>
    extends BaseApiController<Entity, CreateDTO, UpdateDTO, ReturnDTO, Service> {

  @GetMapping
  @Override
  public ResponseEntity<Page<ReturnDTO>> listAll(@PageableDefault(size = 20) Pageable pageable) {
    return super.listAll(pageable);
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<ReturnDTO> getUnique(@PathVariable("id") Long id) {
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
  @Transactional
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<ReturnDTO> edit(@PathVariable("id") Long id, @RequestBody @Valid UpdateDTO dto) {
    return super.edit(id, dto);
  }

  @SuppressWarnings("rawtypes")
  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity delete(@PathVariable("id") Long id) {
    return super.delete(id);
  }

  protected abstract String getBaseUriPath();
}
