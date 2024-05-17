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

import ma.zs.stocky.bean.core.appartenance.Pays;
import ma.zs.stocky.dao.criteria.core.appartenance.PaysCriteria;
import ma.zs.stocky.service.facade.admin.appartenance.PaysAdminService;
import ma.zs.stocky.ws.converter.appartenance.PaysConverter;
import ma.zs.stocky.ws.dto.appartenance.PaysDto;
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
@RequestMapping("/api/admin/pays/")
public class PaysRestAdmin {




    @Operation(summary = "Finds a list of all payss")
    @GetMapping("")
    public ResponseEntity<List<PaysDto>> findAll() throws Exception {
        ResponseEntity<List<PaysDto>> res = null;
        List<Pays> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaysDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all payss")
    @GetMapping("optimized")
    public ResponseEntity<List<PaysDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PaysDto>> res = null;
        List<Pays> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaysDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a pays by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaysDto> findById(@PathVariable Long id) {
        Pays t = service.findById(id);
        if (t != null) {
            PaysDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a pays by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PaysDto> findByLibelle(@PathVariable String libelle) {
	    Pays t = service.findByReferenceEntity(new Pays(libelle));
        if (t != null) {
            PaysDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  pays")
    @PostMapping("")
    public ResponseEntity<PaysDto> save(@RequestBody PaysDto dto) throws Exception {
        if(dto!=null){
            Pays myT = converter.toItem(dto);
            Pays t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PaysDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  pays")
    @PutMapping("")
    public ResponseEntity<PaysDto> update(@RequestBody PaysDto dto) throws Exception {
        ResponseEntity<PaysDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Pays t = service.findById(dto.getId());
            converter.copy(dto,t);
            Pays updated = service.update(t);
            PaysDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of pays")
    @PostMapping("multiple")
    public ResponseEntity<List<PaysDto>> delete(@RequestBody List<PaysDto> dtos) throws Exception {
        ResponseEntity<List<PaysDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Pays> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified pays")
    @DeleteMapping("")
    public ResponseEntity<PaysDto> delete(@RequestBody PaysDto dto) throws Exception {
		ResponseEntity<PaysDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Pays t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified pays")
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
    @Operation(summary = "Delete multiple payss by ids")
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



    @Operation(summary = "Finds a pays and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaysDto> findWithAssociatedLists(@PathVariable Long id) {
        Pays loaded =  service.findWithAssociatedLists(id);
        PaysDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds payss by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaysDto>> findByCriteria(@RequestBody PaysCriteria criteria) throws Exception {
        ResponseEntity<List<PaysDto>> res = null;
        List<Pays> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaysDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated payss by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaysCriteria criteria) throws Exception {
        List<Pays> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PaysDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets pays data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaysCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PaysDto> findDtos(List<Pays> list){
        List<PaysDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaysDto> getDtoResponseEntity(PaysDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PaysAdminService service;
    @Autowired private PaysConverter converter;





}
