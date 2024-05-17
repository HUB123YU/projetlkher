package  ma.zs.stocky.ws.facade.admin.departement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.departement.Departement;
import ma.zs.stocky.dao.criteria.core.departement.DepartementCriteria;
import ma.zs.stocky.service.facade.admin.departement.DepartementAdminService;
import ma.zs.stocky.ws.converter.departement.DepartementConverter;
import ma.zs.stocky.ws.dto.departement.DepartementDto;
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
@RequestMapping("/api/admin/departement/")
public class DepartementRestAdmin {




    @Operation(summary = "Finds a list of all departements")
    @GetMapping("")
    public ResponseEntity<List<DepartementDto>> findAll() throws Exception {
        ResponseEntity<List<DepartementDto>> res = null;
        List<Departement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DepartementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all departements")
    @GetMapping("optimized")
    public ResponseEntity<List<DepartementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DepartementDto>> res = null;
        List<Departement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DepartementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a departement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DepartementDto> findById(@PathVariable Long id) {
        Departement t = service.findById(id);
        if (t != null) {
            DepartementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a departement by code")
    @GetMapping("code/{code}")
    public ResponseEntity<DepartementDto> findByCode(@PathVariable String code) {
	    Departement t = service.findByReferenceEntity(new Departement(code));
        if (t != null) {
            DepartementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  departement")
    @PostMapping("")
    public ResponseEntity<DepartementDto> save(@RequestBody DepartementDto dto) throws Exception {
        if(dto!=null){
            Departement myT = converter.toItem(dto);
            Departement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DepartementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  departement")
    @PutMapping("")
    public ResponseEntity<DepartementDto> update(@RequestBody DepartementDto dto) throws Exception {
        ResponseEntity<DepartementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Departement t = service.findById(dto.getId());
            converter.copy(dto,t);
            Departement updated = service.update(t);
            DepartementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of departement")
    @PostMapping("multiple")
    public ResponseEntity<List<DepartementDto>> delete(@RequestBody List<DepartementDto> dtos) throws Exception {
        ResponseEntity<List<DepartementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Departement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified departement")
    @DeleteMapping("")
    public ResponseEntity<DepartementDto> delete(@RequestBody DepartementDto dto) throws Exception {
		ResponseEntity<DepartementDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Departement t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified departement")
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
    @Operation(summary = "Delete multiple departements by ids")
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



    @Operation(summary = "Finds a departement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DepartementDto> findWithAssociatedLists(@PathVariable Long id) {
        Departement loaded =  service.findWithAssociatedLists(id);
        DepartementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds departements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DepartementDto>> findByCriteria(@RequestBody DepartementCriteria criteria) throws Exception {
        ResponseEntity<List<DepartementDto>> res = null;
        List<Departement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DepartementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated departements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DepartementCriteria criteria) throws Exception {
        List<Departement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DepartementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets departement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DepartementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DepartementDto> findDtos(List<Departement> list){
        List<DepartementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DepartementDto> getDtoResponseEntity(DepartementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DepartementAdminService service;
    @Autowired private DepartementConverter converter;





}
