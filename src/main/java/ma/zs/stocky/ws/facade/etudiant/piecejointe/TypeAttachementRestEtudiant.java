package  ma.zs.stocky.ws.facade.etudiant.piecejointe;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.piecejointe.TypeAttachement;
import ma.zs.stocky.dao.criteria.core.piecejointe.TypeAttachementCriteria;
import ma.zs.stocky.service.facade.etudiant.piecejointe.TypeAttachementEtudiantService;
import ma.zs.stocky.ws.converter.piecejointe.TypeAttachementConverter;
import ma.zs.stocky.ws.dto.piecejointe.TypeAttachementDto;
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
@RequestMapping("/api/etudiant/typeAttachement/")
public class TypeAttachementRestEtudiant {




    @Operation(summary = "Finds a list of all typeAttachements")
    @GetMapping("")
    public ResponseEntity<List<TypeAttachementDto>> findAll() throws Exception {
        ResponseEntity<List<TypeAttachementDto>> res = null;
        List<TypeAttachement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeAttachementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeAttachements")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeAttachementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeAttachementDto>> res = null;
        List<TypeAttachement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeAttachementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeAttachement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeAttachementDto> findById(@PathVariable Long id) {
        TypeAttachement t = service.findById(id);
        if (t != null) {
            TypeAttachementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeAttachement by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeAttachementDto> findByLibelle(@PathVariable String libelle) {
	    TypeAttachement t = service.findByReferenceEntity(new TypeAttachement(libelle));
        if (t != null) {
            TypeAttachementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeAttachement")
    @PostMapping("")
    public ResponseEntity<TypeAttachementDto> save(@RequestBody TypeAttachementDto dto) throws Exception {
        if(dto!=null){
            TypeAttachement myT = converter.toItem(dto);
            TypeAttachement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeAttachementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeAttachement")
    @PutMapping("")
    public ResponseEntity<TypeAttachementDto> update(@RequestBody TypeAttachementDto dto) throws Exception {
        ResponseEntity<TypeAttachementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeAttachement t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeAttachement updated = service.update(t);
            TypeAttachementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeAttachement")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeAttachementDto>> delete(@RequestBody List<TypeAttachementDto> dtos) throws Exception {
        ResponseEntity<List<TypeAttachementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeAttachement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified typeAttachement")
    @DeleteMapping("")
    public ResponseEntity<TypeAttachementDto> delete(@RequestBody TypeAttachementDto dto) throws Exception {
		ResponseEntity<TypeAttachementDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            TypeAttachement t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeAttachement")
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
    @Operation(summary = "Delete multiple typeAttachements by ids")
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



    @Operation(summary = "Finds a typeAttachement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeAttachementDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeAttachement loaded =  service.findWithAssociatedLists(id);
        TypeAttachementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeAttachements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeAttachementDto>> findByCriteria(@RequestBody TypeAttachementCriteria criteria) throws Exception {
        ResponseEntity<List<TypeAttachementDto>> res = null;
        List<TypeAttachement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeAttachementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeAttachements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeAttachementCriteria criteria) throws Exception {
        List<TypeAttachement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeAttachementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeAttachement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeAttachementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeAttachementDto> findDtos(List<TypeAttachement> list){
        List<TypeAttachementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeAttachementDto> getDtoResponseEntity(TypeAttachementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TypeAttachementEtudiantService service;
    @Autowired private TypeAttachementConverter converter;





}
