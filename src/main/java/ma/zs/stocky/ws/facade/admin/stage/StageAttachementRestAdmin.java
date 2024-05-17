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

import ma.zs.stocky.bean.core.stage.StageAttachement;
import ma.zs.stocky.dao.criteria.core.stage.StageAttachementCriteria;
import ma.zs.stocky.service.facade.admin.stage.StageAttachementAdminService;
import ma.zs.stocky.ws.converter.stage.StageAttachementConverter;
import ma.zs.stocky.ws.dto.stage.StageAttachementDto;
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
@RequestMapping("/api/admin/stageAttachement/")
public class StageAttachementRestAdmin {




    @Operation(summary = "Finds a list of all stageAttachements")
    @GetMapping("")
    public ResponseEntity<List<StageAttachementDto>> findAll() throws Exception {
        ResponseEntity<List<StageAttachementDto>> res = null;
        List<StageAttachement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<StageAttachementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a stageAttachement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StageAttachementDto> findById(@PathVariable Long id) {
        StageAttachement t = service.findById(id);
        if (t != null) {
            converter.init(true);
            StageAttachementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  stageAttachement")
    @PostMapping("")
    public ResponseEntity<StageAttachementDto> save(@RequestBody StageAttachementDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            StageAttachement myT = converter.toItem(dto);
            StageAttachement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StageAttachementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  stageAttachement")
    @PutMapping("")
    public ResponseEntity<StageAttachementDto> update(@RequestBody StageAttachementDto dto) throws Exception {
        ResponseEntity<StageAttachementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StageAttachement t = service.findById(dto.getId());
            converter.copy(dto,t);
            StageAttachement updated = service.update(t);
            StageAttachementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stageAttachement")
    @PostMapping("multiple")
    public ResponseEntity<List<StageAttachementDto>> delete(@RequestBody List<StageAttachementDto> dtos) throws Exception {
        ResponseEntity<List<StageAttachementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<StageAttachement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified stageAttachement")
    @DeleteMapping("")
    public ResponseEntity<StageAttachementDto> delete(@RequestBody StageAttachementDto dto) throws Exception {
		ResponseEntity<StageAttachementDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            StageAttachement t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified stageAttachement")
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
    @Operation(summary = "Delete multiple stageAttachements by ids")
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


    @Operation(summary = "find by attachement id")
    @GetMapping("attachement/id/{id}")
    public List<StageAttachementDto> findByAttachementId(@PathVariable Long id){
        return findDtos(service.findByAttachementId(id));
    }
    @Operation(summary = "delete by attachement id")
    @DeleteMapping("attachement/id/{id}")
    public int deleteByAttachementId(@PathVariable Long id){
        return service.deleteByAttachementId(id);
    }

    @Operation(summary = "Finds a stageAttachement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StageAttachementDto> findWithAssociatedLists(@PathVariable Long id) {
        StageAttachement loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        StageAttachementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds stageAttachements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StageAttachementDto>> findByCriteria(@RequestBody StageAttachementCriteria criteria) throws Exception {
        ResponseEntity<List<StageAttachementDto>> res = null;
        List<StageAttachement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<StageAttachementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated stageAttachements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StageAttachementCriteria criteria) throws Exception {
        List<StageAttachement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<StageAttachementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets stageAttachement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StageAttachementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StageAttachementDto> findDtos(List<StageAttachement> list){
        converter.initObject(true);
        List<StageAttachementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StageAttachementDto> getDtoResponseEntity(StageAttachementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private StageAttachementAdminService service;
    @Autowired private StageAttachementConverter converter;





}
