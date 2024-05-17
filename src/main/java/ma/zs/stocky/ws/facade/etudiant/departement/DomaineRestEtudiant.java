package  ma.zs.stocky.ws.facade.etudiant.departement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.departement.Domaine;
import ma.zs.stocky.dao.criteria.core.departement.DomaineCriteria;
import ma.zs.stocky.service.facade.etudiant.departement.DomaineEtudiantService;
import ma.zs.stocky.ws.converter.departement.DomaineConverter;
import ma.zs.stocky.ws.dto.departement.DomaineDto;
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
@RequestMapping("/api/etudiant/domaine/")
public class DomaineRestEtudiant {




    @Operation(summary = "Finds a list of all domaines")
    @GetMapping("")
    public ResponseEntity<List<DomaineDto>> findAll() throws Exception {
        ResponseEntity<List<DomaineDto>> res = null;
        List<Domaine> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DomaineDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all domaines")
    @GetMapping("optimized")
    public ResponseEntity<List<DomaineDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DomaineDto>> res = null;
        List<Domaine> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DomaineDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a domaine by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DomaineDto> findById(@PathVariable Long id) {
        Domaine t = service.findById(id);
        if (t != null) {
            DomaineDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a domaine by code")
    @GetMapping("code/{code}")
    public ResponseEntity<DomaineDto> findByCode(@PathVariable String code) {
	    Domaine t = service.findByReferenceEntity(new Domaine(code));
        if (t != null) {
            DomaineDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  domaine")
    @PostMapping("")
    public ResponseEntity<DomaineDto> save(@RequestBody DomaineDto dto) throws Exception {
        if(dto!=null){
            Domaine myT = converter.toItem(dto);
            Domaine t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DomaineDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  domaine")
    @PutMapping("")
    public ResponseEntity<DomaineDto> update(@RequestBody DomaineDto dto) throws Exception {
        ResponseEntity<DomaineDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Domaine t = service.findById(dto.getId());
            converter.copy(dto,t);
            Domaine updated = service.update(t);
            DomaineDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of domaine")
    @PostMapping("multiple")
    public ResponseEntity<List<DomaineDto>> delete(@RequestBody List<DomaineDto> dtos) throws Exception {
        ResponseEntity<List<DomaineDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Domaine> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified domaine")
    @DeleteMapping("")
    public ResponseEntity<DomaineDto> delete(@RequestBody DomaineDto dto) throws Exception {
		ResponseEntity<DomaineDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Domaine t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified domaine")
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
    @Operation(summary = "Delete multiple domaines by ids")
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



    @Operation(summary = "Finds a domaine and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DomaineDto> findWithAssociatedLists(@PathVariable Long id) {
        Domaine loaded =  service.findWithAssociatedLists(id);
        DomaineDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds domaines by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DomaineDto>> findByCriteria(@RequestBody DomaineCriteria criteria) throws Exception {
        ResponseEntity<List<DomaineDto>> res = null;
        List<Domaine> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DomaineDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated domaines by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DomaineCriteria criteria) throws Exception {
        List<Domaine> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DomaineDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets domaine data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DomaineCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DomaineDto> findDtos(List<Domaine> list){
        List<DomaineDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DomaineDto> getDtoResponseEntity(DomaineDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DomaineEtudiantService service;
    @Autowired private DomaineConverter converter;





}
