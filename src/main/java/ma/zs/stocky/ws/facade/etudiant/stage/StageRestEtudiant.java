package  ma.zs.stocky.ws.facade.etudiant.stage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.stage.Stage;
import ma.zs.stocky.dao.criteria.core.stage.StageCriteria;
import ma.zs.stocky.service.facade.etudiant.stage.StageEtudiantService;
import ma.zs.stocky.ws.converter.stage.StageConverter;
import ma.zs.stocky.ws.dto.stage.StageDto;
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
@RequestMapping("/api/etudiant/stage/")
public class StageRestEtudiant {




    @Operation(summary = "Finds a list of all stages")
    @GetMapping("")
    public ResponseEntity<List<StageDto>> findAll() throws Exception {
        ResponseEntity<List<StageDto>> res = null;
        List<Stage> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<StageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a stage by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StageDto> findById(@PathVariable Long id) {
        Stage t = service.findById(id);
        if (t != null) {
            converter.init(true);
            StageDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  stage")
    @PostMapping("")
    public ResponseEntity<StageDto> save(@RequestBody StageDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Stage myT = converter.toItem(dto);
            Stage t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StageDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  stage")
    @PutMapping("")
    public ResponseEntity<StageDto> update(@RequestBody StageDto dto) throws Exception {
        ResponseEntity<StageDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Stage t = service.findById(dto.getId());
            converter.copy(dto,t);
            Stage updated = service.update(t);
            StageDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stage")
    @PostMapping("multiple")
    public ResponseEntity<List<StageDto>> delete(@RequestBody List<StageDto> dtos) throws Exception {
        ResponseEntity<List<StageDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Stage> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified stage")
    @DeleteMapping("")
    public ResponseEntity<StageDto> delete(@RequestBody StageDto dto) throws Exception {
		ResponseEntity<StageDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Stage t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified stage")
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
    @Operation(summary = "Delete multiple stages by ids")
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


    @Operation(summary = "find by domaine id")
    @GetMapping("domaine/id/{id}")
    public List<StageDto> findByDomaineId(@PathVariable Long id){
        return findDtos(service.findByDomaineId(id));
    }
    @Operation(summary = "delete by domaine id")
    @DeleteMapping("domaine/id/{id}")
    public int deleteByDomaineId(@PathVariable Long id){
        return service.deleteByDomaineId(id);
    }
    @Operation(summary = "find by jury id")
    @GetMapping("jury/id/{id}")
    public List<StageDto> findByJuryId(@PathVariable Long id){
        return findDtos(service.findByJuryId(id));
    }
    @Operation(summary = "delete by jury id")
    @DeleteMapping("jury/id/{id}")
    public int deleteByJuryId(@PathVariable Long id){
        return service.deleteByJuryId(id);
    }
    @Operation(summary = "find by filiere id")
    @GetMapping("filiere/id/{id}")
    public List<StageDto> findByFiliereId(@PathVariable Long id){
        return findDtos(service.findByFiliereId(id));
    }
    @Operation(summary = "delete by filiere id")
    @DeleteMapping("filiere/id/{id}")
    public int deleteByFiliereId(@PathVariable Long id){
        return service.deleteByFiliereId(id);
    }
    @Operation(summary = "find by typeStage id")
    @GetMapping("typeStage/id/{id}")
    public List<StageDto> findByTypeStageId(@PathVariable Long id){
        return findDtos(service.findByTypeStageId(id));
    }
    @Operation(summary = "delete by typeStage id")
    @DeleteMapping("typeStage/id/{id}")
    public int deleteByTypeStageId(@PathVariable Long id){
        return service.deleteByTypeStageId(id);
    }

    @Operation(summary = "Finds a stage and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StageDto> findWithAssociatedLists(@PathVariable Long id) {
        Stage loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        StageDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds stages by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StageDto>> findByCriteria(@RequestBody StageCriteria criteria) throws Exception {
        ResponseEntity<List<StageDto>> res = null;
        List<Stage> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<StageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated stages by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StageCriteria criteria) throws Exception {
        List<Stage> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<StageDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets stage data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StageCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StageDto> findDtos(List<Stage> list){
        converter.initList(false);
        converter.initObject(true);
        List<StageDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StageDto> getDtoResponseEntity(StageDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private StageEtudiantService service;
    @Autowired private StageConverter converter;





}
