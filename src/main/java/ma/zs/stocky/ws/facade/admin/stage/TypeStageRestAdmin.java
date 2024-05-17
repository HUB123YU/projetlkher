package  ma.zs.stocky.ws.facade.admin.stage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.stage.TypeStage;
import ma.zs.stocky.dao.criteria.core.stage.TypeStageCriteria;
import ma.zs.stocky.service.facade.admin.stage.TypeStageAdminService;
import ma.zs.stocky.ws.converter.stage.TypeStageConverter;
import ma.zs.stocky.ws.dto.stage.TypeStageDto;
import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.AuditEntityDto;
import ma.zs.stocky.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.stocky.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.stocky.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/typeStage/")
public class TypeStageRestAdmin {




    @Operation(summary = "Finds a list of all typeStages")
    @GetMapping("")
    public ResponseEntity<List<TypeStageDto>> findAll() throws Exception {
        ResponseEntity<List<TypeStageDto>> res = null;
        List<TypeStage> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeStageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeStages")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeStageDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeStageDto>> res = null;
        List<TypeStage> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeStageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeStage by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeStageDto> findById(@PathVariable Long id) {
        TypeStage t = service.findById(id);
        if (t != null) {
            TypeStageDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeStage by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeStageDto> findByLibelle(@PathVariable String libelle) {
	    TypeStage t = service.findByReferenceEntity(new TypeStage(libelle));
        if (t != null) {
            TypeStageDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeStage")
    @PostMapping("")
    public ResponseEntity<TypeStageDto> save(@RequestBody TypeStageDto dto) throws Exception {
        if(dto!=null){
            TypeStage myT = converter.toItem(dto);
            TypeStage t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeStageDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeStage")
    @PutMapping("")
    public ResponseEntity<TypeStageDto> update(@RequestBody TypeStageDto dto) throws Exception {
        ResponseEntity<TypeStageDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeStage t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeStage updated = service.update(t);
            TypeStageDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeStage")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeStageDto>> delete(@RequestBody List<TypeStageDto> dtos) throws Exception {
        ResponseEntity<List<TypeStageDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeStage> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified typeStage")
    @DeleteMapping("")
    public ResponseEntity<TypeStageDto> delete(@RequestBody TypeStageDto dto) throws Exception {
		ResponseEntity<TypeStageDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            TypeStage t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeStage")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple typeStages by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }



    @Operation(summary = "Finds a typeStage and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeStageDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeStage loaded =  service.findWithAssociatedLists(id);
        TypeStageDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeStages by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeStageDto>> findByCriteria(@RequestBody TypeStageCriteria criteria) throws Exception {
        ResponseEntity<List<TypeStageDto>> res = null;
        List<TypeStage> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeStageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeStages by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeStageCriteria criteria) throws Exception {
        List<TypeStage> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeStageDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeStage data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeStageCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeStageDto> findDtos(List<TypeStage> list){
        List<TypeStageDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeStageDto> getDtoResponseEntity(TypeStageDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TypeStageAdminService service;
    @Autowired private TypeStageConverter converter;





}
