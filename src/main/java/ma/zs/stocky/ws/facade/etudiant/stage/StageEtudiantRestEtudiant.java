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

import ma.zs.stocky.bean.core.stage.StageEtudiant;
import ma.zs.stocky.dao.criteria.core.stage.StageEtudiantCriteria;
import ma.zs.stocky.service.facade.etudiant.stage.StageEtudiantEtudiantService;
import ma.zs.stocky.ws.converter.stage.StageEtudiantConverter;
import ma.zs.stocky.ws.dto.stage.StageEtudiantDto;
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
@RequestMapping("/api/etudiant/stageEtudiant/")
public class StageEtudiantRestEtudiant {




    @Operation(summary = "Finds a list of all stageEtudiants")
    @GetMapping("")
    public ResponseEntity<List<StageEtudiantDto>> findAll() throws Exception {
        ResponseEntity<List<StageEtudiantDto>> res = null;
        List<StageEtudiant> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<StageEtudiantDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a stageEtudiant by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StageEtudiantDto> findById(@PathVariable Long id) {
        StageEtudiant t = service.findById(id);
        if (t != null) {
            converter.init(true);
            StageEtudiantDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  stageEtudiant")
    @PostMapping("")
    public ResponseEntity<StageEtudiantDto> save(@RequestBody StageEtudiantDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            StageEtudiant myT = converter.toItem(dto);
            StageEtudiant t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StageEtudiantDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  stageEtudiant")
    @PutMapping("")
    public ResponseEntity<StageEtudiantDto> update(@RequestBody StageEtudiantDto dto) throws Exception {
        ResponseEntity<StageEtudiantDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StageEtudiant t = service.findById(dto.getId());
            converter.copy(dto,t);
            StageEtudiant updated = service.update(t);
            StageEtudiantDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stageEtudiant")
    @PostMapping("multiple")
    public ResponseEntity<List<StageEtudiantDto>> delete(@RequestBody List<StageEtudiantDto> dtos) throws Exception {
        ResponseEntity<List<StageEtudiantDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<StageEtudiant> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified stageEtudiant")
    @DeleteMapping("")
    public ResponseEntity<StageEtudiantDto> delete(@RequestBody StageEtudiantDto dto) throws Exception {
		ResponseEntity<StageEtudiantDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            StageEtudiant t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified stageEtudiant")
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
    @Operation(summary = "Delete multiple stageEtudiants by ids")
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



    @Operation(summary = "Finds a stageEtudiant and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StageEtudiantDto> findWithAssociatedLists(@PathVariable Long id) {
        StageEtudiant loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        StageEtudiantDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds stageEtudiants by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StageEtudiantDto>> findByCriteria(@RequestBody StageEtudiantCriteria criteria) throws Exception {
        ResponseEntity<List<StageEtudiantDto>> res = null;
        List<StageEtudiant> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<StageEtudiantDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated stageEtudiants by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StageEtudiantCriteria criteria) throws Exception {
        List<StageEtudiant> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<StageEtudiantDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets stageEtudiant data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StageEtudiantCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StageEtudiantDto> findDtos(List<StageEtudiant> list){
        converter.initObject(true);
        List<StageEtudiantDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StageEtudiantDto> getDtoResponseEntity(StageEtudiantDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private StageEtudiantEtudiantService service;
    @Autowired private StageEtudiantConverter converter;





}
