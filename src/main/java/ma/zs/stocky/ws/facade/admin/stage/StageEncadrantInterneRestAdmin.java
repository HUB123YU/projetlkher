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

import ma.zs.stocky.bean.core.stage.StageEncadrantInterne;
import ma.zs.stocky.dao.criteria.core.stage.StageEncadrantInterneCriteria;
import ma.zs.stocky.service.facade.admin.stage.StageEncadrantInterneAdminService;
import ma.zs.stocky.ws.converter.stage.StageEncadrantInterneConverter;
import ma.zs.stocky.ws.dto.stage.StageEncadrantInterneDto;
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
@RequestMapping("/api/admin/stageEncadrantInterne/")
public class StageEncadrantInterneRestAdmin {




    @Operation(summary = "Finds a list of all stageEncadrantInternes")
    @GetMapping("")
    public ResponseEntity<List<StageEncadrantInterneDto>> findAll() throws Exception {
        ResponseEntity<List<StageEncadrantInterneDto>> res = null;
        List<StageEncadrantInterne> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<StageEncadrantInterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a stageEncadrantInterne by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StageEncadrantInterneDto> findById(@PathVariable Long id) {
        StageEncadrantInterne t = service.findById(id);
        if (t != null) {
            converter.init(true);
            StageEncadrantInterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  stageEncadrantInterne")
    @PostMapping("")
    public ResponseEntity<StageEncadrantInterneDto> save(@RequestBody StageEncadrantInterneDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            StageEncadrantInterne myT = converter.toItem(dto);
            StageEncadrantInterne t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StageEncadrantInterneDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  stageEncadrantInterne")
    @PutMapping("")
    public ResponseEntity<StageEncadrantInterneDto> update(@RequestBody StageEncadrantInterneDto dto) throws Exception {
        ResponseEntity<StageEncadrantInterneDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StageEncadrantInterne t = service.findById(dto.getId());
            converter.copy(dto,t);
            StageEncadrantInterne updated = service.update(t);
            StageEncadrantInterneDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stageEncadrantInterne")
    @PostMapping("multiple")
    public ResponseEntity<List<StageEncadrantInterneDto>> delete(@RequestBody List<StageEncadrantInterneDto> dtos) throws Exception {
        ResponseEntity<List<StageEncadrantInterneDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<StageEncadrantInterne> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified stageEncadrantInterne")
    @DeleteMapping("")
    public ResponseEntity<StageEncadrantInterneDto> delete(@RequestBody StageEncadrantInterneDto dto) throws Exception {
		ResponseEntity<StageEncadrantInterneDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            StageEncadrantInterne t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified stageEncadrantInterne")
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
    @Operation(summary = "Delete multiple stageEncadrantInternes by ids")
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



    @Operation(summary = "Finds a stageEncadrantInterne and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StageEncadrantInterneDto> findWithAssociatedLists(@PathVariable Long id) {
        StageEncadrantInterne loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        StageEncadrantInterneDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds stageEncadrantInternes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StageEncadrantInterneDto>> findByCriteria(@RequestBody StageEncadrantInterneCriteria criteria) throws Exception {
        ResponseEntity<List<StageEncadrantInterneDto>> res = null;
        List<StageEncadrantInterne> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<StageEncadrantInterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated stageEncadrantInternes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StageEncadrantInterneCriteria criteria) throws Exception {
        List<StageEncadrantInterne> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<StageEncadrantInterneDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets stageEncadrantInterne data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StageEncadrantInterneCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StageEncadrantInterneDto> findDtos(List<StageEncadrantInterne> list){
        converter.initObject(true);
        List<StageEncadrantInterneDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StageEncadrantInterneDto> getDtoResponseEntity(StageEncadrantInterneDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private StageEncadrantInterneAdminService service;
    @Autowired private StageEncadrantInterneConverter converter;





}
