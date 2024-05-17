package  ma.zs.stocky.ws.facade.admin.appartenance;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.appartenance.Nationalite;
import ma.zs.stocky.dao.criteria.core.appartenance.NationaliteCriteria;
import ma.zs.stocky.service.facade.admin.appartenance.NationaliteAdminService;
import ma.zs.stocky.ws.converter.appartenance.NationaliteConverter;
import ma.zs.stocky.ws.dto.appartenance.NationaliteDto;
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
@RequestMapping("/api/admin/nationalite/")
public class NationaliteRestAdmin {




    @Operation(summary = "Finds a list of all nationalites")
    @GetMapping("")
    public ResponseEntity<List<NationaliteDto>> findAll() throws Exception {
        ResponseEntity<List<NationaliteDto>> res = null;
        List<Nationalite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<NationaliteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all nationalites")
    @GetMapping("optimized")
    public ResponseEntity<List<NationaliteDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<NationaliteDto>> res = null;
        List<Nationalite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<NationaliteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a nationalite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<NationaliteDto> findById(@PathVariable Long id) {
        Nationalite t = service.findById(id);
        if (t != null) {
            NationaliteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a nationalite by code")
    @GetMapping("code/{code}")
    public ResponseEntity<NationaliteDto> findByCode(@PathVariable String code) {
	    Nationalite t = service.findByReferenceEntity(new Nationalite(code));
        if (t != null) {
            NationaliteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  nationalite")
    @PostMapping("")
    public ResponseEntity<NationaliteDto> save(@RequestBody NationaliteDto dto) throws Exception {
        if(dto!=null){
            Nationalite myT = converter.toItem(dto);
            Nationalite t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                NationaliteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  nationalite")
    @PutMapping("")
    public ResponseEntity<NationaliteDto> update(@RequestBody NationaliteDto dto) throws Exception {
        ResponseEntity<NationaliteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Nationalite t = service.findById(dto.getId());
            converter.copy(dto,t);
            Nationalite updated = service.update(t);
            NationaliteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of nationalite")
    @PostMapping("multiple")
    public ResponseEntity<List<NationaliteDto>> delete(@RequestBody List<NationaliteDto> dtos) throws Exception {
        ResponseEntity<List<NationaliteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Nationalite> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified nationalite")
    @DeleteMapping("")
    public ResponseEntity<NationaliteDto> delete(@RequestBody NationaliteDto dto) throws Exception {
		ResponseEntity<NationaliteDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Nationalite t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified nationalite")
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
    @Operation(summary = "Delete multiple nationalites by ids")
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



    @Operation(summary = "Finds a nationalite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<NationaliteDto> findWithAssociatedLists(@PathVariable Long id) {
        Nationalite loaded =  service.findWithAssociatedLists(id);
        NationaliteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds nationalites by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<NationaliteDto>> findByCriteria(@RequestBody NationaliteCriteria criteria) throws Exception {
        ResponseEntity<List<NationaliteDto>> res = null;
        List<Nationalite> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<NationaliteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated nationalites by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody NationaliteCriteria criteria) throws Exception {
        List<Nationalite> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<NationaliteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets nationalite data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody NationaliteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<NationaliteDto> findDtos(List<Nationalite> list){
        List<NationaliteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<NationaliteDto> getDtoResponseEntity(NationaliteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private NationaliteAdminService service;
    @Autowired private NationaliteConverter converter;





}
