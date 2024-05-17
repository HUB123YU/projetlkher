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

import ma.zs.stocky.bean.core.departement.SecteurActivite;
import ma.zs.stocky.dao.criteria.core.departement.SecteurActiviteCriteria;
import ma.zs.stocky.service.facade.etudiant.departement.SecteurActiviteEtudiantService;
import ma.zs.stocky.ws.converter.departement.SecteurActiviteConverter;
import ma.zs.stocky.ws.dto.departement.SecteurActiviteDto;
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
@RequestMapping("/api/etudiant/secteurActivite/")
public class SecteurActiviteRestEtudiant {




    @Operation(summary = "Finds a list of all secteurActivites")
    @GetMapping("")
    public ResponseEntity<List<SecteurActiviteDto>> findAll() throws Exception {
        ResponseEntity<List<SecteurActiviteDto>> res = null;
        List<SecteurActivite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SecteurActiviteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all secteurActivites")
    @GetMapping("optimized")
    public ResponseEntity<List<SecteurActiviteDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<SecteurActiviteDto>> res = null;
        List<SecteurActivite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SecteurActiviteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a secteurActivite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SecteurActiviteDto> findById(@PathVariable Long id) {
        SecteurActivite t = service.findById(id);
        if (t != null) {
            SecteurActiviteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a secteurActivite by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<SecteurActiviteDto> findByLibelle(@PathVariable String libelle) {
	    SecteurActivite t = service.findByReferenceEntity(new SecteurActivite(libelle));
        if (t != null) {
            SecteurActiviteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  secteurActivite")
    @PostMapping("")
    public ResponseEntity<SecteurActiviteDto> save(@RequestBody SecteurActiviteDto dto) throws Exception {
        if(dto!=null){
            SecteurActivite myT = converter.toItem(dto);
            SecteurActivite t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SecteurActiviteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  secteurActivite")
    @PutMapping("")
    public ResponseEntity<SecteurActiviteDto> update(@RequestBody SecteurActiviteDto dto) throws Exception {
        ResponseEntity<SecteurActiviteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            SecteurActivite t = service.findById(dto.getId());
            converter.copy(dto,t);
            SecteurActivite updated = service.update(t);
            SecteurActiviteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of secteurActivite")
    @PostMapping("multiple")
    public ResponseEntity<List<SecteurActiviteDto>> delete(@RequestBody List<SecteurActiviteDto> dtos) throws Exception {
        ResponseEntity<List<SecteurActiviteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<SecteurActivite> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified secteurActivite")
    @DeleteMapping("")
    public ResponseEntity<SecteurActiviteDto> delete(@RequestBody SecteurActiviteDto dto) throws Exception {
		ResponseEntity<SecteurActiviteDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            SecteurActivite t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified secteurActivite")
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
    @Operation(summary = "Delete multiple secteurActivites by ids")
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



    @Operation(summary = "Finds a secteurActivite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SecteurActiviteDto> findWithAssociatedLists(@PathVariable Long id) {
        SecteurActivite loaded =  service.findWithAssociatedLists(id);
        SecteurActiviteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds secteurActivites by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SecteurActiviteDto>> findByCriteria(@RequestBody SecteurActiviteCriteria criteria) throws Exception {
        ResponseEntity<List<SecteurActiviteDto>> res = null;
        List<SecteurActivite> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SecteurActiviteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated secteurActivites by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SecteurActiviteCriteria criteria) throws Exception {
        List<SecteurActivite> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<SecteurActiviteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets secteurActivite data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SecteurActiviteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SecteurActiviteDto> findDtos(List<SecteurActivite> list){
        List<SecteurActiviteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SecteurActiviteDto> getDtoResponseEntity(SecteurActiviteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private SecteurActiviteEtudiantService service;
    @Autowired private SecteurActiviteConverter converter;





}
