package  ma.zs.stocky.ws.facade.admin.encadrant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.encadrant.EncadrantExterne;
import ma.zs.stocky.dao.criteria.core.encadrant.EncadrantExterneCriteria;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantExterneAdminService;
import ma.zs.stocky.ws.converter.encadrant.EncadrantExterneConverter;
import ma.zs.stocky.ws.dto.encadrant.EncadrantExterneDto;
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
@RequestMapping("/api/admin/encadrantExterne/")
public class EncadrantExterneRestAdmin {




    @Operation(summary = "Finds a list of all encadrantExternes")
    @GetMapping("")
    public ResponseEntity<List<EncadrantExterneDto>> findAll() throws Exception {
        ResponseEntity<List<EncadrantExterneDto>> res = null;
        List<EncadrantExterne> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<EncadrantExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all encadrantExternes")
    @GetMapping("optimized")
    public ResponseEntity<List<EncadrantExterneDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EncadrantExterneDto>> res = null;
        List<EncadrantExterne> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EncadrantExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a encadrantExterne by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EncadrantExterneDto> findById(@PathVariable Long id) {
        EncadrantExterne t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EncadrantExterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a encadrantExterne by code")
    @GetMapping("code/{code}")
    public ResponseEntity<EncadrantExterneDto> findByCode(@PathVariable String code) {
	    EncadrantExterne t = service.findByReferenceEntity(new EncadrantExterne(code));
        if (t != null) {
            converter.init(true);
            EncadrantExterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  encadrantExterne")
    @PostMapping("")
    public ResponseEntity<EncadrantExterneDto> save(@RequestBody EncadrantExterneDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            EncadrantExterne myT = converter.toItem(dto);
            EncadrantExterne t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EncadrantExterneDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  encadrantExterne")
    @PutMapping("")
    public ResponseEntity<EncadrantExterneDto> update(@RequestBody EncadrantExterneDto dto) throws Exception {
        ResponseEntity<EncadrantExterneDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EncadrantExterne t = service.findById(dto.getId());
            converter.copy(dto,t);
            EncadrantExterne updated = service.update(t);
            EncadrantExterneDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of encadrantExterne")
    @PostMapping("multiple")
    public ResponseEntity<List<EncadrantExterneDto>> delete(@RequestBody List<EncadrantExterneDto> dtos) throws Exception {
        ResponseEntity<List<EncadrantExterneDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<EncadrantExterne> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified encadrantExterne")
    @DeleteMapping("")
    public ResponseEntity<EncadrantExterneDto> delete(@RequestBody EncadrantExterneDto dto) throws Exception {
		ResponseEntity<EncadrantExterneDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            EncadrantExterne t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified encadrantExterne")
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
    @Operation(summary = "Delete multiple encadrantExternes by ids")
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



    @Operation(summary = "Finds a encadrantExterne and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EncadrantExterneDto> findWithAssociatedLists(@PathVariable Long id) {
        EncadrantExterne loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EncadrantExterneDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds encadrantExternes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EncadrantExterneDto>> findByCriteria(@RequestBody EncadrantExterneCriteria criteria) throws Exception {
        ResponseEntity<List<EncadrantExterneDto>> res = null;
        List<EncadrantExterne> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EncadrantExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated encadrantExternes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EncadrantExterneCriteria criteria) throws Exception {
        List<EncadrantExterne> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<EncadrantExterneDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets encadrantExterne data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EncadrantExterneCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EncadrantExterneDto> findDtos(List<EncadrantExterne> list){
        converter.initObject(true);
        List<EncadrantExterneDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EncadrantExterneDto> getDtoResponseEntity(EncadrantExterneDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EncadrantExterneAdminService service;
    @Autowired private EncadrantExterneConverter converter;





}
