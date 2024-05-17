package  ma.zs.stocky.ws.facade.etudiant.etudiant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.etudiant.Etudiant;
import ma.zs.stocky.dao.criteria.core.etudiant.EtudiantCriteria;
import ma.zs.stocky.service.facade.etudiant.etudiant.EtudiantEtudiantService;
import ma.zs.stocky.ws.converter.etudiant.EtudiantConverter;
import ma.zs.stocky.ws.dto.etudiant.EtudiantDto;
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
@RequestMapping("/api/etudiant/etudiant/")
public class EtudiantRestEtudiant {




    @Operation(summary = "Finds a list of all etudiants")
    @GetMapping("")
    public ResponseEntity<List<EtudiantDto>> findAll() throws Exception {
        ResponseEntity<List<EtudiantDto>> res = null;
        List<Etudiant> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<EtudiantDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a etudiant by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EtudiantDto> findById(@PathVariable Long id) {
        Etudiant t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EtudiantDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  etudiant")
    @PostMapping("")
    public ResponseEntity<EtudiantDto> save(@RequestBody EtudiantDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Etudiant myT = converter.toItem(dto);
            Etudiant t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EtudiantDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  etudiant")
    @PutMapping("")
    public ResponseEntity<EtudiantDto> update(@RequestBody EtudiantDto dto) throws Exception {
        ResponseEntity<EtudiantDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Etudiant t = service.findById(dto.getId());
            converter.copy(dto,t);
            Etudiant updated = service.update(t);
            EtudiantDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of etudiant")
    @PostMapping("multiple")
    public ResponseEntity<List<EtudiantDto>> delete(@RequestBody List<EtudiantDto> dtos) throws Exception {
        ResponseEntity<List<EtudiantDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Etudiant> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified etudiant")
    @DeleteMapping("")
    public ResponseEntity<EtudiantDto> delete(@RequestBody EtudiantDto dto) throws Exception {
		ResponseEntity<EtudiantDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Etudiant t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified etudiant")
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
    @Operation(summary = "Delete multiple etudiants by ids")
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


    @Operation(summary = "find by genre id")
    @GetMapping("genre/id/{id}")
    public List<EtudiantDto> findByGenreId(@PathVariable Long id){
        return findDtos(service.findByGenreId(id));
    }
    @Operation(summary = "delete by genre id")
    @DeleteMapping("genre/id/{id}")
    public int deleteByGenreId(@PathVariable Long id){
        return service.deleteByGenreId(id);
    }
    @Operation(summary = "find by nationalite id")
    @GetMapping("nationalite/id/{id}")
    public List<EtudiantDto> findByNationaliteId(@PathVariable Long id){
        return findDtos(service.findByNationaliteId(id));
    }
    @Operation(summary = "delete by nationalite id")
    @DeleteMapping("nationalite/id/{id}")
    public int deleteByNationaliteId(@PathVariable Long id){
        return service.deleteByNationaliteId(id);
    }
    @Operation(summary = "find by filiere id")
    @GetMapping("filiere/id/{id}")
    public List<EtudiantDto> findByFiliereId(@PathVariable Long id){
        return findDtos(service.findByFiliereId(id));
    }
    @Operation(summary = "delete by filiere id")
    @DeleteMapping("filiere/id/{id}")
    public int deleteByFiliereId(@PathVariable Long id){
        return service.deleteByFiliereId(id);
    }

    @Operation(summary = "Finds a etudiant and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EtudiantDto> findWithAssociatedLists(@PathVariable Long id) {
        Etudiant loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EtudiantDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds etudiants by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EtudiantDto>> findByCriteria(@RequestBody EtudiantCriteria criteria) throws Exception {
        ResponseEntity<List<EtudiantDto>> res = null;
        List<Etudiant> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EtudiantDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated etudiants by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EtudiantCriteria criteria) throws Exception {
        List<Etudiant> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<EtudiantDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets etudiant data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EtudiantCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EtudiantDto> findDtos(List<Etudiant> list){
        converter.initObject(true);
        List<EtudiantDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EtudiantDto> getDtoResponseEntity(EtudiantDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private EtudiantEtudiantService service;
    @Autowired private EtudiantConverter converter;





}
