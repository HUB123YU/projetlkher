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

import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.dao.criteria.core.encadrant.EncadrantInterneCriteria;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.ws.converter.encadrant.EncadrantInterneConverter;
import ma.zs.stocky.ws.dto.encadrant.EncadrantInterneDto;
import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.AuditEntityDto;
import ma.zs.stocky.zynerator.util.PaginatedList;


import ma.zs.stocky.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.stocky.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.stocky.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/encadrantInterne/")
public class EncadrantInterneRestAdmin {




    @Operation(summary = "Finds a list of all encadrantInternes")
    @GetMapping("")
    public ResponseEntity<List<EncadrantInterneDto>> findAll() throws Exception {
        ResponseEntity<List<EncadrantInterneDto>> res = null;
        List<EncadrantInterne> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EncadrantInterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all encadrantInternes")
    @GetMapping("optimized")
    public ResponseEntity<List<EncadrantInterneDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EncadrantInterneDto>> res = null;
        List<EncadrantInterne> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EncadrantInterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a encadrantInterne by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EncadrantInterneDto> findById(@PathVariable Long id) {
        EncadrantInterne t = service.findById(id);
        if (t != null) {
            EncadrantInterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a encadrantInterne by code")
    @GetMapping("code/{code}")
    public ResponseEntity<EncadrantInterneDto> findByCode(@PathVariable String code) {
	    EncadrantInterne t = service.findByReferenceEntity(new EncadrantInterne(code));
        if (t != null) {
            EncadrantInterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  encadrantInterne")
    @PostMapping("")
    public ResponseEntity<EncadrantInterneDto> save(@RequestBody EncadrantInterneDto dto) throws Exception {
        if(dto!=null){
            EncadrantInterne myT = converter.toItem(dto);
            EncadrantInterne t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EncadrantInterneDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  encadrantInterne")
    @PutMapping("")
    public ResponseEntity<EncadrantInterneDto> update(@RequestBody EncadrantInterneDto dto) throws Exception {
        ResponseEntity<EncadrantInterneDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EncadrantInterne t = service.findById(dto.getId());
            converter.copy(dto,t);
            EncadrantInterne updated = service.update(t);
            EncadrantInterneDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of encadrantInterne")
    @PostMapping("multiple")
    public ResponseEntity<List<EncadrantInterneDto>> delete(@RequestBody List<EncadrantInterneDto> dtos) throws Exception {
        ResponseEntity<List<EncadrantInterneDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<EncadrantInterne> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified encadrantInterne")
    @DeleteMapping("")
    public ResponseEntity<EncadrantInterneDto> delete(@RequestBody EncadrantInterneDto dto) throws Exception {
		ResponseEntity<EncadrantInterneDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            EncadrantInterne t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified encadrantInterne")
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
    @Operation(summary = "Delete multiple encadrantInternes by ids")
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



    @Operation(summary = "Finds a encadrantInterne and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EncadrantInterneDto> findWithAssociatedLists(@PathVariable Long id) {
        EncadrantInterne loaded =  service.findWithAssociatedLists(id);
        EncadrantInterneDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds encadrantInternes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EncadrantInterneDto>> findByCriteria(@RequestBody EncadrantInterneCriteria criteria) throws Exception {
        ResponseEntity<List<EncadrantInterneDto>> res = null;
        List<EncadrantInterne> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EncadrantInterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated encadrantInternes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EncadrantInterneCriteria criteria) throws Exception {
        List<EncadrantInterne> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EncadrantInterneDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets encadrantInterne data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EncadrantInterneCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EncadrantInterneDto> findDtos(List<EncadrantInterne> list){
        List<EncadrantInterneDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EncadrantInterneDto> getDtoResponseEntity(EncadrantInterneDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private EncadrantInterneAdminService service;
    @Autowired private EncadrantInterneConverter converter;





}
