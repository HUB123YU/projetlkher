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

import ma.zs.stocky.bean.core.stage.StageEncadrantExterne;
import ma.zs.stocky.dao.criteria.core.stage.StageEncadrantExterneCriteria;
import ma.zs.stocky.service.facade.etudiant.stage.StageEncadrantExterneEtudiantService;
import ma.zs.stocky.ws.converter.stage.StageEncadrantExterneConverter;
import ma.zs.stocky.ws.dto.stage.StageEncadrantExterneDto;
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
@RequestMapping("/api/etudiant/stageEncadrantExterne/")
public class StageEncadrantExterneRestEtudiant {




    @Operation(summary = "Finds a list of all stageEncadrantExternes")
    @GetMapping("")
    public ResponseEntity<List<StageEncadrantExterneDto>> findAll() throws Exception {
        ResponseEntity<List<StageEncadrantExterneDto>> res = null;
        List<StageEncadrantExterne> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<StageEncadrantExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a stageEncadrantExterne by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StageEncadrantExterneDto> findById(@PathVariable Long id) {
        StageEncadrantExterne t = service.findById(id);
        if (t != null) {
            converter.init(true);
            StageEncadrantExterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  stageEncadrantExterne")
    @PostMapping("")
    public ResponseEntity<StageEncadrantExterneDto> save(@RequestBody StageEncadrantExterneDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            StageEncadrantExterne myT = converter.toItem(dto);
            StageEncadrantExterne t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StageEncadrantExterneDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  stageEncadrantExterne")
    @PutMapping("")
    public ResponseEntity<StageEncadrantExterneDto> update(@RequestBody StageEncadrantExterneDto dto) throws Exception {
        ResponseEntity<StageEncadrantExterneDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StageEncadrantExterne t = service.findById(dto.getId());
            converter.copy(dto,t);
            StageEncadrantExterne updated = service.update(t);
            StageEncadrantExterneDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stageEncadrantExterne")
    @PostMapping("multiple")
    public ResponseEntity<List<StageEncadrantExterneDto>> delete(@RequestBody List<StageEncadrantExterneDto> dtos) throws Exception {
        ResponseEntity<List<StageEncadrantExterneDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<StageEncadrantExterne> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified stageEncadrantExterne")
    @DeleteMapping("")
    public ResponseEntity<StageEncadrantExterneDto> delete(@RequestBody StageEncadrantExterneDto dto) throws Exception {
		ResponseEntity<StageEncadrantExterneDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            StageEncadrantExterne t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified stageEncadrantExterne")
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
    @Operation(summary = "Delete multiple stageEncadrantExternes by ids")
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


    @Operation(summary = "find by stage id")
    @GetMapping("stage/id/{id}")
    public List<StageEncadrantExterneDto> findByStageId(@PathVariable Long id){
        return findDtos(service.findByStageId(id));
    }
    @Operation(summary = "delete by stage id")
    @DeleteMapping("stage/id/{id}")
    public int deleteByStageId(@PathVariable Long id){
        return service.deleteByStageId(id);
    }
    @Operation(summary = "find by encadrantExterne id")
    @GetMapping("encadrantExterne/id/{id}")
    public List<StageEncadrantExterneDto> findByEncadrantExterneId(@PathVariable Long id){
        return findDtos(service.findByEncadrantExterneId(id));
    }
    @Operation(summary = "delete by encadrantExterne id")
    @DeleteMapping("encadrantExterne/id/{id}")
    public int deleteByEncadrantExterneId(@PathVariable Long id){
        return service.deleteByEncadrantExterneId(id);
    }

    @Operation(summary = "Finds a stageEncadrantExterne and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StageEncadrantExterneDto> findWithAssociatedLists(@PathVariable Long id) {
        StageEncadrantExterne loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        StageEncadrantExterneDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds stageEncadrantExternes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StageEncadrantExterneDto>> findByCriteria(@RequestBody StageEncadrantExterneCriteria criteria) throws Exception {
        ResponseEntity<List<StageEncadrantExterneDto>> res = null;
        List<StageEncadrantExterne> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<StageEncadrantExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated stageEncadrantExternes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StageEncadrantExterneCriteria criteria) throws Exception {
        List<StageEncadrantExterne> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<StageEncadrantExterneDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets stageEncadrantExterne data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StageEncadrantExterneCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StageEncadrantExterneDto> findDtos(List<StageEncadrantExterne> list){
        converter.initObject(true);
        List<StageEncadrantExterneDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StageEncadrantExterneDto> getDtoResponseEntity(StageEncadrantExterneDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private StageEncadrantExterneEtudiantService service;
    @Autowired private StageEncadrantExterneConverter converter;





}
