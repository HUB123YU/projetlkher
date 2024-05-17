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

import ma.zs.stocky.bean.core.departement.Filiere;
import ma.zs.stocky.dao.criteria.core.departement.FiliereCriteria;
import ma.zs.stocky.service.facade.etudiant.departement.FiliereEtudiantService;
import ma.zs.stocky.ws.converter.departement.FiliereConverter;
import ma.zs.stocky.ws.dto.departement.FiliereDto;
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
@RequestMapping("/api/etudiant/filiere/")
public class FiliereRestEtudiant {




    @Operation(summary = "Finds a list of all filieres")
    @GetMapping("")
    public ResponseEntity<List<FiliereDto>> findAll() throws Exception {
        ResponseEntity<List<FiliereDto>> res = null;
        List<Filiere> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<FiliereDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all filieres")
    @GetMapping("optimized")
    public ResponseEntity<List<FiliereDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<FiliereDto>> res = null;
        List<Filiere> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<FiliereDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a filiere by id")
    @GetMapping("id/{id}")
    public ResponseEntity<FiliereDto> findById(@PathVariable Long id) {
        Filiere t = service.findById(id);
        if (t != null) {
            converter.init(true);
            FiliereDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a filiere by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<FiliereDto> findByLibelle(@PathVariable String libelle) {
	    Filiere t = service.findByReferenceEntity(new Filiere(libelle));
        if (t != null) {
            converter.init(true);
            FiliereDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  filiere")
    @PostMapping("")
    public ResponseEntity<FiliereDto> save(@RequestBody FiliereDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Filiere myT = converter.toItem(dto);
            Filiere t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                FiliereDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  filiere")
    @PutMapping("")
    public ResponseEntity<FiliereDto> update(@RequestBody FiliereDto dto) throws Exception {
        ResponseEntity<FiliereDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Filiere t = service.findById(dto.getId());
            converter.copy(dto,t);
            Filiere updated = service.update(t);
            FiliereDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of filiere")
    @PostMapping("multiple")
    public ResponseEntity<List<FiliereDto>> delete(@RequestBody List<FiliereDto> dtos) throws Exception {
        ResponseEntity<List<FiliereDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Filiere> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified filiere")
    @DeleteMapping("")
    public ResponseEntity<FiliereDto> delete(@RequestBody FiliereDto dto) throws Exception {
		ResponseEntity<FiliereDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Filiere t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified filiere")
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
    @Operation(summary = "Delete multiple filieres by ids")
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


    @Operation(summary = "find by departement id")
    @GetMapping("departement/id/{id}")
    public List<FiliereDto> findByDepartementId(@PathVariable Long id){
        return findDtos(service.findByDepartementId(id));
    }
    @Operation(summary = "delete by departement id")
    @DeleteMapping("departement/id/{id}")
    public int deleteByDepartementId(@PathVariable Long id){
        return service.deleteByDepartementId(id);
    }

    @Operation(summary = "Finds a filiere and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<FiliereDto> findWithAssociatedLists(@PathVariable Long id) {
        Filiere loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        FiliereDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds filieres by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<FiliereDto>> findByCriteria(@RequestBody FiliereCriteria criteria) throws Exception {
        ResponseEntity<List<FiliereDto>> res = null;
        List<Filiere> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<FiliereDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated filieres by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody FiliereCriteria criteria) throws Exception {
        List<Filiere> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<FiliereDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets filiere data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody FiliereCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<FiliereDto> findDtos(List<Filiere> list){
        converter.initObject(true);
        List<FiliereDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<FiliereDto> getDtoResponseEntity(FiliereDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private FiliereEtudiantService service;
    @Autowired private FiliereConverter converter;





}
